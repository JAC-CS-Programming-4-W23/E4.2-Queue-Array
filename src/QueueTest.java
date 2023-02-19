import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {

    public static final int CAPACITY = 20;
    private IntQueue queue;

    @BeforeEach
    public void setUp() {
        queue = new IntQueue(CAPACITY);
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(4);
        assertEquals(4, queue.peek());
        queue.enqueue(5);
        assertEquals(4, queue.peek());
        queue.enqueue(6);
        assertEquals(4, queue.peek());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(6, queue.dequeue());
    }

    @Test
    public void testQueueOverflow() {
        for (int i = 0; i < CAPACITY; i++) {
            queue.enqueue(i);
		}

		assertThrows(QueueOverflowException.class, () ->  queue.enqueue(1));
    }

    @Test
    public void testQueueUnderflow() {
        assertThrows(QueueUnderflowException.class, () -> queue.dequeue());
    }

    @Test
    public void testQueueToString() {
        assertEquals("FRONT --> [] <-- REAR", queue.toString());

        queue.enqueue(4);
        assertEquals("FRONT --> [4] <-- REAR", queue.toString());

        queue.enqueue(5);
        assertEquals("FRONT --> [4, 5] <-- REAR", queue.toString());

        queue.enqueue(6);
        assertEquals("FRONT --> [4, 5, 6] <-- REAR", queue.toString());

    }
}
