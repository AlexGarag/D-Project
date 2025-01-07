package by.tms.d_project.controller;

import by.tms.d_project.entity.Printing;
import by.tms.d_project.service.PrintingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/printing")
public class PrintingController {
    private final PrintingService printingService;

    @Autowired
    public PrintingController(PrintingService printingService) {
        this.printingService = printingService;
    }

    @Operation(summary = "creating an account")
    @PostMapping
    public ResponseEntity<Printing> create(@RequestBody Printing printing) {
        var saved = printingService.create(printing);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}