import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/** a Gibberish Writer */
public class GibberishWriter implements Iterator<String> {
    /** the context size */
    private int contextSize;

    /** an LinkedList of ContextData */
    private ArrayList<ContextData> contextDataArrayList;

    /** the last context */
    private ContextData lastContextData;

    /**
     * Creates a GibberishWriter with the given context size
     * @param contextSize the context size of the GibberishWriter
     */
    public GibberishWriter(int contextSize) {
        this.contextSize = contextSize;
    }

    /**
     * Returns the context size
     * @return the context size
     */
    public int getContextSize() {
        return contextSize;
    }

    /**
     * Gets the context data from the Array List of context data at the inputted index
     * @param index the index of the desired context data
     * @return the context data at the inputted index
     */
    public ContextData getContextData(int index) {
        return contextDataArrayList.get(index);
    }

    /**
     * Sets the Array List of context data to the inputted Array List of context data
     * @param contextDataArrayList the Array List of context data
     */
    public void setContextDataArrayList(ArrayList<ContextData> contextDataArrayList) {
        this.contextDataArrayList = contextDataArrayList;
    }

    /**
     * Returns the last context data
     * @return the last context data
     */
    public ContextData getLastContextData() {
        return lastContextData;
    }

    /**
     * Sets the last context data to the inputted last context data
     * @param lastContextData the last context data
     */
    public void setLastContextData(ContextData lastContextData) {
        this.lastContextData = lastContextData;
    }

