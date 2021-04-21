package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

  private DoublyLinkedList<Integer> list;

  @BeforeEach
  public void setUp() {
    list = new DoublyLinkedList<>();
  }

  @Test
  void iterator() {
    list.add(5);
    list.add(1);
    list.add(0);
    list.add(-1);
    list.add(-9);
    list.add(2);
    int[] targets = new int[] {5, 1, 0, -1, -9, 2};
    int i = 0;
    for (int item : list) {
      assertEquals(targets[i], item);
      ++i;
    }
    for (ListIterator<Integer> iterator = list.listIterator(); iterator.hasNext(); ) {
      int newItem = -iterator.next();
      iterator.set(newItem);
      if (newItem < 0) {
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
      }
    }
    assertEquals(3, list.size());
    assertEquals(0, list.head.val);
    assertEquals(1, list.get(1));
    assertEquals(9, list.tail.val);
    ListIterator<Integer> iterator = list.listIterator();
    while (iterator.hasNext()) {
      iterator.next();
    }
    assertEquals(3, iterator.nextIndex());
    assertTrue(iterator.hasPrevious());
    assertEquals(1, iterator.previous());
    assertEquals(2, iterator.nextIndex());
    assertTrue(iterator.hasNext());
    assertEquals(9, iterator.next());
    assertEquals(3, iterator.nextIndex());
    assertFalse(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    assertEquals(1, iterator.previous());
    assertEquals(2, iterator.nextIndex());
    assertTrue(iterator.hasPrevious());
    assertEquals(0, iterator.previous());
    assertEquals(1, iterator.nextIndex());
    assertFalse(iterator.hasPrevious());
    assertTrue(iterator.hasNext());
    assertEquals(1, iterator.next());
    assertEquals(2, iterator.nextIndex());
    assertTrue(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    assertEquals(9, iterator.next());
    assertEquals(3, iterator.nextIndex());
    iterator.remove();
    assertEquals(2, iterator.nextIndex());
    assertEquals(0, list.head.val);
    assertEquals(1, list.tail.val);
    assertThrows(IllegalStateException.class, () -> iterator.set(4));
    assertEquals(2, list.size()); // 0, 1, current was deleted
    assertFalse(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    assertEquals(1, iterator.previous()); // 0, 1 (iterator)
    assertEquals(2, iterator.nextIndex());
    iterator.set(5); // 0, 5 (iterator)
    assertEquals(5, list.tail.val);
    iterator.remove(); // 0, current was deleted (iterator)
    assertEquals(1, list.size());
    assertEquals(0, list.head.val);
    assertEquals(0, list.tail.val);
    assertNotNull(list.head);
    assertNull(list.head.next);
    iterator.add(2); // 0, 2 (iterator)
    assertEquals(2, list.size());
    assertEquals(0, list.head.val);
    assertEquals(2, list.tail.val);
    assertFalse(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    assertEquals(0, iterator.previous()); // 0 (iterator), 2
    assertFalse(iterator.hasPrevious());
    assertTrue(iterator.hasNext());
    assertEquals(2, iterator.next()); // 0, 2 (iterator)
    assertFalse(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    iterator.add(-1); // 0, 2 (iterator thinks no next), -1
    assertEquals(3, list.size());
    assertEquals(-1, list.tail.val);
    assertEquals(2, list.get(1));
    assertEquals(0, list.head.val);
    assertFalse(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    assertEquals(0, iterator.previous());
    assertFalse(iterator.hasPrevious());
    assertTrue(iterator.hasNext());
    assertEquals(2, iterator.next());
    assertTrue(iterator.hasPrevious());
    assertTrue(iterator.hasNext());
    assertEquals(-1, iterator.next());
    assertTrue(iterator.hasPrevious());
    assertFalse(iterator.hasNext());
  }

  @Test
  void toArray() {
    list.add(5);
    list.add(1);
    list.add(0);
    list.add(0);
    list.add(-9);
    list.add(2);
    Object[] array = new Object[] {5, 1, 0, 0, -9, 2};
    assertArrayEquals(array, list.toArray());
    DoublyLinkedList<Character> chars = new DoublyLinkedList<>();
    chars.add('t');
    chars.add('e');
    chars.add('s');
    chars.add('t');
    chars.add('i');
    chars.add('n');
    chars.add('g');
    Character[] a = new Character[] {'t', 'e', 's', 't', 'i', 'n', 'g'};
    Character[] b = new Character[7];
    chars.toArray(b);
    assertArrayEquals(a, b);
  }

  @Test
  void add() {
    assertTrue(list.add(55));
    assertEquals(1, list.size());
    assertEquals(55, list.head.val);
    assertEquals(55, list.tail.val);
    assertTrue(list.add(-1));
    assertEquals(2, list.size());
    assertEquals(55, list.head.val);
    assertEquals(-1, list.tail.val);
    assertTrue(list.add(7)); // 55, -1, 7
    assertEquals(3, list.size());
    assertEquals(55, list.head.val);
    assertEquals(7, list.tail.val);
    list.add(1, null);
    Integer[] target = new Integer[] {55, null, -1, 7};
    int i = 0;
    for (Integer item : list) {
      assertEquals(target[i], item);
      ++i;
    }
    list.add(0, -2);
    target = new Integer[] {-2, 55, null, -1, 7};
    i = 0;
    for (Integer item : list) {
      assertEquals(target[i], item);
      ++i;
    }
    assertEquals(5, list.size());
    assertEquals(-2, list.head.val);
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(6, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
    list.add(5, 0);
    target = new Integer[] {-2, 55, null, -1, 7, 0};
    i = 0;
    for (Integer item : list) {
      assertEquals(target[i], item);
      ++i;
    }
    assertEquals(6, list.size());
    assertEquals(0, list.tail.val);
  }

  @Test
  void remove() {
    assertFalse(list.remove(Integer.valueOf(3)));
    list.add(null);
    list.add(null);
    assertTrue(list.remove(null));
    assertEquals(1, list.size());
    assertNull(list.tail.val);
    list.add(0, 1);
    list.add(0, 3); // 3, 1, null
    assertEquals(3, list.size());
    assertTrue(list.remove(Integer.valueOf(3))); // 1, null
    assertEquals(2, list.size());
    assertEquals(1, list.head.val);
    assertNull(list.tail.val);
    assertFalse(list.remove(Integer.valueOf(3)));
    list.add(9);
    list.add(4);
    assertTrue(list.remove(Integer.valueOf(4)));
    assertEquals(3, list.size());
    assertEquals(9, list.tail.val);
    assertEquals(1, list.remove(0)); // null, 9
    assertEquals(2, list.size());
    assertNull(list.head.val);
    assertEquals(9, list.tail.val); // null, 9
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertEquals(9, list.remove(1)); // null,
    assertNull(list.head.val);
  }

  @Test
  void addAll() {
    // array needs to implement Collection
    ArrayList<Integer> array = new ArrayList<>(5);
    for (int i = 0; i < 5; ++i) {
      array.add(i);
    }
    list.addAll(array);
    assertEquals(5, list.size());
    assertEquals(0, list.head.val);
    assertEquals(4, list.tail.val);
    list.remove(list.size - 1);
    list.remove(list.size - 1);
    list.addAll(array);
    assertEquals(8, list.size());
    assertEquals(0, list.head.val);
    assertEquals(4, list.tail.val);
    list.remove(list.size - 1);
    list.remove(list.size - 1);
    list.remove(list.size - 1);
    list.remove(list.size - 1);
    list.remove(list.size - 1);
    list.remove(list.size - 1);
    assertEquals(2, list.size());
  }

  @Test
  void removeAll() {
    // array must implement Collection
    ArrayList<Integer> array = new ArrayList<>(5);
    for (int i = 0; i < 5; ++i) {
      array.add(i);
    }
    assertFalse(list.removeAll(array));
    list.add(3);
    list.add(7);
    list.add(2);
    assertTrue(list.removeAll(array));
    assertEquals(1, list.size());
    assertEquals(7, list.tail.val);
    assertEquals(7, list.head.val);
  }

  @Test
  void retainAll() {
    // array must implement Collection
    ArrayList<Integer> array = new ArrayList<>(5);
    for (int i = 0; i < 5; ++i) {
      array.add(i);
    }
    assertFalse(list.retainAll(array));
    list.add(7);
    list.add(3);
    list.add(null);
    list.add(-1);
    list.add(2);
    assertTrue(list.retainAll(array));
    assertEquals(2, list.size());
    assertEquals(3, list.head.val);
    assertEquals(2, list.tail.val);
  }

  @Test
  void contains() {
    list.add(3);
    list.add(2);
    list.add(-9);
    assertTrue(list.contains(3));
    assertTrue(list.contains(2));
    assertTrue(list.contains(-9));
    assertFalse(list.contains(null));
    assertFalse(list.contains(-1));
    assertFalse(list.contains(12));
    list.add(0, -1);
    list.add(null);
    assertTrue(list.contains(3));
    assertTrue(list.contains(2));
    assertTrue(list.contains(-9));
    assertTrue(list.contains(null));
    assertTrue(list.contains(-1));
    assertFalse(list.contains(12));
  }

  @Test
  void containsAll() {
    // array must implement Collection
    ArrayList<Integer> array = new ArrayList<>(5);
    for (int i = 0; i < 5; ++i) {
      array.add(i);
    }
    assertFalse(list.containsAll(array));
    list.add(7);
    list.add(3);
    list.add(null);
    list.add(-1);
    list.add(2);
    assertFalse(list.containsAll(array));
    list.add(0, 0);
    assertFalse(list.containsAll(array));
    list.remove(list.size - 1);
    assertFalse(list.containsAll(array));
    list.add(1);
    assertFalse(list.containsAll(array));
    list.add(4);
    assertFalse(list.containsAll(array));
    list.add(2); // 0, 7, 3, null, -1, 1, 4, 2
    assertTrue(list.containsAll(array));
  }

  @Test
  void clear() {
    list.add(4);
    assertFalse(list.isEmpty());
    list.clear();
    assertEquals(list.size, 0);
    list.clear(0, 0);
    list.add(3);
    list.add(0, 5); // 5, 3
    list.clear(1, 2); // 5,
    assertEquals("[5]", list.toString());
    assertEquals(5, list.head.val);
    assertEquals(5, list.tail.val);
    list.add(null);
    list.add(7);
    list.add(9);
    list.add(0);
    list.clear(0, 3); // 9, 0
    assertEquals(9, list.head.val);
  }

  @Test
  void size() {
    assertEquals(0, list.size());
    list.add(9);
    assertEquals(1, list.size());
    list.add(2);
    assertEquals(2, list.size());
    list.remove(Integer.valueOf(9));
    assertEquals(1, list.size());
    list.remove(list.size - 1);
    assertEquals(0, list.size());
    for (int i = 0; i < 10; ++i) {
      list.add(0, null);
      assertEquals(i + 1, list.size());
    }
    for (int i = 10; i > 0; --i) {
      assertNull(list.head.val);
      list.remove(0);
      assertEquals(i - 1, list.size());
    }
  }

  @Test
  void isEmpty() {
    assertTrue(list.isEmpty());
    list.add(0, 2);
    assertFalse(list.isEmpty());
    list.remove(list.size - 1);
    assertTrue(list.isEmpty());
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  void get() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    list.add(3);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertEquals(3, list.get(0));
    list.add(5);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    assertEquals(3, list.get(0));
    assertEquals(5, list.get(1));
    list.remove(0);
    list.add(0, null); // null, 5
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    assertNull(list.get(0));
    assertEquals(5, list.get(1));
  }

  @Test
  void set() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 4));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 4));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 4));
    list.add(0, 2);
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 4));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 4));
    assertEquals(2, list.set(0, 4)); // setting at head & tail
    assertEquals(list.tail.val, 4);
    assertEquals(list.head.val, 4);
    list.add(8);
    list.add(16);
    assertEquals("[4, 8, 16]", list.toString());
    assertEquals(8, list.set(1, null));
    assertEquals("[4, null, 16]", list.toString());
    assertEquals(3, list.size());
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, -1));
    assertEquals(16, list.set(2, -1)); // setting at tail
    assertEquals("[4, null, -1]", list.toString());
    assertEquals(4, list.head.val);
    assertEquals(-1, list.tail.val);
    assertNull(list.set(1, null)); // it was already null
    assertEquals("[4, null, -1]", list.toString());
    assertEquals(3, list.size());
  }

  @Test
  void indexOf() {
    assertThrows(NoSuchElementException.class, () -> list.indexOf(5));
    assertThrows(NoSuchElementException.class, () -> list.indexOf(null));
    list = new DoublyLinkedList<>(3, -1, 2, 4, 4, null, 2, null, 9);
    // elements: 3, -1, 2, 4, 4, null, 2, null, 9
    // indices:  0,  1, 2, 3, 4, 5,    6, 7,    8
    assertEquals(1, list.indexOf(-1));
    assertEquals(0, list.indexOf(3));
    assertEquals(3, list.indexOf(4));
    assertEquals(8, list.indexOf(9));
    assertEquals(5, list.indexOf(null));
    assertEquals(2, list.indexOf(2));
    assertThrows(NoSuchElementException.class, () -> list.indexOf(1));
    assertThrows(NoSuchElementException.class, () -> list.indexOf(0));
    assertEquals(2, list.set(6, 0));
    //                                 |
    //                         changed v
    // elements: 3, -1, 2, 4, 4, null, 0, null, 9
    // indices:  0,  1, 2, 3, 4, 5,    6, 7,    8
    assertEquals(6, list.indexOf(0));
  }

  @Test
  void lastIndexOf() {
    assertEquals(-1, list.lastIndexOf(5));
    assertEquals(-1, list.lastIndexOf(null));
    list = new DoublyLinkedList<>(3, -1, 2, 4, 4, null, 2, null, 9);
    // elements: 3, -1, 2, 4, 4, null, 2, null, 9
    // indices:  0,  1, 2, 3, 4, 5,    6, 7,    8
    assertEquals(1, list.lastIndexOf(-1));
    assertEquals(0, list.lastIndexOf(3));
    assertEquals(4, list.lastIndexOf(4));
    assertEquals(8, list.lastIndexOf(9));
    assertEquals(7, list.lastIndexOf(null));
    assertEquals(6, list.lastIndexOf(2));
    assertEquals(-1, list.lastIndexOf(1));
    assertEquals(-1, list.lastIndexOf(0));
    assertEquals(2, list.set(6, 0));
    //                                 |
    //                         changed v
    // elements: 3, -1, 2, 4, 4, null, 0, null, 9
    // indices:  0,  1, 2, 3, 4, 5,    6, 7,    8
    assertEquals(6, list.lastIndexOf(0));
    assertEquals(9, list.set(8, 0));
    //                                          |
    //                                  changed v
    // elements: 3, -1, 2, 4, 4, null, 0, null, 0
    // indices:  0,  1, 2, 3, 4, 5,    6, 7,    8
    assertEquals(8, list.lastIndexOf(0));
  }

  @Test
  void subList() {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    for (int i = 0; i < 10; ++i) {
      list.add(i);
    }
    assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
    assertEquals(10, list.size());
    assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 5));
    assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, -2));
    assertThrows(IndexOutOfBoundsException.class, () -> list.subList(10, -2));
    assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-5, -5));
    assertDoesNotThrow(() -> list.subList(3, 10));
    assertDoesNotThrow(() -> list.subList(10, 10));
    assertDoesNotThrow(() -> list.subList(0, 10));
    assertDoesNotThrow(() -> list.subList(0, 0));
    DoublyLinkedList<Integer> sublist = list.subList(0, 0);
    assertEquals("[]", sublist.toString());
    assertEquals(0, sublist.size());
    sublist = list.subList(3, 5);
    assertEquals("[3, 4]", sublist.toString());
    assertEquals(2, sublist.size());
    assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
    assertEquals(10, list.size());
    sublist.clear();
    assertEquals("[]", sublist.toString());
    assertEquals(0, sublist.size());
    assertEquals("[0, 1, 2, 5, 6, 7, 8, 9]", list.toString());
    assertEquals(8, list.size());
    sublist.add(0, null);
    assertEquals("[null]", sublist.toString());
    assertNull(sublist.tail.val);
    assertNull(sublist.head.val);
    assertEquals(1, sublist.size());
    assertEquals("[0, 1, 2, null, 5, 6, 7, 8, 9]", list.toString());
    assertEquals(9, list.size());
    sublist = list.subList(2, 9).subList(0, 7); // 2, null, ..., 9
    sublist.add(4);
    assertEquals("[2, null, 5, 6, 7, 8, 9, 4]", sublist.toString());
    assertEquals(8, sublist.size());
    assertEquals("[0, 1, 2, null, 5, 6, 7, 8, 9, 4]", list.toString());
    assertEquals(10, list.size());
    DoublyLinkedList<Integer> subsub = sublist.subList(0, 4);
    assertEquals("[2, null, 5, 6]", subsub.toString());
    assertEquals(4, subsub.size());
    subsub.subList(2, 4).clear();
    assertEquals("[2, null]", subsub.toString());
    assertEquals(2, subsub.size());
    assertEquals("[2, null, 7, 8, 9, 4]", sublist.toString());
    assertEquals(6, sublist.size());
    assertEquals("[0, 1, 2, null, 7, 8, 9, 4]", list.toString());
    assertEquals(8, list.size());
    sublist = list.subList(0, 5);
    assertEquals(5, sublist.size());
    assertEquals("[0, 1, 2, null, 7]", sublist.toString());
    assertEquals(0, sublist.remove(0));
    assertEquals(1, sublist.head.val);
    assertEquals("[1, 2, null, 7]", sublist.toString());
    assertEquals(4, sublist.size());
    assertEquals("[1, 2, null, 7, 8, 9, 4]", list.toString());
    assertEquals(7, list.size());
    sublist.remove(sublist.size - 1);
    assertNull(sublist.tail.val);
    assertEquals("[1, 2, null]", sublist.toString());
    assertEquals(3, sublist.size());
    assertEquals("[1, 2, null, 8, 9, 4]", list.toString());
    assertEquals(6, list.size());
    sublist.remove(null);
    assertEquals("[1, 2]", sublist.toString());
    assertEquals(2, sublist.size());
    assertEquals("[1, 2, 8, 9, 4]", list.toString());
    assertEquals(5, list.size());
  }

  @Test
  void testToString() {
    assertEquals("[]", list.toString());
    assertTrue(list.add(null));
    assertEquals("[null]", list.toString());
    for (int i = 1; i <= 10; ++i) {
      list.add(i);
    }
    assertEquals("[null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());
    list.add(null);
    assertEquals("[null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null]", list.toString());
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void constructors() {
    DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
    assertTrue(list1.isEmpty());
    list1.add(2);
    assertEquals(1, list1.size());
    assertEquals(2, list1.tail.val);
    assertEquals(2, list1.head.val);
    assertEquals("[2]", list1.toString());
    DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>(null, 7, 2, 9);
    assertEquals("[null, 7, 2, 9]", list2.toString());
    assertEquals(4, list2.size());
    assertNull(list2.head.val);
    assertEquals(9, list2.tail.val);
  }
}
