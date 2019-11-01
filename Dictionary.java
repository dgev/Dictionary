package homework_7;

import java.util.*;

public class Dictionary<K, V> implements Map {

	private K key;
	private V value;
	private final int capacity;
	private int size = 0;
	private DictionaryObject<K, V>[] objects;

	public Dictionary(int capacity) {
		this.capacity = capacity;
		objects = new DictionaryObject[capacity];
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
		return this.size;
	}

	private DictionaryObject<K, V>[] getArr() {
		return objects;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for (DictionaryObject<K, V> obj : objects) {
			if (objects.length > 0 && obj != null) {
				return false;
			}
		}
		return true;
	}

	public int keyToHash(Object key) {
		return Math.abs(key.hashCode() % capacity);
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		if (objects[keyToHash(key)] != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null && obj.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		if (objects[keyToHash(key)] != null) {
			return objects[keyToHash(key)].getValue();
		}
		return null;
	}

	@Override
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		int current_key = keyToHash(key);
		DictionaryObject<K, V> obj = new DictionaryObject(key, value, null);
		objects[current_key] = obj;
		size++;
		return obj;
	}

	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null && obj.getKey().equals(key)) {
				V removed = obj.getValue();
				obj.setKey(null);
				obj.setValue(null);
				return removed;
			}
		}
		return null;
	}

	@Override
	public void putAll(Map m) {
		Iterator<K> iterator = m.keySet().iterator();
		while (iterator.hasNext()) {
			K key = iterator.next();
			put(key, m.get(key));
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			objects[i] = null;
		}
	}

	@Override
	public Set keySet() {
		Set array = new HashSet();
		for (int i = 0; i < size; i++) {
			if (objects[i] != null) {
				array.add(objects[i].getKey());
			}
		}
		return array;
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		ArrayList<V> list = new ArrayList<V>();
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null) {
				list.add(obj.getValue());
			}
		}
		return list;
	}

	@Override
	public Set entrySet() {
		Set set = new HashSet(size);
		for (int i = 0; i < size; i++) {
			if (objects[i] != null) {
				set.add(objects[i]);
			}
		}
		return set;
	}

	public static void main(String[] args) {

		Dictionary<String, String> dict = new Dictionary<String, String>(6);

//        dict.put(1, "value");

		dict.put("4", "value2");
		dict.put("5", "value6");
		dict.put("6", "value8");
		Map<String, String> dicto = new HashMap<String, String>(6);

//      dict.put(1, "value");

		dicto.put("1", "valuee");
		dicto.put("2", "valueferf");
		dicto.put("3", "valuefjyrg");

		Set set;
		set = dict.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(key);
		}
		System.out.println(set.iterator());

		Set entry = dict.entrySet();
		for (Object number : entry) {
			System.out.println("Number = " + number);
		}

		dict.putAll(dicto);
//		System.out.println(dict.isEmpty());
//		System.out.println(dict.size());
		Collection arrlist = dict.values();
		
		for (Object number : arrlist) {
	         System.out.println("Number = " + number);
	      } 

		DictionaryObject<String, String>[] obj = dict.getArr();
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				System.out.println(obj[i].getValue());
			}
		}
	}

}
