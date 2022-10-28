package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.classes.Account;
import ee.taltech.iti0302.webproject.classes.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mappings({
            @Mapping(source = "movies", target = "moviesList"),
            @Mapping(source = "friends", target = "friendList")})
    AccountDto toDto(Account account);

    default String mapToMovieTitle(Movie movie) {
        return movie.getTitle();
    }

    default String mapToAccountUsername(Account account) {
        return account.getUsername();
    }

    List<AccountDto> toDtoList(List<Account> accounts);

}