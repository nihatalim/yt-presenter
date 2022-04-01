package tr.com.nihatalim.yt.presenter.util;

public class ApplicationUtil {
    public static int getConcurrencyLevel() {
        return Runtime.getRuntime().availableProcessors() * 2;
    }
}
