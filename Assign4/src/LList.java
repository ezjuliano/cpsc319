
public class LList <T> {
	T data;
	LList<T> next;
	
	LList(T data) {
		this.data = data;
		next = null;
	}
	
	LList() {
		this(null);
	}
	
	public void add(T data) {
		if(this.data == null)
			this.data = data;
	}
}
