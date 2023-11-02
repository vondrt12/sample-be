package eu.greyson.sample.general.model;

import com.fasterxml.jackson.annotation.JsonView;
import eu.greyson.sample.general.enums.AnimalType;
import eu.greyson.sample.shared.json.DtoView;
import eu.greyson.sample.shared.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@JsonView(DtoView.Detail.class)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "gr_animal")
public class Animal extends BaseEntity<Long> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @NotNull
    private String color;

    @NotNull
    private BigDecimal price;

    private String publicNote;

    private String secretNote;

    @UpdateTimestamp
    private LocalDateTime created;

    @CreationTimestamp
    private LocalDateTime updated;
}


