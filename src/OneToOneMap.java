import java.util.HashMap;
import java.util.Set;

public class OneToOneMap<K, V> extends HashMap<K, V> {
    private HashMap<V, K> reverseMap;

    public OneToOneMap() {
        reverseMap = new HashMap<>();
    }

    public Set<V> valueSet() {
        return reverseMap.keySet();
    }

    public K getReverse(V v) {
        return reverseMap.get(v);
    }

    @Override
    public V put(K k, V v) {
        if (reverseMap.containsKey(v)) {
            System.out.println(v + " is already mapped to " + reverseMap.get(v));
            return null;

        } else {
            reverseMap.put(v, k);
            super.put(k, v);
            return v;
        }
    }

    @Override
    public V remove(Object key) {
        V v = super.get(key);
        reverseMap.remove(v);
        super.remove(key);
        return v;
    }

    @Override
    public V replace(K k, V newV) {
        V oldV = super.get(k);
        reverseMap.remove(oldV);
        reverseMap.put(oldV, k);
        super.replace(k, newV);
        return newV;
    }

}
