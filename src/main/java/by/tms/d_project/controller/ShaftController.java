package by.tms.d_project.controller;

import by.tms.d_project.entity.Shaft;
import by.tms.d_project.service.ShaftService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shaft")
public class ShaftController {
    private final ShaftService shaftService;

    @Autowired
    public ShaftController(ShaftService shaftService) {
        this.shaftService = shaftService;
    }

    @Operation(summary = "creating an shaft")
    @PostMapping
    public ResponseEntity<Shaft> create(@RequestBody Shaft shaft) {
        Shaft saved = shaftService.create(shaft);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}