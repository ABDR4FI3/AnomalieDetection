package org.example.demo_location.Controllers;

import org.example.demo_location.Entity.Position;
import org.example.demo_location.Entity.PositionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/position")
    public Double demo(@RequestBody PositionDto position) {
        String message = "Position received: {}"+ position.getLatitude() + " "+ position.getLongitude();
        logger.warn(message);
        Random random = new Random();
        double dangerPercentage = random.nextInt(101); // Random number between 0 and 100
        logger.warn("Returning danger percentage: {}", dangerPercentage);
        return dangerPercentage;
    }
}
