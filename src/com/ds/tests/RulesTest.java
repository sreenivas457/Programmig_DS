package com.ds.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.ds.commandresponsebuilder.CommandBuilder;
import com.ds.input.ReadInput;
import com.ds.ruleengine.Rules;


public class RulesTest {
	
	private Rules rules;
	private ReadInput ri ;
	private CommandBuilder cb;
	
	@Before
	public void setup(){
		rules = new Rules();
		ri = new ReadInput("input//ds_tempadvise_data.csv");
		cb = ri.buildInput();
	}
	
	@Test
	public void testRemovePajamas(){
		assertEquals("fail",rules.applyRules(cb, "COLD", Arrays.asList(6)));
	}
	
	
	@Test
	public void testHotSuccess() {
		assertEquals("Removing PJs, shorts, t-shirt, sun visor, sandals, leaving house",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 4, 2, 1, 7)));
	}
	
	@Test
	public void testColdSuccess(){
		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house",rules.applyRules(cb, "COLD", Arrays.asList(8, 6, 3, 4, 2, 5, 1, 7)));
	}
	
	@Test
	public void testHotWithDuplicates(){
		assertEquals("Removing PJs, shorts, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 6)));
	}
	
	@Test
	public void testColdWithDuplicates(){
		assertEquals("Removing PJs, pants, shirt, hat, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 6, 4, 2, 2)));
	}
	
	@Test
	public void testHotWithSocks(){
		assertEquals("Removing PJs, shorts, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 3)));
	}
	
	@Test
	public void testHotWithJacket(){
		assertEquals("Removing PJs, shorts, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 5)));
	}

	
	@Test
	public void testColdMissingClothing(){
		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 6, 3, 4, 2, 5, 7)));
	}
	
	@Test
	public void testHotMissingClothing() {
		assertEquals("Removing PJs, shorts, t-shirt, sun visor, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 4, 2, 7)));
	}
	
	
	@Test
	public void testSocksBeforeShoes(){
		assertEquals("Removing PJs, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 1)));
	}
	
	@Test
	public void testPantsBeforeShoes(){
		assertEquals("Removing PJs, socks, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 3, 1)));
	}
	
	@Test
	public void testShirtBeforeHeadWear(){
		assertEquals("Removing PJs, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 2)));
	}
	
	@Test
	public void testShirtBeforeJacket(){
		assertEquals("Removing PJs, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 5)));
	}
}
