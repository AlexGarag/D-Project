package by.tms.d_project.service;

import by.tms.d_project.dao.OtsDao;
import by.tms.d_project.dto.FormDto;
import by.tms.d_project.dto.OtsShortDto;
import by.tms.d_project.entity.*;
import by.tms.d_project.repository.IcOtsRepository;
import by.tms.d_project.repository.OtsRepository;
import by.tms.d_project.utils.SolverOts;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OtsService {
    private final IcOtsRepository icOtsRepository;
    private final OtsRepository otsRepository;
    private final OtsDao otsDao;
    private final SolverOts solverOts;
    private static final Logger log = LoggerFactory.getLogger(OtsService.class);

    public OtsService(IcOtsRepository icOtsRepository,
                      OtsRepository otsRepository,
                      OtsDao otsDao,
                      SolverOts solverOts) {
        this.icOtsRepository = icOtsRepository;
        this.otsRepository = otsRepository;
        this.otsDao = otsDao;
        this.solverOts = solverOts;
    }

    @Transactional
    public OtsShortDto create(IcOts icOts, Account account) {
        icOts.setAuthor(account);
        log.info("Creating an Ots for \'{}\' by \'{}\'", icOts.getTitlePrinting(), account.getUsername());
        icOtsRepository.save(icOts);
        Ots ots = solverOts.makeOts(icOts);
        OtsShortDto otsShortDto = makeOtsDto(ots);
        for (FormIcOts formIcOts : icOts.getFormsIcOts()) {
            formIcOts.setOwner(icOts);
        }
        icOtsRepository.save(icOts);
        for (FormOts formOts : ots.getFormsOts()) {
            formOts.setOwner(ots);
        }
        log.info("A one-time solution was obtained for \'{}\' by \'{}\'", icOts.getTitlePrinting(),
                ots.getAuthor().getUsername());
        otsRepository.save(ots);
        return otsShortDto;
    }

    // работает
    public Optional<OtsShortDto> get(String titlePrinting, String usernameActor) {
        Optional<Ots> otsOptional = otsRepository.findByTitlePrinting(titlePrinting);
        Optional<OtsShortDto> otsShortDtoOptional = Optional.empty();
        if (otsOptional.isPresent()) {
            Ots ots = otsOptional.get();
            OtsShortDto otsShortDto = makeOtsDto(otsOptional.get());
            otsShortDtoOptional = Optional.of(otsShortDto);
            log.info("Ots \'{}\' was given to the actor \'{}\'", ots.getTitlePrinting(), usernameActor);
        }
        return otsShortDtoOptional;
    }

    public void delete(String titlePrinting, String actorUsername) {
        Optional<Ots> otsOptional = otsRepository.findByTitlePrinting(titlePrinting);
        otsOptional.ifPresent(ots -> otsDao.delete(ots.getId()));
        log.info("Deleting an Ots \'{}\' by \'{}\'", titlePrinting, actorUsername);
    }

    // todo продолжить!
    public OtsShortDto find(String titlePrinting, String usernameActor) {
        OtsShortDto otsShortDto = new OtsShortDto();


        return otsShortDto;
    }

    private OtsShortDto makeOtsDto(Ots ots) {
        OtsShortDto otsShortDto = new OtsShortDto();
        otsShortDto.setTitlePrinting(ots.getTitlePrinting());
        otsShortDto.setShaftType(ots.getShaftType());
        otsShortDto.setAuthor(ots.getAuthor().getUsername());
        for (FormOts formOts : ots.getFormsOts()) {
            FormDto formDto = makeFormDto(formOts);
            otsShortDto.getFormsDto().add(formDto);
        }
        return otsShortDto;
    }

    private FormDto makeFormDto(FormOts formOts) {
        FormDto formDto = new FormDto();
        formDto.setNumberOnShaft(formOts.getNumberOnShaft());
        formDto.setTitleForm(formOts.getTitleForm());
        formDto.setIndentationOnShaft(formOts.getIndentationOnShaft());
        formDto.setIntervalLabels(formOts.getIntervalLabels());
        formDto.setToothOnShaft(formOts.getToothOnShaft());
        return formDto;
    }
}