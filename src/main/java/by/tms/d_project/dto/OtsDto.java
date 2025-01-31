package by.tms.d_project.dto;

import lombok.Data;

import java.util.List;

@Data
public class OtsDto { // todo сделать Record
    private String titlePrinting;
    private int shaftSize;
    private List<FormDto> formsOtsDto;
    private AccountShortDto author;
}

