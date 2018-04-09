
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Assign5 {
	
	StringVector inputWords;
	final int primeNumber = 11351;
	
	public Assign5() {
		inputWords = new StringVector(primeNumber);
	}
	/*
	 * Reads an input text file and stores it into a String array
	 */
	public void readInputFile(String filename) {
		try {
			Scanner scan = new Scanner(new FileReader(filename));
			while(scan.hasNext())
				inputWords.addString(scan.nextLine());
			scan.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
    public static void main(String[] args) {
    	
    	Assign5 test = new Assign5();
    	PrintStream printToFile = null;
    	try {
    		printToFile = new PrintStream(new FileOutputStream(args[1]));
		} catch (FileNotFoundException e) {e.printStackTrace();}
    	/*
    	 * TODO:
    	 * 	a)	Read input text file
    	 * 	b)	Create a hashTable
    	 * 	c)	Insert words into the hashTable
    	 * 	d)	Search through the hashTable then write to output file
    	 * 	e)	Calculate performance statistics
    	 */
    	test.readInputFile(args[0]);
    	double tableSize = (double) (test.primeNumber / 0.7);
    	// System.out.println(test.inputWords.size());
//    	HashTable hashT = new HashTable((int)tableSize);
    	HashTable hashT = new HashTable(test.primeNumber);
    	for(int i = 0; i < test.inputWords.size(); i++)
    		hashT.add(test.inputWords.getStringAt(i));
//    		hashT.insert(test.inputWords.getStringAt(i));
    	// hashT.print();
    	int rps = 0;
    	int maxRPS = 0;
    	for(int i = 0; i < test.inputWords.size(); i++) {
    		int findDuration = hashT.find(test.inputWords.getStringAt(i));
//    		int findDuration = hashT.search(test.inputWords.getStringAt(i));
    		if(findDuration > maxRPS)
    			maxRPS = findDuration;
    		rps = findDuration + rps;
    	}
    	double meanRPS = (double) rps / (double) test.inputWords.size();
    	double LF = (double) test.inputWords.size() / (double) hashT.getSize();
    	double efficiency = (LF / meanRPS) * 100;
    	
    	printToFile.println("Longest search duration took:	" + maxRPS + " reads.");
    	printToFile.println("The average number of reads per second is:	" + meanRPS + " reads.");
    	printToFile.println("The load factor is:	" + LF);
    	printToFile.println("The hashing efficiency:	" + String.format("%.2f", efficiency));
    	
    	
    	
//        // Sample words for testing
//        String[] words = { "arc", "ball", "car", "dog", "apple", "bat", "door"};
//        /*
//         * Choose the size of your Hash Table carefully for good hashing efficiency.
//         * I'm just trying to make it at least 70% full
//         */
//        double sizeofTable = (double) words.length / 0.7;
//        HashTable hashTable = new HashTable((int) sizeofTable); // initialize hash table
//
//        // insert words into Hash Table
//        for (int i = 0; i < words.length; i++) {
//            hashTable.add(words[i]);
////            hashTable.insert(words[i]);
//        }
//
//        // hashTable.print(); // check insertions
//
//        /*
//         * Calculate performance
//         */
//
//        // Calculate number of reads per second for each word
//        int readsPerSec = 0;
//        for (int i = 0; i < words.length; i++) {
//            readsPerSec = hashTable.search(words[i]) + readsPerSec;
////        	readsPerSec = hashTable.find(words[i]) + readsPerSec;
//        }
//        
//        // longest chain of reads would be = max. amongst readsPerSec
//        
//        // Calculate average reads per second
//        double avgReadPerSec = (double) readsPerSec / (double) words.length;
//
//        // load factor = number of records / table size
//        double loadFactor = (double) words.length
//                / (double) hashTable.getSize();
//
//        // hash efficiency = load factor / average reads per second
//        double hashEfficieny = (loadFactor / avgReadPerSec) * 100;
//
//        /*
//         * My Hashing Efficiency is around 27.22%. You have to implement your
//         * hash function smartly so that it could produce at least 50% hash
//         * efficiency
//         */
//        System.out.println("Hashing Efficiency: "
//                + String.format("%.2f", hashEfficieny));

    }

}