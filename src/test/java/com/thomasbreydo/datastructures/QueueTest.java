package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

  Queue<Integer> queue;

  @BeforeEach
  void setUp() {
    queue = new Queue<>();
  }

  @Test
  void test() {
    assertTrue(queue.isEmpty());
    queue.offer(3);
    assertFalse(queue.isEmpty());
    assertEquals(3, queue.element());
    assertFalse(queue.isEmpty());
    assertEquals(3, queue.element());
    assertFalse(queue.isEmpty());
    assertEquals(3, queue.element());
    assertFalse(queue.isEmpty());
    assertEquals(3, queue.remove());
    assertTrue(queue.isEmpty());
    assertNull(queue.poll());
    assertNull(queue.peek());
    assertThrows(NoSuchElementException.class, queue::element);
    queue.add(0);
    assertFalse(queue.isEmpty());
    queue.add(1);
    assertFalse(queue.isEmpty());
    queue.add(2);
    assertFalse(queue.isEmpty());
    queue.add(3);
    assertFalse(queue.isEmpty());
    assertEquals(0, queue.peek());
    assertFalse(queue.isEmpty());
    assertEquals(0, queue.poll());
    assertFalse(queue.isEmpty());
    assertEquals(1, queue.poll());
    assertFalse(queue.isEmpty());
    assertEquals(2, queue.peek());
    assertFalse(queue.isEmpty());
    assertEquals(2, queue.element());
    assertEquals(2, queue.remove());
    assertFalse(queue.isEmpty());
    queue.add(null);
    assertFalse(queue.isEmpty());
    assertEquals(3, queue.remove());
    assertNull(queue.remove());
    assertTrue(queue.isEmpty());
    assertThrows(NoSuchElementException.class, queue::remove);
    assertThrows(NoSuchElementException.class, queue::element);
  }
}
