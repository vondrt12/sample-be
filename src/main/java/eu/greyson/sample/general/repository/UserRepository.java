package eu.greyson.sample.general.repository;

import eu.greyson.sample.general.model.User;
import eu.greyson.sample.shared.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
}
