package at.ac.tuwien.sem2024w.ep1;

import static at.ac.tuwien.sem2024w.ep1.Main.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MainTest {
    @ParameterizedTest
    @CsvSource({"12,144", "-1,13", "1,1"})
    public void testIntegerRoot(int expected, int paramenter) {
        assertEquals(expected, getIntegerRoot(paramenter));
    }

    private static Stream<Arguments> getThirdStrings() {
        String test = "Blaukraut";

        return Stream.of(
                Arguments.of("alle unterschiedlich", "toss", "a", "coin"),
                Arguments.of("bleibt", "Blaukraut", "bleibt", test),
                Arguments.of("alle gleich", "badger", "badger", "badger"),
                Arguments.of("alle gleich", "", "", "")
        );
    }

    @ParameterizedTest
    @MethodSource("getThirdStrings")
    public void testGetThird(String expected, String a, String b, String c) {
        assertEquals(expected, getThird(a, b, c));

    }

    @ParameterizedTest
    @CsvSource({"TU Wien,TU Wien", "Hubb1 bubb2!,Hubba bubba!", "1234,aaaa", "123456789101112,aaaaaaaaaaaa"})
    public void testReplaceA(String expected, String parameter) {
        assertEquals(expected, replaceA(parameter));
    }

    @ParameterizedTest
    @CsvSource({"TU Wien,TU Wien", "Hubb1 bubb2!,Hubba bubba!", "1234,aaaa"})
    public void testSimpleReplaceA(String expected, String parameter) {
        assertEquals(expected, simpleReplaceA(parameter));
    }
}
