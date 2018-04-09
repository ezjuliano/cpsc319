
public class HashTable {

    private int      SIZE;     // size of hash table
    private String[] hashtable; // storage or records
    private char[] charHolder;
    final int maxNumberOfElements = 21;
    private int[] asciiValues;
    private int[] groupsOfThree;

    // public constructor
    public HashTable(int size) {
        SIZE = size;
        hashtable = new String[SIZE];
    }

    // returns size of hash table
    public int getSize() {
        return SIZE;
    }
    
    /*
     * ADDED FUNCTIONS
     */
    
    public void insert(String text) {
    	int probe = 0;
    	do {
    		int hashedIndex = hash(text, probe);
//    		System.out.println(text + "	---	" + hashedIndex);
    		if(hashtable[hashedIndex] == null) {
    			hashtable[hashedIndex] = text;
    			return;
    		}
    		probe++;
    	}while(probe < SIZE);
    	throw new RuntimeException("Hash table overflow");
    }
    
    public int find(String text) {
         int probe = 0;
         do {
        	 int hashedIndex = hash(text, probe);
//        	 System.out.println(text + "	---	" + hashedIndex);
        	 if(hashtable[hashedIndex] == null)
        		 return -1;
        	 else {
        		 if(hashtable[hashedIndex].equals(text)) {
        			 return probe++;
        		 }
        		 probe++;
        	 }
         }while(probe < SIZE);
         
         
         
         
    	return -1;
    }
    
    public int hash(String text, int probe) {
    	/*
    	 * a) Convert text to char array
    	 * b) Copy each character to the charHolder
    	 * c) Convert each char to their ASCII values into the integer array
    	 * d) Group 2 elements at a time
    	 * e) Add each group altogether
    	 * f) Take sum mod 45007
    	 * g) Take mod 11351
    	 */
    	convertToCharArray(text);
    	convertToASCII();
    	//regroup();
    	flip(asciiValues);
    	groupByThrees();
    	return (generateHashedIndex() + probe) % SIZE;
    }
    
    private void convertToCharArray(String text) {
    	char[] textTochar = text.toCharArray();
    	charHolder = new char[maxNumberOfElements];
    	for(int i = 0; i < textTochar.length; i++) {
    		charHolder[i] = textTochar[i];
    	}
    	for(int i = textTochar.length; i < charHolder.length; i++) {
    		charHolder[i] = ' ';
    	}
    }
    
    private void convertToASCII() {
    	asciiValues = new int[maxNumberOfElements];
    	for(int i = 0; i < charHolder.length; i++) {
    		asciiValues[i] = (int) charHolder[i];
    	}
    }
    
    private void regroup() {
    	int[] temp = new int[asciiValues.length/2];
    	for(int i = 0, j = 0; i < temp.length; i++, j = j + 2) {
    		String value = Integer.toString(asciiValues[j]) + Integer.toString(asciiValues[j + 1]);
    		temp[i] = Integer.parseInt(value);
    	}
    	asciiValues = temp;
    }
    
    private void flip(int[] source) {
    	int[] temp = new int[source.length];
    	for(int i = 0, j = source.length - 1; i < source.length; i++, j--) {
    		temp[i] = source[j];
    	}
    	source = temp;
    }
    
    private void groupByThrees() {
    	groupsOfThree = new int[asciiValues.length/3];
    	for(int index = 0, i = 0; index < groupsOfThree.length; index++, i = i + 3) {
    		groupsOfThree[index] = asciiValues[i] + asciiValues[i + 1]
    				 + asciiValues[i + 2];
    	}
    }
    
    private int generateHashedIndex() {
    	long hashedIndex = 0;
    	for(int i = 0; i < groupsOfThree.length; i++) {
    		hashedIndex += groupsOfThree[i];
    		hashedIndex *= hashedIndex;
    	}
    	hashedIndex = hashedIndex % 104729;
    	hashedIndex = hashedIndex % 11351;
    	return (int) Math.abs(hashedIndex);
    }
    
    /*
     * END OF ADDED FUNCTIONS
     */
    
    

    /**
     * Adds new record into Hash Table
     * 
     * @param value
     *            - words as input (Don't worry about key as key and values are
     *            the same according to the requirement)
     * @throws RuntimeException
     *             when new record is tried to be inserted into the full hash
     *             table
     */
    public void add(String value) {

        int i = 0; // probe number

        do {
            // sequentially searches all positions until
            // an open position is found
            int pos = norm(value, i);
            if (hashtable[pos] == null) {
                hashtable[pos] = value;
                return;
            }
            i++;
        } while (i < SIZE);

        // hash table is full
        throw new RuntimeException("Hash table overflow");
    }

    /**
     * Searches for given record in the Hash Table
     * 
     * @param value - words as input
     * @returns number of reads per second if record is found 
     *                                     if record is not found return -1
     */
    public int search(String value) {
        int i = 0;

        do {
            int pos = norm(value, i);
            if (hashtable[pos] == null) {
                return -1;
            } else {
                if (hashtable[pos].equals(value)) {
                    return i + 1;
                }
                i++;
            }

        } while (i < SIZE);

        return -1;
    }

    public int norm(String key, int i) {
        /*
         * My hash function would generate sequential integers according to
         * first character of the word. As example: "apple", "arc" would produce
         * 0. "ball", "boy" would produce 1. "dog" , "doll" would produce 3 and
         * so on..
         */
        char firstCharacter = key.charAt(0); // fetch the first character
        int asciiValue = (int) firstCharacter;
        int hash = asciiValue - 97; // hash = h(k)

        // for linear probing, my norm function is norm(k, i) = (h(k) + i) % tableSize
        return Math.abs(hash + i) % SIZE;
    }

    // prints current records in the hash table
    public void print() {
        for (int i = 0; i < hashtable.length; i++)
            System.out.println(i + " " + hashtable[i]);
    }
}