    /**
     * Runs the Gibberish Writer
     * @param args a file name for the input data, an integer to represent the context size, and an integer to represent the number of words in the output
     */
    public static void main(String[] args) {
        /** Creates a Gibberish Writer instance with the appropriate context size */
        GibberishWriter gibberishWriter = new GibberishWriter(Integer.parseInt(args[1]));
        /** Calls the addDataFile method on the file name (error message for invalid file name in method) */
        gibberishWriter.addDataFile(args[0]);
        /** Prints the desired number of output words */
        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
            System.out.println(gibberishWriter.next());
        }
    }

    /**
     * Takes a Context and a sorted LinkedList storing ContextData as input and returns a ContextData. If the list
     * contains a ContextData instance for the input Context value, the ContextData instance is returned. If not, a
     * new ContextData instance is created for the Context at the point where it should be and is returned
     * @param context the context to be added
     * @param list the sorted LinkedList containing ContextData
     * @return the ContextData instance for the inputted context
     */
    public static ContextData addContextData(Context context, LinkedList<ContextData> list) {
        /** Creates an iterator for the LinkedList */
        LLIterator<ContextData> contextDataLLIterator = (LLIterator<ContextData>) list.iterator();
        ContextData contextData = contextDataLLIterator.next();
        /** If the list is empty, the first value of the list is set to the newly created ContextData for the
         * inputted Context
         */
        if (contextData == null) {
            list.addToFront(new ContextData(context));
        }
        else {
            /** Linearly searches for the correct location of the ContextData for the Context */
            while (contextData != null) {
                int compare = context.compareTo(contextData.getContext());

                if (compare == 0) {
                    return contextData;
                } else if (compare < 0) {
                    contextDataLLIterator.addBefore(new ContextData(context));
                    contextData = null;
                } else {
                    contextData = contextDataLLIterator.next();
                    if (contextData == null) {
                        contextDataLLIterator.addAfter(new ContextData(context));
                    }
                }
            }
        }
        return new ContextData(context);
    }

    /**
     * Takes a String fileName as input and does the following:
     * 1. Creates a LinkedList that stores the ContextData of the ArrayList field, in the same order.
     * 2. Open the file with the name fileName and prepares to read the contents of the file word by word
     * 3. Create a Context that we will call the current context from the first context size words of the file
     * 4. Keep reading words from the file until there are no more words. With each word read, calls the addContextData
     *     method with the current context and the LinkedList as inputs to get a ContextData for the current context.
     *     Adds the just read word to the ContextData as a following word. Finally, adjusts the current context by
     *     shifting the words of the current context down one index (thus dropping the first word of the context) and
     *     making the just read word the last work of the current context
     * 5. When all the words have been read, calls the toArrayList method of the LinkedList and save the returned list
     *     to the ArrayList field.
     * @param fileName the fileName of the file to be scanned
     */
    public void addDataFile(String fileName) {
        /** Creates a LinkedList that stores the ContextData of the ArrayList field, in the same order */
        LinkedList<ContextData> contextDataLinkedList = new LinkedList<>();
        contextDataLinkedList.setArrayList(contextDataArrayList);
        try {
            /** Opens the file with the name fileName and prepares to read the contents of the file word by word */
            Scanner input = new Scanner(new FileReader(fileName));
            input.useDelimiter("\\W+");

            /** Creates a Context "currentContext" from the first "contextSize" words of the file */
            ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < contextSize; i++) {
                words.add(input.next().toLowerCase().replaceAll("[^a-z]", ""));
            }
            input.reset();
            Context currentContext = new Context(Arrays.copyOf(words.toArray(), words.size(), String[].class));

            /** Keeps reading words from the file until there are no more words */
            while (input.hasNext()) {
                String word = input.next().toLowerCase().replaceAll("[^a-z]", "");
                addContextData(currentContext, contextDataLinkedList).addFollowingWord(word);
                currentContext.moveDown(word);
            }
            /** Saves the returned list to the ArrayList field */
            contextDataArrayList = contextDataLinkedList.toArrayList();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Returns true if there is more data we can loop over and false if the ArrayList is empty
     * @return true if the ArrayList is not empty
     */
    @Override
    public boolean hasNext() {
        return contextDataArrayList != null;
    }

    /**
     * Looks up the last context from the ArrayList containing ContextData. Chooses a random word to follow that context
     * by getting the number of occurrences of the context, and choosing a random number between 1 and that value. Then
     * calls the getFollowingWord method to get the appropriate word that can follow the current context
     * @return the next random word
     */
    @Override
    public String next() {
        /** Looks up the last context from the ArrayList containing ContextData */
        if (getLastContextData() == null) {
            /** Chooses one at random if next has not been called yet */
            setLastContextData(getContextData((int) (Math.random() * contextDataArrayList.size())));
        }
        else {
            /** Iterates the ContextData to the next one */
            int lastContextIndex = Collections.binarySearch(contextDataArrayList, lastContextData) + 1;
            if (lastContextIndex == contextDataArrayList.size()) {
                lastContextIndex = 0;
            }
            setLastContextData(getContextData(lastContextIndex));
        }
        /** Chooses a random word to follow that context by getting the number of occurrences of the context, and choosing a random number between 1 and that value */
        String randomWord = getLastContextData().getFollowingWord((int)(Math.random() * lastContextData.numOccurrences() + 1));
        getLastContextData().getContext().moveDown(randomWord);
        return randomWord;
    }

    /**
     * The WordData class
     */
    public static class WordData {
        /** the word */
        private String word;

        /** the count of the word */
        private int count;

        /**
         * Creates a new WordData for the given word
         * @param word the word being recorded
         */
        public WordData(String word) {
            this.word = word;
            count = 1;
        }

        /**
         * Increases the count of the word by one
         */
        public void incrementCount() {
            count++;
        }

        /**
         * Returns the word
         * @return the word
         */
        public String getWord() {
            return word;
        }

        /**
         * Returns the count
         * @return the count
         */
        public int getCount() {
            return count;
        }
    }

    /**
     * The context for a word
     */
    public static class Context implements Comparable<Context>{
        /** the array of words that are the context */
        private String[] words;

        /**
         * Creates a Context with the inputted array of Strings
         * @param words an array of Strings for the context
         */
        public Context(String[] words) {
            this.words = words.clone();
        }

        /**
         * Returns the length of the context by returning the length of the array
         * @return the length of the context
         */
        public int length() {
            return words.length;
        }

        /**
         * Returns all the words in the array in one String
         * @return the array of words in a String
         */
        public String toString() {
            return String.join(" ", words);
        }

        /**
         * Returns the word at the inputted index
         * @param index the index of the word
         * @return the word at the index
         */
        public String getWord(int index) {
            return words[index];
        }

        /**
         * Returns true if all the words that make up this context are the same as the inputted context's words
         * @param words a context
         * @return boolean value for if the words are the same
         */
        public boolean equals(Context words) {
            if (this.words.length != words.length()) {
                return false;
            }
            for (int i = 0; i < this.words.length; i++) {
                if (!getWord(i).equals(words.getWord(i))) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Compares two contexts lexographically by iterating through the words each context comprises
         * @param o the context to be compared.
         * @return 0 if they are the same, 1 if this context comes after the inputted context, and -1 if this context comes before the inputted context
         */
        @Override
        public int compareTo(Context o) {
            for (int i = 0; i < Math.min(length(), o.length()); i++) {
                int compare = getWord(i).compareTo(o.getWord(i));
                if (compare != 0) {
                    return compare / Math.abs(compare);
                }
            }
            if (length() > o.length()) {
                return 1;
            }
            else if (length() < o.length()) {
                return -1;
            }
            return 0;
        }

        /**
         * Moves all the words in a context down one index. Removes the first word and adds the inputted word to the last index
         * @param followingWord the word to be added
         */
        public void moveDown(String followingWord) {
            if (length() < 2) {
                words = new String[]{followingWord};
            } else {
                for (int i = 0; i < length() - 1; i++) {
                    words[i] = words[i + 1];
                }
                words[length() - 1] = followingWord;
            }

        }
    }

    /**
     * The context data
     */
    public static class ContextData implements Comparable<ContextData> {
        /** the context */
        private Context context;

        /** the number of occurrences */
        private int occurrences;

        /** the list of WordData */
        private LinkedList<WordData> list;

        /**
         * Creates a ContextData object with a context
         * @param context the context
         */
        public ContextData(Context context) {
            this.context = context;
            occurrences = 0;
            list = new LinkedList<>();
        }

        /**
         * Returns the context
         * @return the context
         */
        public Context getContext() {
            return context;
        }

        /**
         * Returns the number of occurrences
         * @return the number of occurrences
         */
        public int numOccurrences() {
            return occurrences;
        }

        /**
         * Compares two ContextData by their contexts
         * @param o the ContextData
         * @return 0, 1, or -1 based on the contexts
         */
        @Override
        public int compareTo(ContextData o) {
            return getContext().compareTo(o.getContext());
        }

        /**
         * Adds a word to the list. If the word is already in the list, the WordData count for the word is incremented.
         * Otherwise, a new WordData is created for the word.
         * @param word the word to be added
         */
        public void addFollowingWord(String word) {
            LLIterator<WordData> wordDataIterator = (LLIterator<WordData>) this.list.iterator();
            WordData wordData = wordDataIterator.next();
            /** if the list is empty (first one) then a new WordData is created and is set as the first */
            if (wordData == null) {
                this.list.setFirstNode(new LLNode<>(new WordData(word), null));
            } else {
                while (wordData != null) {
                    int compare = word.compareTo(wordData.getWord());

                    if (compare < 0) {
                        wordDataIterator.addBefore(new WordData(word));
                        wordData = null;
                    } else if (compare == 0) {
                        wordData.incrementCount();
                        wordData = null;
                    } else {
                        wordData = wordDataIterator.next();
                        if (wordData == null) {
                            wordDataIterator.addAfter(new WordData(word));
                        }
                    }
                }
            }
            occurrences++;
        }

        /**
         * Returns the word with the inputted value from the list of WordData
         * @param value the value of the word
         * @return the word
         */
        public String getFollowingWord(int value) {
            if (value < 1) {
                throw new NoSuchElementException();
            }
            LLIterator<WordData> wordDataIterator = (LLIterator<WordData>) this.list.iterator();
            WordData wordData = wordDataIterator.next();
            while (value > wordData.getCount()) {
                value -= wordData.getCount();
                wordData = wordDataIterator.next();
                if (wordData == null) {
                    throw new NoSuchElementException();
                }
            }
            return wordData.getWord();
        }
    }
}
