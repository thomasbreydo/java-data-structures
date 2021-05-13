package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
  Map<Integer, Integer> m;

  @BeforeEach
  void setUp() {
    m = new Map<>();
  }

  @Test
  void expand() {
    assertEquals(Map.DEFAULT_CAPACITY, m.capacity);
    m.expand(20);
    assertEquals(20, m.capacity);
  }

  @Test
  void size() {
    assertEquals(0, m.size());
    assertNull(m.insert(3, 6));
    assertEquals(1, m.size());
    assertEquals(6, m.insert(3, 5));
    assertEquals(1, m.size());
    assertTrue(m.delete(3));
    assertEquals(0, m.size());
    m = new Map<>();
    assertEquals(0, m.size());
  }

  @Test
  void insert() {
    m = new Map<>(2);
    assertEquals(2, m.capacity);
    assertNull(m.insert(1, 2));
    assertNull(m.insert(2, 4));
    assertEquals(4, m.capacity);
    assertNull(m.insert(3, null));
    assertEquals(4, m.capacity);
    assertNull(m.insert(9, null));
    assertEquals(8, m.capacity);
    assertEquals(4, m.size);
  }

  @Test
  void get() {
    assertNull(m.get(0));
    m.insert(0, 5);
    assertEquals(5, m.get(0));
    m = new Map<>(99999);
    assertNull(m.get(0));
  }

  @Test
  void hasKey() {
    assertFalse(m.hasKey(null));
    assertFalse(m.hasKey(9));
    m.insert(9, 5);
    assertTrue(m.hasKey(9));
    assertFalse(m.hasKey(null));
    m.insert(null, null);
    assertTrue(m.hasKey(9));
    assertTrue(m.hasKey(null));
  }
}
