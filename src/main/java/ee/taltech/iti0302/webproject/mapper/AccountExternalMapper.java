package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.model.AccountExternalDto;
import ee.taltech.iti0302.webproject.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountExternalMapper {
    AccountExternalDto accountToAccountExternalDto(Account account);
    Account accountExternalDtoToAccount(AccountExternalDto account);
}
