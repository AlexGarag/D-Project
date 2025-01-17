package by.tms.d_project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShaftDto {
    private String titlePrinting;
    private int typeShaft;
    private List<FormDto> formsDto = new ArrayList<>();
    private String creator;
}