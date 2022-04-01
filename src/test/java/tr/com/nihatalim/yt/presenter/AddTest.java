package tr.com.nihatalim.yt.presenter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTest {
    public String addBinary(String a, String b) {
        String additionalZeros = "";

        final int additionalZerosCount = Math.abs(a.length() - b.length());

        for (int j = 0; j < additionalZerosCount; j++) {
            additionalZeros = "0" + additionalZeros;
        }

        if (a.length() < b.length()) {
            a = additionalZeros + a;
        } else if (a.length() > b.length()){
            b = additionalZeros + b;
        }

        int temp = 0;
        String result = "";

        for (int i = 0; i < a.length(); i++) {
            String charA = a.substring(a.length() - 1 - i, a.length() - i);
            String charB = b.substring(b.length() - 1 - i, b.length() - i);

            int sum = Integer.parseInt(charA) + Integer.parseInt(charB) + temp;

            temp = 0;

            if (sum > 1) {
                temp = 1;
            }

            result = sum % 2 + result;
        }

        if (temp > 0) {
            result = "1" + result;
        }

        return result;
    }


    @ParameterizedTest
    @MethodSource("provider")
    public void addTest(String a, String b, String expectedResult) {
        final String actualResult = addBinary(a, b);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("11", "1", "100"),
            Arguments.of("1010", "1011", "10101"),
            Arguments.of("100", "11", "111")
        );
    }
}
