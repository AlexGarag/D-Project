package by.tms.d_project.controller;

import by.tms.d_project.dto.OtsShortDto;
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

import static by.tms.d_project.constant_reference_etc.HttpCode.FORBIDDEN_CODE;
import static by.tms.d_project.constant_reference_etc.HttpCode.NOT_FOUND_CODE;
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
            return responseGenerator.error(bindingResult);
        }
        String usernameActor = authentication.getName();
        Account account = accountService.checkAccount(usernameActor);
        OtsShortDto otsShortDto = otsService.create(icOts, account);
        return ResponseEntity.status(HttpStatus.CREATED).body(otsShortDto);
    }

    @Operation(summary = "getting an Ots")
    @GetMapping("/{titlePrinting}")
    public ResponseEntity<?> get(@PathVariable("titlePrinting") String titlePrinting, Authentication authentication) {
        String usernameActor = authentication.getName();
        Optional<OtsShortDto> otsShortDtoOptional = otsService.get(titlePrinting, usernameActor);
        if (otsShortDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(otsShortDtoOptional.get());
        } else {
            return responseGenerator.replay(NOT_FOUND_CODE);
        }
    }

    @Operation(summary = "deleting an Ots with a titlePrinting")
    @DeleteMapping("/{titlePrinting}")
    public ResponseEntity<?> deleteByUsername(@PathVariable("titlePrinting") String titlePrinting, Authentication authentication) {
        String usernameActor = authentication.getName();
        Optional<OtsShortDto> otsShortDtoOptional = otsService.get(titlePrinting, usernameActor);
        String author = null;
        if (otsShortDtoOptional.isPresent()) {
            author = otsShortDtoOptional.get().getAuthor();
            boolean isRights = checkerRights.checkRights(author, authentication);
            if (isRights) {
                otsService.delete(titlePrinting, authentication.getName());
                return ResponseEntity.status(HttpStatus.OK).body(DELETED_MESSAGE);
            } else {
            log.info("There was an attempt to delete the account \'{}\' by \'{}\'", usernameActor, authentication.getName());
                return responseGenerator.replay(FORBIDDEN_CODE);
            }
        } else {
            return responseGenerator.replay(NOT_FOUND_CODE);
        }
    }
}