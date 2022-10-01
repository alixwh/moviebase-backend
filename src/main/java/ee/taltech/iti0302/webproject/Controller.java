package ee.taltech.iti0302.webproject;

import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {
    @GetMapping("api/test")
    public String test() {
        return "test";
    }
}
