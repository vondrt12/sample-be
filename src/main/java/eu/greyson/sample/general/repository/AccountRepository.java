package eu.greyson.sample.general.repository;

import eu.greyson.sample.general.model.Account;
import eu.greyson.sample.general.model.Transaction;
import eu.greyson.sample.shared.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {
    Optional<Account> findAccountByIBAN(String iban);
}
