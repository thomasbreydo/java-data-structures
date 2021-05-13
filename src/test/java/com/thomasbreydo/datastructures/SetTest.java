package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
  Set<Integer> s;

  @BeforeEach
  void setUp() {
    s = new Set<>();
  }

  @Test
  void size() {
    assertEquals(0, s.size());
    s.insert(0);
    assertEquals(1, s.size());
    s.insert(0);
    assertEquals(1, s.size());
    s.delete(0);
    assertEquals(0, s.size());
  }

  @Test
  void test() {
    assertFalse(s.contains(5));
    assertTrue(s.insert(5));
    assertTrue(s.contains(5));
    assertFalse(s.insert(5));
    assertTrue(s.contains(5));
    assertTrue(s.delete(5));
    assertFalse(s.contains(5));
  }
}
