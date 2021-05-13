package com.thomasbreydo.datastructures;

/** @param <E> element type */
public class Set<E> {
  final Map<E, Boolean> m; // {a: false, b: false, ...}

  Set() {
    m = new Map<>();
  }

  Set(int capacity) {
    m = new Map<>(capacity);
  }

  Set(int capacity, double loadFactor) {
    m = new Map<>(capacity, loadFactor);
  }

  int size() {
    return m.size();
  }

  /**
   * Inserts {@code element} into this set. Returns {@code true} if this set was modified (a.k.a.
   * the set didn't previously contain {@code element}.
   *
   * @param element the element to add
   * @return {@code true} if this set was modified, else {@code false}
   */
  public boolean insert(E element) {
    return m.insert(element, false) == null;
  }

  /**
   * Checks if this set contains {@code element}.
   *
   * @param element the element
   * @return {@code true} if {@code element} is in this set, else {@code false}
   */
  public boolean contains(E element) {
    return m.hasKey(element);
  }

  /**
   * Delete an element, if it is present.
   *
   * @param element the element to delete
   * @return {@code true} if key was found before deletion, else {@code false}
   */
  public boolean delete(E element) {
    return m.delete(element);
  }
}
