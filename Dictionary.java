package homework_7;

import java.util.*;

public class Dictionary<K, V> implements Map {

	private K key;
	private V value;
	private final int capacity;
	private int size = 0;
	private DictionaryObject<K, V>[] objects;

	//creates a new dictionary with a fixed capacity
	public Dictionary(int capacity) {
		this.capacity = capacity;
		objects = new DictionaryObject[capacity];
	}
	
	//gets the key
	public K getKey() {
		return key;
	}
	
	//sets the key
	public void setKey(K key) {
		this.key = key;
	}
	
	//gets the value	
	public V getValue() {
		return value;
	}
	
	//sets the value
	public void setValue(V value) {
		this.value = value;
	}
	
	//gets the size of the dictionary
	public int size() {
		return this.size;
	}
	
	//returns an array of type DictionaryObject<K, V> incluing the objects of the dictionary
	private DictionaryObject<K, V>[] getArr() {
		return objects;
	}

	//gets the object with the corresponding key in the parameter
	private DictionaryObject<K, V> getObject(Object key) {
		DictionaryObject<K, V> current = objects[keyToHash(key)];
		if (current != null) {
			if (current.getKey().equals(key)) {
				return current;
			}
			current = current.getNext();
			while (current != null) {
				if (current.getKey().equals(key)) {
					return current;
				}
				current = current.getNext();
			}
		}
		return null;
	}
	
	//checks if the dictionary is empty or not
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for (DictionaryObject<K, V> obj : objects) {
			if (size > 0 && obj != null) {
				return false;
			}
		}
		return true;
	}
	
	//finds the hashcode of the key
	public int keyToHash(Object key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	//checks if the dictionary contains the given key
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		DictionaryObject<K, V> obj = getObject(key);
		if (obj != null) {
			return true;
		}
		return false;
	}
	
	//checks if the dictionary contains the given value
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null) {
				if (obj.getValue().equals(value)) {
					return true;
				}
				obj = obj.getNext();
				while (obj != null) {
					if (obj.getValue().equals(value)) {
						return true;
					}
					obj = obj.getNext();
				}
			}
		}
		return false;
	}
	
	//fetches the value mapped by a particular key mentioned in the parameter
	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		DictionaryObject<K, V> current = getObject(key);
		if (current != null) {
			return current.getValue();
		}
		return null;
	}
	
	//inserts a mapping into the dictionary
	@Override
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		if (getObject(key) == null) {
			int current_key = keyToHash(key);
			DictionaryObject<K, V> obj = new DictionaryObject(key, value, null);
			DictionaryObject<K, V> current = objects[current_key];
			if (current == null) {
				objects[current_key] = obj;
			} else {
				while (current.getNext() != null) {
					current = current.getNext();
				}
				current.setNext(obj);
			}
			size++;
			return obj;
		}
		return null;
	}
	
	//accepts a key as a parameter and removes the corresponding value mapped to the key
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub

		DictionaryObject<K, V> object = getObject(key);
		if (object != null) {
			size--;
			V removed = object.getValue();
			DictionaryObject<K, V> next = object.getNext();
			if (object.equals(objects[keyToHash(key)])) {
				if (next != null) {
					DictionaryObject<K, V> last = next.getNext();
					while (next.getNext() != null && last.getNext() != null) {
						next = next.getNext();
					}
					if (last == null) {
						object.setKey(next.getKey());
						object.setValue(next.getValue());
						object.setNext(null);
					} else {
						object.setKey(last.getKey());
						object.setValue(last.getValue());
						next.setNext(null);
					}
				}else {
					objects[keyToHash(key)] = null;
				}
			} else {
				DictionaryObject<K, V> previous = objects[keyToHash(key)];
				while (!previous.getNext().equals(object)) {
					previous = previous.getNext();
				}
				if (next == null) {
					previous.setNext(null);
				} else {
					object.setNext(null);
					previous.setNext(next);
				}
			}
			return removed;
		}
		return null;
	}
	
	//copies all of the elements i.e., the mappings, from one map into another
	@Override
	public void putAll(Map m) {
		Iterator<K> iterator = m.keySet().iterator();
		while (iterator.hasNext()) {
			K key = iterator.next();
			put(key, m.get(key));
		}
	}
	
	//removes all items from the dictionary
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			objects[i] = null;
		}
	}
	
	//creates a set out of the key elements contained in the hash map
	@Override
	public Set keySet() {
		Set array = new HashSet();
		for (int i = 0; i < size; i++) {
			if (objects[i] != null) {
				array.add(objects[i].getKey());
				DictionaryObject<K, V> next = objects[i].getNext();
				while (next != null) {
					array.add(next.getKey());
					next = next.getNext();
				}
			}
		}
		return array;
	}

	//returns a list of values from the dictionary
	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		ArrayList<V> list = new ArrayList<V>();
		for (DictionaryObject<K, V> obj : objects) {
			if (obj != null) {
				list.add(obj.getValue());
				DictionaryObject<K, V> next = obj.getNext();
				while (next != null) {
					list.add(next.getValue());
					next = next.getNext();
				}
			}
		}
		return list;
	}
	
	//creates a set out of the same elements contained in the hash map
	@Override
	public Set entrySet() {
		Set set = new HashSet(size);
		for (int i = 0; i < size; i++) {
			if (objects[i] != null) {
				set.add(objects[i]);
				DictionaryObject<K, V> next = objects[i].getNext();
				while (next != null) {
					set.add(next);
					next = next.getNext();
				}
			}
		}
		return set;
	}

	public static void main(String[] args) {

		Dictionary<String, String> dict = new Dictionary<String, String>(6);
		
		System.out.println(dict.isEmpty());

		dict.put("1", "value");
		dict.put("1", "anotherValue");
		dict.put("10", "value2");
		dict.put("7", "value8");

//		System.out.println(dict.size());

//		System.out.println(dict.get("1"));
//		System.out.println(dict.get("7"));

//		System.out.println(dict.containsValue("value"));

//		Map<String, String> dicto = new HashMap<String, String>(6);
//		dicto.put("1", "valuee");
//		dicto.put("2", "valueferf");
//		dicto.put("3", "valuefjyrg");

//		dict.putAll(dicto);

//		dict.remove("7");
//		System.out.println(dict.get("1"));

//		DictionaryObject<String, String>[] obj = dict.getArr();
//		for (int i = 0; i < obj.length; i++) {
//			if (obj[i] != null) {
//				System.out.println(obj[i].getValue());
//				DictionaryObject<String, String> nexObject = obj[i].getNext();
//				while (nexObject != null) {
//					System.out.println(nexObject.getValue());
//					nexObject = nexObject.getNext();
//				}
//			}
//		}

//		Set set;
//		set = dict.keySet();
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			System.out.println(key);
//		}

//		Set entry = dict.entrySet();
//		for (Object object : entry) {
//			System.out.println("DictionaryObject = " + object);
//		}

//		System.out.println(dict.isEmpty());
//		System.out.println(dict.size());

//		Collection arrlist = dict.values();
//
//		for (Object object : arrlist) {
//			System.out.println("Value is " + object);
//		}

	}
}
