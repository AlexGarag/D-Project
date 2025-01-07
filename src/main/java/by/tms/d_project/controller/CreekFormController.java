package by.tms.d_project.controller;

import by.tms.d_project.entity.CreekForm;
import by.tms.d_project.service.CreekFormService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creekform")
public class CreekFormController {
    private final CreekFormService creekFormService;

    @Autowired
    public CreekFormController(CreekFormService creekFormService) {
        this.creekFormService = creekFormService;
    }

    @Operation(summary = "creating a connection between creek and form")
    @PostMapping
    public ResponseEntity<CreekForm> create(@RequestBody CreekForm creekForm) {
        var saved = creekFormService.create(creekForm);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}