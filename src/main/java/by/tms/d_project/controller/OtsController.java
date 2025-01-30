package by.tms.d_project.controller;

import by.tms.d_project.dto.AccountShortDto;
import by.tms.d_project.dto.OtsDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.entity.IcOts;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.service.OtsService;
import by.tms.d_project.utils.CheckerRights;
import by.tms.d_project.utils.ResponseGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.tms.d_project.constant_reference_etc.HttpCode.*;
import static by.tms.d_project.constant_reference_etc.Message.*;

@RestController
@RequestMapping("/ots")
@Tag(name = "Ots resource")
public class OtsController {
    private final OtsService otsService;
    private final AccountService accountService;
    private final CheckerRights checkerRights;
    private final ResponseGenerator responseGenerator;
    private static final Logger log = LoggerFactory.getLogger(OtsService.class);

    @Autowired
    public OtsController(OtsService otsService,
                         AccountService accountService,
                         CheckerRights checkerRights,
                         ResponseGenerator responseGenerator) {
        this.otsService = otsService;
        this.accountService = accountService;
        this.checkerRights = checkerRights;
        this.responseGenerator = responseGenerator;
    }

    @ApiResponse(responseCode = "200", description = "returns DTO Ots")
    @Operation(summary = "creating an Ots", description = "initial conditions (IC) are set for obtaining a one-time solution (Ots)")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid IcOts icOts, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return responseGenerator.errorReplay(bindingResult);
        }
        String usernameActor = authentication.getName();
        Account account = accountService.checkAccount(usernameActor);
        Optional<OtsDto> otsDtoOptional = otsService.create(icOts, account);
        if (otsDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(otsDtoOptional.get());
        } else {
            return responseGenerator.errorReplay(INTERNAL_SERVER_ERROR_CODE);
        }
    }

    @Operation(summary = "getting an Ots")
    @GetMapping("/{titlePrinting}")
    public ResponseEntity<?> get(@PathVariable("titlePrinting") String titlePrinting, Authentication authentication) {
        String usernameActor = authentication.getName();
        Optional<OtsDto> otsShortDtoOptional = otsService.get(titlePrinting, usernameActor);
        if (otsShortDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(otsShortDtoOptional.get());
        } else {
            return responseGenerator.errorReplay(NOT_FOUND_CODE);
        }
    }

    @Operation(summary = "deleting an Ots with a titlePrinting")
    @DeleteMapping("/{titlePrinting}")
    public ResponseEntity<?> deleteByUsername(@PathVariable("titlePrinting") String titlePrinting, Authentication authentication) {
        String usernameActor = authentication.getName();
        Optional<OtsDto> otsDtoOptional = otsService.get(titlePrinting, usernameActor);
        if (otsDtoOptional.isPresent()) {
            AccountShortDto author = otsDtoOptional.get().getAuthor();
            boolean isRights = checkerRights.checkRights(author.username(), authentication);
            if (isRights) {
                otsService.delete(titlePrinting, authentication.getName());
                return ResponseEntity.status(HttpStatus.OK).body(DELETED_MESSAGE);
            } else {
            log.info("There was an attempt to delete the account \'{}\' by \'{}\'", usernameActor, authentication.getName());
                return responseGenerator.errorReplay(FORBIDDEN_CODE);
            }
        } else {
            return responseGenerator.errorReplay(NOT_FOUND_CODE);
        }
    }
}