import java.io.PrintWriter;
/*
 * This class creates a binary search tree
 * that contains student data within each node
 */
public class BST {
	public class BSTnode{
		// student data
		Student student;
		// left and right child pointers
		BSTnode left;
		BSTnode right;
		public BSTnode(Student s) {
			student = s;
			left = null;
			right = null;
		}
		public Student getStudent() {return student;}
		public BSTnode getLeft() {return left;}
		public BSTnode getRight() {return right;}
	}
	// root node
	public BSTnode root;
	public BST() {
		root = null;
	}
	 // Search for a node that contains the key given in the argument of the method
	public boolean search(String name) {
		BSTnode current = root;
		while(current!=null) {
			// if key is found return
			if(current.getStudent().getLastname().equalsIgnoreCase(name))
				return true;
			// if current key > key go to left subtree
			else if(current.getStudent().getLastname().compareTo(name) > 0)
				current = current.getLeft();
			// else go to right subtree
			else
				current = current.getRight();
		}
		return false;
	}
	// Deletes a node that contains the data specified by the method argument
	public boolean delete(Student s) {
		BSTnode current = root, parent = root;
		// identifies if the node is a leftChild
		boolean leftChild = false;
		// find the node
		while(!(current.getStudent().getLastname().equalsIgnoreCase(s.getLastname()))) {
			// track the parent node
			parent = current;
			// if current key > key go left
			if(current.getStudent().getLastname().compareTo(s.getLastname()) > 0) {
				leftChild = true;
				current = current.getLeft();
			}
			// else go right
			else {
				leftChild = false;
				current = current.getRight();
			}
			// if current is null return
			if(current == null)
				return false;
		}
		// CASE 1: LEAF NODE
		if(current.getLeft() == null && current.getRight() == null) {
			// if empty
			if(current == root)
				root = null;
			// if the node to be deleted is a left child
			if(leftChild)
				parent.left = null;
			// if the node to be deleted is a right child
			else
				parent.right = null;
		}
		// CASE 2A: Node to delete has a left child
		else if(current.getRight() == null) {
			if(current == root)
				root = current.getLeft();
			if(leftChild)
				parent.left = current.getLeft();
			else
				parent.right = current.getLeft();
		}
		// CASE 2B: Node to delete has a right child
		else if(current.getLeft() == null) {
			if(current == root)
				root = current.getRight();
			if(leftChild)
				parent.left = current.getRight();
			else
				parent.right = current.getRight();
		}
		// CASE 3: Node to delete has 2 children
		else if(current.getLeft() != null && current.getRight() != null) {
			// find min value from the right subtree
			BSTnode min = getMin(current);
			// if empty
			if(current == root)
				root = min;
			// if current is a left child
			if(leftChild)
				parent.left = min;
			// if current is a right child
			else
				parent.right = min;
			// splice the node
			min.left = current.getLeft();
		}
		return true;
	}
	private BSTnode getMin(BSTnode node) {
		// minimum node
		BSTnode min = null;
		// minimum node's parent
		BSTnode minParent = null;
		// go to right subtree
		BSTnode current = node.getRight();
		while(current!=null) {
			minParent = min;
			min = current;
			// traverse left subtree of the right subtree
			current = current.getLeft();
		}
		// splice node
		if(min!=node.getRight()) {
			minParent.left = min.getRight();
			min.right = node.getRight();
		}
		return min;
	}
	public void insert(Student s) {
		BSTnode newNode = new BSTnode(s);
		// if empty
		if(root == null) {
			root = newNode;
			return;
		}
		BSTnode current = root;
		BSTnode parent = null;
		// find parent node then attach the new node
		while(true) {
			parent = current;
			// if current key > key go left
			if(current.getStudent().getLastname().compareTo(s.getLastname()) > 0) {
				current = current.getLeft();
				// if reached a null node attach to parent as left child
				if(current == null) {
					parent.left = newNode;
					return;
				}
			}
			// else go right
			else {
				current = current.getRight();
				// if reached a null node attach to parent as right child
				if(current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}
	// DEPTH FIRST
	public void DFT(BSTnode root, PrintWriter cursor) {
		if(root!= null) {
			// go to node with the least key
			DFT(root.getLeft(), cursor);
			// process
			cursor.println(root.getStudent());
			// go to node with higher keys
			DFT(root.getRight(), cursor);
		}
	}
	// BREADTH FIRST
	public void BFT(PrintWriter cursor) {
		// find the height of the tree
		int level, height = treeHeight(root);
		// go down the tree level by level from L to R
		for(level = 1; level <= height; level++)
			printLevel(root,level,cursor);
	}
	private int treeHeight(BSTnode root) {
		if(root == null)
			return 0;
		else {
			int leftHeight = treeHeight(root.left);
			int rightHeight = treeHeight(root.right);
			
			if(leftHeight > rightHeight)
				return(leftHeight+1);
			else
				return(rightHeight+1);
		}
	}
	private void printLevel(BSTnode root, int level, PrintWriter cursor) {
		if(root == null)
			return;
		if(level == 1)
			cursor.println(root.getStudent());
		else if(level > 1) {
			// gets the first node from the left
			printLevel(root.left, level-1, cursor);
			// enables to print all other nodes on the same level as the previous one
			printLevel(root.right, level-1, cursor);
		}
	}
}
