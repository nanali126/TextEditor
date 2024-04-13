/**
 * the StudentEditorTests class is to test some special cases 
 * Known Bugs: None
 * 
 * @Author: Nana Li
 * kejiali@brandeis.edu
 * Feb.28th, 2024
 * COSI21A PA1
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Editor;
import main.Node;

class StudentEditorTests {

	/** Editor used for testing */
	Editor e;

	/**
	 * Before each test, the Editor is re-initialized
	 */
	@BeforeEach
	void reset() {
		e = new Editor();
	}
	
	/**
	 * to test that the move left doesn;t work while the cur is the head node
	 */
	@Test
	void testMoveLeftAtHead() {
		loadEditorBLUE(e);
		e.cur = e.head;
		e.curPos = 0;
		e.moveLeft();
		assertEquals(0, e.getCursorPosition());
		assertEquals('b', e.cur.data);
	}
	
	/**
	 * to test that the move right doesn't work while the cursor is after the tail node
	 */
	@Test
	void testMoveRightAtEnd() {
		loadEditorBLUE(e);
		e.cur = null;
		e.curPos = e.numChars;
		e.moveRight();
		assertEquals(e.numChars, e.getCursorPosition());
		assertEquals(null, e.cur);
	}
	
	/**
	 * to test while inserting a new node at head, the head node of the editor updated successfully
	 */
	@Test
	void testInsertHead() {
		loadEditorBLUE(e);
		e.cur = e.head;
		e.curPos = 0;
		e.insert('a');
		assertEquals(e.head.getData(),'a');
		assertEquals(e.curPos,1);
		assertEquals(e.cur.data, 'b');
	}
	
	/**
	 * to test while inserting a new node at end, the tail node of the editor updated successfully
	 */
	@Test
	void testInsertTail() {
		loadEditorBLUE(e);
		e.cur=null;
		e.curPos=e.numChars;
		e.insert('a');
		assertEquals(e.tail.data,'a');
		assertEquals(e.curPos, e.numChars);
		assertEquals(e.cur, null);
	}
	
	/**
	 * to test the delete method works correctly while delete the tail node
	 */
	@Test
	void testDeleteTail() {
		loadEditorBLUE(e);
		//to test while the cursor is after the tail node, the delete method doesn't work
		e.cur=null;
		e.curPos=e.numChars;
		int a = e.numChars;
		e.delete();
		assertEquals(e.cur, null);
		assertEquals(a, e.numChars);
		
		//to test delete the tail node correctly
		e.cur=e.tail;
		e.curPos=e.numChars-1;
		e.delete();
		assertEquals(e.cur, null);
		assertEquals(e.tail.data, 'u');
		assertEquals(e.curPos, e.numChars);
	}
	
	/**
	 * to test the delete method correctly while delte the head node
	 */
	@Test
	void testDeleteHead() {
		loadEditorBLUE(e);
		e.cur = e.head;
		e.curPos=0;
		e.delete();
		assertEquals(e.head.data, 'l');
		assertEquals(e.cur.data, 'l');
		assertEquals(e.curPos, 0);
	}
	
	/**
	 * to test the backspce method correctly while backspce the tail node
	 */
	@Test
	void testBackspaceTail() {
		loadEditorBLUE(e);
		e.cur = e.tail;
		e.curPos = e.numChars;
		e.backspace();
		assertEquals(e.tail.data, 'u');
		assertEquals(e.curPos, e.numChars);
		assertEquals(e.cur, null);
	}
	
	/**
	 * to test the backspace method while backspace the head node
	 */
	@Test
	void testBackspceHead() {
		loadEditorBLUE(e);
		//to test while the cursor is after the head node, the backspace method doesn't work
		e.cur = e.head;
		e.curPos = 0;
		int a = e.numChars;
		e.backspace();
		assertEquals(e.head.data, 'b');
		assertEquals(e.cur.data, 'b');
		assertEquals(a, e.numChars);
		
		//to test backspce the head node correctly
		e.cur = e.cur.next;
		e.curPos = 1;
		e.backspace();
		assertEquals(e.head.data, 'l');
		assertEquals(e.curPos, 0);
		assertEquals(e.cur.data, 'l');
		
	}

	public static void loadEditorBLUE(Editor editor) {
		Node b = new Node('b');
		Node l = new Node('l');
		Node u = new Node('u');
		Node e = new Node('e');

		b.next = l;
		l.prev = b;
		l.next = u;
		u.prev = l;
		u.next = e;
		e.prev = u;

		editor.head = b;
		editor.tail = e;

		editor.numChars = 4;
	}
}
