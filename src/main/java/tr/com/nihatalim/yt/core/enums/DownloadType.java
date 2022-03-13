package tr.com.nihatalim.yt.core.enums;

import java.util.Arrays;

public enum DownloadType {
    SINGLE("SINGLE"),
    LIST("LIST");

    private final String value;

    DownloadType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DownloadType fromString(String value) {
        return Arrays.stream(DownloadType.values())
            .filter(item -> item.getValue().equals(value))
            .findFirst()
            .orElse(null);
    }
}
