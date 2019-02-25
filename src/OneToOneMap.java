import java.util.HashMap;
import java.util.Set;

/**
 * Would have been better to just use BidiMap from Apache Commons, but seeing as external deps aren't allowed...
 * Even then, it would be much better to implement Map than to extend HashMap, but that would be more work than the rest
 * of the actual assignment.
 * Middle ground!
 *
 * An implementation of a bidirectional map using the adapter pattern on HashMap to allow bidirectional lookup between
 * keys and values.
 * Allows us to enforce one-to-one letter mappings for the cryptogram.
 *
 * @param <K> Arbitrary type.
 * @param <V> Arbitrary type.
 */
public class OneToOneMap<K, V> extends HashMap<K, V> {
    private HashMap<V, K> reverseMap;

    /**
     * Default constructor
     */
    public OneToOneMap() {
        reverseMap = new HashMap<>();
    }

    /**
     * @return Equivalent to HashMap::values, except we know that it can return a Set rather than just a Collection
     */
    public Set<V> valueSet() {
        return reverseMap.keySet();
    }

    /**
     * Method to query the Map in the reverse direction.
     *
     * @param v Key to the reverse map
     * @return Value from the reverse map
     */
    public K getReverse(V v) {
        return reverseMap.get(v);
    }

    /**
     * Overriding HashMap::put to make it also put the key-value pair into the reverse map (in reverse, funnily enough).
     *
     * @param k
     * @param v
     * @return The "value" inserted. Unfortunate artifact of the adapter pattern.
     */
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

    /**
     * Overriding HashMap::put to make it also put the key-value pair into the reverse map (in reverse, funnily enough).
     * @param key
     * @return
     */
    @Override
    public V remove(Object key) {
        V v = super.get(key);
        reverseMap.remove(v);
        super.remove(key);
        return v;
    }

    /**
     *
     * @param k
     * @param newV
     * @return
     */
    @Override
    public V replace(K k, V newV) {
        V oldV = super.get(k);
        reverseMap.remove(oldV);
        reverseMap.put(oldV, k);
        super.replace(k, newV);
        return newV;
    }

}
