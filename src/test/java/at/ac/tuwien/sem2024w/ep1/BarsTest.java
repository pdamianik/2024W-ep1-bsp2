package at.ac.tuwien.sem2024w.ep1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static at.ac.tuwien.sem2024w.ep1.Main.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

public class BarsTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void overrideStreams() {
        System.setOut(new PrintStream(outContent));
    }

    private static Stream<Arguments> getPrintBarsValues() {
        return Stream.of(
                Arguments.of(2, "1-2+\n"),
                Arguments.of(19, """
                        1-2+4-5+7-8+10-11+13-14+16-17+19-
                        3+6-9+12-15+18-
                        """),
                Arguments.of(20, """
                        1-2+4-5+7-8+10-11+13-14+16-17+19-20+
                        3+6-9+12-15+18-
                        """),
                Arguments.of(21, """
                        1-2+4-5+7-8+10-11+13-14+16-17+19-20+
                        3+6-9+12-15+18-21+
                        """)
        );
    }

    @ParameterizedTest
    @MethodSource("getPrintBarsValues")
    public void testPrintBars(int parameter, String expected) {
        printBars(parameter);
        assertEquals(expected, outContent.toString());
        outContent.reset();
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }
}
