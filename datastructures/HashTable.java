package datastructures;

import java.util.Random; 
import java.util.Iterator;
        
/* 
 * Hash Table implementation with 
 * `Seperate Chaining` collision handling
 *
 * When load factor greater than 0.5(λ > 0.5)
 * table resize with formula: 2 * capacity - 1
 * and all the previous entries rehashed and placed 
 * to the new table
 *
 * */
public class HashTable<K, V> implements Iterable { 
    private int n = 0;      // #entires
    private int capacity;   
    private int prime;      // prime factor 
    private long scale, shift; 


    // store table inside array, each contains bucket entry list
    private  SinglyLinkedList<Entry<K, V>>[] table;

    public HashTable(int capacity, int prime) { 
        this.capacity = capacity;
        this.prime = prime;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }


    public HashTable(int capacity) { 
        this(capacity, 109345121); // default prime
    }

    public HashTable() { 
        this(17); // default capacity
    }


    /* store each key-value pair */
    public static class Entry<K, V> { 
        private K k;
        private V v;

        public Entry(K key, V value) { 
            k = key;
            v = value;
        }

        public K getKey() { return k;}
        public V getValue() { return v;}

        public V setValue(V value) { 
            V old = v;
            this.v = value;
            return old;
        }

        @Override
        public String toString() { 
            return "(" + k + ", " + v + ")";
        }
    }
    
    public int size() { 
        return n;
    }

    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) // load > .5
            resize(2 * capacity - 1); // 2 * n - 1
        return answer;
    }

    public V get(K key) { 
        return bucketGet(hashValue(key), key);
    }

    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    /* implement Cyclic-Shift Hash Code */
    public int hashValue(K key) { 
        return (int)((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    };

    /* private helper methods */
    private void resize(int newCapacity) { 
        Entry<K, V>[] backup = (Entry<K, V>[])(new Entry[n]); 
        Iterator<Entry<K, V>> iterator = this.iterator(); 
        int i = 0;
        while (iterator.hasNext()) { 
            backup[i] = iterator.next();
            i++;
        }
        capacity = newCapacity;
        createTable();
        n = 0;
        for (Entry<K, V> entry : backup)
            put(entry.getKey(), entry.getValue());
    }

    /* create empty table */
    private void createTable() {
        table  = new SinglyLinkedList[capacity];
    }

    /* place entry into bucket, return old value */
    private V bucketPut(int h, K k, V v) {
        SinglyLinkedList<Entry<K, V>> bucket = table[h];
        //  init empty bucket
        if (bucket == null) {  
            bucket = table[h] = new SinglyLinkedList<Entry<K, V>>();
        } 
        Iterator<Entry<K, V>> iterator = bucket.iterator(); 
        while (iterator.hasNext()) { 
            Entry<K, V> entry = iterator.next();
            // key already exist, update value
            if (k.equals(entry.getKey())) { 
                V oldValue = (V)entry.getValue();
                entry.setValue(v);
                return oldValue;
            }
         }
        bucket.append(new Entry(k, v));
        n++; 
        return null; // new element inserted, no old value
    }

    private V bucketGet(int h, K k) {
        if (table[h] == null) return null; // bucket does not exists
        Iterator<Entry<K, V>> iterator = table[h].iterator();
        // search in the bucket
        while (iterator.hasNext()) { 
            Entry<K, V> entry = iterator.next();
            if (k.equals(entry.getKey()))
                    return entry.getValue();
        }
        return null;

    }

    /*
     * TODO: remove entry element when key match
     * make it O(n) instead of O(2N) 
    */
    private V bucketRemove(int h, K k) {
        if (table[h] == null) return null; // bucket does not exists
        // search in the bucket
        int i = 0;
        Iterator<Entry<K, V>> iterator = table[h].iterator();
        // find remove index
        while (iterator.hasNext()) { 
            Entry<K, V> entry = iterator.next();
            if (k.equals(entry.getKey()))
                break;
            i++;
        }
        Entry<K, V> removed = table[h].remove(i);
        // if no element empty the bucket
        if (table[h].size() == 0)
            table[h] = null;
        return removed.getValue();
    };

    public Iterable<K> keySet() { 
        Iterator<Entry<K, V>> iterator = this.iterator();
        SinglyLinkedList<K> keyList = new SinglyLinkedList<K>();
        // search in the bucket
        while (iterator.hasNext()) { 
            Entry<K, V> entry = iterator.next();
            keyList.append(entry.getKey());
        }
        return keyList;
    };

    public Iterable<V> valueSet() { 
        Iterator<Entry<K, V>> iterator = this.iterator();
        SinglyLinkedList<V> valueList = new SinglyLinkedList<V>();
        // search in the bucket
        while (iterator.hasNext()) { 
            Entry<K, V> entry = iterator.next();
            valueList.append(entry.getValue());
        }
        return valueList;
    }

    public class CustomIterator<K, V> implements Iterator<Entry> {
        Iterator<Entry<K, V>> bucketIterator;
        SinglyLinkedList<Entry<K, V>>[] table;
        int bucketIndex;
          
        // constructor
        public CustomIterator(SinglyLinkedList<Entry<K, V>>[] table) {
            this.table = table;
            bucketIterator = findNextBucketIterator(0);
        }
          
        // Checks if the next element exists
        public boolean hasNext() {
            return bucketIndex >= 0 && bucketIndex < table.length;
        }

        /* return next Buckets iterator */ 
        private Iterator<Entry<K, V>> findNextBucketIterator(int start) { 
            for (int i = start; i < table.length; i++) { 
                if (table[i] != null) {
                    bucketIndex = i;
                    return table[bucketIndex].iterator();
                }
            }
            bucketIndex = table.length;
            return null;
        }
          
        // moves the cursor iterator to next element
        public Entry<K, V> next() {
            Entry<K, V> entry = bucketIterator.next();  
            // when there is no entry, find new bucket 
            if (!bucketIterator.hasNext()) { 
                bucketIterator = findNextBucketIterator(bucketIndex + 1);
            }
            return entry;
        }
          
        // Used to remove an element. Implement only if needed
        public void remove() {
            // Default throws UnsupportedOperationException.
        }
    }


    // code for data structure
    public Iterator iterator() {
        return new CustomIterator(this.table);
    }

    @Override
    public String toString() { 
        DynamicString dStr = new DynamicString("[ ");
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) { 
            dStr.append(iterator.next() + " ");
        }
        dStr.append("]");
        return dStr.toString();
    }


    public void printTableDensity() { 
        System.out.print("buckets sizes: ");
        for (SinglyLinkedList<Entry<K, V>> bucket : table) { 
            // skip null buckets
            if (bucket == null) {
                System.out.print("-,");
                continue; 
            }
            System.out.print(bucket.size() + ",");
        }
        System.out.println();
    }

}
