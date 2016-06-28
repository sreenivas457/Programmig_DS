package com.ds.commandresponsebuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ds.commandresponsebuilder.CommandBuilder;
import com.ds.commandresponsebuilder.Response;

/**
 * CommandBuilderTest class to CommandBilder Class
 * @author sreenivas
 */
public class CommandBuilderTest {
	private Response response;
	private CommandBuilder cb;

	/**
	 * sets up basic information required to test CommandBuilders command<->Response Map
	 */
	@Before
	public void setup() {
		response = new Response();
		cb = new CommandBuilder();
		response.buildResponse("HOT", "sandals");
		response.buildResponse("COLD", "boots");
		response.setDescription("Put on footwear");
	}
	
	/**
	 * Tests the CommandBuilder class for command<->Response Map
	 */
	@Test
	public void testAddandRetrieveCommand() {
		cb.addCommand(1, response);
		Response resultResponse = cb.retrieveResponse(1);
		assertEquals("Put on footwear", resultResponse.getDescription());
		assertEquals("sandals", resultResponse.getResponse("HOT"));
		assertEquals("boots", resultResponse.getResponse("COLD"));
	}
}
