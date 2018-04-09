
public class StringVector {
	public String[] stringArray;
	public int index;
	public int capacity;
	public StringVector(int capacity) {
		stringArray = new String[capacity];
		this.capacity = capacity;
		index = -1;
	}
	public void addString(String text) {
//		if(isFull())
//			expand();
		stringArray[++index] = text;
	}
//	private void expand() {
//		capacity *= 2;
//		String[] temp = new String[capacity];
//		for(int i = 0; i < stringArray.length; i++)
//			temp[i] = stringArray[i];
//		stringArray = temp;
//	}
//	public boolean isFull() {
//		if(capacity == (index + 1))
//			return true;
//		return false;
//	}
	public int size() {return (index + 1);}
	public String getStringAt(int index) {
		if(index >= capacity) {
			try {
				throw new Exception("ERROR: Invalid index.");
			} catch (Exception e) {e.printStackTrace();}
			return null;
		}
		return stringArray[index];
	}
}
