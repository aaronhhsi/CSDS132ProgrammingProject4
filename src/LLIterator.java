import java.util.Iterator;

/** An iterator for our LinkedList. The iterator loops over the data in the list from the first node to the last */
public interface LLIterator<T> extends Iterator<T> {
    @Override
    boolean hasNext();

    @Override
    T next();

    void addBefore(T element);

    void addAfter(T element);
}
