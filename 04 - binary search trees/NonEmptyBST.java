package comp210.assn04;

import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {

	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	@Override
	public BST<T> insert(T element) {
		switch(this.getElement().compareTo(element)){
			case 0:
				break;
			case 1:
					this.getLeft().insert(element);
				break;
			case -1:
					this.getRight().insert(element);
				break;
		}

		return this;
	}

	@Override
	public BST<T> remove(T element) {
		switch(element.compareTo(this._element)) {
			case 1:
				this._right = this._right.remove(element);
				break;
			case -1: 
				this._left = this._left.remove(element);
				break;
			default:
				if (this._left.isEmpty()) {
					return this._right;
				}
				else if (this._right.isEmpty()) {
					return this._left;
				}
				this._element = minVal(this._right);
				this._right = this._right.remove(this._element);
		}

		return this;
	}

	T minVal(BST<T> item) {
		T min = item.getElement();

		while (!item.getLeft().isEmpty())
		{
			min = item.getLeft().getElement();
			item = item.getLeft();
		}

		return min;
	}

	@Override
	public void printPreOrderTraversal() {
		System.out.print(this.getElement() + " ");
		this.getLeft().printPreOrderTraversal();
		this.getRight().printPreOrderTraversal();
	}

	@Override
	public void printPostOrderTraversal() {
		this.getLeft().printPostOrderTraversal();
		this.getRight().printPostOrderTraversal();
		System.out.print(this.getElement() + " ");
	}

	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST<T>> queue = new LinkedList<BST<T>>();
		queue.add(this);
		
		while (queue.size() > 0) {
			BST<T> temp = queue.poll();
			System.out.print(temp.getElement() + " ");
			if (!temp.getLeft().isEmpty()) {
				queue.add(temp.getLeft());
			}
			if (!temp.getRight().isEmpty()) {
				queue.add(temp.getRight());
			}
		}
	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
