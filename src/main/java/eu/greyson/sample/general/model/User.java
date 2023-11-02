package eu.greyson.sample.general.model;

import eu.greyson.sample.shared.enums.AuthProvider;
import eu.greyson.sample.shared.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "gr_user")
public class User extends BaseEntity<Long>  {

    private String firstName;
    private String lastName;

    private String email;

    private String imageUrl;
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
