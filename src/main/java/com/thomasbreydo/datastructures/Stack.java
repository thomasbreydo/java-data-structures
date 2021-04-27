package com.thomasbreydo.datastructures;

import java.util.EmptyStackException;

/**
 * Last-in-first-out data structure.
 *
 * @param <E> element type
 */
public class Stack<E> extends DoublyLinkedList<E> {
  Stack() {
    super();
  }

  /**
   * Returns {@code true} if this stack has no elements.
   *
   * @return {@code true} if this stack has no elements.
   */
  public boolean empty() {
    return isEmpty();
  }

  /**
   * Adds {@code item} to the top of the stack.
   *
   * @param item item to add
   */
  public void push(E item) {
    add(item);
  }

  /**
   * Removes the object at the top of this stack and returns that object as the value of this
   * function.
   *
   * @return The object at the top of this stack.
   * @throws EmptyStackException if this stack is empty.
   */
  public E pop() {
    if (isEmpty()) throw new EmptyStackException();
    return remove(size - 1);
  }

  /**
   * Looks at the object at the top of this stack without removing it from the stack.
   *
   * @return the object at the top of this stack.
   * @throws EmptyStackException if this stack is empty.
   */
  public E peek() {
    if (isEmpty()) throw new EmptyStackException();
    return get(size - 1);
  }
}
