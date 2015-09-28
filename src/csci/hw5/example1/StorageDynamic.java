/**
 * 
 */
package csci.hw5.example1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vishal
 *
 */
public class StorageDynamic<E, V> implements Storage<E, V>, Iterable<E> {

	Node<E> head = null;
	Node<E> tail = null;

	public boolean isEmpty() {
		return head == null;
	}

	// Appends the specified element to the end of this storage.
	// Returns true, if the element could be added, else false
	public boolean add(E e) {
		if (isEmpty()) {
			head = new Node<E>(e, head);
			tail = head;
			return true;
		}
		Node<E> oldTail = tail;
		tail = new Node<E>(e, null);
		oldTail.next = tail;
		return true;
	}

	// Inserts the specified element at the specified position in this Storage.
	// retursn true, if the element could be added at position index, else false
	public boolean add(int index, E element) {
		Node<E> temp = head;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		if (temp == null) {
			return false;
		}
		temp = new Node<E>(element, temp);
		return false;
	}

	// Adds the specified component to the end of this storage, increasing its
	// size by one.
	public void addElement(E obj) {
		add(obj);
	}

	// Adds the specified component to the end of this storage, increasing its
	// size by one.
	public void addElement(E obj, V elem) {
		add(obj);
	}

	// Returns the current capacity of this storage.
	public int capacity() {
		int capacity = 0;
		Node<E> temp = head;
		while (temp.next != null) {
			temp = temp.next;
			capacity++;
		}
		return capacity;
	}

	// Removes all of the elements from this storage.
	public void clear() {
		head = null;
		tail = null;
	}

	// Returns a clone of this storage.
	public Object clone() {
		StorageDynamic<E, V> dupe = new StorageDynamic<>();
		Node<E> temp = head;
		while (temp != null) {
			temp = temp.next;
			dupe.add(temp.obj);
		}
		return dupe;
	}

	// Returns the first component (the item at index 0) of this storage.
	public E firstElement() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return head.obj;
	}

	// Returns the element at the specified position in this storage.
	public E get(int index) {
		Node<E> temp = head;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		if (temp == null) {
			throw new IndexOutOfBoundsException();
		}
		return temp.obj;
	}

	// Returns the last component of the storage.
	public E lastElement() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return tail.obj;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Object x : this)
			result.append(x + " ");

		return result.toString();
	}

	private static class Node<E> {
		private E obj;
		private Node<E> next;

		public Node(E data, Node<E> next) {
			this.obj = data;
			this.next = next;
		}
	}

	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<E> {
		private Node<E> nextNode;

		public LinkedListIterator() {
			nextNode = head;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E res = nextNode.obj;
			nextNode = nextNode.next;
			return res;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
