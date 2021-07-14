package org.junit.training.JUnitprojects;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Activity1 {
	
	
	//Text fixtures
		static ArrayList<String> list;
		
		
		  // Initialize test fixtures before each test method
		@BeforeEach
		public void setUp() {
			
			// Initialize a new ArrayList
			list = new ArrayList<String>();

			// Add values to the list
			list.add("alpha"); // at index 0
			list.add("beta"); // at index 1
		}
		
		
		@Test
		public void insertTest()
		{
			// Assert size of list
			assertEquals(2, list.size(), "Wrong size");
			
			//Adding new element
			list.add("charlie");
			
			
			// Assert size of list
			assertEquals(3, list.size(), "Wrong size");
			
			// Assert individual elements
	        assertEquals("alpha", list.get(0), "Wrong element");
	        assertEquals("beta", list.get(1), "Wrong element");
	        assertEquals("charlie", list.get(2), "Wrong element");
			
		}
		
		@Test
		public void replaceTest()
		{
			//replace new element
			list.set(1, "charlie");
			
			// Assert size of list
			assertEquals(2, list.size(), "Wrong size");
			
			// Assert individual elements
	        assertEquals("alpha", list.get(0), "Wrong element");
	        assertEquals("charlie", list.get(1), "Wrong element");
		}
}
