package com.example.cipipeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AWS {
    @GetMapping("/aws")
    public String getData(){
        return "AWS";
    }
}
