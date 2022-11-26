package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.ActorDto;
import ee.taltech.iti0302.webproject.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/public")
@RestController
public class ActorController {

    private final ActorService actorService;

    @GetMapping("actors/{id}")
    public ActorDto getActorById(@PathVariable("id") int actorId) {
        return actorService.findById(actorId);
    }
}
