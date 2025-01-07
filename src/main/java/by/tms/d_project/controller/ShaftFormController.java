package by.tms.d_project.controller;

import by.tms.d_project.entity.ShaftForm;
import by.tms.d_project.service.ShaftFormService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shaftform")
public class ShaftFormController {
    private final ShaftFormService shaftFormService;

    @Autowired
    public ShaftFormController(ShaftFormService shaftFormService) {
        this.shaftFormService = shaftFormService;
    }

    @Operation(summary = "creating a connection between shaft and form")
    @PostMapping
    public ResponseEntity<ShaftForm> create(@RequestBody ShaftForm shaftForm) {
        var saved = shaftFormService.create(shaftForm);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}