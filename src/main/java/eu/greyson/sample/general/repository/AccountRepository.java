package eu.greyson.sample.general.repository;

import eu.greyson.sample.general.model.Account;
import eu.greyson.sample.shared.repository.BaseRepository;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {
    Optional<Account> findAccountByIBAN(String iban);
}
