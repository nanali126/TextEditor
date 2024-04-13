/**
 * This class is Node class contributing as a data structure for the editor
 * 
 * Known Bugs: None
 * 
 * @Author: Nana Li
 * kejiali@brandeis.edu
 * Feb.28th, 2024
 * COSI21A PA1
 */
package main;

public class Node {
	
	public Node next;
	public Node prev;
	public char data;
	
	public Node() {
		
	}
	
	/**
	 * to construct a node containning value char c inside
	 * @param c: char representing the node data stored
	 */
	public Node(char c) {
		this.next = null;
		this.prev = null;
		this.data = c;
	}
	
	/**
	 * to set the next node of the present node
	 * @param n: the next node of the present node
	 */
	public void setNext(Node n) {
		this.next = n;
	}
	
	/**
	 * to set the previous node of the present node
	 * @param n: the previous node of the present node
	 */
	public void setPrev(Node n) {
		this.prev = n;
	}
	
	/**
	 * to set the data of the present node
	 * @param c: char c should be stored in the present node
	 */
	public void setData(char c) {
		this.data = c;
	}
	
	/**
	 * to return the next node of the present node
	 * @return Node: the next node of the present node
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * to return the previous node of the present node
	 * @return Node: the previous node of the present node
	 */
	public Node getPrev() {
		return prev;
	}
	
	/**
	 * to return the data storing in the node
	 * @return char: the character value storing in the node
	 */
	public char getData() {
		return data;
	}
	
	/**
	 * to return the boolean determine whether one node is equal to another node
	 */
	public boolean equals(Object o) {
		Node other = (Node) o;
		if(other.data == this.data) {
			return true;
		}
		return false;
	}
}
