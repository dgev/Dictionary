
public class DictionaryObject<K, V> {
    private K key;
    private V value;
    private DictionaryObject<K, V> next;

    public DictionaryObject(K key, V value, DictionaryObject<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public DictionaryObject<K, V> getNext() {
        return this.next;
    }

    public void setNext(DictionaryObject<K, V> next) {
        this.next = next;
    }

}
