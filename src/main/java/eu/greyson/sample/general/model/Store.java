package eu.greyson.sample.general.model;

import eu.greyson.sample.shared.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "gr_store")
public class Store extends BaseEntity<Long> {

    @NotBlank
    private String name;

    @NotNull
    @Column(name = "cmpn_num", unique = true)
    private Integer companyNumber;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Animal> animals;
}
