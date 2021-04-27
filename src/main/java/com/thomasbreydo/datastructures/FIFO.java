package com.thomasbreydo.datastructures;

import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * First-in-first-out data structure.
 *
 * @param <E> element type
 */
public class FIFO<E> extends DoublyLinkedList<E> implements Queue<E> {
  FIFO() {
    super();
  }

  /**
   * Inserts the specified element into this queue if it is possible to do so immediately without
   * violating capacity restrictions. When using a capacity-restricted queue, this method is
   * generally preferable to {@link #add}, which can fail to insert an element only by throwing an
   * exception.
   *
   * @param e the element to add
   * @return {@code true} if the element was added to this queue, else {@code false}
   * @throws ClassCastException if the class of the specified element prevents it from being added
   *     to this queue
   * @throws NullPointerException if the specified element is null and this queue does not permit
   *     null elements
   * @throws IllegalArgumentException if some property of this element prevents it from being added
   *     to this queue
   */
  @Override
  public boolean offer(E e) {
    add(e);
    return true;
  }

  /**
   * Retrieves and removes the head of this queue. This method differs from {@link #poll() poll()}
   * only in that it throws an exception if this queue is empty.
   *
   * @return the head of this queue
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public E remove() {
    if (isEmpty()) throw new NoSuchElementException();
    E val = get(0);
    remove(0);
    return val;
  }

  /**
   * Retrieves and removes the head of this queue, or returns {@code null} if this queue is empty.
   *
   * @return the head of this queue, or {@code null} if this queue is empty
   */
  @Override
  public E poll() {
    if (isEmpty()) return null;
    E val = get(0);
    remove(0);
    return val;
  }

  /**
   * Retrieves, but does not remove, the head of this queue. This method differs from {@link #peek
   * peek} only in that it throws an exception if this queue is empty.
   *
   * @return the head of this queue
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public E element() {
    if (isEmpty()) throw new NoSuchElementException();
    return get(0);
  }

  /**
   * Retrieves, but does not remove, the head of this queue, or returns {@code null} if this queue
   * is empty.
   *
   * @return the head of this queue, or {@code null} if this queue is empty
   */
  @Override
  public E peek() {
    if (isEmpty()) return null;
    return get(0);
  }
}
