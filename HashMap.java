import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

    private long			getLoops;
    private long			putLoops;
    private int       tableSize;

    private List< List<Entry<K,V>> > 	table;
    private int			count;

    public HashMap() {
        tableSize = 1000003; // prime number
        table = new ArrayList<List<Entry<K,V>>>(tableSize);
        // initializes table as a list of empty lists
        for (int i = 0; i < tableSize; i++)
            table.add(new LinkedList<Entry<K,V>>());
        count = 0;

        resetGetLoops();
        resetPutLoops();
    }

    public long getGetLoopCount() {
        return getLoops;
    }

    public long getPutLoopCount() {
        return putLoops;
    }

    public void resetGetLoops() {
        getLoops = 0;
    }
    public void resetPutLoops() {
        putLoops = 0;
    }

    public boolean containsKey(K key) {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
		List<Entry<K, V>> bucketList = table.get(index);
		
		Iterator<Entry<K, V>> it = bucketList.iterator();
		boolean contains = false;
		if (table.get(index).isEmpty() == true) {
			return contains;
		}		
		else {
			while (it.hasNext()) {
				if (bucketList.get(0).getKey().equals(key) == true) {
					contains = true;
					return contains;
				}
				else if (it.next().getKey().equals(key) == true) {
					contains = true;
					return contains;
				}
				else {
					contains = false;
					return contains;
				}
			}
		}
		
        return contains;
    }

    public V get (K key) throws KeyNotFoundException {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
		List<Entry<K, V>> bucketList = table.get(index);
		
		Iterator<Entry<K, V>> it = bucketList.iterator();

		if (table.get(index).isEmpty() == true) {
			throw new KeyNotFoundException();
		}		
		else {
			while (it.hasNext()) {
				Entry<K, V> e = it.next();
				if (e.getKey().equals(key) == true) {
					return e.getValue();
				}

				else {
					throw new KeyNotFoundException();
				}
			}
		}			
        return null;
    }

    public List<Entry<K,V> >	entryList() {
        List <Entry<K,V>> l = new LinkedList<Entry<K,V>>();
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through every index in the table
        //      use a second Iterator to go through each element in a chain at a specific index
        //      and add each element to l
		
				
		for (int i = 0; i < tableSize; i++) {
			List<Entry<K, V>> bucketList = table.get(i);
			Iterator<Entry<K, V>> it = bucketList.iterator();
			if (bucketList.isEmpty() != true) {
				while (it.hasNext()) {
					l.add(it.next());
				}
			}
		}

        return l;
    }

    public void put (K key, V value){
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode())%tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        //  if key is found, update value.  if key is not found add a new Entry with key,value
        // The tester expects that you will add the newest Entry to the END of the list
		
		List<Entry<K, V>> bucketList = table.get(index);
		Entry<K, V> newEntry = new Entry(key, value);
		// System.out.println("entry: " + newEntry);
		int i = 0;
		if (table.get(index).isEmpty() == true) {
			count++;
			bucketList.add(newEntry);
		}

		else {
			Iterator<Entry<K, V>> it = bucketList.iterator();
			while (it.hasNext()) {
				Entry<K, V> e = it.next();
				if (e.getKey().equals(key) == true) {
					e.setValue(value);
				}
				else {
					bucketList.add(newEntry);
					count++;
				}
			}
		}
    }

    public int size() {
        return count;
    }

    public void clear() {
        table.clear();
        count = 0;
    }

}
