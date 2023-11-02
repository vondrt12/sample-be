package eu.greyson.sample.shared.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ConfigurationException extends RuntimeException {

    public ConfigurationException(String message) {
        super(message);
    }
}
