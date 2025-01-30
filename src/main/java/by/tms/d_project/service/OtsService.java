package by.tms.d_project.service;

import by.tms.d_project.dao.IcOtsDao;
import by.tms.d_project.dao.OtsDao;
import by.tms.d_project.dto.FormDto;
import by.tms.d_project.dto.OtsDto;
import by.tms.d_project.entity.*;
import by.tms.d_project.mapper.FormMapper;
import by.tms.d_project.mapper.OtsMapper;
import by.tms.d_project.repository.IcOtsRepository;
import by.tms.d_project.repository.OtsRepository;
import by.tms.d_project.utils.SolverOts;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OtsService {
    private final IcOtsRepository icOtsRepository;
    private final OtsRepository otsRepository;
    private final FormMapper formMapper;
    private final OtsMapper otsMapper;
    private final OtsDao otsDao;
    private final IcOtsDao icOtsDao;
    private final SolverOts solverOts;
    private static final Logger log = LoggerFactory.getLogger(OtsService.class);

    public OtsService(IcOtsRepository icOtsRepository,
                      OtsRepository otsRepository,
                      OtsMapper otsMapper,
                      FormMapper formMapper,
                      OtsDao otsDao,
                      IcOtsDao icOtsDao,
                      SolverOts solverOts) {
        this.icOtsRepository = icOtsRepository;
        this.otsRepository = otsRepository;
        this.otsMapper = otsMapper;
        this.formMapper = formMapper;
        this.otsDao = otsDao;
        this.icOtsDao = icOtsDao;
        this.solverOts = solverOts;
    }

    @Transactional
    public Optional<OtsDto> create(IcOts icOts, Account account) {
        icOts.setAuthor(account);
        icOtsRepository.save(icOts);
        log.info("Creating an Ots for \'{}\' by \'{}\'", icOts.getTitlePrinting(), account.getUsername());
        Ots ots = solverOts.makeOts(icOts);
        for (FormIcOts formIcOts : icOts.getFormsIcOts()) {
            formIcOts.setOwner(icOts);
        }
        icOtsRepository.save(icOts);
        for (FormOts formOts : ots.getFormsOts()) {
            formOts.setOwner(ots);
        }
        otsRepository.save(ots);
        log.info("A one-time solution was obtained for \'{}\' by \'{}\'", icOts.getTitlePrinting(),
                ots.getAuthor().getUsername());
        OtsDto otsDto = otsMapper.toDto(ots);
        List<FormDto> formsDto = formMapper.toFormDtoList(ots.getFormsOts());
//        for (FormOts formOts : ots.getFormsOts()) {
//            formsDto.add(formMapper.toFormDto(formOts));
//        }
        otsDto.setFormsDto(formsDto);
        return Optional.of(otsDto);
    }

    public Optional<OtsDto> get(String titlePrinting, String usernameActor) {
        Optional<Ots> otsOptional = otsRepository.findByTitlePrinting(titlePrinting);
        Optional<OtsDto> otsShortDtoOptional = Optional.empty();
        if (otsOptional.isPresent()) {
            Ots ots = otsOptional.get();
            OtsDto otsDto = otsMapper.toDto(ots);
            otsShortDtoOptional = Optional.of(otsDto);
            log.info("Ots \'{}\' was given to the actor \'{}\'", ots.getTitlePrinting(), usernameActor);
        }
        return otsShortDtoOptional;
    }

    public void delete(String titlePrinting, String actorUsername) {
        Optional<Ots> otsOptional = otsRepository.findByTitlePrinting(titlePrinting); // удаляю Разовое Решение
        otsOptional.ifPresent(ots -> otsDao.delete(ots.getId()));
        Optional<IcOts> icOtsOptional = icOtsRepository.findByTitlePrinting(titlePrinting); // удаляю НУ к Разовому Решению
        icOtsOptional.ifPresent(icOts -> icOtsDao.delete(icOts.getId()));
        log.info("Deleting an Ots \'{}\' by \'{}\'", titlePrinting, actorUsername);
    }
}