package ee.taltech.iti0302.webproject.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {
    @GetMapping("api/test")
    public String test() {
        return "test";
    }
}
