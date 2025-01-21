package by.tms.d_project.controller;

import by.tms.d_project.dto.ShaftDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.service.OtsService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ots")
public class OtsController {
    private final OtsService otsService;
    private final AccountService accountService;
    private static final Logger log = LoggerFactory.getLogger(OtsController.class);

    @Autowired
    public OtsController(OtsService otsService,
                         AccountService accountService) {
        this.otsService = otsService;
        this.accountService = accountService;
    }

    @Operation(summary = "creating an Ots")
    @PostMapping()
    public ResponseEntity<ShaftDto> create(@RequestBody ShaftIcOts shaftIcOts, Authentication authentication) {
        String username = authentication.getName();
        log.info("Creating an Ots for \'{}\' by \'{}\'", shaftIcOts.getTitlePrinting(), username);
        Account account = accountService.checkAccount(username);
        ShaftDto shaftDto = otsService.create(shaftIcOts, account);
        return new ResponseEntity<>(shaftDto, HttpStatus.CREATED);
    }
}