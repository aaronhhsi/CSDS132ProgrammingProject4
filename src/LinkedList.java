import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes. The list is Iterable to indicate
 that we can iterate over the data in the list.
 */
public class LinkedList<T> implements Iterable<T> {
    /** the first node of the list, or null if the list is empty */
    private LLNode<T> firstNode;
    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }
    /**
     * Returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }
    /**
     * Changes the front node.
     * @param node the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
        this.firstNode = node;
    }
    /**
     * Add an element to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new LLNode<T>(element, getFirstNode()));
    }
    /**
     * Return whether the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }
    /**
     * Returns the length of the linked list
     * @return the number of nodes in the list
     */
    public int length() {
        int lengthSoFar = 0;
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }
    /**
     * Return an iterator that runs over the contents stored in the linked list
     *
     * @return an iterator for the linked list
     */
    public Iterator<T> iterator() {
        return new LLIterator<T>() {
            // keeps track of which node will store the next value of the iteration
            private LLNode<T> nextNode = getFirstNode();

            private LLNode<T> currentNode = getFirstNode();

            private LLNode<T> previousNode = getFirstNode();
            /**
             * Returns true if there is more data we can loop over and false if the loop
             reached the end of the list.
             * @return true if there is more data to loop over
             */
            @Override
            public boolean hasNext() {
                return nextNode != null;
            }
            /**
             * Returns the next value from the linked list in this iterator that loops over
             the list data.
             * @return the next value in this loop over the linked list data
             * @throws NoSuchElementException if next() is called after the loop reaches the
            end of the list
             */
            @Override
            public T next() {
                if (hasNext()) {
                    previousNode = currentNode;
                    currentNode = nextNode;
                    nextNode = nextNode.getNext();
                    return currentNode.getElement();
                }
                else {
                    return null;
                }
            }
            /**
             * Takes the inputted element and adds it to the list being iterated so that the element
             * occurs before the element that was just returned by the most recent call to next
             * @param element to be added
             * @throws NoSuchElementException if the list is empty or if next has not been called
             */
            @Override
            public void addBefore(T element) {
                if (getFirstNode() == null || nextNode == getFirstNode()) {
                    throw new NoSuchElementException();
                }
                else {
                    if (currentNode == getFirstNode()) {
                        setFirstNode(new LLNode<>(element, currentNode));
                        previousNode = getFirstNode();
                    } else {
                        previousNode.setNext(new LLNode<>(element, currentNode));
                        previousNode = previousNode.getNext();
                    }
                }
            }
            /**
             * Takes the inputted element and adds it to the list being iterated so that the element
             * occurs after the element that was just returned by the most recent call to next
             * @param element to be added
             */
            @Override
            public void addAfter(T element) {
                if (getFirstNode() == null || getFirstNode() == nextNode) {
                    setFirstNode(new LLNode<T>(element, null));
                    if (hasNext()) {
                        getFirstNode().setNext(nextNode);
                    }
                }
                else {
                    currentNode.setNext(new LLNode<>(element, nextNode));
                    nextNode = currentNode.getNext();
                }

            }
        };
    }
    /**
     * Stores a value in the linked list in the proper place in its "natural order".
     * The values in the list should already be stored in non-decreasing order,
     according to the "natural order"
     * of the type stored in the list.
     * @param element the element to insert
     * @param list the list to insert the element into
     */
    public static <S extends Comparable<? super S>> void insertInOrder(S element,
                                                                       LinkedList<S> list) {
        if (list.isEmpty())
            list.addToFront(element);
        else {
            LLNode<S> nodeptr = list.getFirstNode();
            while (nodeptr.getNext() != null &&
                    nodeptr.getNext().getElement().compareTo(element) < 0)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode<S>(element, nodeptr.getNext()));
        }
    }

    /**
     * Returns an ArrayList that contains the same elements of the linked list and in the same order
     * @return an ArrayList that contains the same elements of the linked list and in the same order
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        LLNode<T> nodeptr = this.getFirstNode();
        while (nodeptr != null) {
            list.add(nodeptr.getElement());
            nodeptr = nodeptr.getNext();
        }
        return list;
    }

    /**
     * Sets the values in the linked list to the inputted array list
     * @param list the inputted array list
     */
    public void setArrayList(ArrayList<T> list) {
        if (list != null) {
            LLNode<T> nodeptr = getFirstNode();
            for (T element : list) {
                nodeptr.setElement(element);
                nodeptr = nodeptr.getNext();
            }
        }
    }
}