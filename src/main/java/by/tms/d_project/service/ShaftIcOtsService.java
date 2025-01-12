package by.tms.d_project.service;

import by.tms.d_project.dto.FormIcOtsDto;
import by.tms.d_project.dto.ShaftIcOtsDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.entity.FormIcOts;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.repository.ShaftIcOtsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShaftIcOtsService {
    private final ShaftIcOtsRepository shaftIcOtsRepository;

    @Autowired
    public ShaftIcOtsService(ShaftIcOtsRepository shaftIcOtsRepository) {
        this.shaftIcOtsRepository = shaftIcOtsRepository;
    }

    @Transactional
    public ShaftIcOts create(ShaftIcOtsDto shaftIcOtsDto, Account creator) {
        ShaftIcOts shaftIcOts = new ShaftIcOts();
        shaftIcOts.setTitlePrinting(shaftIcOtsDto.getTitlePrinting());
        shaftIcOts.setTypeShaft(shaftIcOtsDto.getTypeShaft());
        shaftIcOts.setCreator(creator);
//        shaftIcOts.setFormsIcOts(shaftIcOtsDto.getFormsIcOtsDto());
        shaftIcOtsRepository.save(shaftIcOts);
        UUID ownerUUID = shaftIcOts.getId();
        for (FormIcOtsDto formIcOtsDto : shaftIcOtsDto.getFormsIcOtsDto()) {
            FormIcOts formIcOts = new FormIcOts();
            formIcOts.setId(ownerUUID);
            formIcOts.setTitle(formIcOtsDto.getTitle());
            formIcOts.setQuantityImprint(formIcOtsDto.getQuantityImprint());
            formIcOts.setWidth(formIcOtsDto.getWidth());
            formIcOts.setRightMargin(formIcOtsDto.getRightMargin());
            formIcOts.setIntervalLabels(formIcOtsDto.getIntervalLabels());

            int i = 0;


//            FormIcOts saved = FormIcOtsService.create(F);
        }
        return shaftIcOts;
    }

    // todo show, deleteById


}