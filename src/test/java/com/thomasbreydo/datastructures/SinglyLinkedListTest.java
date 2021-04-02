package com.thomasbreydo.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ListIterator;
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
    assertThrows(NoSuchElementException.class, list::topBack);
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
    assertThrows(NoSuchElementException.class, list::topBack);
  }

  @Test
  void popBack() {
    assertThrows(NoSuchElementException.class, list::popBack);
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
    assertThrows(NoSuchElementException.class, list::topFront);
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
    assertThrows(NoSuchElementException.class, list::popFront);
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
    assertThrows(NoSuchElementException.class, list::popFront);
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
    list.pushBack(-1);
    list.pushBack(-9);
    list.pushBack(2);
    int[] targets = new int[] {5, 1, 0, -1, -9, 2};
    int i = 0;
    for (int item : list) {
      assertEquals(targets[i], item);
      ++i;
    }
    for (ListIterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
      int newItem = -iterator.next();
      iterator.set(newItem);
      if (newItem < 0) {
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
      }
    }
    assertEquals(3, list.size());
    assertEquals(0, list.topFront());
    assertEquals(1, list.get(1));
    assertEquals(9, list.topBack());
    ListIterator<Integer> iterator = list.iterator();
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
    assertEquals(0, list.topFront());
    assertEquals(1, list.topBack());
    assertThrows(IllegalStateException.class, () -> iterator.set(4));
    assertEquals(2, list.size()); // 0, 1, current was deleted
    assertFalse(iterator.hasNext());
    assertTrue(iterator.hasPrevious());
    assertEquals(1, iterator.previous()); // 0, 1 (iterator)
    assertEquals(2, iterator.nextIndex());
    iterator.set(5); // 0, 5 (iterator)
    assertEquals(5, list.topBack());
    iterator.remove(); // 0, current was deleted
    assertEquals(1, list.size());
    assertEquals(0, list.topFront());
    assertEquals(0, list.topBack());
    assertNotNull(list.head);
    assertNull(list.head.next);
    iterator.add(2); // 0, 2 (iterator)
    assertEquals(2, list.size());
    assertEquals(0, list.topFront());
    assertEquals(2, list.topBack());
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
    assertEquals(-1, list.topBack());
    assertEquals(2, list.get(1));
    assertEquals(0, list.topFront());
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
    list.pushBack(5);
    list.pushBack(1);
    list.pushBack(0);
    list.pushBack(0);
    list.pushBack(-9);
    list.pushBack(2);
    Object[] array = new Object[] {5, 1, 0, 0, -9, 2};
    assertArrayEquals(array, list.toArray());
    SinglyLinkedList<Character> chars = new SinglyLinkedList<>();
    chars.pushBack('t');
    chars.pushBack('e');
    chars.pushBack('s');
    chars.pushBack('t');
    chars.pushBack('i');
    chars.pushBack('n');
    chars.pushBack('g');
    Character[] a = new Character[] {'t', 'e', 's', 't', 'i', 'n', 'g'};
    Character[] b = new Character[7];
    chars.toArray(b);
    assertArrayEquals(a, b);
  }

  @Test
  void add() {
    assertTrue(list.add(55));
    assertEquals(1, list.size());
    assertEquals(55, list.topFront());
    assertEquals(55, list.topBack());
    assertTrue(list.add(-1));
    assertEquals(2, list.size());
    assertEquals(55, list.topFront());
    assertEquals(-1, list.topBack());
    assertTrue(list.add(7)); // 55, -1, 7
    assertEquals(3, list.size());
    assertEquals(55, list.topFront());
    assertEquals(7, list.topBack());
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
    assertEquals(-2, list.head.item);
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
    assertEquals(0, list.tail.item);
  }

  @Test
  void remove() {
    assertFalse(list.remove(Integer.valueOf(3)));
    list.pushBack(null);
    list.pushBack(null);
    assertTrue(list.remove(null));
    assertEquals(1, list.size());
    assertNull(list.topBack());
    list.pushFront(1);
    list.pushFront(3); // 3, 1, null
    assertEquals(3, list.size());
    assertTrue(list.remove(Integer.valueOf(3))); // 1, null
    assertEquals(2, list.size());
    assertEquals(1, list.topFront());
    assertNull(list.topBack());
    assertFalse(list.remove(Integer.valueOf(3)));
    list.pushBack(9);
    list.pushBack(4);
    assertTrue(list.remove(Integer.valueOf(4)));
    assertEquals(3, list.size());
    assertEquals(9, list.topBack());
    assertEquals(1, list.remove(0)); // null, 9
    assertEquals(2, list.size());
    assertNull(list.head.item);
    assertEquals(9, list.topBack()); // null, 9
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertEquals(9, list.remove(1)); // null,
    assertNull(list.head.item);
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
    assertEquals(0, list.topFront());
    assertEquals(4, list.topBack());
    list.popBack();
    list.popBack();
    list.addAll(array);
    assertEquals(8, list.size());
    assertEquals(0, list.topFront());
    assertEquals(4, list.topBack());
    list.popBack();
    list.popBack();
    list.popBack();
    list.popBack();
    list.popBack();
    list.popBack();
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
    list.pushBack(3);
    list.pushBack(7);
    list.pushBack(2);
    assertTrue(list.removeAll(array));
    assertEquals(1, list.size());
    assertEquals(7, list.topBack());
    assertEquals(7, list.topFront());
  }

  @Test
  void retainAll() {
    // array must implement Collection
    ArrayList<Integer> array = new ArrayList<>(5);
    for (int i = 0; i < 5; ++i) {
      array.add(i);
    }
    assertFalse(list.retainAll(array));
    list.pushBack(7);
    list.pushBack(3);
    list.pushBack(null);
    list.pushBack(-1);
    list.pushBack(2);
    assertTrue(list.retainAll(array));
    assertEquals(2, list.size());
    assertEquals(3, list.topFront());
    assertEquals(2, list.topBack());
  }

  @Test
  void contains() {
    list.pushBack(3);
    list.pushBack(2);
    list.pushBack(-9);
    assertTrue(list.contains(3));
    assertTrue(list.contains(2));
    assertTrue(list.contains(-9));
    assertFalse(list.contains(null));
    assertFalse(list.contains(-1));
    assertFalse(list.contains(12));
    list.pushFront(-1);
    list.pushBack(null);
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
    list.pushBack(7);
    list.pushBack(3);
    list.pushBack(null);
    list.pushBack(-1);
    list.pushBack(2);
    assertFalse(list.containsAll(array));
    list.pushFront(0);
    assertFalse(list.containsAll(array));
    list.popBack();
    assertFalse(list.containsAll(array));
    list.pushBack(1);
    assertFalse(list.containsAll(array));
    list.pushBack(4);
    assertFalse(list.containsAll(array));
    list.pushBack(2); // 0, 7, 3, null, -1, 1, 4, 2
    assertTrue(list.containsAll(array));
  }

  @Test
  void clear() {
    list.pushBack(4);
    assertFalse(list.isEmpty());
    list.clear();
    assertEquals(list.size, 0);
    list.clear(0, 0);
    list.pushBack(3);
    list.pushFront(5); // 5, 3
    list.clear(1, 2); // 5,
    assertEquals("[5]", list.toString());
    assertEquals(5, list.head.item);
    assertEquals(5, list.tail.item);
    list.pushBack(null);
    list.pushBack(7);
    list.pushBack(9);
    list.pushBack(0);
    list.clear(0, 3); // 9, 0
    assertEquals(9, list.head.item);
  }

  @Test
  void size() {
    assertEquals(0, list.size());
    list.pushBack(9);
    assertEquals(1, list.size());
    list.pushBack(2);
    assertEquals(2, list.size());
    list.remove(Integer.valueOf(9));
    assertEquals(1, list.size());
    list.popBack();
    assertEquals(0, list.size());
    for (int i = 0; i < 10; ++i) {
      list.pushFront(null);
      assertEquals(i + 1, list.size());
    }
    for (int i = 10; i > 0; --i) {
      assertNull(list.topFront());
      list.popFront();
      assertEquals(i - 1, list.size());
    }
  }

  @Test
  void isEmpty() {
    assertTrue(list.isEmpty());
    list.pushFront(2);
    assertFalse(list.isEmpty());
    list.popBack();
    assertTrue(list.isEmpty());
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  void get() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    list.pushBack(3);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertEquals(3, list.get(0));
    list.pushBack(5);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    assertEquals(3, list.get(0));
    assertEquals(5, list.get(1));
    list.popFront();
    list.pushFront(null); // null, 5
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
    list.pushFront(2);
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 4));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 4));
    assertEquals(2, list.set(0, 4)); // setting at head & tail
    assertEquals(list.topBack(), 4);
    assertEquals(list.topFront(), 4);
    list.pushBack(8);
    list.pushBack(16);
    assertEquals("[4, 8, 16]", list.toString());
    assertEquals(8, list.set(1, null));
    assertEquals("[4, null, 16]", list.toString());
    assertEquals(3, list.size());
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, -1));
    assertEquals(16, list.set(2, -1)); // setting at tail
    assertEquals("[4, null, -1]", list.toString());
    assertEquals(4, list.topFront());
    assertEquals(-1, list.topBack());
    assertNull(list.set(1, null)); // it was already null
    assertEquals("[4, null, -1]", list.toString());
    assertEquals(3, list.size());
  }

  @Test
  void indexOf() {
    assertThrows(NoSuchElementException.class, () -> list.indexOf(5));
    assertThrows(NoSuchElementException.class, () -> list.indexOf(null));
    list = new SinglyLinkedList<>(3, -1, 2, 4, 4, null, 2, null, 9);
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
    assertThrows(NoSuchElementException.class, () -> list.lastIndexOf(5));
    assertThrows(NoSuchElementException.class, () -> list.lastIndexOf(null));
    list = new SinglyLinkedList<>(3, -1, 2, 4, 4, null, 2, null, 9);
    // elements: 3, -1, 2, 4, 4, null, 2, null, 9
    // indices:  0,  1, 2, 3, 4, 5,    6, 7,    8
    assertEquals(1, list.lastIndexOf(-1));
    assertEquals(0, list.lastIndexOf(3));
    assertEquals(4, list.lastIndexOf(4));
    assertEquals(8, list.lastIndexOf(9));
    assertEquals(7, list.lastIndexOf(null));
    assertEquals(6, list.lastIndexOf(2));
    assertThrows(NoSuchElementException.class, () -> list.lastIndexOf(1));
    assertThrows(NoSuchElementException.class, () -> list.lastIndexOf(0));
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
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
    SinglyLinkedList<Integer> sublist = list.subList(0, 0);
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
    assertNull(sublist.topBack());
    assertNull(sublist.topFront());
    assertEquals(1, sublist.size());
    assertEquals("[0, 1, 2, null, 5, 6, 7, 8, 9]", list.toString());
    assertEquals(9, list.size());
    sublist = list.subList(2, 9).subList(0, 7); // 2, null, ..., 9
    sublist.pushBack(4);
    assertEquals("[2, null, 5, 6, 7, 8, 9, 4]", sublist.toString());
    assertEquals(8, sublist.size());
    assertEquals("[0, 1, 2, null, 5, 6, 7, 8, 9, 4]", list.toString());
    assertEquals(10, list.size());
    SinglyLinkedList<Integer> subsub = sublist.subList(0, 4);
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
    assertEquals(1, sublist.topFront());
    assertEquals("[1, 2, null, 7]", sublist.toString());
    assertEquals(4, sublist.size());
    assertEquals("[1, 2, null, 7, 8, 9, 4]", list.toString());
    assertEquals(7, list.size());
    sublist.popBack();
    assertNull(sublist.topBack());
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
    SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
    assertTrue(list1.isEmpty());
    list1.pushBack(2);
    assertEquals(1, list1.size());
    assertEquals(2, list1.topBack());
    assertEquals(2, list1.topFront());
    assertEquals("[2]", list1.toString());
    SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>(null, 7, 2, 9);
    assertEquals("[null, 7, 2, 9]", list2.toString());
    assertEquals(4, list2.size());
    assertNull(list2.head.item);
    assertEquals(9, list2.tail.item);
  }
}
