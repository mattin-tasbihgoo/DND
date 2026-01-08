// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	private BinaryNode<E> root; // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public int getHeight() {
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		return containsHelper(value, root);
	}

	public boolean containsHelper(E value, BinaryNode<E> temp) {
		if (temp.hasLeft()) {
			if (value.equals(temp.getLeft()))
				return true;
			containsHelper(value, temp.getLeft());
		}

		if (value.equals(temp.getValue()))
			return true;

		if (temp.hasRight()) {
			if (value.equals(temp.getRight()))
				return containsHelper(value, temp.getLeft());
		}
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		BinaryNode<E> temp = root;
		BinaryNode<E> temp2 = new BinaryNode<E>(value);
		if (root == null) {
			root = temp2;
			return true;
		}
		while (value.compareTo((E) temp.getValue()) != 0) {
			if (value.compareTo((E) temp.getValue()) < 0) {
				if (!temp.hasLeft()) {
					temp.setLeft(temp2);
					return true;
				} else {
					temp = temp.getLeft();
				}
			}
			if (value.compareTo((E) temp.getValue()) > 0) {
				if (!temp.hasRight()) {
					temp.setRight(temp2);
					return true;
				} else {
					temp = temp.getRight();
				}
			}
		}
		return false;
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// largest node in the right subtree
	public boolean remove(E value) {
		return false;
	}

	// Returns the minimum in the tree
	public E min() {
		return minHelper(root);
	}

	public E minHelper(BinaryNode<E> temp) {
		if (temp.hasLeft())
			minHelper(temp.getLeft());
		return temp.getValue();
	}

	// Returns the maximum in the tree.
	public E max() {
		return maxHelper(root);
	}

	public E maxHelper(BinaryNode<E> temp) {
		if (temp.hasRight()) {
			minHelper(temp.getRight());
		}
		return temp.getValue();
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the
	// nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		stringHelper(string, root);
		string.delete(string.length() - 2, string.length());
		string.append("]");
		return string.toString();
	}

	public StringBuilder stringHelper(StringBuilder sb, BinaryNode<E> temp) {
		if (temp.hasLeft()) {
			stringHelper(sb, temp.getLeft());
		}

		sb.append(temp.getValue() + ", ");

		if (temp.hasRight()) {
			stringHelper(sb, temp.getRight());
		}
		return sb;
	}
}
