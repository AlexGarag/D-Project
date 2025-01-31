package by.tms.d_project.mapper;

import by.tms.d_project.dto.FormDto;
import by.tms.d_project.entity.FormOts;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormMapper {

    FormDto toDto(FormOts formOts);

    List<FormDto> toListDto(List<FormOts> formsOts);
}
