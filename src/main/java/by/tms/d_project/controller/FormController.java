package by.tms.d_project.controller;

import by.tms.d_project.entity.Form;
import by.tms.d_project.service.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    public ResponseEntity<Form> create(@RequestBody Form form) {
        var saved = formService.create(form);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}