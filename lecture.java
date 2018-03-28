<February 12, 2018>

~ any node is the root of a subtree
	~ a subtree consists of it and the nodes below it
~ a set of trees is  called a forest
~ a tree consists of levels
								(	)					LEVEL 0
					(	)					(	)		LEVEL 1
			(	)			(	)		(	)	(	)	LEVEL 2
						(	)	(	)					LEVEL 3
~ the height (depth) of the tree is the distance from the root to the node(s) furthest away
	~ for the above example, it is 3
~ path length is the sum of edges from each node to the root
	~ for the above example, it is 18
~ with ordered trees, the order of the children at every node is specified
	~ these are much ore useful than unordered trees

							BINARY TREES
~ a tree where every node has either 0, 1, or 2 children(s)
~ each node contains:
	~ Data
	~ Left and Right child pointers
	~ (optional) Parent pointer
~ root pointer that is used to point to the root node
~ each node is an object of a class such as the following:

	public class Node{
		private int data;
		private Node parent, left, right;

		public Node(int el, Node p, Node l, Node r){
			data = el;
			parent = p;
			left = l;
			right = r;
		}
		//... more code
	}
					BINARY SEARCH TREES
~ also called ordered binary trees
~ Organized so that:
	~ every left child is less than (or equal to) the parent node
	~ every right child is greater than the parent node
~ all nodes in the left subtree will be less than or equal to the parent node
~ all nodes in the right subtree will be greater than the parent node
~ in the best case, the tree is balanced and the height is minimized
	~ the height of the tree is roughly lg(n)
~ in the worst case, the tree degenerates into a linked list
	~ the height is (n-1)
~ if the tree is balanced, searches are efficient
	~ O(lg(n))
	~ related to the binary search of a sorted array

									INSERTION
~ requires a search of the existing tree to find the appropriate parent node of the new ndoe
	~ new nodes are always added as leaf nodes
~ the new node is then attached to the parent
~ if the tree is empty, then the new node becomes the root node

	public void insert(int el){
		Node current = root, parent = null;	// assumes current is an instance variable
		while(current!=null){
			parent = current;
			if(el>current.data)	// might have a data getter method
				current = current.right;
			else
				current = current.left;
		}
		if(root==null)
			root = new Node(el,parent,null,null);
		else if(el>parent.data)
			parent.right = new Node(el,parent,null,null);
		else
			parent.left = new Node(el,parent,null,null);
	}

								TRAVERSAL
~ to traverse a tree, allnodes are visited once in some prescribed order
~ two types:
							~ DEPTH-FIRST
		~ recursively visits wach node starting at the far left (or right)
		~ DEPTH-FIRST , IN-ORDER traversal
			~ visits nodes in asceding order

				public void inorder(Node current){
					if(current!=null){
						inorder(current.left);	// goes down the left hand side of the tree
						// visit current node
						System.out.println(current.toString());
						inorder(current.right);	// goes down the right hand side of the tree
					}
				}

		~ would be called from client code as follows:

			Node root = null;
			// build tree doing successive insertions
			...
			// traverse tree
			inorder(root);
			...

		~ DEPTH-FIRST , PRE-ORDER traversal
			~ processes the node first then the left subtree then the right subtree

				public void preorder(Node current){
					if(current!=null){
						// visit current node
						System.out.println(current.toString());
						preorder(current.left);
						preorder(current.right);
					}
				}

		~ DEPTH-FIRST , POST-ORDER traversal
			~ processes the left subtree then the right then the node

			public void postorder(Node current){
				if(current!=null){
					postorder(current.left);
					postorder(current.right);
					System.out.println(current.toString());
				}
			}

		~ depth first traversals could be implemented non-recursively
			~ requires iteration and an explicit stack
			~ less elegant than the recursive implementation

							~ BREADTH-FIRST
		~ starting at the highest level, move down level by level, visiting
			nodes on each level from left to right
			~ can also start at the bottom, traverse from right to left
		~ requires use of a queue
			~ top-down, left-right implementation	(Drozdek p 224, 231)

			public void breadthfirst(){
				IntBSTNode p = root;
				Queue queue = new Queue();
				if(p!=null){
					queue.enqueue(p);
					while(!queue.isempty()){
						p = (IntBSTNode) queue.dequeue();
						p.visit();
						if(p.left!=null)
							queue.enqueue(p.left);
						if(p.right!=null)
							queue.enqueue(p.right);
					}
				}
			}

						BINARY SEARCH TREES
~ SEARCHING
	~ can be done iteratively

		public Node search(Node current,int key){
			while(current != null){
				if(key == current.data) return current;
				else if(key < current.data) current = current.left;
				else current = current.right;
			}
			return null;
		}

~ binary search trees are very efficient when performed on a well balanced tree
	~ is O(lg n) when the height of the tree is minimized
	~ is also O(lg n) whne the tree is formed by inserting nodes in random order
~ is less efficient if the tree has degenerated into a linked list
	~ O(n)

~ DELETING NODES
	~ 3 cases:
		~ case 1: deleting a leaf node
			~ set parent node's child pointer to null
			~ free the deleted node's memory
		~ case 2: deleting a node with only one child
			~ set parent node's  child pointer to the child of the deleted node ("splice out the node")
			~ free the deleted node's memory
		~ case 3: deleting a node with two children
			~ find the smallest node in the right subtree below the node to delete
				NOTE: an alternative would be finding the largest node in the left subtree
			~ "Splice out" that node, using steps form one of the cases above
			~ substitute the spliced node for the deleted node either by copying or by adjusting pointers
			~ free the delted node's memory'

 						<< READING EXERCISES >>
