package com.thomasbreydo.datastructures;

import java.lang.reflect.Array;
import java.util.*;

public class DoublyLinkedList<E> implements List<E>, Collection<E> {
  int size = 0;
  Node<E> head = null;
  Node<E> tail = null;

  DoublyLinkedList() {}

  DoublyLinkedList(Collection<? extends E> c) {
    addAll(c);
  }

  @SafeVarargs
  DoublyLinkedList(E... values) {
    addAll(Arrays.asList(values));
  }

  /**
   * Returns the string representation of the items in this linked list.
   *
   * @return the string representation.
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[");
    Node<E> node = head;
    for (int i = 0; i < size; ++i) {
      s.append(node.val);
      if (i + 1 != size) {
        s.append(", ");
      }
      node = node.next;
    }
    s.append("]");
    return s.toString();
  }

  /**
   * Returns the number of elements in this list.
   *
   * @return the number of elements in this list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns {@code true} if this list contains no elements.
   *
   * @return {@code true} if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns {@code true} if this list contains the specified element. More formally, returns {@code
   * true} if and only if this list contains at least one element {@code e} such that {@code
   * Objects.equals(o, e)}.
   *
   * @param o element whose presence in this list is to be tested
   * @return {@code true} if this list contains the specified element
   * @throws NullPointerException if the specified element is null and this list does not permit
   *     null elements (<a href="Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public boolean contains(Object o) {
    Node<E> node = head;
    while (node != null) {
      if (Objects.equals(node.val, o)) {
        return true;
      }
      node = node.next;
    }
    return false;
  }

  /**
   * Returns an iterator over the elements in this list in proper sequence.
   *
   * @return an iterator over the elements in this list in proper sequence
   */
  @Override
  public Iterator<E> iterator() {
    return new DoublyLinkedListItr<>(this);
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element).
   *
   * <p>The returned array will be "safe" in that no references to it are maintained by this list.
   * (In other words, this method must allocate a new array even if this list is backed by an
   * array). The caller is thus free to modify the returned array.
   *
   * <p>This method acts as bridge between array-based and collection-based APIs.
   *
   * @return an array containing all of the elements in this list in proper sequence
   * @see Arrays#asList(Object[])
   */
  @Override
  public Object[] toArray() {
    Object[] a = new Object[size];
    Node<E> node = head;
    for (int i = 0; i < size; ++i) {
      a[i] = node.val;
      node = node.next;
    }
    return a;
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element); the runtime type of the returned array is that of the specified array. If the
   * list fits in the specified array, it is returned therein. Otherwise, a new array is allocated
   * with the runtime type of the specified array and the size of this list.
   *
   * <p>If the list fits in the specified array with room to spare (i.e., the array has more
   * elements than the list), the element in the array immediately following the end of the list is
   * set to {@code null}. (This is useful in determining the length of the list <i>only</i> if the
   * caller knows that the list does not contain any null elements.)
   *
   * <p>Like the {@link #toArray()} method, this method acts as bridge between array-based and
   * collection-based APIs. Further, this method allows precise control over the runtime type of the
   * output array, and may, under certain circumstances, be used to save allocation costs.
   *
   * <p>Suppose {@code x} is a list known to contain only strings. The following code can be used to
   * dump the list into a newly allocated array of {@code String}:
   *
   * <pre>{@code
   * String[] y = x.toArray(new String[0]);
   * }</pre>
   *
   * <p>Note that {@code toArray(new Object[0])} is identical in function to {@code toArray()}.
   *
   * @param a the array into which the elements of this list are to be stored, if it is big enough;
   *     otherwise, a new array of the same runtime type is allocated for this purpose.
   * @return an array containing the elements of this list
   * @throws NullPointerException if the specified array is null
   */
  @Override
  @SuppressWarnings("unchecked cast")
  public <T> T[] toArray(T[] a) {
    if (a.length < size) {
      a = (T[]) Array.newInstance(a.getClass(), size);
    } else if (a.length > size) {
      a[size] = null;
    }
    Node<E> node = head;
    for (int i = 0; i < size; ++i) {
      a[i] = (T) node.val;
      node = node.next;
    }
    return a;
  }

  /**
   * Appends the specified element to the end of this list (optional operation).
   *
   * <p>Lists that support this operation may place limitations on what elements may be added to
   * this list. In particular, some lists will refuse to add null elements, and others will impose
   * restrictions on the type of elements that may be added. List classes should clearly specify in
   * their documentation any restrictions on what elements may be added.
   *
   * @param e element to be appended to this list
   * @return {@code true} (as specified by {@link Collection#add})
   * @throws UnsupportedOperationException if the {@code add} operation is not supported by this
   *     list
   * @throws ClassCastException if the class of the specified element prevents it from being added
   *     to this list
   * @throws IllegalArgumentException if some property of this element prevents it from being added
   *     to this list
   */
  @Override
  public boolean add(E e) {
    add(size, e);
    return true;
  }

  /**
   * Removes the first occurrence of the specified element from this list, if it is present
   * (optional operation). If this list does not contain the element, it is unchanged. More
   * formally, removes the element with the lowest index {@code i} such that {@code
   * Objects.equals(o, get(i))} (if such an element exists). Returns {@code true} if this list
   * contained the specified element (or equivalently, if this list changed as a result of the
   * call).
   *
   * @param o element to be removed from this list, if present
   * @return {@code true} if this list contained the specified element
   * @throws ClassCastException if the type of the specified element is incompatible with this list
   *     (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified element is null and this list does not permit
   *     null elements (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws UnsupportedOperationException if the {@code remove} operation is not supported by this
   *     list
   */
  @Override
  public boolean remove(Object o) {
    Node<E> node = head;
    while (node != null) {
      if (Objects.equals(node.val, o)) {
        toss(node);
        return true;
      }
      node = node.next;
    }
    return false;
  }

  /**
   * Returns {@code true} if this list contains all of the elements of the specified collection.
   *
   * @param c collection to be checked for containment in this list
   * @return {@code true} if this list contains all of the elements of the specified collection
   * @throws ClassCastException if the types of one or more elements in the specified collection are
   *     incompatible with this list (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified collection contains one or more null elements and
   *     this list does not permit null elements (<a
   *     href="Collection.html#optional-restrictions">optional</a>), or if the specified collection
   *     is null
   * @see #contains(Object)
   */
  @Override
  public boolean containsAll(Collection<?> c) {
    for (Object o : c) {
      if (!contains(o)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Appends all of the elements in the specified collection to the end of this list, in the order
   * that they are returned by the specified collection's iterator (optional operation). The
   * behavior of this operation is undefined if the specified collection is modified while the
   * operation is in progress. (Note that this will occur if the specified collection is this list,
   * and it's nonempty.)
   *
   * @param c collection containing elements to be added to this list
   * @return {@code true} if this list changed as a result of the call
   * @throws UnsupportedOperationException if the {@code addAll} operation is not supported by this
   *     list
   * @throws ClassCastException if the class of an element of the specified collection prevents it
   *     from being added to this list
   * @throws NullPointerException if the specified collection contains one or more null elements and
   *     this list does not permit null elements, or if the specified collection is null
   * @throws IllegalArgumentException if some property of an element of the specified collection
   *     prevents it from being added to this list
   * @see #add(Object)
   */
  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;
    for (E e : c) {
      add(e);
      modified = true;
    }
    return modified;
  }

  /**
   * Inserts all of the elements in the specified collection into this list at the specified
   * position (optional operation). Shifts the element currently at that position (if any) and any
   * subsequent elements to the right (increases their indices). The new elements will appear in
   * this list in the order that they are returned by the specified collection's iterator. The
   * behavior of this operation is undefined if the specified collection is modified while the
   * operation is in progress. (Note that this will occur if the specified collection is this list,
   * and it's nonempty.)
   *
   * @param index index at which to insert the first element from the specified collection
   * @param c collection containing elements to be added to this list
   * @return {@code true} if this list changed as a result of the call
   * @throws UnsupportedOperationException if the {@code addAll} operation is not supported by this
   *     list
   * @throws ClassCastException if the class of an element of the specified collection prevents it
   *     from being added to this list
   * @throws NullPointerException if the specified collection contains one or more null elements and
   *     this list does not permit null elements, or if the specified collection is null
   * @throws IllegalArgumentException if some property of an element of the specified collection
   *     prevents it from being added to this list
   * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >
   *     size()})
   */
  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    check0ToSize(index);
    boolean modified = false;
    for (E e : c) {
      add(index++, e);
      modified = true;
    }
    return modified;
  }

  /**
   * Removes from this list all of its elements that are contained in the specified collection
   * (optional operation).
   *
   * @param c collection containing elements to be removed from this list
   * @return {@code true} if this list changed as a result of the call
   * @throws UnsupportedOperationException if the {@code removeAll} operation is not supported by
   *     this list
   * @throws ClassCastException if the class of an element of this list is incompatible with the
   *     specified collection (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if this list contains a null element and the specified collection
   *     does not permit null elements (<a
   *     href="Collection.html#optional-restrictions">optional</a>), or if the specified collection
   *     is null
   * @see #remove(Object)
   * @see #contains(Object)
   */
  @Override
  public boolean removeAll(Collection<?> c) {
    boolean modified = false;
    for (Node<E> node = head; node != null; node = node.next) {
      if (c.contains(node.val)) {
        modified = true;
        toss(node);
      }
    }
    return modified;
  }

  /**
   * Retains only the elements in this list that are contained in the specified collection (optional
   * operation). In other words, removes from this list all of its elements that are not contained
   * in the specified collection.
   *
   * @param c collection containing elements to be retained in this list
   * @return {@code true} if this list changed as a result of the call
   * @throws UnsupportedOperationException if the {@code retainAll} operation is not supported by
   *     this list
   * @throws ClassCastException if the class of an element of this list is incompatible with the
   *     specified collection (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if this list contains a null element and the specified collection
   *     does not permit null elements (<a
   *     href="Collection.html#optional-restrictions">optional</a>), or if the specified collection
   *     is null
   * @see #remove(Object)
   * @see #contains(Object)
   */
  @Override
  public boolean retainAll(Collection<?> c) {
    boolean modified = false;
    for (Node<E> node = head; node != null; node = node.next) {
      if (!c.contains(node.val)) {
        toss(node);
        modified = true;
      }
    }
    return modified;
  }

  /**
   * Removes all of the elements from this list (optional operation). The list will be empty after
   * this call returns.
   *
   * @throws UnsupportedOperationException if the {@code clear} operation is not supported by this
   *     list
   */
  @Override
  public void clear() {
    clear(0, size);
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >=
   *     size()})
   */
  @Override
  public E get(int index) {
    check0ToSizeMinus1(index);
    return getNode(index).val;
  }

  /**
   * Replaces the element at the specified position in this list with the specified element
   * (optional operation).
   *
   * @param index index of the element to replace
   * @param element element to be stored at the specified position
   * @return the element previously at the specified position
   * @throws UnsupportedOperationException if the {@code set} operation is not supported by this
   *     list
   * @throws ClassCastException if the class of the specified element prevents it from being added
   *     to this list
   * @throws NullPointerException if the specified element is null and this list does not permit
   *     null elements
   * @throws IllegalArgumentException if some property of the specified element prevents it from
   *     being added to this list
   * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >=
   *     size()})
   */
  @Override
  public E set(int index, E element) {
    check0ToSizeMinus1(index);
    Node<E> node = getNode(index);
    E old = node.val;
    node.val = element;
    return old;
  }

  /**
   * Inserts the specified element at the specified position in this list (optional operation).
   * Shifts the element currently at that position (if any) and any subsequent elements to the right
   * (adds one to their indices).
   *
   * @param index index at which the specified element is to be inserted
   * @param element element to be inserted
   * @throws UnsupportedOperationException if the {@code add} operation is not supported by this
   *     list
   * @throws ClassCastException if the class of the specified element prevents it from being added
   *     to this list
   * @throws NullPointerException if the specified element is null and this list does not permit
   *     null elements
   * @throws IllegalArgumentException if some property of the specified element prevents it from
   *     being added to this list
   * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >
   *     size()})
   */
  @Override
  public void add(int index, E element) {
    check0ToSize(index);
    if (size == 0) {
      head = new Node<>(element, null, null);
      tail = head;
      ++size;
      return;
    }
    Node<E> succ = getNode(index);
    if (succ == null) {
      // new tail
      tail.next = new Node<>(element, tail, null);
      tail = tail.next;
    } else if (succ == head) {
      head.prev = new Node<>(element, null, head);
      head = head.prev;
    } else {
      succ.prev.next = new Node<>(element, succ.prev, succ);
      succ.prev = succ.prev.next;
    }
    ++size;
  }

  /**
   * Removes the element at the specified position in this list (optional operation). Shifts any
   * subsequent elements to the left (subtracts one from their indices). Returns the element that
   * was removed from the list.
   *
   * @param index the index of the element to be removed
   * @return the element previously at the specified position
   * @throws UnsupportedOperationException if the {@code remove} operation is not supported by this
   *     list
   * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >=
   *     size()})
   */
  @Override
  public E remove(int index) {
    check0ToSizeMinus1(index);
    Node<E> node = getNode(index);
    toss(node);
    return node.val;
  }

  /**
   * Returns the index of the first occurrence of the specified element in this list, or -1 if this
   * list does not contain the element. More formally, returns the lowest index {@code i} such that
   * {@code Objects.equals(o, get(i))}, or -1 if there is no such index.
   *
   * @param o element to search for
   * @return the index of the first occurrence of the specified element in this list, or -1 if this
   *     list does not contain the element
   * @throws ClassCastException if the type of the specified element is incompatible with this list
   *     (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified element is null and this list does not permit
   *     null elements (<a href="Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public int indexOf(Object o) {
    Node<E> node = head;
    for (int i = 0; node != null; ++i) {
      if (Objects.equals(node.val, o)) {
        return i;
      }
      node = node.next;
    }
    throw new NoSuchElementException();
  }

  /**
   * Returns the index of the last occurrence of the specified element in this list, or -1 if this
   * list does not contain the element. More formally, returns the highest index {@code i} such that
   * {@code Objects.equals(o, get(i))}, or -1 if there is no such index.
   *
   * @param o element to search for
   * @return the index of the last occurrence of the specified element in this list, or -1 if this
   *     list does not contain the element
   * @throws ClassCastException if the type of the specified element is incompatible with this list
   *     (<a href="Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified element is null and this list does not permit
   *     null elements (<a href="Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public int lastIndexOf(Object o) {
    int i = size - 1;
    for (Node<E> node = tail; node != null; node = node.prev) {
      if (Objects.equals(node.val, o)) {
        break;
      }
      --i;
    }
    return i;
  }

  /**
   * Returns a list iterator over the elements in this list (in proper sequence).
   *
   * @return a list iterator over the elements in this list (in proper sequence)
   */
  @Override
  public ListIterator<E> listIterator() {
    return new DoublyLinkedListItr<>(this);
  }

  /**
   * Returns a list iterator over the elements in this list (in proper sequence), starting at the
   * specified position in the list. The specified index indicates the first element that would be
   * returned by an initial call to {@link ListIterator#next next}. An initial call to {@link
   * ListIterator#previous previous} would return the element with the specified index minus one.
   *
   * @param index index of the first element to be returned from the list iterator (by a call to
   *     {@link ListIterator#next next})
   * @return a list iterator over the elements in this list (in proper sequence), starting at the
   *     specified position in the list
   * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >
   *     size()})
   */
  @Override
  public ListIterator<E> listIterator(int index) {
    return new DoublyLinkedListItr<>(this);
  }

  /**
   * Returns a view of the portion of this list between the specified {@code fromIndex}, inclusive,
   * and {@code toIndex}, exclusive. (If {@code fromIndex} and {@code toIndex} are equal, the
   * returned list is empty.) The returned list is backed by this list, so non-structural changes in
   * the returned list are reflected in this list, and vice-versa. The returned list supports all of
   * the optional list operations supported by this list.
   *
   * <p>
   *
   * <p>This method eliminates the need for explicit range operations (of the sort that commonly
   * exist for arrays). Any operation that expects a list can be used as a range operation by
   * passing a subList view instead of a whole list. For example, the following idiom removes a
   * range of elements from a list:
   *
   * <pre>{@code
   * list.subList(from, to).clear();
   * }</pre>
   *
   * Similar idioms may be constructed for {@code indexOf} and {@code lastIndexOf}, and all of the
   * algorithms in the {@code Collections} class can be applied to a subList.
   *
   * <p>
   *
   * <p>The semantics of the list returned by this method become undefined if the backing list
   * (i.e., this list) is <i>structurally modified</i> in any way other than via the returned list.
   * (Structural modifications are those that change the size of this list, or otherwise perturb it
   * in such a fashion that iterations in progress may yield incorrect results.)
   *
   * @param fromIndex low endpoint (inclusive) of the subList
   * @param toIndex high endpoint (exclusive) of the subList
   * @return a view of the specified range within this list
   * @throws IndexOutOfBoundsException for an illegal endpoint index value ({@code fromIndex < 0 ||
   *     toIndex > size || fromIndex > toIndex})
   */
  @Override
  public DoublyLinkedList<E> subList(int fromIndex, int toIndex) {
    return new DoublyLinkedSublist<>(this, fromIndex, toIndex);
  }

  void check0ToSize(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
  }

  void check0ToSizeMinus1(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  void check0ToSize(int from, int to) {
    if (from < 0 || to > size || from > to) {
      throw new IndexOutOfBoundsException();
    }
  }

  void check0ToSizeMinus1(int from, int to) {
    if (from < 0 || to >= size || from > to) {
      throw new IndexOutOfBoundsException();
    }
  }

  /**
   * @param index index of node to get ({@code 0 <= index <= size})
   * @return the node at index {@index index} or {@code null} if {@code index == size}
   */
  Node<E> getNode(int index) {
    if (index == size) {
      return null;
    }
    if (index > size / 2) {
      Node<E> node = tail;
      for (int i = size - 1; i > index; --i) {
        node = node.prev;
      }
      return node;
    }
    Node<E> node = head;
    for (int i = 0; i < index; ++i) {
      node = node.next;
    }
    return node;
  }

  void clear(int from, int to) {
    check0ToSize(from, to);
    if (to == 0 || from == to) {
      return;
    }
    Node<E> firstToDel = getNode(from);
    Node<E> firstToKeep = getNode(to);
    if (firstToDel == head) {
      head = firstToKeep;
      if (head != null) {
        head.prev = null;
      }
    }
    if (firstToKeep == null) {
      tail = firstToDel.prev;
      if (tail != null) {
        tail.next = null;
      }
    }
    if (firstToDel.prev != null) {
      firstToDel.prev.next = firstToKeep;
    }
    if (firstToKeep != null) {
      firstToKeep.prev = firstToDel.prev;
    }
    size -= to - from;
  }

  void toss(Node<E> node) {
    // assert node != null
    --size;
    if (node == head) {
      head = head.next;
    } else if (node == tail) {
      tail = tail.prev;
    }
    if (node.prev != null) {
      node.prev.next = node.next;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    }
  }

  static class Node<E> {
    E val;
    Node<E> prev;
    Node<E> next;

    public Node(E val, Node<E> prev, Node<E> next) {
      this.val = val;
      this.prev = prev;
      this.next = next;
    }
  }

  static class DoublyLinkedListItr<E> implements ListIterator<E> {
    final DoublyLinkedList<E> LIST;
    Node<E> cur;
    int index = -1;
    boolean removed = false;
    boolean set = false;
    boolean added = false;

    DoublyLinkedListItr(DoublyLinkedList<E> list) {
      LIST = list;
      cur = new Node<>(null, null, list.head);
    }

    /**
     * Returns {@code true} if this list iterator has more elements when traversing the list in the
     * forward direction. (In other words, returns {@code true} if {@link #next} would return an
     * element rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when traversing the list in the
     *     forward direction
     */
    @Override
    public boolean hasNext() {
      return nextIndex() < LIST.size;
    }

    /**
     * Returns the next element in the list and advances the cursor position. This method may be
     * called repeatedly to iterate through the list, or intermixed with calls to {@link #previous}
     * to go back and forth. (Note that alternating calls to {@code next} and {@code previous} will
     * return the same element repeatedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iteration has no next element
     */
    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      index = nextIndex();
      cur = cur.next;
      if (added && !removed) {
        cur = cur.next;
      }
      removed = false;
      set = false;
      added = false;
      return cur.val;
    }

    /**
     * Returns {@code true} if this list iterator has more elements when traversing the list in the
     * reverse direction. (In other words, returns {@code true} if {@link #previous} would return an
     * element rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when traversing the list in the
     *     reverse direction
     */
    @Override
    public boolean hasPrevious() {
      return previousIndex() >= 0;
    }

    /**
     * Returns the previous element in the list and moves the cursor position backwards. This method
     * may be called repeatedly to iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth. (Note that alternating calls to {@code next} and {@code
     * previous} will return the same element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous element
     */
    @Override
    public E previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      cur = cur.prev;
      index = previousIndex();
      removed = false;
      set = false;
      added = false;
      return cur.val;
    }

    /**
     * Returns the index of the element that would be returned by a subsequent call to {@link
     * #next}. (Returns list size if the list iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a subsequent call to {@code next},
     *     or list size if the list iterator is at the end of the list
     */
    @Override
    public int nextIndex() {
      int i = index + 1;
      if (removed) --i;
      if (added) ++i;
      return i;
    }

    /**
     * Returns the index of the element that would be returned by a subsequent call to {@link
     * #previous}. (Returns -1 if the list iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a subsequent call to {@code
     *     previous}, or -1 if the list iterator is at the beginning of the list
     */
    @Override
    public int previousIndex() {
      return index - 1;
    }

    /**
     * Removes from the list the last element that was returned by {@link #next} or {@link
     * #previous} (optional operation). This call can only be made once per call to {@code next} or
     * {@code previous}. It can be made only if {@link #add} has not been called after the last call
     * to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove} operation is not supported by
     *     this list iterator
     * @throws IllegalStateException if neither {@code next} nor {@code previous} have been called,
     *     or {@code remove} or {@code add} have been called after the last call to {@code next} or
     *     {@code previous}
     */
    @Override
    public void remove() {
      if (removed || added) {
        throw new IllegalStateException();
      }
      LIST.toss(cur);
      removed = true;
    }

    /**
     * Replaces the last element returned by {@link #next} or {@link #previous} with the specified
     * element (optional operation). This call can be made only if neither {@link #remove} nor
     * {@link #add} have been called after the last call to {@code next} or {@code previous}.
     *
     * @param e the element with which to replace the last element returned by {@code next} or
     *     {@code previous}
     * @throws UnsupportedOperationException if the {@code set} operation is not supported by this
     *     list iterator
     * @throws ClassCastException if the class of the specified element prevents it from being added
     *     to this list
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from
     *     being added to this list
     * @throws IllegalStateException if neither {@code next} nor {@code previous} have been called,
     *     or {@code remove} or {@code add} have been called after the last call to {@code next} or
     *     {@code previous}
     */
    @Override
    public void set(E e) {
      if (removed || added) {
        throw new IllegalStateException();
      }
      set = true;
      cur.val = e;
    }

    /**
     * Inserts the specified element into the list (optional operation). The element is inserted
     * immediately before the element that would be returned by {@link #next}, if any, and after the
     * element that would be returned by {@link #previous}, if any. (If the list contains no
     * elements, the new element becomes the sole element on the list.) The new element is inserted
     * before the implicit cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new element. (This call increases by one
     * the value that would be returned by a call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param e the element to insert
     * @throws UnsupportedOperationException if the {@code add} method is not supported by this list
     *     iterator
     * @throws ClassCastException if the class of the specified element prevents it from being added
     *     to this list
     * @throws IllegalArgumentException if some aspect of this element prevents it from being added
     *     to this list
     */
    @Override
    public void add(E e) {
      LIST.add(nextIndex(), e);
      added = true;
    }
  }

  static class DoublyLinkedSublist<E> extends DoublyLinkedList<E> {
    final DoublyLinkedList<E> LIST;
    final int FROM;

    DoublyLinkedSublist(DoublyLinkedList<E> list, int from, int to) {
      list.check0ToSize(from, to);
      LIST = list;
      FROM = from;
      size = to - from;
      if (size > 0) {
        head = LIST.getNode(from);
        tail = LIST.getNode(to - 1);
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
    public void add(int index, E val) {
      LIST.add(FROM + index, val);
      ++size;
      if (index == 0) {
        head = LIST.getNode(FROM);
      }
      if (index == size - 1) {
        tail = LIST.getNode(FROM + size - 1);
      }
    }

    @Override
    public E remove(int index) {
      E val = LIST.remove(FROM + index);
      --size;
      if (size == 0) {
        head = null;
        tail = null;
      } else if (index == 0) {
        head = head.next;
      } else if (index == size) {
        tail = tail.prev;
      }
      return val;
    }

    @Override
    public void clear(int fromIndex, int toIndex) {
      check0ToSize(fromIndex, toIndex);
      LIST.clear(FROM + fromIndex, FROM + toIndex);
      if (fromIndex == 0) {
        if (toIndex == size) {
          head = null;
          tail = null;
        } else {
          head = LIST.getNode(FROM);
        }
      } else if (toIndex == size) {
        tail = LIST.getNode(FROM + fromIndex - 1);
      }
      size -= toIndex - fromIndex;
    }
  }
}
