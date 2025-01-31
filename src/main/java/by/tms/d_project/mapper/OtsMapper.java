package by.tms.d_project.mapper;

import by.tms.d_project.dto.OtsDto;
import by.tms.d_project.entity.Ots;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AccountMapper.class, FormMapper.class})
public interface OtsMapper {

    @Mapping(source = "ots.formsOts", target = "formsOtsDto")
    OtsDto toDto(Ots ots);
}