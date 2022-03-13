package tr.com.nihatalim.yt.presenter.exception;

public class KafkaProduceFailedException extends RuntimeException {
    public KafkaProduceFailedException(String message) {
        super(message);
    }
}
