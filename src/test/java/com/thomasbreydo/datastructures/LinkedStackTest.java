package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {
  LinkedStack<Integer> stack;

  @BeforeEach
  void setUp() {
    stack = new LinkedStack<>();
  }

  @Test
  void test() {
    assertTrue(stack.empty());
    assertThrows(EmptyStackException.class, stack::peek);
    stack.push(5);
    assertFalse(stack.empty());
    assertDoesNotThrow(stack::peek);
    assertEquals(5, stack.peek());
    assertEquals(5, stack.pop());
    assertTrue(stack.empty());
    stack.push(0);
    assertFalse(stack.empty());
    assertEquals(0, stack.peek());
    stack.push(9);
    assertFalse(stack.empty());
    assertEquals(9, stack.peek());
    stack.push(null);
    assertFalse(stack.empty());
    assertNull(stack.peek());
    stack.push(-1);
    assertFalse(stack.empty());
    assertEquals(-1, stack.peek());
    assertEquals(-1, stack.pop());
    assertFalse(stack.empty());
    assertNull(stack.pop());
    assertEquals(9, stack.pop());
    assertFalse(stack.empty());
    assertEquals(0, stack.pop());
    assertTrue(stack.empty());
  }
}
