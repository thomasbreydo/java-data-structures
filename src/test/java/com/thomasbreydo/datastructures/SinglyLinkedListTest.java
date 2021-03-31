package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

  private SinglyLinkedList<Integer> list;

  @BeforeEach
  public void setUp() {
    list = new SinglyLinkedList<>();
  }

  @Test
  void topBack() {
    assertThrows(
        NoSuchElementException.class,
        () -> {
          list.topBack();
        });
    list.pushFront(3);
    assertEquals(3, list.topBack());
    list.pushFront(5);
    assertEquals(3, list.topBack());
    list.popBack();
    assertEquals(5, list.topBack());
    list.pushBack(10);
    assertEquals(10, list.topBack());
    list.popBack();
    list.popBack();
    assertThrows(
        NoSuchElementException.class,
        () -> {
          list.topBack();
        });
  }

  @Test
  void popBack() {
    assertThrows(
        NoSuchElementException.class,
        () -> {
          list.popBack();
        });
    list.pushFront(21);
    list.popBack();
    assertTrue(list.isEmpty());
    list.pushBack(16);
    list.pushBack(-52);
    list.popBack();
    assertEquals(16, list.topBack());
    assertEquals(1, list.size());
    list.pushBack(5);
    list.pushBack(9);
    list.popBack();
    assertEquals(2, list.size());
    assertEquals(5, list.topBack());
    list.popBack();
    list.popBack();
    assertTrue(list.isEmpty());
  }

  @Test
  void pushBack() {
    list.pushBack(3);
    list.pushBack(7);
    list.pushBack(0);
    list.pushBack(0);
    list.pushBack(-2);
    assertEquals(-2, list.topBack());
    assertEquals(3, list.topFront());
    assertEquals(5, list.size());
    list.popBack();
    assertEquals(0, list.topBack());
    list.popFront();
    assertEquals(7, list.topFront());
  }

  @Test
  void topFront() {
    assertThrows(
        NoSuchElementException.class,
        () -> {
          list.topFront();
        });
    list.pushFront(5);
    assertEquals(5, list.topFront());
    list.pushFront(6);
    assertEquals(6, list.topFront());
    list.popBack();
    assertEquals(6, list.topFront());
    list.pushFront(8);
    list.pushFront(7);
    assertEquals(7, list.topFront());
    list.popFront();
    list.popFront();
    assertEquals(6, list.topFront());
    list.popBack();
    assertTrue(list.isEmpty());
  }

  @Test
  void popFront() {
    assertThrows(
        NoSuchElementException.class,
        () -> {
          list.popFront();
        });
    list.pushFront(5);
    list.pushFront(0);
    list.pushFront(0);
    list.pushFront(-1);
    assertEquals(4, list.size());
    list.popFront();
    assertEquals(3, list.size());
    assertEquals(0, list.topFront());
    assertEquals(5, list.topBack());
    list.popFront();
    assertEquals(2, list.size());
    assertEquals(0, list.topFront());
    assertEquals(5, list.topBack());
    list.popFront();
    assertEquals(1, list.size());
    assertEquals(5, list.topFront());
    assertEquals(5, list.topBack());
    list.popFront();
    assertTrue(list.isEmpty());
    assertThrows(
        NoSuchElementException.class,
        () -> {
          list.popFront();
        });
  }

  @Test
  void pushFront() {
    list.pushFront(4);
    assertEquals(1, list.size());
    assertEquals(4, list.topFront());
    assertEquals(4, list.topBack());
    list.pushFront(9);
    assertEquals(2, list.size());
    assertEquals(9, list.topFront());
    assertEquals(4, list.topBack());
    list.pushFront(-1);
    assertEquals(3, list.size());
    assertEquals(-1, list.topFront());
    assertEquals(4, list.topBack());
    list.pushFront(5);
    assertEquals(4, list.size());
    assertEquals(5, list.topFront());
    assertEquals(4, list.topBack());
  }

  @Test
  void iterator() {
    list.pushBack(5);
    list.pushBack(1);
    list.pushBack(0);
    list.pushBack(0);
    list.pushBack(-9);
    list.pushBack(2);
    int[] targets = new int[] {5, 1, 0, 0, -9, 2};
    int i = 0;
    for (int item : list) {
      assertEquals(targets[i], item);
      ++i;
    }
  }

  @Test
  void toArray() {
    list.pushBack(5);
    list.pushBack(1);
    list.pushBack(0);
    list.pushBack(0);
    list.pushBack(-9);
    list.pushBack(2);
    Object[] array = new Object[] {5, 1, 0, 0, -9, 2};
    assertArrayEquals(array, list.toArray());
  }
}
