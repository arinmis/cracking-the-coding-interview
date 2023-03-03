package datastructures;

/* Last-in, First-out */
public class Stack<E> { 
    DynamicArray<E> data;

    public Stack() { 
        data = new DynamicArray<E>();
    }

    public void push(E e) {
        data.add(e);
    }

    public E pop() { 
        return data.remove(data.size() - 1);
    }

    // check the last element without remove
    public E peek() {
        return data.get(data.size() - 1);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public String toString() { 
        return data.toString();
    }

}
