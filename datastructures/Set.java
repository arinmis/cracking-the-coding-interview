package datastructures; 

import java.util.Iterator;

public class Set<E> implements Iterable<E> { 
    HashTable<E, E> hashTable;

    public Set() { 
        hashTable = new HashTable<E, E>();
    }

    public void add(E e) { 
        // control the uniquenes
        if (!contains(e))
            hashTable.put(e, null);
    };

    public E remove(E e) { 
        if (hashTable.remove(e) == null)
            return null;
        return e;
    }

    public int size() { 
        return hashTable.size();
    }

    public boolean contains(E e) { 
        return hashTable.contains(e);
    };

    public Iterator<E> iterator() {
        return hashTable.keySet().iterator();
    }

    public Set<E> union(Set<E> s) { 
        Set<E> union = new Set<E>();
        for (E e : this.hashTable.keySet()) 
            union.add(e);
        for (E e : s) 
            union.add(e);
        return union;
    };

    public Set<E> intersection(Set<E> s) { 
        Set<E> intersection = new Set<E>();
        for (E e : this.hashTable.keySet())  { 
            if (s.contains(e))
                intersection.add(e);
        }
        return intersection;
    }

    public Set<E> difference(Set<E> s) { 
        Set<E> diff = new Set<E>();
        for (E e : this.hashTable.keySet())  { 
            if (!s.contains(e))
                diff.add(e);
        }
        return diff;
    }

    @Override
    public String toString() { 
        return hashTable.keySet().toString();
    }
}
