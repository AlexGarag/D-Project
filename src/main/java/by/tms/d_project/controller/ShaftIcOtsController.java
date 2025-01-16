package by.tms.d_project.controller;

//import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.entity.ShaftOts;
import by.tms.d_project.service.ShaftIcOtsService;
import by.tms.d_project.utils.Ots;
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
    private final Ots ots;
    private static final Logger log = LoggerFactory.getLogger(ShaftIcOtsController.class);

    @Autowired
    public ShaftIcOtsController(ShaftIcOtsService shaftIcOtsService,
                                Ots ots) {
        this.shaftIcOtsService = shaftIcOtsService;
        this.ots = ots;
    }

    @Operation(summary = "creating an ShaftIcOts")
    @PostMapping()
    public ResponseEntity<ShaftOts> create(@RequestBody ShaftIcOts shaftIcOts) {
        log.info("Creating an shaftIcOts for \'{}\'", shaftIcOts.getTitlePrinting());
        //        AccountDto accountDto = new AccountDto();
//        accountDto.setUsername(account.getUsername());
//        accountDto.setCreatedAt(account.getCreatedAt());
        ShaftIcOts saved = shaftIcOtsService.create(shaftIcOts);
        ShaftOts shaftOts = ots.makeOts(saved);
        return new ResponseEntity<>(shaftOts, HttpStatus.CREATED);
    }
}