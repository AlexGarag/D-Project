package by.tms.d_project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OtsShortDto {
    private String titlePrinting;
    private int shaftType;
    private List<FormDto> formsDto = new ArrayList<>();
    private String author;
}