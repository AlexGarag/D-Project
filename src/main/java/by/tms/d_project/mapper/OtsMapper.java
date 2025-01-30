package by.tms.d_project.mapper;

import by.tms.d_project.dto.OtsDto;
import by.tms.d_project.entity.Ots;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AccountMapper.class})
public interface OtsMapper {

    OtsDto toDto(Ots ots);
}