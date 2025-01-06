package by.tms.d_project.controller;

import by.tms.d_project.entity.Creek;
import by.tms.d_project.service.CreekService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creek")
public class CreekController {
    private final CreekService creekService;

    public CreekController(CreekService creekService) {
        this.creekService = creekService;
    }

    @PostMapping
    public ResponseEntity<Creek> createCreek(@RequestBody Creek creek) {
        var saved = creekService.create(creek);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}