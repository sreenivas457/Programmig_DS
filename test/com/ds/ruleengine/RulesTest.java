package com.ds.ruleengine;

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
	
	/**
	 * This is test business rule: Pajamas must be removed before anything else
	 */
	@Test
	public void testRemovePajamas(){
		assertEquals("fail",rules.applyRules(cb, "COLD", Arrays.asList(6)));
	}
	
	/**
	 * This is to test business rule: HOT temperature with all clothing and successfully leaving the house
	 */
	@Test
	public void testHotSuccess() {
		assertEquals("Removing PJs, shorts, t-shirt, sun visor, sandals, leaving house",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 4, 2, 1, 7)));
	}
	
	/**
	 * This is to test business rule: COLD temperature with all clothing and successfully leaving the house
	 */
	@Test
	public void testColdSuccess(){
		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house",rules.applyRules(cb, "COLD", Arrays.asList(8, 6, 3, 4, 2, 5, 1, 7)));
	}
	
	/**
	 *  This is to test business rule: Tests duplicate clothing with HOT temperature
	 */
	@Test
	public void testHotWithDuplicates(){
		assertEquals("Removing PJs, shorts, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 6)));
	}
	
	/**
	 * This is to test business rule: Tests duplicate clothing with COLD temperature
	 */
	@Test
	public void testColdWithDuplicates(){
		assertEquals("Removing PJs, pants, shirt, hat, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 6, 4, 2, 2)));
	}
	
	/**
	 * This is to test business rule: Socks cannot be put on with HOT temperature
	 */
	@Test
	public void testHotWithSocks(){
		assertEquals("Removing PJs, shorts, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 3)));
	}
	
	/**
	 * This is to test business rule: Jacket cannot be put on with HOT temperature
	 */
	@Test
	public void testHotWithJacket(){
		assertEquals("Removing PJs, shorts, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 5)));
	}

	/**
	 * This is to test business rule: If all clothing is put on, before leaving house with COLD temperature
	 */
	@Test
	public void testColdMissingClothing(){
		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 6, 3, 4, 2, 5, 7)));
	}
	
	/**
	 * This is to test business rule: If all clothing is put on, before leaving house with HOT temperature
	 */
	@Test
	public void testHotMissingClothing() {
		assertEquals("Removing PJs, shorts, t-shirt, sun visor, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 6, 4, 2, 7)));
	}
	
	/**
	 * This is to test business rule: Socks is put on before Shoes
	 */
	@Test
	public void testSocksBeforeShoes(){
		assertEquals("Removing PJs, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 1)));
	}
	
	/**
	 * This is to test business rule: Pants is put on before Shoes
	 */
	@Test
	public void testPantsBeforeShoes(){
		assertEquals("Removing PJs, socks, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 3, 1)));
	}
	
	/**
	 * This is to test business rule: Shirt should be put on before headwear 
	 */
	@Test
	public void testShirtBeforeHeadWear(){
		assertEquals("Removing PJs, fail",rules.applyRules(cb, "HOT", Arrays.asList(8, 2)));
	}
	
	/**
	 * This is to test business rule: Shirt should be put on before jacket
	 */
	@Test
	public void testShirtBeforeJacket(){
		assertEquals("Removing PJs, fail",rules.applyRules(cb, "COLD", Arrays.asList(8, 5)));
	}
}
