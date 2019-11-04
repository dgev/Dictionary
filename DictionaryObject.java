
public class DictionaryObject<K, V> {
    private K key;
    private V value;
    private DictionaryObject<K, V> next;

    //constructor
    public DictionaryObject(K key, V value, DictionaryObject<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    //gets the value of the corresponding key in the dictionary
    public V getValue() {
        return this.value;
    }

    //sets a new value
    public void setValue(V value) {
        this.value = value;
    }

    //gets the keys of the dictionary
    public K getKey() {
        return this.key;
    }

    //sets a new key
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
