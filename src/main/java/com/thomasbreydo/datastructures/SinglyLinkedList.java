package com.thomasbreydo.datastructures;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Singly-linked list, where each element is stored as a node that points its successor.
 *
 * @param <E> the type of the elements this linked list stores.
 */
public class SinglyLinkedList<E> implements Collection<E>, List<E> {
  Node<E> head = null;
  Node<E> tail = null;
  int size = 0;
  /** Create a new instance of a singly-linked list with no elements. */
  public SinglyLinkedList() {}
  /**
   * Create a new instance of a singly-linked list with no elements.
   *
   * @param elements elements to add to this new list
   */
  @SafeVarargs
  public SinglyLinkedList(E... elements) {
    for (E e : elements) {
      pushBack(e);
    }
  }

  /**
   * Gets the number of elements in this linked list.
   *
   * @return the number of elements in this linked list.
   */
  @Override
  public int size() {
    // O(1)
    return size;
  }

  /**
   * Checks whether this linked list contains any elements.
   *
   * @return true if this list is empty.
   */
  @Override
  public boolean isEmpty() {
    // O(1)
    return size == 0;
  }

  /**
   * Checks if this linked list contains {@code o}.
   *
   * @param o the object.
   * @return true if this linked list contains {@code o}.
   */
  @Override
  public boolean contains(Object o) {
    // O(size)
    for (E item : this) {
      if (Objects.equals(o, item)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Alias for {@link #listIterator() listIterator}.
   *
   * @return a list iterator over the elements in this linked list.
   */
  @Override
  public ListIterator<E> iterator() {
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
    // O(size)
    Object[] array = new Object[size];
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
    // O(size)
    T[] array;
    if (a.length >= size) {
      array = a;
    } else {
      array = (T[]) Array.newInstance(a.getClass(), size);
    }
    int i = 0;
    for (E item : this) {
      array[i] = (T) item;
      ++i;
    }
    return array;
  }

  /**
   * Alias for {@link #pushBack(E e) pushBack}.
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

  /**
   * Remove {@code o} from this linked list if present.
   *
   * @param o the item to remove.
   * @return true if this linked list was modified.
   */
  @Override
  public boolean remove(Object o) {
    // O(size)
    // Use iterator because it keeps track of previous node.
    for (Iterator<E> iterator = iterator(); iterator.hasNext(); ) {
      if (Objects.equals(iterator.next(), o)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if evey item in {@code c} is present in this linked list.
   *
   * @param c the collection of items.
   * @return true if this linked list contains every item in {@code c}.
   */
  @Override
  public boolean containsAll(Collection<?> c) {
    // O(mn), where c has size m
    for (Object o : c) {
      if (!contains(o)) {
        return false;
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
    // O(m), where c has size m
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
    // O(size) * O(x) where c.contains() is O(x)
    boolean modified = false;
    for (Iterator<E> iterator = iterator(); iterator.hasNext(); ) {
      if (c.contains(iterator.next())) {
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
    // O(size) * O(x) where c.contains() is O(x)
    boolean modified = false;
    for (Iterator<E> iterator = iterator(); iterator.hasNext(); ) {
      if (!c.contains(iterator.next())) {
        iterator.remove();
        modified = true;
      }
    }
    return modified;
  }

  /** Removes all elements from this linked list. */
  @Override
  public void clear() {
    // O(n)
    clear(0, size);
  }

  void clear(int fromIndex, int toIndex) {
    checkIndices(fromIndex, toIndex);
    if (toIndex == 0) {
      return;
    }
    Node<E> lastToClear = getNode(toIndex - 1);
    if (fromIndex == 0) {
      head = lastToClear.next;
    } else {
      Node<E> lastBeforeClear = getNode(fromIndex - 1);
      lastBeforeClear.next = lastToClear.next;
      if (lastToClear == tail) {
        tail = lastBeforeClear;
      }
    }
    size -= toIndex - fromIndex;
  }

  /**
   * Add {@code item} to the end of this linked list.
   *
   * @param item the item to add to this linked list.
   */
  public void pushBack(E item) {
    // O(1)
    if (size == 0) {
      head = new Node<>(item, null);
      tail = head;
    } else {
      tail.next = new Node<>(item, null);
      tail = tail.next;
    }
    ++size;
  }

  /**
   * Gets the last element.
   *
   * @throws NoSuchElementException if this linked list is empty.
   * @return the last element.
   */
  public E topBack() {
    // O(1)
    if (size == 0) {
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
    // O(size)
    if (size == 0) {
      throw new NoSuchElementException();
    }
    if (size == 1) {
      head = null;
      tail = null;
    } else {
      Node<E> penultimate = getPenultimate();
      penultimate.next = null;
      tail = penultimate;
    }
    --size;
  }

  /**
   * Gets the first element.
   *
   * @return the first element.
   * @throws NoSuchElementException if this linked list is empty.
   */
  public E topFront() {
    // O(1)
    if (size == 0) {
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
    if (size == 0) {
      throw new NoSuchElementException();
    }
    head = head.next;
    --size;
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
    ++size;
  }

  /**
   * Insert every item in {@code c} into position {@code index} of this linked list.
   *
   * @param index the index at which to insert the items.
   * @param c the collection of items to insert.
   * @return true if this linked list was modified.
   */
  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    // O(index) + O(m) where c has size m
    checkIndex(index);
    Node<E> node = head;
    int i = 0;
    while (i < index) {
      node = node.next;
      ++i;
    }
    boolean modified = false;
    for (E item : c) {
      node.next = new Node<>(item, node.next);
      node = node.next;
      modified = true;
    }
    return modified;
  }

  /**
   * Gets the element at index {@code index}.
   *
   * @param index the index of the element to return.
   * @return the element at this index.
   */
  @Override
  public E get(int index) {
    // O(index)
    checkIndex(index);
    for (E item : this) {
      if (index == 0) {
        return item;
      }
      --index;
    }
    // should never be reached because of checkIndex() above
    throw new NoSuchElementException();
  }

  @Override
  public E set(int index, E element) {
    // O(index)
    checkIndex(index);
    for (ListIterator<E> iterator = iterator(); iterator.hasNext(); ) {
      E value = iterator.next();
      if (index == 0) {
        iterator.set(element);
        return value;
      }
      --index;
    }
    // should never be reached because of checkIndex() above
    throw new NoSuchElementException();
  }

  @Override
  public void add(int index, E element) {
    // O(index)
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index is " + index + " but size will be " + (size + 1));
    }
    ++size;
    if (index == 0) {
      head = new Node<>(element, head);
      if (tail == null) {
        // assert size == 1;
        tail = head;
      }
    } else {
      Node<E> prev = getNode(index - 1);
      Node<E> current = prev.next;
      prev.next = new Node<>(element, current);
      if (prev == tail) {
        tail = prev.next;
      }
    }
  }

  @Override
  public E remove(int index) {
    // O(index)
    checkIndex(index);
    --size;
    E item;
    if (index == 0) {
      item = head.item;
      head = head.next;
    } else {
      Node<E> prev = getNode(index - 1);
      Node<E> current = prev.next;
      item = current.item;
      prev.next = current.next;
      if (current == tail) {
        tail = prev;
      }
    }
    return item;
  }

  /**
   * Gets the index of the first occurrence of {@code o} in this linked list.
   *
   * @throws NoSuchElementException if this list does not contain {@code o}.
   * @param o the object whose index to get.
   * @return {@code o}'s index.
   */
  @Override
  public int indexOf(Object o) {
    // O(size)
    int index = 0;
    for (E e : this) {
      if (Objects.equals(o, e)) {
        return index;
      }
      ++index;
    }
    throw new NoSuchElementException();
  }

  /**
   * Gets the index of the last occurrence of {@code o} in this linked list.
   *
   * @throws NoSuchElementException if this list does not contain {@code o}.
   * @param o the object whose index to get.
   * @return {@code o}'s index.
   */
  @Override
  public int lastIndexOf(Object o) {
    // O(size)
    int i = 0;
    Integer index = null;
    for (E e : this) {
      if (Objects.equals(o, e)) {
        index = i;
      }
      ++i;
    }
    if (index == null) {
      throw new NoSuchElementException();
    }
    return index;
  }

  /**
   * Returns a list iterator over the elements in this linked list, starting with the first element.
   *
   * @return the iterator.
   */
  @Override
  public ListIterator<E> listIterator() {
    // O(1)
    return new SinglyLinkedListItr<>(this);
  }

  /**
   * Returns a list iterator over the elements in this linked list, starting with the element at
   * index {@code index}.
   *
   * @param index the index at which the iterator should start.
   * @return the iterator.
   */
  @Override
  public ListIterator<E> listIterator(int index) {
    // O(index)
    checkIndex(index);
    return new SinglyLinkedListItr<>(this, index);
  }

  /**
   * Returns a linked-list view into this linked list starting at {@code fromIndex} and ending at
   * {@code toIndex}. If {@code fromIndex > toIndex}, returns an empty linked list.
   *
   * @param fromIndex starting index, inclusive.
   * @param toIndex end index, exclusive.
   * @throws IndexOutOfBoundsException if either of {@code fromIndex} or {@code toIndex} are invalid
   *     indices.
   * @return the sublist.
   */
  @Override
  public SinglyLinkedList<E> subList(int fromIndex, int toIndex) {
    // O(toIndex)
    checkIndices(fromIndex, toIndex);
    return new SinglyLinkedSublist<>(this, fromIndex, toIndex);
  }

  /**
   * Returns the string representation of the items in this linked list.
   *
   * @return the string representation.
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[");
    int i = 0;
    for (E e : this) {
      s.append(e);
      ++i;
      if (i != size) {
        s.append(", ");
      }
    }
    s.append("]");
    return s.toString();
  }

  private Node<E> getNode(int index) {
    checkIndex(index);
    Node<E> node = head;
    for (int i = 0; i < index; ++i) {
      node = node.next;
    }
    return node;
  }

  /**
   * @throws NoSuchElementException if this list has fewer than 2 elements.
   * @return the second-to-last node.
   */
  private Node<E> getPenultimate() {
    // O(size)
    if (size < 2) {
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

  void checkIndex(int index) {
    // O(1)
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= size) {
      throw new IndexOutOfBoundsException(
          "Index (" + index + ") is not less than size (" + size + ")");
    }
  }

  void checkIndices(int from, int to) {
    if (from < 0) {
      throw new IndexOutOfBoundsException("Index (" + from + ") is negative");
    }
    if (to > size) {
      throw new IndexOutOfBoundsException(
          "Index (" + to + ") is not less than size (" + size + ")");
    }
    if (from > to) {
      throw new IndexOutOfBoundsException("fromIndex is greater than toIndex");
    }
  }

  static class Node<E> {
    E item;
    Node<E> next;

    Node(E item, Node<E> next) {
      this.item = item;
      this.next = next;
    }
  }

  static class SinglyLinkedListItr<E> implements ListIterator<E> {
    private final SinglyLinkedList<E> LIST;
    private int index = -1;
    private int nextIndex = 0;
    private Node<E> next;
    private Node<E> current = null;
    private Node<E> prev = null;
    private boolean removed;
    private boolean added;

    SinglyLinkedListItr(SinglyLinkedList<E> list) {
      next = list.head;
      LIST = list;
    }

    SinglyLinkedListItr(SinglyLinkedList<E> list, int startIndex) {
      // assert startIndex >= 0 && startIndex < list.size;
      next = list.head;
      LIST = list;
      while (index < startIndex) {
        next();
      }
    }

    @Override
    public boolean hasNext() {
      return nextIndex < LIST.size;
    }

    /** @return the next item in the underlying linked list. */
    @Override
    public E next() {
      if (!removed) {
        prev = current;
      }
      current = next;
      next = next.next;
      index = nextIndex;
      ++nextIndex;
      removed = false;
      added = false;
      return current.item;
    }

    @Override
    public boolean hasPrevious() {
      return index > 0;
    }

    /** @return the previous item in the underlying linked list. */
    @Override
    public E previous() {
      if (!removed || added) {
        next = current;
      }
      current = prev;
      prev = nodeBefore(prev);
      nextIndex = index;
      index = previousIndex();
      removed = false;
      added = false;
      return current.item;
    }

    @Override
    public int nextIndex() {
      return nextIndex;
    }

    @Override
    public int previousIndex() {
      return index - 1;
    }

    @Override
    public void remove() {
      if (removed) {
        throw new IllegalStateException("cannot remove before calling next() or previous()");
      }
      if (current == LIST.head) {
        LIST.head = LIST.head.next;
      }
      if (current == LIST.tail) {
        LIST.tail = prev;
      }
      if (prev != null) {
        prev.next = next;
      }
      current = prev;
      --LIST.size;
      --nextIndex;
      removed = true;
    }

    @Override
    public void set(E e) {
      if (removed) {
        throw new IllegalStateException("cannot set before calling next() or previous()");
      }
      current.item = e;
    }

    /** Adds before whatever would be returned by {@code next()}. */
    @Override
    public void add(E e) {
      if (removed) {
        current = new Node<>(e, next);
        prev.next = current;
        if (prev == LIST.tail) {
          LIST.tail = current;
        }
      } else {
        current.next = new Node<>(e, next);
        if (current == LIST.tail) {
          LIST.tail = current.next;
        }
      }
      added = true;
      ++nextIndex;
      ++LIST.size;
    }

    Node<E> nodeBefore(Node<E> node) {
      if (node == LIST.head) {
        return null;
      }
      Node<E> ans = LIST.head;
      while (ans != null) {
        if (Objects.equals(ans.next, node)) {
          return ans;
        }
        ans = ans.next;
      }
      throw new NoSuchElementException();
    }
  }

  static class SinglyLinkedSublist<E> extends SinglyLinkedList<E> {
    private final SinglyLinkedList<E> LIST;
    private final int startRelToLIST;

    public SinglyLinkedSublist(SinglyLinkedList<E> list, int fromIndex, int toIndex) {
      LIST = list;
      startRelToLIST = fromIndex;
      size = toIndex - fromIndex;
      if (size != 0) {
        Node<E> node = LIST.head;
        int i = 0;
        while (i < fromIndex) {
          node = node.next;
          ++i;
        }
        head = node;
        while (i < toIndex - 1) {
          node = node.next;
          ++i;
        }
        tail = node;
      }
    }

    @Override
    public boolean add(E e) {
      add(size, e);
      return true;
    }

    @Override
    public boolean remove(Object o) {
      remove(indexOf(o));
      return true;
    }

    @Override
    public void clear() {
      clear(0, size);
    }

    @Override
    public void clear(int fromIndex, int toIndex) {
      checkIndices(fromIndex, toIndex);
      LIST.clear(startRelToLIST + fromIndex, startRelToLIST + toIndex);
      if (fromIndex == 0) {
        if (toIndex == size) {
          head = null;
          tail = null;
        } else {
          head = LIST.getNode(startRelToLIST);
        }
      } else {
        if (toIndex == size) {
          tail = LIST.getNode(startRelToLIST + fromIndex - 1);
        }
      }
      size -= toIndex - fromIndex;
    }

    @Override
    public void pushBack(E item) {
      add(size, item);
    }

    @Override
    public void popBack() {
      remove(size - 1);
    }

    @Override
    public void popFront() {
      remove(0);
    }

    @Override
    public void pushFront(E item) {
      add(0, item);
    }

    @Override
    public void add(int index, E element) {
      LIST.add(startRelToLIST + index, element);
      ++size;
      if (index == 0) {
        head = LIST.getNode(startRelToLIST);
      }
      if (index == size - 1) {
        if (size == 1) {
          // assert tail == null;
          tail = head;
        } else {
          tail = tail.next;
        }
      }
    }

    @Override
    public E remove(int index) {
      E item = LIST.remove(startRelToLIST + index);
      if (size == 1) {
        head = null;
        tail = null;
      } else if (index == 0) {
        head = head.next;
      } else if (index == size - 1) {
        tail = LIST.getNode(startRelToLIST + index - 1);
      }
      --size;
      return item;
    }
  }
}
