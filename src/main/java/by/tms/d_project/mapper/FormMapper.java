package by.tms.d_project.mapper;

import by.tms.d_project.dto.FormDto;
import by.tms.d_project.entity.FormOts;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormMapper {

    FormDto toFormDto(FormOts formOts);

    List<FormDto> toFormDtoList(List<FormOts> formsOts);
}
