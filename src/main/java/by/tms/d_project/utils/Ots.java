package by.tms.d_project.utils;

import by.tms.d_project.entity.FormIcOts;
import by.tms.d_project.entity.FormOts;
import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.entity.ShaftOts;
import org.springframework.stereotype.Component;

/**
 * Класс создаёт "Разовое решение" на вал по параметрам, заданным в ShaftIcOts
 * Ots - one-time solution
 */
@Component
public class Ots {
    private final int lengthShaft = 1270;  // todo сделать глобальной
    private int widthShapesShaft = 0;
    private int margin = 0;

    public ShaftOts makeOts(ShaftIcOts shaftIcOts) {    // todo добавить верификации заданных параметров
        ShaftOts shaftOts = new ShaftOts();
        shaftOts.setTitlePrinting(shaftIcOts.getTitlePrinting());
        shaftOts.setTypeShaft(shaftIcOts.getTypeShaft());
        shaftOts.setCreator(shaftIcOts.getCreator());
        for (FormIcOts formIcOts : shaftIcOts.getFormsIcOts()) {
            widthShapesShaft = widthShapesShaft + formIcOts.getWidth();
        }
        margin = (lengthShaft - widthShapesShaft) / 2;
        for (int numberOnShaft = shaftIcOts.getFormsIcOts().size() - 1; numberOnShaft > -1; numberOnShaft --) {
            FormOts formOts = makeOtsForm(shaftIcOts.getFormsIcOts().get(numberOnShaft), numberOnShaft + 1);
            shaftOts.getFormsOts().add(formOts);
        }
        return shaftOts;
    }

    private FormOts makeOtsForm(FormIcOts formIcOts, int numberOnShaft) {
        FormOts formOts = new FormOts();
        formOts.setTitleForm(formIcOts.getTitleForm());
        formOts.setNumberOnShaft(numberOnShaft);
        formOts.setIndentationOnShaft(formIcOts.getRightMargin() + margin);
        margin = margin + formIcOts.getWidth();
        formOts.setIntervalLabels(formIcOts.getIntervalLabels());
        return formOts;
    }
}