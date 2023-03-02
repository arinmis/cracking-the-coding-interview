package datastructures;

import java.util.Random; 
import java.util.Iterator;
        
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

    /* 
     * select table size prime number
     * to reduce collision 
     *
     * use linear probing
     * maintain load factor: λ < 0.9
     * to do that resize table
     * recompute compression function
     *
     * */

    /* store each key-value pair */
    private static class Entry<K, V> { 
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
        /*
        if (n > capacity / 2) // load <= .5
            resize(2 * capacity - 1); // 2 * n - 1
        */
        return answer;
    }

    /*
    public Entry<K, V>[] entrySet() {
        Entry<K, V>[] set = new Entry<K, V>[n];  
        return set;
    }
    */

    /*
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


    /* return talbe entries in a table 
    public Entry[] entrySet() {}
    */

    /* private helper methods 
    private void resize(int newCapacity) { 
        Entry<K, V>[] backup = new Entry<>[n]; 
        for (Entry<K, V> entry : entrySet())
            backup.add(entry);
        capacity = newCapacity;
        createTable();
        n = 0;
        for (Entry<K, V> entry : backup)
            put(entry.getKey(), entry.getValue());
    }
    */

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

    /*
    private V  bucketGet(int h, K k) {}
    private V bucketRemove(int h, K k) {};
    */

    /* put entry in a bucket called h 
        private E[] entrySet();
        public boolean isEmpty();
    */


    /* 
     * use Multiply-Add-and-Divide method 
     * ((a * i) % p) % N
     * where 0 < a,b < p < N
    public int compressionFunction(int hashCode);
    */
    /*
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<Entry<K,V>> entrySet( );
    */

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
        public Entry next() {
            Entry entry = bucketIterator.next();  
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
        System.out.print("buckets sizes: asdf");
        for (SinglyLinkedList<Entry<K, V>> bucket : table) { 
            // skip null buckets
            if (bucket == null) {
                System.out.print("-,");
                continue; 
            }
            Iterator<Entry<K, V>> iterator = bucket.iterator(); 
            int count = 0;
            while (iterator.hasNext()) { 
                count++;
                iterator.next();
             }
             System.out.print(count + ",");
        }
        System.out.println();
    }


}
