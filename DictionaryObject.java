package example;

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
	
	public String getKey() {
		return this.key.toString();
	}

}
