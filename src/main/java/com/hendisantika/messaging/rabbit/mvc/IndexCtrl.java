package com.hendisantika.messaging.rabbit.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCtrl {
    @RequestMapping("/")
    public String sendMessage(final Model model, final HttpServletRequest request) {
        model.addAttribute("request", request);
        return "index";
    }
}
