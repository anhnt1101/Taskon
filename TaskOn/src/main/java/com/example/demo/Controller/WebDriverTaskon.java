package com.example.demo.Controller;

import com.example.demo.service.WebDriverTasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/taskon/")
public class WebDriverTaskon {

    @Autowired
    WebDriverTasonService webDriverTasonService;

    @GetMapping("trang-chu")
    public String index() {
        return "index";
    }

    @PostMapping("check-time")
    public String goLink(
            @RequestParam String link
    ) {

        webDriverTasonService.runSeleniumScript(link);
        return "redirect:/taskon/trang-chu";

    }

}

