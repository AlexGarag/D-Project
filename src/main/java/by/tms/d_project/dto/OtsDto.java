package by.tms.d_project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OtsDto {
    private String titlePrinting;
    private int shaftSize;
    private List<FormDto> formsDto = new ArrayList<>();
    private AccountShortDto author;
}

