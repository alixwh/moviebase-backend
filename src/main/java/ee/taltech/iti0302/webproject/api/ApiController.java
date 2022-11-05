package ee.taltech.iti0302.webproject.api;

import ee.taltech.iti0302.webproject.api.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class ApiController {
    private final ApiService apiService;

    @PostMapping("save/genres")
    public void saveGenres() {
        apiService.saveGenres();
    }

    @PostMapping("save/movies/{limit}")
    public void saveMovies(@PathVariable("limit") Integer limit) {
        apiService.saveMovies(limit);
    }
}
