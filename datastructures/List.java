/* simple list interface */
public interface List<E> { 
    // returns number of elements
    int size();
    // is list empty
    boolean isEmpty();
    // returns element at given index
    E get(int i) throws IndexOutOfBoundsException;
    // replace elements in given index with given element 
    E set(int i, E e) throws IndexOutOfBoundsException;
    // inserts element to given index and shift all the followings
    void add(int i, E e) throws IndexOutOfBoundsException;
    // removes element at the given index, and returns deleted element
    E remove(int i) throws IndexOutOfBoundsException;
}
