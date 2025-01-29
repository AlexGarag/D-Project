package by.tms.d_project.utils;

import by.tms.d_project.entity.FormIcOts;
import by.tms.d_project.entity.FormOts;
import by.tms.d_project.entity.IcOts;
import by.tms.d_project.entity.Ots;
import org.springframework.stereotype.Component;

import static by.tms.d_project.constant_reference_etc.Constant.LENGTH_SHAFT;

/**
 * Класс формирует "Разовое решение" (Ots - one-time solution)
 * по начальным условиям, заданным в IcOts *
 */
@Component
public class SolverOts {

    public Ots makeOts(IcOts icOts) {
        Ots ots = new Ots();
        ots.setTitlePrinting(icOts.getTitlePrinting());
        ots.setShaftSize(icOts.getShaftSize());
        ots.setAuthor(icOts.getAuthor());
        int widthShapesShaft = 0;
        for (FormIcOts formIcOts : icOts.getFormsIcOts()) {
            widthShapesShaft = widthShapesShaft + formIcOts.getWidth();
        }
        int margin = (LENGTH_SHAFT - widthShapesShaft) / 2;
        for (int numberOnShaft = icOts.getFormsIcOts().size() - 1; numberOnShaft > -1; numberOnShaft --) {
            FormOts formOts = makeOtsForm(icOts.getFormsIcOts().get(numberOnShaft), numberOnShaft + 1);
            ots.getFormsOts().add(formOts);
        }
        return ots;
    }

    private FormOts makeOtsForm(FormIcOts formIcOts, int numberOnShaft) {
        FormOts formOts = new FormOts();
        formOts.setTitleForm(formIcOts.getTitleForm());
        formOts.setNumberOnShaft(numberOnShaft);
        int margin = 0;
        formOts.setIndentationOnShaft(formIcOts.getRightMargin() + margin);
        margin = margin + formIcOts.getWidth();
        formOts.setIntervalLabels(formIcOts.getIntervalLabels());
        return formOts;
    }
}