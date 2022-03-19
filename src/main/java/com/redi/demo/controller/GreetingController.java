package com.redi.demo.controller;

import com.redi.demo.domain.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        String content = "Hello " + name + "!";
        // save stats for the name then increment counter
        return new Greeting(counter.incrementAndGet(), content);
    }

    // call method of stats
}
