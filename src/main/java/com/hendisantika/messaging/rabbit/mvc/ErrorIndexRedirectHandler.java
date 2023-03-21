package com.hendisantika.messaging.rabbit.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorIndexRedirectHandler implements ErrorController {
    @Value("${server.error.path:${error.path:/error}}")
    String path;

    public String getErrorPath() {
        return path;
    }

    @RequestMapping(produces = "text/html")
    public String errorHtml(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "index";
    }
}
