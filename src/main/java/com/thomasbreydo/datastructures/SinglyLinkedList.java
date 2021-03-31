package com.thomasbreydo.datastructures;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> implements Collection<E> {
  Node<E> head = null;
  Node<E> tail = null;
  int length = 0;

  public SinglyLinkedList() {}

  /**
   * @throws NoSuchElementException if this list has fewer than 2 elements.
   * @return the second-to-last node.
   */
  private Node<E> getPenultimate() {
    // O(length)
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

  /** @return the number of elements in this linked list. */
  @Override
  public int size() {
    // O(1)
    return length;
  }

  /** @return true if this list is empty. */
  @Override
  public boolean isEmpty() {
    // O(1)
    return length == 0;
  }

  /**
   * Check if this linked list contains {@code o}.
   *
   * @param o the object.
   * @return true if this linked list contains {@code o}.
   */
  @Override
  public boolean contains(Object o) {
    // O(length)
    for (E item : this) {
      if (Objects.equals(o, item)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the last element.
   *
   * @throws NoSuchElementException if this linked list is empty.
   * @return the last element.
   */
  public E topBack() {
    // O(1)
    if (length == 0) {
      throw new NoSuchElementException();
    }
    return Objects.requireNonNull(tail).item;
  }

  /**
   * Delete the last element.
   *
   * @throws NoSuchElementException if this linked list is empty.
   */
  public void popBack() {
    // O(length)
    if (length == 0) {
      throw new NoSuchElementException();
    }
    if (length == 1) {
      head = null;
      tail = null;
    } else {
      Node<E> penultimate = getPenultimate();
      penultimate.next = null;
      tail = penultimate;
    }
    --length;
  }

  /**
   * Add {@code item} to the end of this linked list.
   *
   * @param item the item to add to this linked list.
   */
  public void pushBack(E item) {
    // O(1)
    if (length == 0) {
      head = new Node<>(item, null);
      tail = head;
    } else {
      tail.next = new Node<>(item, null);
      tail = tail.next;
    }
    ++length;
  }

  /**
   * Get the first element.
   *
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
    head = head.next;
    --length;
  }

  /**
   * Add {@code item} to the start of this linked list.
   *
   * @param item the item to add to this linked list.
   */
  public void pushFront(E item) {
    // O(1)
    head = new Node<>(item, head);
    if (tail == null) {
      tail = head;
    }
    ++length;
  }

  /** @return an iterator over the elements in this linked list. */
  @Override
  public Iterator<E> iterator() {
    // O(1)
    return new SinglyLinkedListItr<>(this);
  }

  /**
   * Convert this linked list to an array.
   *
   * @return this linked list as an array.
   */
  @Override
  public Object[] toArray() {
    // O(length)
    Object[] array = new Object[length];
    int i = 0;
    for (E item : this) {
      array[i] = item;
      ++i;
    }
    return array;
  }

  /**
   * If {@code a} has enough space to hold the elements in this linked list, copy every element into
   * {@code a}. Otherwise, return an array of type {@code T[]} that contains the elements in this
   * linked list.
   *
   * @param a the array.
   * @param <T> the array's member class.
   * @return {@code a} (if it has enough space) otherwise a new array.
   */
  @Override
  @SuppressWarnings("unchecked cast")
  public <T> T[] toArray(T[] a) {
    // O(length)
    T[] array;
    if (a.length >= length) {
      array = a;
    } else {
      array = (T[]) Array.newInstance(a.getClass(), length);
    }
    int i = 0;
    for (E item : this) {
      array[i] = (T) item;
      ++i;
    }
    return array;
  }

  /**
   * Alias for {@link #pushBack(E e), pushBack}.
   *
   * @param e the element to add.
   * @return true if this linked list was modified.
   */
  @Override
  public boolean add(E e) {
    // O(1)
    pushBack(e);
    return true;
  }

  private SinglyLinkedListNodeItr<E> nodeIterator() {
    return new SinglyLinkedListNodeItr<>(this);
  }

  /**
   * Remove {@code o} from this linked list if present.
   *
   * @param o the item to remove.
   * @return true if this linked list was modified.
   */
  @Override
  public boolean remove(Object o) {
    // O(length)
    for (SinglyLinkedListNodeItr<E> iterator = nodeIterator(); iterator.hasNext(); ) {
      if (Objects.equals(iterator.next().item, o)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Check if evey item in {@code c} is present in this linked list.
   *
   * @param c the collection of items.
   * @return true if this linked list contains every item in {@code c}.
   */
  @Override
  public boolean containsAll(Collection<?> c) {
    // O(mn), where c has length m
    for (Object o : c) {
      if (contains(o)) {
        return true;
      }
    }
    return true;
  }

  /**
   * Add all items from {@code c} to this linked list.
   *
   * @param c the collection of items.
   * @return true if the linked list was modified.
   */
  @Override
  public boolean addAll(Collection<? extends E> c) {
    // O(m), where c has length m
    boolean modified = false;
    for (E o : c) {
      modified = add(o) || modified;
    }
    return modified;
  }

  /**
   * Remove all items in this linked list that are also in {@code c}
   *
   * @param c the collection of items.
   * @return true if this linked list was modified.
   */
  @Override
  public boolean removeAll(Collection<?> c) {
    // O(length) * O(x) where c.contains() is O(x)
    boolean modified = false;
    for (SinglyLinkedListNodeItr<E> iterator = nodeIterator(); iterator.hasNext(); ) {
      if (c.contains(iterator.next().item)) {
        iterator.remove();
        modified = true;
      }
    }
    return modified;
  }

  /**
   * Remove all items in this linked list that are not in also {@code c}.
   *
   * @param c the collection of items.
   * @return true if this linked list was modified.
   */
  @Override
  public boolean retainAll(Collection<?> c) {
    // O(length) * O(x) where c.contains() is O(x)
    boolean modified = false;
    for (SinglyLinkedListNodeItr<E> iterator = nodeIterator(); iterator.hasNext(); ) {
      if (!c.contains(iterator.next().item)) {
        iterator.remove();
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public void clear() {
    head = null;
    tail = null;
    length = 0;
  }
}

class SinglyLinkedListItr<E> implements Iterator<E> {
  private int nRemaining;
  private Node<E> current;

  SinglyLinkedListItr(SinglyLinkedList<E> list) {
    nRemaining = list.length;
    current = list.head;
  }

  @Override
  public boolean hasNext() {
    return nRemaining > 0;
  }

  @Override
  public E next() {
    E item = current.item;
    current = current.next;
    --nRemaining;
    return item;
  }
}

class SinglyLinkedListNodeItr<E> implements Iterator<Node<E>> {
  private final SinglyLinkedList<E> list;
  private int nRemaining;
  private Node<E> next;
  private Node<E> current = null;
  private Node<E> prev = null;
  private boolean removed;

  SinglyLinkedListNodeItr(SinglyLinkedList<E> list) {
    nRemaining = list.length;
    next = list.head;
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    return nRemaining > 0;
  }

  /** @return the next node in the underlying linked list. */
  @Override
  public Node<E> next() {
    prev = current;
    current = next;
    next = next.next;
    --nRemaining;
    removed = false;
    return current;
  }

  /**
   * @throws IllegalStateException if {@code next()} has not been called since last call to {@code
   *     remove}
   */
  @Override
  public void remove() {
    if (removed) {
      throw new IllegalStateException();
    }
    if (current == list.head) {
      list.head = list.head.next;
    }
    if (current == list.tail) {
      list.tail = prev;
    }
    if (prev != null) {
      prev.next = next;
    }
    --list.length;
    removed = true;
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
