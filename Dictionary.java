package example;

public class Dictionary<K, V> {

	private K key;
	private V value;
	private final int size;
	private DictionaryObject<K, V>[] objects;

	public Dictionary(int size) {
		this.size = size;
		objects = new DictionaryObject[size];
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int size() {
		return objects.length;
	}

	public boolean isEmpty() {
		for (DictionaryObject<K, V> obj : objects) {
			if (objects.length > 0 && obj != null) {
				return false;
			}
		}
		return true;
	}

	public boolean containsKey(K key) {
		if (objects[key.hashCode() % size] != null) {
			return true;
		}

		return false;

	}

	public boolean containsValue(V value) {
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null && obj.getValue().equals(value)) {
				return true;
			}
		}
		return false;

	}

	public V get(K key) {
		if (objects[key.hashCode() % size] == null) {
			return null;
		}
		return objects[key.hashCode() % size].getValue();
	}

	public void put(K key, V value) {
		int current_key = key.hashCode() % size;
		DictionaryObject<K, V> obj = new DictionaryObject<K, V>(key, value);
		objects[current_key] = obj;
	}

	public V remove(K key) {
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null && obj.getKey().equals(key.toString())) {
				V removed = obj.getValue();
				objects[key.hashCode() % size] = null;
				return removed;
			}
		}
		return null;

	}

	public void clear() {
		for (int i = 0; i < objects.length; i++) {
			objects[i] = null;
		}
	}

	public String[] entrySet() {
		String[] array = new String[3];
		for (int i = 0; i < objects.length; i++) {
			array[i] = " Key " + (objects[i].getKey().toString()) + " Value " + objects[i].getValue();

		}
		return array;
	}

	public String[] getKeys() {
		String[] array = new String[3];
		for (int i = 0; i < objects.length; i++) {
			array[i] = objects[i].getKey().toString();
		}
		return array;
	}

	public boolean equals(Dictionary<K, V> newDict) {
		if (objects.length == newDict.size()) {
			for (int i = 0; i < size; i++) {
				if (newDict.getArr()[i] != null && objects[i] != null) {
					if (newDict.getArr()[i].getValue() != objects[i].getValue()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Dictionary<Integer, String> dict = new Dictionary<Integer, String>(3);
		dict.put(1, "value");
		dict.put(2, "value2");
//		Dictionary <Integer, Integer> dict = new Dictionary<Integer, Integer>(3);
//		dict.put(1, 5);
//		System.out.println(dict.containsKey(1));
//		System.out.println(dict.get(1));
//		System.out.println(dict.containsValue("value"));
		System.out.println(dict.isEmpty());
		dict.clear();
		System.out.println(dict.isEmpty());
//		dict.remove(1);
//		System.out.println(dict.remove(1));

	}

}
