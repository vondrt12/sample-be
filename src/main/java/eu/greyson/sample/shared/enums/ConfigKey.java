package eu.greyson.sample.shared.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigKey {
    AUTH_USERNAME("sample.auth.username"),
    AUTH_PASSWORD("sample.auth.password");

    private final String value;
}
