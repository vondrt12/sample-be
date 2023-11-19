package eu.greyson.sample.general.model;

import com.fasterxml.jackson.annotation.JsonView;
import eu.greyson.sample.shared.json.DtoView;
import eu.greyson.sample.shared.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@JsonView(DtoView.Detail.class)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "gr_account")
public class Account extends BaseEntity<Long> {
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "owner")
    private User owner;

    @NotBlank
    private String IBAN;

    @NotBlank
    private String name;

    @NotNull
    private int balance;

    @OneToMany(mappedBy = "creditor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Transaction> creditorTransactions;

    @OneToMany(mappedBy = "debtor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Transaction> debtorTransactions;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Card> cards;

    @Size(min=3, message = "{validation.name.size.too_short}")
    @Size(min=3, message = "{validation.name.size.too_long}")
    private String currency;
}
