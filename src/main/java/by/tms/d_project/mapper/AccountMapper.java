package by.tms.d_project.mapper;

import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.dto.AccountShortDto;
import by.tms.d_project.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    AccountShortDto toAccountShortDto(Account account);

    AccountDto toAccountDto(Account account);
}