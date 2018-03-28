public class Word{

	private String data;
	private String key;

	public Word(String text){
		data = text;
		key = (charSort(text.toCharArray())).toString();
	}

	public String getData(){return data;}
	public String getKey(){return key;}
	public void setData(String text){data = text;}

	private char[] charSort(char[] charArray) {
		// outer loop starts at the second item of the array
		for(int i = 1,j; i < charArray.length; i++) {
			char temp = charArray[i];
			// inner loop only runs when the element to the left is more than the current element
			// if it is more they are switched
			for(j = i; (j>0)&&(temp<charArray[j-1]); j--)
				charArray[j] = charArray[j-1];
			charArray[j] = temp;
		}
		return charArray;
	}
}
