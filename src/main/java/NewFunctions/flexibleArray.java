package NewFunctions;

/**
 * @author Joachim
 */
/**
 * An array that grows when filled.
 *
 * @param <T> the type of the array elements
 */
public class flexibleArray<T> {

    private T[] less = null;
    private T[] more = null;
    private int split = 0;
    private T tempT;

    public void add(T element) {
        if (more == null) {
            more = (T[]) new Object[]{element};
            return;
        }
        if (split == 0) {
            less = more;
            more = (T[]) new Object[less.length << 1];
            split = less.length;
        }
        more[more.length - split--] = element;
        more[split] = less[split];
    }

    public T get(int index) {
        return index < split ? less[index] : more[index];
    }

    public void set(int index, T element) {
        if (index >= size()) {
            add(element);
        } else if (index < split) {
            less[index] = element;
        } else {
            more[index] = element;
        }
    }

    public int size() {
        return more.length - split;
    }

    public boolean contains(T element) {
        return false;
    }
}
