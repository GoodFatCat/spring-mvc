package com.github.goodfatcat.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/first")
public class Calculator {
    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "a") int a,
                            @RequestParam(value = "b") int b,
                            @RequestParam(value = "action") String action,
                            Model model) {

        Set<String> actions = new HashSet<>();
        Collections.addAll(actions, "multiplication", "addition", "subtraction", "division");

        Optional<Double> res = Optional.empty();
        if (actions.contains(action)) {
            switch (action) {
                case ("multiplication") -> res = Optional.of((double) a * b);
                case ("addition") -> res = Optional.of((double) a + b);
                case ("subtraction") -> res = Optional.of((double) a - b);
                case ("division") -> res = Optional.of((double) a / b);
            }
        }

        res.ifPresent(x -> model.addAttribute("result", x));

        return "/first/calculator";
    }
}
