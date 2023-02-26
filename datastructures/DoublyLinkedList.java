package datastructures; 

public class DoublyLinkedList<E> implements List<E> { 

    private Node<E> head;
    private Node<E> tail;
    private int size;


    //inner node class
    public static class Node<E> {
        private E value;
        private Node next;
        private Node prev;

        public Node(E value, Node prev, Node next) {
            this.value = value;
            this.next = next;
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

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        @Override 
        public String toString() { 
            return value.toString();
        }
    }

    public DoublyLinkedList() {
        // init head and tail
        Node<E> tail = new Node(-2, null, null);
        Node<E> head = new Node(-1, null, tail);
        tail.setPrev(head);
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
    // TODO: iterate forward or backward depending of index
    private Node<E> getNode(int i) { 
        // iterator node points head
        Node<E> iterNode = new Node<E>(null, null, this.head);
        for (int k = 0; k <= i; k++) { 
            iterNode = iterNode.getNext();
        }
        return iterNode.getNext();
    }

    /* check the given index for size */
    private void checkIndex(int i, int size)  throws IndexOutOfBoundsException { 
        if (i > size || i <  -1)
            throw new IndexOutOfBoundsException("index " + i + " is out of bound size: " + size);
    }


    /* create new Node, and insert between given two */
    private void insertBetween(Node prevNode, Node nextNode, E value) { 
        Node<E> newNode = new Node<E>(value, prevNode, nextNode);
        prevNode.setNext(newNode);
        nextNode.setPrev(newNode);
    }

    /* returns value at given index */
    public E get(int i) {
        checkIndex(i, this.size);
        return getNode(i).getValue();
    }

    // replace elements in given index with given element 
    public E set(int i, E e) { 
        checkIndex(i, this.size);
        Node<E> targetNode = getNode(i);
        targetNode.setValue(e);
        return targetNode.getValue();
    }

    // inserts element to given index and shift all the followings
    public void add(int i, E e) { 
        checkIndex(i, this.size);
        Node<E> prevNode = getNode(i - 1);
        insertBetween(prevNode, prevNode.getNext(), e); 
        size++;
    }

    /* 
     * append to the end of the list, 
     * it will take O(1) times
     * since prev nodes are tracing 
     * */
    public void append(E e) { 
        insertBetween(tail.getPrev(), tail, e);
        size++;
    }

    // removes element at the given index, and returns deleted element
    public E remove(int i) {
        checkIndex(i, this.size());
        Node<E> prevNode = getNode(i - 1);
        Node<E> removedNode =  prevNode.getNext();
        Node<E> nextNode =  removedNode.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        size--;
        return removedNode.getValue();
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
