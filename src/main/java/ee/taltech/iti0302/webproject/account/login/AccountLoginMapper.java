package ee.taltech.iti0302.webproject.account.login;

import ee.taltech.iti0302.webproject.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountLoginMapper {
    Account accountLoginTOAccount(AccountLoginDto accountLoginDto);

}
