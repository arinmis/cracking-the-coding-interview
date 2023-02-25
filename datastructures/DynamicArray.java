package datastructures;

import java.util.Arrays;
public class DynamicArray<E> { // implements List<E> { 

    public static final int CAPACITY=16;
    private E[] data;
    private int size = 0;


    public DynamicArray() { this(CAPACITY); }
    public DynamicArray(int capacity) { 
        data = (E[])new Object[capacity];
    }
    
    // returns number of elements
    public int size() { 
        return size;
    }

    // is list empty
    public boolean isEmpty() { 
        return size == 0; 
    }

    // returns element at given index
    public  E get(int i) throws IndexOutOfBoundsException { 
        return data[i];
    };
    
    // replace elements in given index with given element
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, this.size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }


    /* 
     * inserts element to given index
     * and shift all the followings 
     * runs at: 
     *
     * X insertion takes roughly O(2X) time where X is log(N)
     * The amortized time for each insertion is O(1)
     *
     * */
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        // consider capacity is full 
        // consider later
        if (size == data.length)
            resize(2 * data.length); // double the size 
        for (int j = size - 1; j >= i; j--) 
            data[j + 1] = data[j];
        data[i] = e;
        size++;
    }

    /* add end of the array */
    public void add(E e) { 
        add(size, e);
    }
    
    /* 
     * removes element at the given index, 
     * and returns deleted element 
     * runs at O(n)
     *
     * */
    public E remove(int i) throws IndexOutOfBoundsException { 
        checkIndex(i, size);
        E removed = data[i];
        for (int j = i; j < size; j++) { 
            data[j] = data[j + 1];
        }
        data[size - 1] = null; // duplicate last element
        size--;
        return removed;
    }

    /* check the given index for size */
    private void checkIndex(int i, int size) { 
        if (i >= size || i <=  -1)
            throw new IndexOutOfBoundsException("index " + i + " is out of bound size: " + size);
    }


    /* resize data array, capacity >= size*/
    protected void resize(int capacity) { 
        E[] newData = (E[])new Object[capacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    @Override
    public String toString() { 
        String str = "[ ";
        for (int i = 0; i < size; i++)
            str += data[i].toString() +  " ";
        return str + "]";
    }
}
