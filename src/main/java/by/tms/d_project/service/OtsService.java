package by.tms.d_project.service;

import by.tms.d_project.dto.FormDto;
import by.tms.d_project.dto.ShaftDto;
import by.tms.d_project.entity.*;
import by.tms.d_project.repository.IcOtsRepository;
import by.tms.d_project.repository.OtsRepository;
import by.tms.d_project.utils.SolverOts;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OtsService {
    private final IcOtsRepository icOtsRepository;
    private final OtsRepository otsRepository;
    private final SolverOts solverOts;
    private static final Logger log = LoggerFactory.getLogger(OtsService.class);

    public OtsService(IcOtsRepository icOtsRepository,
                      OtsRepository otsRepository,
                      SolverOts solverOts) {
        this.icOtsRepository = icOtsRepository;
        this.otsRepository = otsRepository;
        this.solverOts = solverOts;
    }

    @Transactional
    public ShaftDto create(IcOts icOts, Account account) {
        icOts.setAuthor(account);
        icOtsRepository.save(icOts);
        Ots ots = solverOts.makeOts(icOts);
        ShaftDto shaftDto = makeShaftDto(ots);
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
        return shaftDto;
    }

    private ShaftDto makeShaftDto(Ots ots) {
        ShaftDto shaftDto = new ShaftDto();
        shaftDto.setTitlePrinting(ots.getTitlePrinting());
        shaftDto.setTypeShaft(ots.getShaftType());
        shaftDto.setAuthor(ots.getAuthor().getUsername());
        for (FormOts formOts : ots.getFormsOts()) {
            FormDto formDto = makeFormDto(formOts);
            shaftDto.getFormsDto().add(formDto);
        }
        return shaftDto;
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