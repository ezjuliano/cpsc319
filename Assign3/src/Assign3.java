import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Assign3 {
	
	BST recordTree;
	String inputFilename;
	String outputFilename1;
	String outputFilename2;
	
	public Assign3() {
		recordTree = new BST();
	}
	
	public void readTextFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFilename));
			Student data;
			String text = null, ln, dept, prog;
			char oc;
			int id = 0, y = 0;
			char[] charArr = new char[42];
			while((text = in.readLine()) != null) {
				charArr = text.toCharArray();
				oc = charArr[0];
				id =  Integer.parseInt(String.valueOf(charArr, 1, 7));
				ln = String.valueOf(charArr, 8, 25);
				dept = String.valueOf(charArr, 33, 4);
				prog = String.valueOf(charArr, 37, 4);
				y = Integer.parseInt(String.valueOf(charArr, 41, 1));
				data = new Student(ln,dept,prog,id,y);
				if(oc == 'I')
					recordTree.insert(data);
				if(oc == 'D')
					recordTree.delete(data);
				charArr = new char[42];
			}
			in.close();
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
		catch(NumberFormatException n) {
			System.err.println(n.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Assign3 test = new Assign3();
		test.inputFilename = args[0].toString();
		test.outputFilename1 = args[1].toString();
		test.outputFilename2 = args[2].toString();
		test.readTextFile();
		PrintWriter cursor;
		try {
			cursor = new PrintWriter(test.outputFilename1);
			//cursor.println("ID      LASTNAME                  DEPT PROG YEAR");
			test.recordTree.DFT(test.recordTree.root, cursor);
			cursor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			cursor = new PrintWriter(test.outputFilename2);
			//cursor.println("ID      LASTNAME                  DEPT PROG YEAR");
			test.recordTree.BFT(cursor);
			cursor.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
