package datastructures;

public class Deque<E> { 
    SinglyLinkedList<E> list;

    public Deque() { 
        list = new SinglyLinkedList<E>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return isEmpty();
    }

    public E first() {
        return list.get(0);
    }

    public E last() {
        return list.get(list.size() - 1);
    }

    public void addFirst(E e) {
        list.add(0, e); 
    }

    public void addLast(E e) {
        list.append(e); 
    }

    public E removeFirst() {
        return list.remove(0);
    }

    public E removeLast() {
        return list.remove(list.size() - 1); 
    }

    @Override 
    public String toString() { 
        return list.toString();
    }
    /*
    */
}
