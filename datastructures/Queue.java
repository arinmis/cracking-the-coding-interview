package datastructures;

/* first-in, first-out(FIFO) */
public class Queue<E> { 
    SinglyLinkedList<E> list;

    public Queue() { 
        this.list = new SinglyLinkedList<E>();
    }

    public void enqueue(E e) {
        list.add(0, e);
    }

    public E dequeue() {
        return list.remove(0);
    }

    public E first() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public String toString() { 
        return list.toString();
    }
}
