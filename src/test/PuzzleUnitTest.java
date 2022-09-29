package test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import trabalho.Board;
import trabalho.Ilayout;

public class PuzzleUnitTest {
	@Test
	public void testConstructor() {
		Board b = new Board("023145678");
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		pw.println(" 23");
		pw.println("145");
		pw.println("678");
		assertEquals(b.toString(), writer.toString());
		pw.close();
	}

	@Test
	public void testConstructor2() {
		Board b = new Board("123485670");
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		pw.println("123");
		pw.println("485");
		pw.println("67 ");
		assertEquals(b.toString(), writer.toString());
		pw.close();
	}
	
	@Test
	public void testIsGoal() {
		Board a = new Board("567102348");
		Board b = new Board("567102348");
		Board c = new Board("567102384");
		Board d = new Board();
		assertTrue(a.isGoal(b));
		assertFalse(a.isGoal(c));
		assertFalse(a.isGoal(d));
	}

	public void testhasCode() {
		Board a = new Board("123485670");
		Board b = new Board("123485670");
	    assertTrue(a.equals(b) && b.equals(a));
	    assertTrue(a.hashCode() == b.hashCode());
	}

	 @Test
	 public void testHashcode() {
		 Board s1 = new Board("567102348");
		 Board s2 = new Board("567102348");
		 
		 assertTrue(s1.equals(s2));
		 assertTrue(s1.hashCode()==s2.hashCode());
	 }
	 
	 @Test
	 public void testEquals() {
		 Board s1 = new Board("567102348");
		 Board s2 = new Board("567102348");
		 Board s3 = new Board("567102384");
		 
		 assertTrue(s1.equals(s2));
		 assertFalse(s1.equals(s3));
	 }
	 
	 @Test
	 public void testchildren() {
		 Board s1 = new Board("567102348");
		 Board s2 = new Board("507162348");
		 Board s3 = new Board("567012348");
		 Board s4 = new Board("567120348");
		 Board s5 = new Board("567142308");
		 List<Ilayout> o1 = new ArrayList<Ilayout>();
		 List<Ilayout> o2 = s1.children();
		 o1.add(s2);
		 o1.add(s3);
		 o1.add(s4);
		 o1.add(s5);
		 assertEquals(o1, o2);
//		 Arrays.deepEquals(o1, o2);
	 }
}