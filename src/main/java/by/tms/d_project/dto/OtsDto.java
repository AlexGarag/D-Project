package by.tms.d_project.dto;

import java.util.List;

public record OtsDto(String titlePrinting,
                     int shaftSize,
                     List<FormDto> formsOtsDto,
                     AccountShortDto author) {}

