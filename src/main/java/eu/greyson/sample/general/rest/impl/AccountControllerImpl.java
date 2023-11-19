package eu.greyson.sample.general.rest.impl;
import eu.greyson.sample.general.dto.AccountDto;
import eu.greyson.sample.general.dto.requests.CardBlockRequest;
import eu.greyson.sample.general.dto.CardDto;
import eu.greyson.sample.general.dto.TransactionDto;
import eu.greyson.sample.general.dto.requests.TransactionRequest;
import eu.greyson.sample.general.rest.AccountController;
import eu.greyson.sample.general.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountControllerImpl implements AccountController {
    private final AccountService accountService;

    @Override
    @GetMapping
    public ResponseEntity<List<AccountDto>> getUserAccounts(@RequestParam Long userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @Override
    @GetMapping("{accountId}")
    public ResponseEntity<AccountDto> getAccountDetail(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccount(accountId));
    }

    @Override
    @GetMapping("{accountId}/Transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionsWithAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getTransactionsWithAccountId(accountId));
    }

    @PostMapping("{accountId}/transactions")
    @Override
    public ResponseEntity<TransactionDto> makeCreditorTransaction(@PathVariable Long accountId, @RequestBody TransactionRequest transaction) {
        TransactionDto savedTransaction = accountService.addTransaction(accountId, transaction.getDebtor(), transaction.getAmount());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTransaction);
    }

    @Override
    @GetMapping("{accountId}/cards")
    public ResponseEntity<List<CardDto>> getAccountCards(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountCards(accountId));
    }

    @Override
    @GetMapping("{accountId}/cards/{cardId}")
    public ResponseEntity<CardDto> getAccountCardDetail(@PathVariable Long accountId,@PathVariable Long cardId) {
        return ResponseEntity.ok(accountService.getAccountCard(accountId, cardId));
    }

    @Override
    @PutMapping("{accountId}/cards/{cardId}")
    public ResponseEntity<CardDto> changeAccountCardBlockedStatus(@PathVariable Long accountId,@PathVariable Long cardId, @RequestBody CardBlockRequest blocked) {
        return ResponseEntity.ok(accountService.updateCardBlockedStatus(accountId, cardId, blocked.isBlocked()));
    }
}
