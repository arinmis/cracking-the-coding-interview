package datastructures; 
import java.util.Iterator;


public class SinglyLinkedList<E> implements List<E>, Iterable<E> { 
    private Node<E> head;
    private Node<E> tail;
    private int size;


    //inner node class
    public static class Node<E> {
        private E value;
        private Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        // to create head 
        public Node(Node next) {
            this(null, next);
        }

        // to create tail
        public Node() {
            this(null, null);
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override 
        public String toString() { 
            return value.toString();
        }
    }

    public static class  CustomIterator<E> implements Iterator<E> {
        Node<E> current;
          
        // constructor
        public CustomIterator(SinglyLinkedList<E> sList) {
            this.current = sList.getHead().getNext();; 
        }
          
        // Checks if the next element exists
        public boolean hasNext() {
            return this.current.getNext() != null;
        }
          
        // moves the cursor/iterator to next element
        public E next() {
            E value = current.getValue();
            current = current.getNext();
            System.out.println("this is iterated value: " + value);
            return value;
        }
          
        // Used to remove an element. Implement only if needed
        public void remove() {
            // Default throws UnsupportedOperationException.
        }
    }



    public SinglyLinkedList() {
        // init head and tail
        Node<E> tail = new Node(-2, null);
        Node<E> head = new Node(-1, tail);
        this.head = head;
        this.tail = tail;
    }

    // returns number of elements
    public int size() { 
        return this.size;
    }

    // is list empty
    public boolean isEmpty() {
        return this.size == 0;
    };


    /* access node at the specified index */
    private Node<E> getNode(int i) { 
        // iterator node points head
        Node<E> iterNode = new Node<E>(this.head);
        for (int k = 0; k <= i; k++) { 
            iterNode = iterNode.getNext();
        }
        return iterNode.getNext();
    }

    /* check the given index for size */
    private void checkIndex(int i, int size) { 
        if (i > size || i <  -1)
            throw new IndexOutOfBoundsException("index " + i + " is out of bound size: " + size);
    }


    /* create new Node, and insert between given two */
    private void insertBetween(Node prevNode, Node nextNode, E value) { 
        Node<E> newNode = new Node<E>(value, nextNode);
        prevNode.setNext(newNode);
    }

    /* returns value at given index */
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, this.size);
        return getNode(i).getValue();
    }

    // replace elements in given index with given element 
    public E set(int i, E e) throws IndexOutOfBoundsException { 
        checkIndex(i, this.size);
        Node<E> targetNode = getNode(i);
        targetNode.setValue(e);
        return targetNode.getValue();
    }

    // inserts element to given index and shift all the followings
    public void add(int i, E e) throws IndexOutOfBoundsException { 
        checkIndex(i, this.size);
        Node<E> prevNode = getNode(i - 1);
        insertBetween(prevNode, prevNode.getNext(), e); 
        size++;
    }

    /* append end of the list */
    public void append(E e) { 
        add(size, e);
    }

    // removes element at the given index, and returns deleted element
    public E remove(int i) throws IndexOutOfBoundsException {
        Node<E> prevNode = getNode(i - 1);
        Node<E> removedNode =  prevNode.getNext();
        prevNode.setNext(removedNode.getNext());
        size--;
        return removedNode.getValue();
    }

    // code for data structure
    public Iterator<E> iterator() {
        return new CustomIterator<E>(this);
    }

    public Node<E> getHead() { 
        return this.head;
    }

    public Node<E> getTail() { 
        return this.tail;
    }
    
    @Override
    public String toString() { 
        DynamicString dStr = new DynamicString("[ ");
        Node<E> iterNode = head.getNext();
        for (int i = 0; i < this.size; i++) { 
            dStr.append(iterNode.getValue() + " ");
            iterNode = iterNode.getNext();
        }
        dStr.append("]");
        return dStr.toString();
    }
}


