package eu.greyson.sample.general.service;

import eu.greyson.sample.general.dto.AccountDto;
import eu.greyson.sample.general.dto.CardDto;
import eu.greyson.sample.general.dto.TransactionDto;
import eu.greyson.sample.general.dto.UserDto;
import eu.greyson.sample.general.model.Account;
import eu.greyson.sample.general.model.Card;
import eu.greyson.sample.general.model.Transaction;
import eu.greyson.sample.general.model.User;
import eu.greyson.sample.general.repository.AccountRepository;
import eu.greyson.sample.general.repository.CardRepository;
import eu.greyson.sample.general.repository.TransactionRepository;
import eu.greyson.sample.general.repository.UserRepository;
import eu.greyson.sample.shared.exception.BadRequestException;
import eu.greyson.sample.shared.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountService extends BaseService<Account, Long, AccountRepository> implements InitializingBean {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    protected AccountRepository getDefaultRepository() {
        return this.accountRepository;
    }

    @Override
    public void afterPropertiesSet() {
        this.mapper.addMappings(new AccountDtoMapper());
        this.mapper.addMappings(new TransactionDtoMapper());
        this.mapper.addMappings(new CardDtoMapper());
    }

    private AccountDto createAccountDto(Account account) {return mapper.map(account, AccountDto.class);}
    private TransactionDto createTransactionDto(Transaction transaction) {return mapper.map(transaction, TransactionDto.class);}
    private CardDto createCardDto(Card card) {return mapper.map(card, CardDto.class);}
    public AccountDto getAccount(Long id) {
        return createAccountDto(accountRepository.findUnsafe(id));
    }
    public List<AccountDto> getAccountsByUserId(Long userId) {
        List<Account> accounts = userRepository.findUnsafe(userId).getAccounts();
        return accounts.stream().map(this::createAccountDto).toList();
    }

    public List<TransactionDto> getDebtorTransactionsWithAccountId(Long accountId) {
        return accountRepository.findUnsafe(accountId).getDebtorTransactions().stream().map(this::createTransactionDto).collect(Collectors.toList());
    }
    public List<TransactionDto> getCreditorTransactionsWithAccountId(Long accountId) {
        return accountRepository.findUnsafe(accountId).getCreditorTransactions().stream().map(this::createTransactionDto).collect(Collectors.toList());
    }
    public List<TransactionDto> getTransactionsWithAccountId(Long accountId) {
        Account account = accountRepository.findUnsafe(accountId);
        return Stream.concat(
                account.getCreditorTransactions().stream(),
                account.getDebtorTransactions().stream()
        ).map(this::createTransactionDto).collect(Collectors.toList());
    }
    public TransactionDto addTransaction(Long accountId, String debtor, int amount) {
        Account debtor_account = accountRepository.findAccountByIBAN(debtor).orElseThrow(
                ()-> new BadRequestException("Debtor account with given IBAN: " + debtor +" does not exist.")
        );
        Account creditor_account = accountRepository.findUnsafe(accountId);
        Transaction transaction = new Transaction();
        transaction.setDebtor(debtor_account);
        transaction.setCreditor(creditor_account);
        transaction.setAmount(amount);
        return createTransactionDto(transactionRepository.saveAndFlush(transaction));
    }

    public CardDto getAccountCard(Long accountId, Long cardId) {
        Card card = cardRepository.findUnsafe(cardId);
        if(!card.getAccount().getId().equals(accountId)) throw new BadRequestException("Given account id and account id of the card's owner doesn't match.");
        return createCardDto(card);
    }
    public List<CardDto> getAccountCards(Long accountId) {
        return accountRepository.findUnsafe(accountId).getCards().stream().map(this::createCardDto).collect(Collectors.toList());
    }
    public CardDto updateCardBlockedStatus(Long accountId, Long cardId, boolean blocked) {
        Card card = cardRepository.findUnsafe(cardId);
        if(!card.getAccount().getId().equals(accountId)) throw new BadRequestException("Given account id and account id of the card's owner doesn't match.");
        if(!card.isBlocked() && blocked){
            card.setDateLocked(LocalDateTime.now());
        } else if (!blocked) {
            card.setDateLocked(null);
        }
        card.setBlocked(blocked);
        return createCardDto(cardRepository.saveAndFlush(card));
    }

    private static class AccountDtoMapper extends PropertyMap<Account, AccountDto> {
        @Override
        protected void configure() {
            map().setId(source.getId());
            map().setBalance(source.getBalance());
            map().setIBAN(source.getIBAN());
            map().setName(source.getName());
            map().setCurrency(source.getCurrency());
        }
    }
    private static class TransactionDtoMapper extends PropertyMap<Transaction, TransactionDto> {
        @Override
        protected void configure() {
            map().setId(source.getId());
            map().setAmount(source.getAmount());
            map().setCreditor(source.getCreditor().getIBAN());
            map().setDebtor(source.getDebtor().getIBAN());
            map().setDateCreated(source.getDateCreated());
            map().setDateExecuted(source.getDateExecuted());
        }
    }

    private static class CardDtoMapper extends PropertyMap<Card, CardDto> {

        @Override
        protected void configure() {
            map().setId(source.getId());
            map().setName(source.getName());
            map().setBlocked(source.isBlocked());
        }
    }
}