Drozdek Sections 6.1 - 6.6

							AVL TREES
~ searches and insertions are most efficient when a binary tree is well balanced
~ binary trees may become unbalanced after insertions and deletions
	~ in the worst case the tree degenerates into a linked list
~ there are several variants of orderd binary trees that remain well balanced after deletions and insertions
	~ AVL trees are one example

						BALANCE OF A NODE
~ balance of a node is the height of the right subtree minus the left subtree
	balance(n) = rightHeight(n) - leftHeight(n);
	where n is some node in the tree

~ AVL trees are ordered binary trees where every node has a balance of -1,0, or +1
~ the difference between the subtrees can never exceed 1

						NON-AVL TREES
~ if the difference between subtrees exceed 1

						NODE STRUCTURE
~must add a balance field to the node class used for binary search trees

	public class Node{
		private int data;
		private Node parent, left, right;
		private int balance;	// newly added item
		...
	}

				INSERTION INTO AN AVL TREE
~ general procedure:
	~ insert the node into the tree, following the rules for a regular binary search tree
	~ if necessary, adjust the shape of the tree so that it conforms to the rules of an AVL tree
	this involves doing single or double rotation
	~ upate the balance fields for all nodes affected by the steps above
~ PIVOT NODE
	~ is the ancestor node closest to the inserted node that is not in balance (ie: not 0)
	~ it is possible that there is no pivot when doing an insertion
	~ one adjusts the AVL tree and updates the balancecs according to the anture of the pivot and
	where the insertion is done
~ three possible cases when doing an insertion:
	1. There is no pivot
	2. The pivot exists and you add to the shorter subtree
	3. The pivot exists and you add to the longer subtree
~ case 1: there is no pivot
	~ essentially you are adding to a subtree with all 0 balances
		~ you change all the balnes for all ancestor nodes by +/- 1
	~ the shape of the tree is not adjusted after the insertion bu the balances must be updated
	~ procedure:
		1. insert the node into its proper place in the tree
		2. adjust the balances for all nodes from the inserted node up to the root node (all nodes on the search path)
			~ the inserted node is given a balance value of 0
			~ for other nodes, if the inserted node < node value, decrement balance else increment
~ case 2: pivot exists and node is added to the shorter subtree
	~ essentially adding to the shorter subtree to bring it into better balance
	~ the shape of the tree is not adjusted after the insertion but the balances must be updated
	~ you must be able to tell is you are adding to the shorter subtree (distinguish from case 3)
		~ if pivot == +1 and inserted node < pivot node
		~ if pivot == -1 and inserted node > pivot node
	~ procedure:
		~ insert the node into its proper place in the tree
		~ adjust the balances for all the nodes from the inserted node up to and including the pivot node
			~ the inserted node is given a balance of 0
			~ for other nodes:
				~ if inserted node < node value, decrement balance
				~ if inserted node > node value, increment balance
			~ balances do not change above the pivot node
~ ANCESTOR NODE
	~ the parent node of the pivot node
~ SON NODE
	~ the child node of the pivot node on the path from the pivot to the inserted node
~ GRANDSON NODE
	~ the child node of the son node on the path from the pivot to the inserted node
~ OUTSIDE SUBTREE
	~ the left subtree of the son node is pivot is negative else the right if positive
~ case 3: a pivot exists and you add to the longer subtree
	~ esentially we are putting the tree into a worse balance <pivot's balance changes to +/- 2>
	~ must adjust the shape of the tree after doing the insertion
	~ you must be able to tell if you are adding to the longer subtree to distinguish from case 2
		~ pivot == +1 and inserted node > pivot node
		~ pivot == -1 and inserted node < pivot node
	~ case 3.A: when you are adding to the "outside subtree" of the "son" of the pivot on the search path
		~ procedure:
			~ insert the node into its proper location
			~ adjust the shape of the tree by doing a single rotation
				a. do a right rotation if the outside subtree is on the left (pivot is negative)
				b. do a left rotation if the outside subtree is on the right (pivot is positive)
				3 pointers must be changed:
					1.	if pivot < ancestor, then ancestor's left child pointer is set to the son node
						otherwise set the right child pointer
					2.	a. RIGHT ROTATION: pivot's left child pointer is set to the right child of the son node
						b. LEFT ROTATION: pivot's right child pointer is set to the left child of the son node
					3.	a. RIGHT ROTATION: son's right child pointer set to the pivot node
						b. LEFT ROTATION: son's left child pointer set to the pivot node
			~ adjust balances of affected nodes
				~ set pivot and inserted node to 0
				~ adjust the balances for all the nodes above the inserted node up to the child of the son node
					~ if inserted node < node value -- else ++
	~ case 3.B: when you are adding to the "inside subtree" of the "son" of the pivot on the search path.
		~ to adjust the tree after insertion a double rotation is performed, it consists of:
			~	a right rotation at one node, followed by a left roattion at another node (RL rotation), or
				the inverse (LR rotation)
		~ procedure:
			~ insert the node to its proper place
			~ adjust the shape of the tree by doing a double rotation
				CASE 1: RL rotation if the pivot is positive
				CASE 2: LR rotation if the pivot is negative