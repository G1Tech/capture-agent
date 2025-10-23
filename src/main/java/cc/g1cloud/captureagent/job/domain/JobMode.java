package cc.g1cloud.captureagent.job.domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum JobMode {
    COPY,
    TRANSCODE;

    public static Optional<JobMode> fromString(String value) {
        if (value == null) {
            return Optional.empty();
        }
        return Arrays.stream(values())
                .filter(mode -> mode.name().equalsIgnoreCase(value.trim()))
                .findFirst();
    }

    public String asToken() {
        return name().toLowerCase(Locale.ROOT);
    }
}
