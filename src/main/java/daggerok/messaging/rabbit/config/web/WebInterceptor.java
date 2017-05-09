package daggerok.messaging.rabbit.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.servlet.ServletContext;
import java.util.Optional;

@Slf4j
@Component
public class WebInterceptor implements WebRequestInterceptor {

    @Autowired ObjectMapper objectMapper;
    @Autowired ServletContext servletContext;

    @Override
    public void preHandle(WebRequest request) throws Exception {}

    @Override
    @SneakyThrows
    public void postHandle(final WebRequest request, final ModelMap model) {

        Optional.ofNullable(model).ifPresent(m -> {

            try {

                m.addAttribute("ctx", servletContext.getContextPath());
                m.addAttribute("parameters", objectMapper.writeValueAsString(request.getParameterMap()));

            } catch (Throwable e) {

                log.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {}
}
