package com.slusarz.pokerafterdark.spring.delivery.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

    @GetMapping(value = {"/pokerafterdark/pokerstats/*", "/pokerafterdark", "/pokerafterdark/pokerstats/", "/"})
    public String init() {
        return "index.html";
    }
}