package eu.greyson.sample.shared.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Base entity with primary key
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    public static final long serialVersionUID = 1;

    /**
     * Storage primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

}
