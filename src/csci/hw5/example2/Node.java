package csci.hw5.example2;

public class Node<E>{
	E data;
	Node<E> next;
	Node(E data, Node<E> link){
		this.data = data;
		this.next = link;
	}
}
