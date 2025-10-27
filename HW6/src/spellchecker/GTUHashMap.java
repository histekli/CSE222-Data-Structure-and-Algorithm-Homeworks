// File: GTUHashMap.java
package src.spellchecker;

/**
 * Custom HashMap implementation using open addressing with linear probing.
 * Handles collisions, deletion via tombstones, and rehashing.
 * 
 * @param <K> The type of keys maintained by this map.
 * @param <V> The type of mapped values.
 */
public class GTUHashMap<K, V> {
    private Entry<K, V>[] table;
    private int size; // Number of actual key-value pairs
    private int capacity;
    private static final int INITIAL_CAPACITY = 16; // Must be a power of 2 for bitwise AND trick, or use prime for
                                                    // modulo
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private int tombstones; // Number of tombstones

    @SuppressWarnings("unchecked")
    public GTUHashMap() {
        this.capacity = findNextPrime(INITIAL_CAPACITY);
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
        this.size = 0;
        this.tombstones = 0;
    }

    @SuppressWarnings("unchecked")
    public GTUHashMap(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        this.capacity = findNextPrime(initialCapacity);
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
        this.size = 0;
        this.tombstones = 0;
    }

    private int hash(K key) {
        if (key == null)
            return 0; // Or throw IllegalArgumentException
        return Math.abs(key.hashCode()) % capacity;
    }

    // Helper for finding an index for a key.
    // Returns index if key found or an empty/tombstone slot is found for insertion.
    // For linear probing, the step is i. For quadratic probing, the step would be
    // i*i.
    private int findIndex(K key, boolean forInsert) {
        int index = hash(key);
        int i = 0; // Probe attempt counter
        int firstTombstoneIndex = -1;

        while (true) {
            int probeIndex = (index + i) % capacity; // Linear probing step: i

            // For Quadratic probing, replace with:
            // int probeIndex = (index + i * i) % capacity;

            if (table[probeIndex] == null) {
                return (forInsert && firstTombstoneIndex != -1) ? firstTombstoneIndex : probeIndex;
            } else if (table[probeIndex] == Entry.TOMBSTONE) {
                if (forInsert && firstTombstoneIndex == -1) {
                    firstTombstoneIndex = probeIndex; // Remember first tombstone for insertion
                }
            } else if (table[probeIndex].key.equals(key)) {
                return probeIndex; // Key found
            }

            i++;
            if (i >= capacity) { // Should not happen if rehash works correctly
                // This means table is full or only contains tombstones and non-matching keys
                // If forInsert is true and we found a tombstone, we should have returned it
                // If forInsert is true and no tombstone, it means table is full of actual
                // entries
                return forInsert ? firstTombstoneIndex : -1; // Or throw an exception / rehash aggressively
            }
        }
    }

    /**
     * Adds or updates a key-value pair in the map.
     * 
     * @param key   The key to be added or updated.
     * @param value The value associated with the key.
     */
    public void put(K key, V value) {
        if (key == null) {
            // Decide on null key handling: throw exception or handle specially
            // For simplicity in this homework, let's assume keys are not null.
            // If allowed, a specific strategy for null key hashing/storage would be needed.
            System.err.println("Null keys are not supported in this GTUHashMap implementation.");
            return;
        }

        if ((double) (size + tombstones) / capacity >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }

        int index = findIndex(key, true);

        if (table[index] == null || table[index] == Entry.TOMBSTONE) {
            if (table[index] == Entry.TOMBSTONE) {
                tombstones--;
            }
            table[index] = new Entry<>(key, value);
            size++;
        } else { // Key already exists, update value
            table[index].value = value;
        }
    }

    /**
     * Retrieves the value associated with the given key.
     * 
     * @param key The key to search for.
     * @return The value associated with the key, or null if not found.
     */
    public V get(K key) {
        if (key == null)
            return null; // Consistent with not supporting null keys in put
        int index = findIndex(key, false);
        if (index != -1 && table[index] != null && table[index] != Entry.TOMBSTONE && table[index].key.equals(key)) {
            return table[index].value;
        }
        return null;
    }

    /**
     * Checks if the map contains the given key.
     * 
     * @param key The key to search for.
     * @return true if the key exists, false otherwise.
     */
    public boolean containsKey(K key) {
        if (key == null)
            return false;
        int index = findIndex(key, false);
        return index != -1 && table[index] != null && table[index] != Entry.TOMBSTONE && table[index].key.equals(key);
    }

    /**
     * Removes the key-value pair associated with the given key.
     * 
     * @param key The key to be removed.
     * @return The value associated with the removed key, or null if not found.
     */
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        if (key == null)
            return null;
        int index = findIndex(key, false);
        if (index != -1 && table[index] != null && table[index] != Entry.TOMBSTONE && table[index].key.equals(key)) {
            V oldValue = table[index].value;
            table[index] = (Entry<K, V>) Entry.TOMBSTONE;
            size--;
            tombstones++;
            // Optional: Consider rehashing if tombstones get too high relative to size
            // e.g., if (tombstones > size) rehash();
            return oldValue;
        }
        return null;
    }

    public int size() {
        return size;
    }

    /**
     * Rehashes the map to a larger capacity (next prime number).
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        int oldCapacity = capacity;

        capacity = findNextPrime(capacity * 2); // Next prime greater than twice current
        table = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
        tombstones = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldTable[i] != null && oldTable[i] != Entry.TOMBSTONE) {
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }

    // Helper for finding the next prime number greater than or equal to n.
    private int findNextPrime(int n) {
        if (n <= 1)
            return 2;
        if (n % 2 == 0)
            n++; // Start with an odd number
        while (true) {
            if (isPrime(n))
                return n;
            n += 2; // Check next odd number
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1)
            return false;
        if (num <= 3)
            return true;
        if (num % 2 == 0 || num % 3 == 0)
            return false;
        for (int i = 5; i * i <= num; i = i + 6) {
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        }
        return true;
    }

    /**
     * Prints the current state of the hash table (for debugging purposes).
     */
    public void printTable() {
        System.out.println("HashMap (Capacity: " + capacity + ", Size: " + size + ", Tombstones: " + tombstones + "):");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                System.out.println("Index " + i + ": null");
            } else if (table[i] == Entry.TOMBSTONE) {
                System.out.println("Index " + i + ": TOMBSTONE");
            } else {
                System.out.println("Index " + i + ": Key=" + table[i].key + ", Value=" + table[i].value);
            }
        }
    }

    // Retrieves all keys in the map as a GTUArrayList.
    public GTUArrayList<K> getKeys() {
        GTUArrayList<K> keys = new GTUArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i] != Entry.TOMBSTONE) {
                keys.add(table[i].key);
            }
        }
        return keys;
    }
}