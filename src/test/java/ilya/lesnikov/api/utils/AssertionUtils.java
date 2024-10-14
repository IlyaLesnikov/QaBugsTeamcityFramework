package ilya.lesnikov.api.utils;

import org.assertj.core.api.Assertions;

public class AssertionUtils {
    private AssertionUtils() {}

    public static <E, A> void assertEquals(E expected, A actual) {
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
