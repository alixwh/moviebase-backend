package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.mapper.AccountMapper;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> findAll() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    public AccountDto findById(int id) {
        return accountMapper.toDto(accountRepository.findById(id).orElse(null));
    }

}