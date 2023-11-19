package eu.greyson.sample.general.model;

import eu.greyson.sample.shared.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "gr_user")
public class User extends BaseEntity<Long>  {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDateTime birthDate;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Account> accounts;

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
