package by.tms.d_project.controller;

import by.tms.d_project.dto.OtsShortDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.entity.IcOts;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.service.OtsService;
import by.tms.d_project.utils.CheckerRights;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.tms.d_project.constant_and_reference.Message.*;

@RestController
@RequestMapping("/ots")
public class OtsController {
    private final OtsService otsService;
    private final AccountService accountService;
    private final CheckerRights checkerRights;

    @Autowired
    public OtsController(OtsService otsService,
                         AccountService accountService,
                         CheckerRights checkerRights) {
        this.otsService = otsService;
        this.accountService = accountService;
        this.checkerRights = checkerRights;
    }

    @Operation(summary = "creating an Ots")
    @PostMapping()
    public ResponseEntity<OtsShortDto> create(@RequestBody IcOts icOts, Authentication authentication) {
        String usernameActor = authentication.getName();
        Account account = accountService.checkAccount(usernameActor); // todo check OR get?
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
    }

    @Operation(summary = "getting an Ots")
    @GetMapping("/find/{titlePrinting}")
    public ResponseEntity<?> find(@PathVariable("titlePrinting") String titlePrinting, Authentication authentication) {
        String usernameActor = authentication.getName();
        OtsShortDto otsShortDto = otsService.find(titlePrinting, usernameActor);
//        if (otsShortDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(otsShortDto);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
//        }
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
//            log.info("There was an attempt to delete the account \'{}\' by \'{}\'", username, authentication.getName());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(NO_RIGHTS_MESSAGE);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
    }
}