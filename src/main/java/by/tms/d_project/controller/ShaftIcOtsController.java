package by.tms.d_project.controller;

import by.tms.d_project.entity.Account;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.dto.ShaftIcOtsDto;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.service.ShaftIcOtsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shafticots")
public class ShaftIcOtsController {
    private final ShaftIcOtsService shaftIcOtsService;
    private final AccountService accountService;

    @Autowired
    public ShaftIcOtsController(ShaftIcOtsService shaftIcOtsService,
                                AccountService accountService) {
        this.shaftIcOtsService = shaftIcOtsService;
        this.accountService = accountService;
    }

    @Operation(summary = "setting the initial conditions for the shaft")
    @PostMapping
    public ResponseEntity<ShaftIcOts> create(@RequestBody ShaftIcOtsDto shaftIcOtsDto, Authentication authentication) {
        String username = authentication.getName();
        Account creator = accountService.checkAccount(username);
        ShaftIcOts saved = shaftIcOtsService.create(shaftIcOtsDto, creator);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}