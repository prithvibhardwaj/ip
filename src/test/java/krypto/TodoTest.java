package krypto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import krypto.tasks.Todo;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testToFileFormat() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.toFileFormat());
    }
}
