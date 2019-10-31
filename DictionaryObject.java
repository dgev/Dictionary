package Dictionary;

public class DictionaryObject<K, V> {
	private K key;
	private V value;

	public DictionaryObject(K key2, V value2) {
		this.key = key2;
		this.value = value2;
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

}
