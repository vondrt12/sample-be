package eu.greyson.sample.general.model;

import eu.greyson.sample.shared.model.BaseEntity;
import eu.greyson.sample.shared.validators.ValidTransaction;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ValidTransaction
@Table(name = "gr_transaction")
public class Transaction extends BaseEntity<Long> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creditor")
    private Account creditor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "debtor")
    private Account debtor;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    private LocalDateTime dateExecuted;

    @NotNull
    private int amount;

}
