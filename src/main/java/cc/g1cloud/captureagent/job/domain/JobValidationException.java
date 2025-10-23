package cc.g1cloud.captureagent.job.domain;

public class JobValidationException extends RuntimeException {

    private final String code;

    public JobValidationException(String message) {
        this("BUSINESS_RULE_VIOLATION", message);
    }

    public JobValidationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String code() {
        return code;
    }
}
