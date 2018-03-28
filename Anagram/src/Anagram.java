import java.io.*;
import java.util.Arrays;

public class Anagram {
	/*
	 * User defined list to contain anagram matrix
	 */
	CustomList[] wordMat;
	/*
	 * File name of the input text file
	 */
	String fileIN;
	/*
	 * File name of the output text file
	 */
	String fileOUT;
	/*
	 * Time measurement fields
	 */
	double start, stop, totalStart, totalStop;
	/*
	 * File printing field
	 */
	PrintWriter cursor;
	/*
	 * Input size and storage array size
	 */
	int arraySize, lines;
	/*
	 * Read input text file and store into custom list
	 */
	public void readInputFile() throws IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(fileIN));
		String data;
		arraySize = 0;
		// scans the input file by checking if the next characters is an EOL
		double now, later;
		now = System.nanoTime();
		while((data = buffer.readLine()) != null) {
			if(!isAnagram(data)) {
				wordMat[arraySize] = new CustomList(data);
				arraySize++;
			}
		}
		later = System.nanoTime();
		cursor.println("The method to determine if two words are anagrams took "+(later-now)+" nanoseconds.");
		buffer.close();
	}
	/*
	 * Identifies the number of words to read from the file
	 */
	public void numberOfWords() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileIN));
		lines = 0;
		while (reader.readLine() != null) {
		    lines++;
		}
		reader.close();
	}
	/*
	 * Determines if two words are anagrams of each other
	 */
	public boolean isAnagram(String text) {
		char[] inputAsChar = text.toCharArray();
		SortFuncs.charSort(inputAsChar);
		// String inputText = inputAsChar.toString();
		for(int i = 0; i<arraySize; i++) {
			char[] currentAsChar = wordMat[i].getHead().data.toCharArray();
			SortFuncs.charSort(currentAsChar);
			// String currentText = currentAsChar.toString();
			if(Arrays.equals(inputAsChar, currentAsChar)) {
				wordMat[i].addFront(text);
				return true;
			}
		}
		return false;
	}
	/*
	 * Prints the output to a file
	 */
	public void printToFile() {
		try {
			cursor.print("This is the sorted list of anagrams.\n");
			// goes through all the pointers
			for(int i = 0; wordMat[i] != null; i++) {
				// goes through all the contents within pointer[i]
				for(int j = 0; j < wordMat[i].size(); j++) {
					// prints to file
					cursor.print(wordMat[i].getData(j) + " ");
				}
				cursor.println();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("File does not exist.");
		}
	}
	
	public static void main(String[] args) throws IOException {
		Anagram sample = new Anagram();

		sample.fileIN	= args[0];
		sample.fileOUT	= args[1];
		sample.cursor = new PrintWriter(sample.fileOUT);
		
		sample.totalStart = System.nanoTime();
		System.out.println("The program has started.");
		sample.cursor.println("The program has started.");
		
		sample.start = System.nanoTime();
		sample.numberOfWords();
		sample.wordMat = new CustomList[sample.lines];
		sample.readInputFile();
		sample.stop = System.nanoTime();
		sample.cursor.print("Reading the input file took "+(sample.stop-sample.start)/1000000000.0+" seconds.\n");
		
		sample.start = System.nanoTime();
		for(int i = 0; i<sample.arraySize; i++)
			SortFuncs.insertionSort(sample.wordMat[i]);
		sample.stop = System.nanoTime();
		sample.cursor.print("Sorting each row took "+(sample.stop-sample.start)/1000000000.0+" seconds.\n");
		
		sample.start = System.nanoTime();
		SortFuncs.quickSort(0,sample.arraySize-1,sample.wordMat);
		sample.stop = System.nanoTime();		
		sample.cursor.print("Sorting the rows took "+(sample.stop-sample.start)/1000000000.0+" seconds.\n");

		sample.start = System.nanoTime();
		sample.printToFile();
		sample.stop = System.nanoTime();		
		sample.cursor.print("Printing output to file took "+(sample.stop-sample.start)/1000000000.0+" seconds.\n");
		
		System.out.println("The program has ended.");
		sample.totalStop = System.nanoTime();		
		sample.cursor.print("Processing the input file that contains "+sample.lines+" words took "+(sample.totalStop-sample.totalStart)/1000000000.0+" seconds.\n");
		
		sample.cursor.println("The program has ended.");
		sample.cursor.close();
		
		System.out.print("Processing the input file that contains "+sample.lines+" words took "+(sample.totalStop-sample.totalStart)/1000000000.0+" seconds.\n");
	}
}
