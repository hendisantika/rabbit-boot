package daggerok.messaging.rabbit.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorIndexRedirectHandler implements ErrorController {
    @Value("${server.error.path:${error.path:/error}}")
    String path;

    @Override
    public String getErrorPath() {
        return path;
    }

    @RequestMapping(produces = "text/html")
    public String errorHtml(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "index";
    }
}
