package eu.greyson.sample.general.rest;

import eu.greyson.sample.general.dto.AccountDto;
import eu.greyson.sample.general.dto.CardDto;
import eu.greyson.sample.general.dto.TransactionDto;
import eu.greyson.sample.general.dto.requests.CardBlockRequest;
import eu.greyson.sample.general.dto.requests.TransactionRequest;
import eu.greyson.sample.shared.swagger.ApiResponses_200_400_401_404_500;
import eu.greyson.sample.shared.swagger.ApiResponses_200_401_404_500;
import eu.greyson.sample.shared.swagger.ApiResponses_201_400_401_404_500;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Accounts", description = "Accounts controller")
public interface AccountController {
    @Operation(
            summary = "List of user's accounts",
            description = "This endpoint returns list of all user's accounts."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<List<AccountDto>> getUserAccounts(@NotNull Long userId);

    @Operation(
            summary = "Account detail endpoint",
            description = "This endpoint returns detailed information about account."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<AccountDto> getAccountDetail(@NotNull Long id);

    @Operation(
            summary = "Account's transactions endpoint",
            description = "This endpoint returns list of all transactions where account with given id figures as creditor or debtor."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<List<TransactionDto>> getTransactionsWithAccountId(@NotNull Long id);

    @Operation(
            summary = "Make transaction endpoint",
            description = "This endpoint creates new transaction with given account as creditor."
    )
    @ApiResponses_201_400_401_404_500
    ResponseEntity<TransactionDto> addCreditorTransaction(@NotNull Long id, @NotNull TransactionRequest transaction);

    @Operation(
            summary = "Account's cards endpoint",
            description = "This endpoint returns list of all cards registered to the account with given id."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<List<CardDto>> getAccountCards(@NotNull Long id);
    @Operation(
            summary = "Account's card detail endpoint",
            description = "This endpoint returns detailed information about card with given id."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<CardDto> getAccountCardDetail(@NotNull Long accountId, @NotNull Long cardId);
    @Operation(
            summary = "Change card's blocked status",
            description = "This endpoint is used for changing blocked status of the card with given id to true or false."
    )
    @ApiResponses_200_400_401_404_500
    ResponseEntity<CardDto> changeAccountCardBlockedStatus(@NotNull Long accountId, @NotNull Long cardId, @NotNull CardBlockRequest blocked);
}
