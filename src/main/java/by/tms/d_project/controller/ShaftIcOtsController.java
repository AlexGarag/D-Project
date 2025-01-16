package by.tms.d_project.controller;

//import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.service.ShaftIcOtsService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shafticots")
public class ShaftIcOtsController {
    private final ShaftIcOtsService shaftIcOtsService;
    private static final Logger log = LoggerFactory.getLogger(ShaftIcOtsController.class);

    @Autowired
    public ShaftIcOtsController(ShaftIcOtsService shaftIcOtsService) { this.shaftIcOtsService = shaftIcOtsService; }

    @Operation(summary = "creating an ShaftIcOts")
    @PostMapping()
    public ResponseEntity<ShaftIcOts> create(@RequestBody ShaftIcOts shaftIcOts) {
        log.info("Creating an shaftIcOts for \'{}\'", shaftIcOts.getTitlePrinting());
        ShaftIcOts saved = shaftIcOtsService.create(shaftIcOts);
//        AccountDto accountDto = new AccountDto();
//        accountDto.setUsername(account.getUsername());
//        accountDto.setCreatedAt(account.getCreatedAt());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}