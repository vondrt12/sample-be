package eu.greyson.sample.shared.config;

import eu.greyson.sample.shared.repository.impl.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"eu.greyson.sample"}, repositoryBaseClass = BaseRepositoryImpl.class)
@EnableTransactionManagement
public class PersistenceContext {
}
