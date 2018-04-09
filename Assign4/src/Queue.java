
public class Queue {
	LList<Vertex> list;
	LList<Vertex> head;
	LList<Vertex> tail;
	
	Queue() {
		list = new LList<Vertex>();
		head = tail = null;
	}
	
	public void enqueue(Vertex data) {
		if(tail == null)
			head = tail = list;
		else {
			LList<Vertex> temp = new LList<Vertex>();
			tail.next = temp;
			tail = temp;
		}
		tail.add(data);
		data.visited = true;
	}
	
	public Vertex dequeue() {
		Vertex temp;
		if(head == null)
			return null;
		else {
			temp = head.data;
			if(head == tail) {
				head = tail = null;
				list = new LList<Vertex>();
			}
			else
				head = head.next;
		}
		return temp;
	}
	
	public boolean isEmpty() {
		if(list.data == null)
			return true;
		return false;
	}
}
