package minimanimo.game;

import minimanimo.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class UpDownTest {
    @Test
    void testGameName() { // Test getGameName method
        UpDown game = new UpDown();
        assertEquals("UpDown", game.getGameName());
    }

    @Test
    void testIsLowerScoreBetter() { // Test isLowerScoreBetter method
        UpDown game = new UpDown();
        assertFalse(game.isLowerScoreBetter());
    }

    @Test
    void testInvalidInputAndGameOver() {
        String input = "abc\n" +
                "def\n" +
                " \n" +
                "0\n" +
                "101\n" +
                "-50\n" +
                "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n";

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        User user = new User("TestUser");
        UpDown game = new UpDown();

        int score = game.startGame(user, scanner);

        assertTrue(score >= 0);
    }

    @Test
    void testMaxAttemptsCheck() {
        StringBuilder inputs = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            inputs.append(i).append("\n");
        }

        ByteArrayInputStream in = new ByteArrayInputStream(inputs.toString().getBytes());
        Scanner scanner = new Scanner(in);
        User user = new User("TestUser");
        UpDown game = new UpDown();

        int score = game.startGame(user, scanner);

        assertTrue(score >= 0 && score <= 10);
    }

    @Test
    void testMixedInvalidInputs() {
        String inputs = "invalid\n" +
                "999\n" +
                "-1\n" +
                "50\n" +
                "invalid\n" +
                "51\n52\n53\n54\n55\n56\n57\n58\n59\n";

        ByteArrayInputStream in = new ByteArrayInputStream(inputs.getBytes());
        Scanner scanner = new Scanner(in);
        User user = new User("TestUser");
        UpDown game = new UpDown();

        assertDoesNotThrow(() -> game.startGame(user, scanner));

    }
}
