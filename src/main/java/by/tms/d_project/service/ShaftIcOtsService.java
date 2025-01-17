package by.tms.d_project.service;

import by.tms.d_project.dto.FormDto;
import by.tms.d_project.dto.ShaftDto;
import by.tms.d_project.entity.*;
import by.tms.d_project.repository.ShaftIcOtsRepository;
import by.tms.d_project.repository.ShaftOtsRepository;
import by.tms.d_project.utils.Ots;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShaftIcOtsService {
    private final ShaftIcOtsRepository shaftIcOtsRepository;
    private final ShaftOtsRepository shaftOtsRepository;
    private final Ots ots;
    private static final Logger log = LoggerFactory.getLogger(ShaftIcOtsService.class);

    public ShaftIcOtsService(ShaftIcOtsRepository shaftIcOtsRepository,
                             ShaftOtsRepository shaftOtsRepository,
                             Ots ots) {
        this.shaftIcOtsRepository = shaftIcOtsRepository;
        this.shaftOtsRepository = shaftOtsRepository;
        this.ots = ots;
    }

    @Transactional
    public ShaftDto create(ShaftIcOts shaftIcOts, Account account) {
        shaftIcOts.setCreator(account);
        shaftIcOtsRepository.save(shaftIcOts);
        ShaftOts shaftOts = ots.makeOts(shaftIcOts);
        ShaftDto shaftDto = makeShaftDto(shaftOts);
        for (FormIcOts formIcOts : shaftIcOts.getFormsIcOts()) {
            formIcOts.setOwner(shaftIcOts);
        }
        shaftIcOtsRepository.save(shaftIcOts);
        for (FormOts formOts : shaftOts.getFormsOts()) {
            formOts.setOwner(shaftOts);
        }
        log.info("A one-time solution was obtained for \'{}\' by \'{}\'", shaftIcOts.getTitlePrinting(),
                shaftOts.getCreator().getUsername());
        shaftOtsRepository.save(shaftOts);
        return shaftDto;
    }

    private ShaftDto makeShaftDto(ShaftOts shaftOts) {
        ShaftDto shaftDto = new ShaftDto();
        shaftDto.setTitlePrinting(shaftOts.getTitlePrinting());
        shaftDto.setTypeShaft(shaftOts.getTypeShaft());
        shaftDto.setCreator(shaftOts.getCreator().getUsername());
        for (FormOts formOts : shaftOts.getFormsOts()) {
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