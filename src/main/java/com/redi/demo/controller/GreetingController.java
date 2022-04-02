package com.redi.demo.controller;

import com.redi.demo.domain.Greeting;
import com.redi.demo.domain.GreetingStatistic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

  private final AtomicLong counter = new AtomicLong();
  //create
  private final Map<String, Long> statistics = new HashMap<>();

  @GetMapping("/greeting")
  public Greeting greet(@RequestParam(value = "name", defaultValue = "World") String name) {
    String content = "Hello " + name + "!";
    return new Greeting(counter.incrementAndGet(), content);
  }

  // save stats for the name then increment counter
  public List<GreetingStatistic> getStatistics() {
    List<GreetingStatistic> greetingStatistics = new ArrayList<>();
    for (Map.Entry<String, Long> entry : statistics.entrySet()) {
      GreetingStatistic greetingStatistic = new GreetingStatistic(entry.getKey(), entry.getValue());
      greetingStatistics.add(greetingStatistic);
    }
    return greetingStatistics;
  }

  // endpoint calls the method of statistics
  @GetMapping("statistics")
  public List<GreetingStatistic> statistics() {
    return getStatistics();
  }
}
