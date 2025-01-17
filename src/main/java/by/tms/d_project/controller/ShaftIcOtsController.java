package by.tms.d_project.controller;

import by.tms.d_project.dto.ShaftDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.entity.ShaftOts;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.service.ShaftIcOtsService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shafticots")
public class ShaftIcOtsController {
    private final ShaftIcOtsService shaftIcOtsService;
    private final AccountService accountService;
    private static final Logger log = LoggerFactory.getLogger(ShaftIcOtsController.class);

    @Autowired
    public ShaftIcOtsController(ShaftIcOtsService shaftIcOtsService,
                                AccountService accountService) {
        this.shaftIcOtsService = shaftIcOtsService;
        this.accountService = accountService;
    }

    @Operation(summary = "creating an ShaftIcOts")
    @PostMapping()
    public ResponseEntity<ShaftDto> create(@RequestBody ShaftIcOts shaftIcOts, Authentication authentication) {
//    public ResponseEntity<ShaftOts> create(@RequestBody ShaftIcOts shaftIcOts, Authentication authentication) {
        String username = authentication.getName();
        log.info("Creating an shaftIcOts for \'{}\' by \'{}\'", shaftIcOts.getTitlePrinting(), username);
        Account account = accountService.checkAccount(username);
//        ShaftDto shaftOts = shaftIcOtsService.create(shaftIcOts, account);
        ShaftDto shaftDto = shaftIcOtsService.create(shaftIcOts, account);
//        return new ResponseEntity<>(shaftOts, HttpStatus.CREATED);
        return new ResponseEntity<>(shaftDto, HttpStatus.CREATED);
    }
}