package src.spellchecker;

/**
 * Represents an entry in the GTUHashMap.
 * 
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public class Entry<K, V> {
    public K key;
    public V value;
    public boolean isDeleted; // Indicates if the entry is a tombstone (deleted)

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }

    // Special constructor for the TOMBSTONE static instance
    private Entry(boolean isTombstone) {
        this.key = null;
        this.value = null;
        this.isDeleted = isTombstone; // True for tombstone
    }

    // A static final instance to represent a deleted entry (tombstone)
    @SuppressWarnings("rawtypes")
    public static final Entry TOMBSTONE = new Entry(true);

    @Override
    public String toString() {
        if (this == TOMBSTONE) {
            return "TOMBSTONE";
        }
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                ", isDeleted=" + isDeleted +
                '}';
    }
}