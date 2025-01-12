package by.tms.d_project.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ShaftIcOtsDto {
    private String titlePrinting;
    private int typeShaft;
    private List<FormIcOtsDto> formsIcOtsDto;
//
//    public void setFormsIcOtsDto(List<FormIcOtsDto> formsIcOtsDto) {
//        this.formsIcOtsDto = formsIcOtsDto;
//    }
//
//    public void setTitlePrinting(String titlePrinting) {
//        this.titlePrinting = titlePrinting;
//    }
//
//    public void setTypeShaft(int typeShaft) {
//        this.typeShaft = typeShaft;
//    }
//
//    public List<FormIcOtsDto> getFormsIcOtsDto() {
//        return formsIcOtsDto;
//    }
//
//    public String getTitlePrinting() {
//        return titlePrinting;
//    }
//
//    public int getTypeShaft() {
//        return typeShaft;
//    }
}