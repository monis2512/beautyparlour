package com.myparlour.app.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaConfig {
    @GetMapping({"/", "/admin"})
    public String spa() {
        return "forward:/index.html";
    }
}
