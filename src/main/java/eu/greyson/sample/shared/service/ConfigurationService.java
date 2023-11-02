package eu.greyson.sample.shared.service;

import eu.greyson.sample.shared.dto.Configuration;
import eu.greyson.sample.shared.enums.ConfigKey;
import eu.greyson.sample.shared.exception.ConfigurationException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigurationService {

    private Environment env;

    /**
     * Finds configuration for given key
     *
     * @param key identifier
     * @return configuration key
     * @throws ConfigurationException when no record was found
     */
    public Configuration findByKey(ConfigKey key) {
        var value = Optional.ofNullable(env.getProperty(key.getValue())).orElseThrow(() -> new ConfigurationException(String.format("Konfigurační položka %s neexistuje!", key.getValue())));
        return new Configuration(key, value);
    }

}
