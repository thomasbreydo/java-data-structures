package com.thomasbreydo.datastructures;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @param <K> key type
 * @param <V> value type
 */
public class Map<K, V> {
  static final int DEFAULT_CAPACITY = 16;
  static final double DEFAULT_LOAD_FACTOR = 0.75;
  final double loadFactor;
  int size;
  int capacity;
  ArrayList<DoublyLinkedList<Entry<K, V>>> buckets;

  Map(int capacity, double loadFactor) {
    checkCapacity(capacity);
    this.capacity = capacity;
    this.loadFactor = loadFactor;
    recreateEmptyBuckets();
  }

  Map(int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  Map() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  /**
   * If {@code capacity} has changed, the new {@code buckets} will reflect the new {@code capacity}.
   */
  void recreateEmptyBuckets() {
    size = 0;
    buckets = new ArrayList<>(capacity);
    for (int i = 0; i < capacity; ++i) {
      buckets.add(new DoublyLinkedList<>());
    }
  }

  void checkCapacity(int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException();
  }

  void expand(int newCapacity) {
    checkCapacity(newCapacity);
    ArrayList<DoublyLinkedList<Entry<K, V>>> oldBuckets = buckets; // todo check if clone needed
    capacity = newCapacity;
    recreateEmptyBuckets();
    for (DoublyLinkedList<Entry<K, V>> bucket : oldBuckets) {
      for (Entry<K, V> entry : bucket) {
        insert(entry.key, entry.value);
      }
    }
  }

  DoublyLinkedList<Entry<K, V>> bucket(K key) {
    return buckets.get(Objects.hash(key) % buckets.size());
  }

  public int size() {
    return size;
  }

  public Entry<K, V> getEntry(K key) {
    DoublyLinkedList<Entry<K, V>> list = bucket(key);
    for (Entry<K, V> entry : list) {
      if (entry.key == key) {
        return entry;
      }
    }
    return null;
  }

  /**
   * Inserts an entry with {@code (key, value)} into this map. If a value with key {@code key}
   * already existed, the old value is overwritten.
   *
   * @param key the key
   * @param value the value
   * @return the old value if {@code key} was present in the map, otherwise {@code null}.
   */
  public V insert(K key, V value) {
    if ((double) (size + 1) / capacity > loadFactor) {
      expand(capacity * 2);
    }
    Entry<K, V> entry = getEntry(key);
    if (entry == null) {
      bucket(key).add(new Entry<>(key, value));
      ++size;
      return null;
    }
    V v = entry.value;
    entry.setValue(value);
    return v;
  }

  /**
   * Gets the value for {@code key}. Returns {@code null} if {@code key} is not found.
   *
   * @param key the key whose value to find
   * @return the value for {@code key} or {@code null}
   */
  public V get(K key) {
    Entry<K, V> entry = getEntry(key);
    if (entry == null) {
      return null;
    }
    return entry.value;
  }

  /**
   * Checks if this map contains an entry whose key is {@code key}.
   *
   * @param key the key to check
   * @return {@code true} if the key is in this map, else {@code false}
   */
  public boolean hasKey(K key) {
    return getEntry(key) != null;
  }

  /**
   * Delete the entry of a key, if it is present.
   *
   * @param k key whose entry to delete if present
   * @return {@code true} if key was found before deletion, else {@code false}
   */
  public boolean delete(K k) {
    if (!bucket(k).removeIf(entry -> entry.key == k)) return false;
    --size;
    return true;
  }

  /** Stores a pair of {@code (key, value)}. */
  public static class Entry<K, V> {
    K key;
    V value;

    Entry(K k, V v) {
      key = k;
      value = v;
    }

    public K getKey() {
      return key;
    }

    public void setKey(K k) {
      key = k;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V v) {
      value = v;
    }

    @Override
    public int hashCode() {
      return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Entry<?, ?> entry = (Entry<?, ?>) o;
      return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
    }
  }
}
