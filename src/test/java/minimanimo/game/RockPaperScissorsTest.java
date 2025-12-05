package minimanimo.game;

import minimanimo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

    @Test
    @DisplayName("Check Game Name and Score Type")
    void testGameProperties() {
        RockPaperScissors game = new RockPaperScissors();

        // 1. Check Name
        assertEquals("RPS", game.getGameName(), "Game name should be 'RPS'");

        // 2. Check Score Type
        assertFalse(game.isLowerScoreBetter(), "In RPS, higher score (more wins) is better, so this should be false.");
    }

    @Test
    @DisplayName("Test Input Validation and Game Termination")
    void testGameExecution() {
        // [Scenario]
        // 1. "abc" -> Invalid input (String)
        // 2. "5"   -> Invalid input (Out of range)
        // 3. "1"   -> Valid input (Rock). Repeated many times.
        //
        // Since the computer plays randomly, we repeat "1" (Rock) enough times
        // to guarantee that the computer eventually picks "2" (Paper), causing a loss and ending the loop.
        // Statistically, the chance of the computer NOT picking Paper in 50 tries is astronomically low.
        
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append("abc\n"); // Invalid: String
        inputBuilder.append("5\n");   // Invalid: Range
        
        // Append "1" (Rock) 50 times to ensure game finishes eventually
        for (int i = 0; i < 50; i++) {
            inputBuilder.append("1\n");
        }

        // Setup Input Stream
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(inputBuilder.toString().getBytes()));
        
        // Setup Scanner and Game
        Scanner scanner = new Scanner(System.in);
        RockPaperScissors game = new RockPaperScissors();
        User dummyUser = new User("TestBot");

        // Execute Game
        int score = game.startGame(dummyUser, scanner);

        // Cleanup
        System.setIn(stdin); // Restore original System.in

        // Assertions
        // The score cannot be negative.
        assertTrue(score >= 0, "Score should be 0 or higher.");
        
        System.out.println("[Test Info] Game finished successfully with score: " + score);
    }
}
