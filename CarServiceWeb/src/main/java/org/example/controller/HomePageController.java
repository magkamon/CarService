package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("home");
        return "home";
    }

}
