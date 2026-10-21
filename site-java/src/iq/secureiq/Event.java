package iq.secureiq;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record Event(
        long ts,
        String zone,
        String type,
        String severity,
        String source,
        double score,
        boolean anomaly
) {
    public static final ZoneId ZONE = ZoneId.of("Asia/Karachi");
    private static final DateTimeFormatter HHMM = DateTimeFormatter.ofPattern("HH:mm");

    public String time() {
        return Instant.ofEpochMilli(ts).atZone(ZONE).format(HHMM);
    }

    public String iso() {
        return Instant.ofEpochMilli(ts).toString();
    }

    public String sevClass() {
        return switch (severity) {
            case "critical" -> "sev-critical";
            case "high" -> "sev-high";
            case "medium" -> "sev-medium";
            default -> "sev-low";
        };
    }
}
