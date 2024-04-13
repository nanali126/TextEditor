/**
 * This class is to construct the editor and set the necessary functionality
 * 
 * Known Bugs: None
 * 
 * @Author: Nana Li
 * kejiali@brandeis.edu
 * Feb.28th, 2024
 * COSI21A PA1
 */
package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Editor {
	
	public int numChars; /** KEEP THIS PUBLIC : use this to store the number of characters in your Editor */
	public int curPos; /** KEEP THIS PUBLIC : use this to store the current cursor index in [0, numChars] */
	
	public Node cur; /** KEEP THIS PUBLIC : use this to reference the node that is after the visual cursor or null if curPos = numChars */
	public Node head; /** KEEP THIS PUBLIC : use this to reference the first node in the Editor's doubly linked list */
	public Node tail; /** KEEP THIS PUBLIC : use this to reference the last node in the Editor's doubly linked list */
	
	/**
	 * to construct the blank editor 
	 * O(1)
	 */
	public Editor() {
		cur = null;
		head = null;
		tail = null;
		numChars = 0;
		curPos = 0;
	}
	
	/**
	 * to construct an editor to read the content from the filepath and get the content typed in the editor
	 * @param filepath: filepath directing to the desired file
	 * @throws FileNotFoundException
	 * O(n^2)
	 */
	public Editor(String filepath) throws FileNotFoundException {
		//set the scanner help to read the content from the desired file
		Scanner input = new Scanner(new File(filepath));
		int x = 1;
		Node prev = null;
		boolean nextLine = input.hasNextLine();
		//stop reading content while there's noe next line in the scanner
		while(nextLine) {
			String line = input.nextLine();
			System.out.println(line);
			//reading each char from string
			for(int i = 0; i<line.length();i++) {
				char character = line.charAt(i);
				Node n = new Node(character);
				//to check whether it is the first char in the content, is true, it should be head node
				if(x==1 && i==0) {
					head = n;
					//set the head node
					n.setPrev(null);
				}else {
					//set the new node connecting with previous node
					n.setPrev(prev);
					prev.setNext(n);
				}
				prev = n;
				curPos++;
				numChars++;
			}
			nextLine = input.hasNextLine();
			if(nextLine) {
				Node n = new Node('\n');
				prev.setNext(n);
				n.setPrev(prev);
				prev = n;
				curPos++;
				numChars++;
			}
			x++;
		}
		//set the tail node
		tail = prev;
		tail.setNext(null);
		updateCur(curPos);
	}
	
	/**
	 * return the position of cursor
	 * @return int: the integer representing the cursor's position
	 * O(1)
	 */
	public int getCursorPosition() {
		return curPos;
	}
	
	/**
	 * return the size of the content in the editor
	 * @return int: the size of the content in the editor
	 * O(1)
	 */
	public int size() {
		return numChars;
	}
	
	/**
	 * to move the cursor left 
	 * O(1)
	 */
	public void moveRight() {
		if(curPos<numChars) {
			updateCur(curPos);
			cur = cur.getNext();
			curPos++;
		}
	}
	
	/**
	 * to move the cursor right
	 * O(1)
	 */
	public void moveLeft() {
		if(curPos!=0) {
			if(curPos == numChars) {
				cur = tail;
			}else {
				updateCur(curPos);
				cur = cur.getPrev();
			}
			curPos--;
		}
	}
	
	/**
	 * to move the cursor to the head 
	 * O(1)
	 */
	public void moveToHead() {
		cur = head;
		curPos = 0;
		
	}
	
	/**
	 * to move the cursor to the tail
	 * O(1)
	 */
	public void moveToTail() {
		cur = null;
		curPos = numChars;
	}
	
	/**
	 * to insert a new char as a node into the linkedlist
	 * @param c: the char needed to be inserted
	 * O(1)
	 */
	public void insert(char c) { 
		Node n = new Node(c);
		//to insert the new node at the end
		if(curPos == numChars) {
			cur = null;
			//if there's no node existing, the inserting new node will be both end and tail
			if(curPos == 0) {
				tail = n;
				head = n;
				n.setNext(null);
				n.setPrev(null);
			}else {
				n.setNext(null);
				n.setPrev(tail);
				tail.setNext(n);
				tail = n;
			}
		//to insert the new node at the head or in the middle
		}else {
			updateCur(curPos);
			n.setNext(cur);
			//to insert node at the head
			if(curPos == 0 ){
				n.setPrev(null);
				head = n;
			//to insert node in the middle
			}else {
				n.setPrev(cur.getPrev());
				cur.getPrev().setNext(n);
			}
			cur.setPrev(n);
		}
		curPos++;
		numChars++;
	}
	
	/**
	 * to delete the node which the curPos represent
	 * O(1)
	 */
	public void delete() {
		//to determine whether there's node can be deleted
		if(curPos>=0 && curPos<numChars) {
			updateCur(curPos);
			//to delete the head node
			if(curPos == 0 && curPos != numChars-1) {
				cur.getNext().setPrev(null);
				cur=cur.getNext();
				head = cur;
				numChars--;
			//to delete the last node
			}else if(curPos == numChars-1){
				if(curPos==0) {
					clear();
				}else {
					cur.getPrev().setNext(null);
					tail = cur.getPrev();
					cur = null;
					numChars--;
				}
			//to delte the node in the middle
			}else {
				cur.getNext().setPrev(cur.getPrev());
				cur.getPrev().setNext(cur.getNext());
				cur = cur.getNext();
				numChars--;
			}
		}
	}
	
	/**
	 * to delete the node which is the previous node of current node
	 * O(1)
	 */
	public void backspace() {
		//to determine whether the backspcace can be run
		if(curPos>=1 && curPos<=numChars) {
			updateCur(curPos);
			//to backspace the head node
			if(curPos == 1 && curPos!=numChars) {
				cur.setPrev(null);
				head = cur;
				curPos --;
				numChars--;
			//to backspace the tail node
			}else if(curPos == numChars) {
				if(curPos == 1) {
					clear();
				}else {
					tail.getPrev().setNext(null);
					tail = tail.getPrev();
					curPos--;
					numChars--;
				}
			//to backspace the node in the middle	
			}else {
				cur.getPrev().getPrev().setNext(cur);
				cur.setPrev(cur.getPrev().getPrev());
				curPos--;
				numChars--;
			}
		}
		
	}
	
	/**
	 * to return the String typed in the editor
	 * @return String: the String storing the content typed in the editor
	 * O(n)
	 */
	public String toString() {
		String output = "";
		Node n = head;
		while(n !=null) {
			output = output + Character.toString(n.getData());
			n = n.getNext();
		}
		return output;
	}
	
	/**
	 * to clear all the node in the editor
	 * O(1)
	 */
	public void clear() {
		head = null;
		tail = null;
		cur = null;
		curPos = 0;
		numChars = 0;
	}
	
	/**
	 * to save the content in the editor into a file with filepath
	 * @param savepath: the savepath of file need to be saved
	 * @throws FileNotFoundException
	 * O(1)
	 */
	public void save(String savepath) throws FileNotFoundException {
		PrintStream output = new PrintStream(new File(savepath));
		output.println(toString());
	}
	
	/**
	 * to update the current node based on the curPos
	 * @param Pos: the curPos
	 * O(n)
	 */
	public void updateCur(int Pos){
		cur = head;
		for(int i = 0; i<Pos; i++) {
			cur = cur.getNext();
		}
	}
	
	
}
