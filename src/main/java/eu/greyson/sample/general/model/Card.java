package eu.greyson.sample.general.model;

import eu.greyson.sample.shared.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "gr_card")
public class Card extends BaseEntity<Long> {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @NotBlank
    private String name;

    @NotNull
    private boolean blocked;

    private LocalDateTime dateLocked;
}
