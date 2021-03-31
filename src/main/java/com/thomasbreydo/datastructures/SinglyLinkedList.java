package com.thomasbreydo.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements Iterable<E> {
  Node<E> head = null;
  int length = 0;

  public SinglyLinkedList() {}

  /**
   * Initialize this linked list with the elements in {@code iterable}.
   *
   * @param iterable the iterable from which to copy items.
   * @param reverse if true, allow for O(n) instantiation.
   */
  public SinglyLinkedList(Iterable<E> iterable, boolean reverse) {
    if (reverse) {
      // O(n^2)
      for (E item : iterable) {
        pushBack(item);
      }
    } else {
      for (E item : iterable) {
        // O(n)
        pushFront(item);
      }
    }
  }

  /** @return the first node. */
  private Node<E> getHead() {
    // O(1)
    return head;
  }

  /**
   * @throws NoSuchElementException if this list has fewer than 2 elements.
   * @return the second-to-last node.
   */
  private Node<E> getPenultimate() {
    // O(n)
    if (length < 2) {
      throw new NoSuchElementException();
    }
    Node<E> prev = head;
    Node<E> current = head.next;
    while (current.next != null) {
      prev = current;
      current = current.next;
    }
    return prev;
  }

  /** @return the last node. */
  private Node<E> getTail() {
    // O(n)
    if (length == 0) {
      return null;
    }
    if (length == 1) {
      return head;
    }
    Node<E> current = head;
    while (current.next != null) {
      current = current.next;
    }
    return current;
  }

  /** @return the number of elements in this linked list */
  public int size() {
    return length;
  }

  /** @return true if this list is empty. */
  public boolean isEmpty() {
    // O(1)
    return length == 0;
  }

  /**
   * @throws NoSuchElementException if this linked list is empty.
   * @return the last element.
   */
  public E topBack() {
    // O(n)
    if (length == 0) {
      throw new NoSuchElementException();
    }
    return getTail().item;
  }

  /**
   * Delete the last element.
   *
   * @throws NoSuchElementException if this linked list is empty.
   */
  public void popBack() {
    // O(n)
    if (length == 0) {
      throw new NoSuchElementException();
    }
    if (length == 1) {
      head = null;
    } else {
      getPenultimate().next = null;
    }
    --length;
  }

  /**
   * Add {@code item} to the end of this linked list.
   *
   * @param item the item to add to this linked list.
   */
  public void pushBack(E item) {
    // O(n)
    if (length == 0) {
      pushFront(item);
    } else {
      getTail().next = new Node<>(item, null);
      ++length;
    }
  }

  /**
   * @return the first element.
   * @throws NoSuchElementException if this linked list is empty.
   */
  public E topFront() {
    // O(1)
    if (length == 0) {
      throw new NoSuchElementException();
    }
    return head.item;
  }

  /**
   * Delete the first element.
   *
   * @throws NoSuchElementException if this linked list is empty.
   */
  public void popFront() {
    // O(1)
    if (length == 0) {
      throw new NoSuchElementException();
    }
    --length;
    head = head.next;
  }

  /**
   * Add {@code item} to the start of this linked list.
   *
   * @param item the item to add to this linked list.
   */
  public void pushFront(E item) {
    // O(1)
    ++length;
    head = new Node<>(item, head);
  }

  @Override
  public Iterator<E> iterator() {
    return new SinglyLinkedListIterator<>(this);
  }

  /**
   * Convert this linked list to an array.
   *
   * @return this linked list as an array.
   */
  public Object[] toArray() {
    // O(n)
    Object[] array = new Object[length];
    int i = 0;
    for (E item : this) {
      array[i] = item;
      ++i;
    }
    return array;
  }
}

class SinglyLinkedListIterator<E> implements Iterator<E> {
  private int nRemaining;
  private Node<E> current;

  SinglyLinkedListIterator(SinglyLinkedList<E> list) {
    nRemaining = list.length;
    current = list.head;
  }

  @Override
  public boolean hasNext() {
    return nRemaining > 0;
  }

  @Override
  public E next() {
    --nRemaining;
    E item = current.item;
    current = current.next;
    return item;
  }
}

class Node<E> {
  E item;
  Node<E> next;

  Node(E item, Node<E> next) {
    this.item = item;
    this.next = next;
  }
}
