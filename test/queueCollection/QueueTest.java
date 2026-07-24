package queueCollection;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue myQueue;

    public QueueTest() {
    }

    @BeforeEach
    public void setUp() {
        myQueue = new Queue(5);
    }

    @Test
    public void queueIsEmptyTest() {
        assertTrue(myQueue.isEmpty());
    }

    @Test
    public void addString_queueIsNotEmptyTest() {
        assertTrue(myQueue.add("Thy Will"));
        assertFalse(myQueue.isEmpty());
    }

    @Test
    public void addStringToFullQueue_throwErrorTest() {
        myQueue.add("Thy Will");
        myQueue.add("Jack Rich");
        myQueue.add("Real Men");
        myQueue.add("Undisputed Champion");
        myQueue.add("We are who we are");
        assertThrows(IllegalArgumentException.class, () -> myQueue.add("The Talibans"));
    }

    @Test
    public void offerStringToFullQueue_throwErrorTest() {
        myQueue.offer("Thy Will");
        myQueue.offer("Jack Rich");
        myQueue.offer("Real Men");
        myQueue.offer("Undisputed Champion");
        myQueue.offer("We are who we are");
        assertFalse(myQueue.offer("Let them breathe"));
    }

    @Test
    public void removeString_returnStringTest() {
        myQueue.add("Thy Will");
        myQueue.add("Half of a yellow sun");
        assertEquals("Thy Will", myQueue.remove());
    }

    @Test
    public void remove2Strings_return2StringTest() {
        myQueue.add("Real Men");
        myQueue.add("Jack Rich");
        assertEquals("Real Men", myQueue.remove());
        assertEquals("Jack Rich", myQueue.remove());
    }

    @Test
    public void removeStringFromEmptyQueue_throwErrorTest() {
        assertThrows(NoSuchElementException.class, () -> myQueue.remove());
    }

    @Test
    public void pollString_returnStringTest() {
        myQueue.add("Thy Will");
        myQueue.add("Rich Gang");
        assertEquals("Thy Will", myQueue.poll());
    }

    @Test
    public void poll2Strings_return2String_queueIsEmptyTest() {
        myQueue.add("Thy Will");
        myQueue.add("Rich Gang");
        assertEquals("Thy Will", myQueue.poll());
        assertEquals("Rich Gang", myQueue.poll());
        assertTrue(myQueue.isEmpty());
    }

    @Test
    public void pollStringFromEmptyQueue_throwErrorTest() {
        assertNull(myQueue.poll());
    }

    @Test
    public void elementString_returnStringTest() {
        myQueue.add("Thy Will");
        myQueue.add("Richie Rich");
        assertEquals("Thy Will", myQueue.element());
    }

    @Test
    public void add2Strings_returnHeadOfQueue_removeOneString_returnHeadOfQueueTest() {
        myQueue.add("Thy Will");
        myQueue.add("Rich Gang");
        assertEquals("Thy Will", myQueue.element());
        myQueue.remove();
        assertEquals("Rich Gang", myQueue.element());
    }

    @Test
    public void retrieveStringFromEmptyQueue_throwErrorTest() {
        assertThrows(NoSuchElementException.class, () -> myQueue.element());
    }

    @Test
    public void peekString_returnHeadOfQueueTest() {
        myQueue.add("Thy Will");
        myQueue.add("Rich Gang");
        assertEquals("Thy Will", myQueue.peek());
    }

    @Test
    public void add3Strings_returnHeadOfQueue_removeOneString_returnHeadOfQueueTest() {
        myQueue.add("Thy Will");
        myQueue.add("Rich Gang");
        myQueue.offer("The Lady's Guy");
        assertEquals("Thy Will", myQueue.peek());
        myQueue.remove();
        myQueue.remove();
        assertEquals("The Lady's Guy", myQueue.peek());
    }

    @Test
    public void peekStringFromEmptyQueue_throwErrorTest() {
        assertNull(myQueue.peek());
    }
}
