package csci.hw5.example2;

/**
 * Another storage
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FastCompetition<E extends Comparable<E>> implements Competition<E>, Iterable<E> {
	Node<E> head = null;
	Node<E> tail = null;
	private int size = 0;

	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean add(E e) {
		if (isEmpty()) {
			head = new Node<E>(e, head);
			tail = head;
			size++;
			return true;
		}
		Node<E> oldTail = tail;
		tail = new Node<E>(e, null);
		oldTail.next = tail;
		size++;
		return true;

	}

	@Override
	public boolean contains(Object o) {
		Node<E> temp = head;
		while (temp != null) {
			if (temp.data.equals(o))
				return true;
			temp = temp.next;
		}
		return false;
	}

	private boolean removeHead() {
		if (head == null)
			throw new RuntimeException("cannot delete");
		head = head.next;
		size--;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (head == null)
			return false;

		if (head.data.equals(o)) {
			head = head.next;
			size--;
			return true;
		}
		Node<E> prev = null;
		Node<E> cur = head;
		while (cur != null) {
			if (cur.data.equals(o)) {
				prev.next = cur.next;
				size--;
				return true;
			}
			prev = cur;
			cur = cur.next;
		}
		return false;
	}

	@Override
	public E elementAt(int index) {
		Node<E> temp = head;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		if (temp == null) {
			throw new IndexOutOfBoundsException();
		}
		return temp.data;
	}

	@Override
	public Competition<E> sort() {
		// TODO Auto-generated method stub
		return mergeSort(this);
	}

	@Override
	public int size() {
		return size;
	}

	private Competition<E> mergeSort(FastCompetition<E> list) {
		int listSize = list.size();
		if (listSize <= 1) {
			return list;
		}
		int mid = listSize % 2 == 0 ? listSize / 2 : (listSize + 1) / 2;
		FastCompetition<E> left = new FastCompetition<>();
		FastCompetition<E> right = new FastCompetition<>();
		for (int i = 0; i < mid; i++) {
			left.add(list.elementAt(i));
		}
		for (int i = mid; i < listSize; i++) {
			right.add(list.elementAt(i));
		}

		left = (FastCompetition<E>) mergeSort(left);
		right = (FastCompetition<E>) mergeSort(right);
		return merge(left,right);
	}

	private Competition<E> merge(FastCompetition<E> left, FastCompetition<E> right) {
		FastCompetition<E> result = new FastCompetition<>();
		while(!left.isEmpty() && !right.isEmpty()){
			if(left.elementAt(0).compareTo(right.elementAt(0)) <= 0){
				result.add(left.elementAt(0));
				left.removeHead();
			}else {
				result.add(right.elementAt(0));
				right.removeHead();
			}			
		}
		while(!left.isEmpty()){
			result.add(left.elementAt(0));
			left.removeHead();
		}
		while(!right.isEmpty()){
			result.add(right.elementAt(0));
			right.removeHead();
		}		
		return result;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new FastCompetitionIterator();
	}

	private class FastCompetitionIterator implements Iterator<E> {
		Node<E> nextNode;

		public FastCompetitionIterator() {
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E res = nextNode.data;
			nextNode = nextNode.next;
			return res;
		}

	}
}
