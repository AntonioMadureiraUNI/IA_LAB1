package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

import trabalho.Board;

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
	
	
	
	
}