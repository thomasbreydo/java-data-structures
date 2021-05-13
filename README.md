# Data Structures in Java

Yes, these structures exist as built-in Java objects. The purpose of this
project is to practice by implementing them.

## To-do

- Set

- Heap

## Completed

### Map

- View my [source file](src/main/java/com/thomasbreydo/datastructures/Map.java).

- View
  my [unit tests](src/test/java/com/thomasbreydo/datastructures/MapTest.java).

The map is implemented as a hash-map. All four map operations
(`insert`, `delete`, `get`, `hasKey`) have runtime O(1)
by design.

> :warning: Since resizing the map takes O(n) time, some insertions are O(n). On average, this happens only 1/n times, so the average runtime of `insert` is O(1).

### Queue

- View
  my [source file](src/main/java/com/thomasbreydo/datastructures/Queue.java).

- View
  my [unit tests](src/test/java/com/thomasbreydo/datastructures/QueueTest.java).

The queue is implemented as a subclass of `DoublyLinkedList`. All five queue
operations (`offer`, `remove`, `poll`, `element`, `peek`)
have runtime O(1) by design.

### Stack

- View
  my [source file](src/main/java/com/thomasbreydo/datastructures/Stack.java).

- View
  my [unit tests](src/test/java/com/thomasbreydo/datastructures/StackTest.java).

The stack is implemented as a subclass of `DoublyLinkedList`. All four stack
operations (`push`, `pop`, `peek`, `empty`)
have runtime O(1) by design.

### Doubly-linked list

- View
  my [source file](src/main/java/com/thomasbreydo/datastructures/DoublyLinkedList.java)
  .

- View
  my [unit tests](src/test/java/com/thomasbreydo/datastructures/DoublyLinkedListTest.java)
  .

Unlike singly-linked nodes, doubly-linked nodes keep track of their
predecessor (in addition to their successor). This makes reverse traversal an
operation that runs in O(1) time. It also allows for much more concise code
because it eliminates the need to keep track of the preceding node while
iterating over the list (once the target is found, it stores a pointer to the
previous node). Searching for an element or indexing is still O(n).

### Singly-linked list

- View
  my [source file](src/main/java/com/thomasbreydo/datastructures/SinglyLinkedList.java)
  .

- View
  my [unit tests](src/test/java/com/thomasbreydo/datastructures/SinglyLinkedListTest.java)
  .

Singly-linked nodes only keep track of their successor so there is no easy way
to iterate backward. Certain operations are O(n) instead of O(1) because to find
a preceding node one must iterate from the start of the list.

## License

[MIT](LICENSE) license.

