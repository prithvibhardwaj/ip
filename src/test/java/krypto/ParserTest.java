package krypto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import krypto.commands.Command;
import krypto.commands.ExitCommand;

/**
 * Contains JUnit tests for the Parser class.
 * Ensures that user commands are parsed into the correct Command objects.
 */
public class ParserTest {

    /**
     * Tests if the "bye" command correctly parses into an ExitCommand.
     *
     * @throws KryptoException If an unexpected parsing error occurs.
     */
    @Test
    public void parse_byeCommand_returnsExitCommand() throws KryptoException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    /**
     * Tests if providing an unrecognised command throws a KryptoException.
     */
    @Test
    public void parse_unknownCommand_throwsKryptoException() {
        assertThrows(KryptoException.class, () -> {
            Parser.parse("some random text");
        });
    }
}
