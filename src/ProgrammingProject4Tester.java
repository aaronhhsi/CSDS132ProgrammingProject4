import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ProgrammingProject4Tester {

    /**
     * Test the LinkedList class
     */
    @Test
    public void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(new ArrayList<>(), list.toArrayList());                            // test zero

        list.setFirstNode(new LLNode<>(3,null));

        assertEquals(new ArrayList<>(Arrays.asList(3)), list.toArrayList());            // test one

        list.addToFront(2);
        list.addToFront(1);

        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3)), list.toArrayList());      // test many

        list.setArrayList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        assertEquals(Integer.valueOf(1),list.getFirstNode().getElement());
    }

    /**
     * Test that the addBefore method throws a NoSuchElementException when attempting to use on an empty list
     */
    @Test(expected = NoSuchElementException.class)
    public void testAddBeforeException() {
        LinkedList<Integer> list = new LinkedList<>();
        ((LLIterator<Integer>) list.iterator()).addBefore(1);
    }

    /**
     * Test that the addBefore method throws a NoSuchElementException when attempting to use before the next method is used
     */
    @Test(expected = NoSuchElementException.class)
    public void testAddBeforeException2() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        ((LLIterator<Integer>) list.iterator()).addBefore(1);
    }

    /**
     * Test the addBefore method
     */
    @Test
    public void testAddBefore() {
        // test one
        LinkedList<Integer> list = new LinkedList<>();
        list.addToFront(3);
        LLIterator<Integer> llIterator = (LLIterator<Integer>) list.iterator();
        llIterator.next();
        llIterator.addBefore(2);
        assertEquals(Integer.valueOf(2), list.getFirstNode().getElement());
        list.addToFront(2);
        list.addToFront(0);

        // test many
        llIterator = (LLIterator<Integer>) list.iterator();
        llIterator.next();
        llIterator.next();
        llIterator.addBefore(1);
        assertEquals(Integer.valueOf(1), list.getFirstNode().getNext().getElement());

    }

    /**
     * Test the addAfter method
     */
    @Test
    public void testAddAfter() {
        // test empty list
        LinkedList<Integer> list = new LinkedList<>();
        LLIterator<Integer> llIterator = (LLIterator<Integer>) list.iterator();
        llIterator.addAfter(0);
        assertEquals(Integer.valueOf(0), list.getFirstNode().getElement());

        // test next has not been called
        list.addToFront(3);
        llIterator = (LLIterator<Integer>) list.iterator();
        llIterator.addAfter(0);
        assertEquals(Integer.valueOf(0), list.getFirstNode().getElement());

        list.addToFront(2);
        list.addToFront(0);
        llIterator = (LLIterator<Integer>) list.iterator();
        llIterator.next();
        llIterator.addAfter(1);
        assertEquals(Integer.valueOf(1), list.getFirstNode().getNext().getElement());
    }

    /**
     * Test the LLIterator interface
     */
    @Test
    public void testIterator() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        LLIterator<Integer> llIterator = (LLIterator<Integer>) list.iterator();
        System.out.println(llIterator.next());
        System.out.println(llIterator.next());
        list.addToFront(0);
        llIterator = (LLIterator<Integer>) list.iterator();
        System.out.println(llIterator.next());
        System.out.println(llIterator.next());
    }

    /**
     * Test the WordData class
     */
    @Test
    public void testWordData() {
        GibberishWriter.WordData wordData = new GibberishWriter.WordData("word");
        assertEquals(1, wordData.getCount());
        wordData.incrementCount();
        assertEquals("word", wordData.getWord());
        assertEquals(2, wordData.getCount());
    }

    /**
     * Test the Context class
     */
    @Test
    public void testContext() {
        GibberishWriter.Context context = new GibberishWriter.Context(new String[]{"apple", "orange"});
        assertEquals(2, context.length());
        assertEquals("apple orange", context.toString());
        assertEquals("orange", context.getWord(1));
        assertEquals(true, context.equals(new GibberishWriter.Context(new String[]{"apple", "orange"})));
        assertEquals(1, context.compareTo(new GibberishWriter.Context(new String[]{"apple", "banana"})));
        assertEquals(0, context.compareTo(new GibberishWriter.Context(new String[]{"apple", "orange"})));
        assertEquals(-1, context.compareTo(new GibberishWriter.Context(new String[]{"apple", "pineapple"})));
    }

    /**
     * Test the ContextData class
     */
    @Test
    public void testContextData() {
        GibberishWriter.ContextData contextData = new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple", "orange"}));
        assertEquals("apple orange", contextData.getContext().toString());
        assertEquals(0, contextData.numOccurrences());
        assertEquals(1, contextData.compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple", "banana"}))));
        assertEquals(0, contextData.compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple", "orange"}))));
        assertEquals(-1, contextData.compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple", "pineapple"}))));
        contextData.addFollowingWord("be");
        contextData.addFollowingWord("be");
        contextData.addFollowingWord("the");
        contextData.addFollowingWord("the");
        contextData.addFollowingWord("the");
        contextData.addFollowingWord("take");
        contextData.addFollowingWord("suffer");
        assertEquals("be", contextData.getFollowingWord(1));
        assertEquals("be", contextData.getFollowingWord(2));
        assertEquals("suffer", contextData.getFollowingWord(3));
        assertEquals("take", contextData.getFollowingWord(4));
        assertEquals("the", contextData.getFollowingWord(5));
        assertEquals("the", contextData.getFollowingWord(6));
        assertEquals("the", contextData.getFollowingWord(7));
    }

    /**
     * Test that the getFollowingWord method throws a NoSuchElementException when the inputted value is less than 1
     */
    @Test(expected = NoSuchElementException.class)
    public void testGetFollowingWordException() {
        GibberishWriter.ContextData contextData = new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple", "orange"}));
        contextData.getFollowingWord(0);
    }

    /**
     * Test that the getFollowingWord method throws a NoSuchElementException when the inputted value is greater than the total WordData count
     */
    @Test(expected = NoSuchElementException.class)
    public void testGetFollowingWordException2() {
        GibberishWriter.ContextData contextData = new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple", "orange"}));
        contextData.addFollowingWord("be");
        contextData.addFollowingWord("be");
        contextData.addFollowingWord("the");
        contextData.addFollowingWord("the");
        contextData.addFollowingWord("the");
        contextData.addFollowingWord("take");
        contextData.addFollowingWord("suffer");
        contextData.getFollowingWord(8);
    }

    /**
     * Test the GibberishWriter class
     */
    @Test
    public void testGibberishWriter() {
        GibberishWriter gibberishWriter = new GibberishWriter(1);
        assertEquals(1, gibberishWriter.getContextSize());
        LinkedList<GibberishWriter.ContextData> contextDataLinkedList = new LinkedList<>();
        GibberishWriter.addContextData(new GibberishWriter.Context(new String[]{"apple"}), contextDataLinkedList);
        gibberishWriter.setContextDataArrayList(contextDataLinkedList.toArrayList());
        assertEquals(0, gibberishWriter.getContextData(0).compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"apple"}))));
        GibberishWriter.addContextData(new GibberishWriter.Context(new String[]{"mango"}), contextDataLinkedList);
        GibberishWriter.addContextData(new GibberishWriter.Context(new String[]{"banana"}), contextDataLinkedList);
        GibberishWriter.addContextData(new GibberishWriter.Context(new String[]{"coconut"}), contextDataLinkedList);
        GibberishWriter.addContextData(new GibberishWriter.Context(new String[]{"pineapple"}), contextDataLinkedList);
        GibberishWriter.addContextData(new GibberishWriter.Context(new String[]{"orange"}), contextDataLinkedList);
        gibberishWriter.setContextDataArrayList(contextDataLinkedList.toArrayList());
        assertEquals(0, gibberishWriter.getContextData(1).compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"banana"}))));
        assertEquals(0, gibberishWriter.getContextData(2).compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"coconut"}))));
        assertEquals(0, gibberishWriter.getContextData(3).compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"mango"}))));
        assertEquals(0, gibberishWriter.getContextData(4).compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"orange"}))));
        assertEquals(0, gibberishWriter.getContextData(5).compareTo(new GibberishWriter.ContextData(new GibberishWriter.Context(new String[]{"pineapple"}))));
    }
}
