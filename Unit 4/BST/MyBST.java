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

	public boolean find(E value) {
		return containsHelper(value, root);
	}

	public E findHelper(E value, BinaryNode<E> temp) {
		if (temp.hasLeft()) {
			if (value.equals(temp.getLeft()))
				return (E) temp.getLeft();
			findHelper(value, temp.getLeft());
		}

		if (value.equals(temp.getValue()))
			return temp.getValue();

		if (temp.hasRight()) {
			if (value.equals(temp.getRight()))
				return findHelper(value, temp.getLeft());
		}
		return null;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		BinaryNode<E> temp = root;
		BinaryNode<E> temp2 = new BinaryNode<>(value);
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
		return deport(value);
	}

	private boolean deport(E value) {
		if (!contains(value))
			return false;
		return false;
	}

	// Returns the minimum in the tree
	public E min() {
		if (root == null)
			return null;
		return minHelper(root);
	}

	public E minHelper(BinaryNode<E> temp) {
		if (temp.hasLeft())
			minHelper(temp.getLeft());
		return temp.getValue();
	}

	// Returns the maximum in the tree.
	public E max() {
		if (root == null)
			return null;
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
	@Override
	public String toString() {
		if (root == null)
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		stringHelper(sb, root);
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}

	public StringBuilder stringHelper(StringBuilder sb, BinaryNode<E> temp) {
		if (temp == null)
			return sb;
		stringHelper(sb, temp.getLeft());
		sb.append(temp.getValue()).append(", ");
		stringHelper(sb, temp.getRight());
		return sb;
	}
}
