package com.thomasbreydo.datastructures;

import java.util.EmptyStackException;

public class LinkedStack<E> extends DoublyLinkedList<E> {
  LinkedStack() {}

  /**
   * Returns {@code true} if this stack has no elements.
   *
   * @return {@code true} if this stack has no elements.
   */
  public boolean empty() {
    return size == 0;
  }

  /**
   * Adds {@code item} to the top of the stack.
   *
   * @param item
   */
  public void push(E item) {
    add(item);
  }

  void checkNotEmpty() {
    if (size == 0) {
      throw new EmptyStackException();
    }
  }

  /**
   * Removes the object at the top of this stack and returns that object as the value of this
   * function.
   *
   * @return The object at the top of this stack.
   * @throws EmptyStackException if this stack is empty.
   */
  public E pop() {
    checkNotEmpty();
    return remove(size - 1);
  }

  /**
   * Looks at the object at the top of this stack without removing it from the stack.
   *
   * @return the object at the top of this stack.
   * @throws EmptyStackException if this stack is empty.
   */
  public E peek() {
    checkNotEmpty();
    return get(size - 1);
  }
}
