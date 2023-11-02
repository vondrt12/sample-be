package eu.greyson.sample.shared.model;

import java.io.Serializable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
