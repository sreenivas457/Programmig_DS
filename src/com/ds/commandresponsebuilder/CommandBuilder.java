package com.ds.commandresponsebuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sreenivas
 * 
 * This CommandBuilder class stores command with command number and corresponding Response.
 * We can easily add more commands to command builder class just by using add Command method.
 *
 */

public class CommandBuilder {
	/**
	 * Command Builder class
	 */

	private Map<Integer, Response> commandMap;
	

	/** Construct a new CommandBuilder
	 * Initializes members
	 * 
	 */
	
	public CommandBuilder(){
		commandMap = new HashMap<Integer, Response>();
	}
	
	/**
	 * Adds a new command to the command map 
	 * @param commandNumber
	 * 			The number for the command
	 * @param response
	 * 			response including temperature types ,responses and corresponding  description
	 */
	public void addCommand(int commandNumber, Response response){
		commandMap.put(commandNumber, response);
	}

	/**
	 * Retrieves the response corresponding to a command number
	 * @param commandNumber
	 * 			The number for the command
	 * @return
	 * 			Response associated to the command
	 */
	public Response retrieveResponse(int commandNumber){
		return commandMap.get(commandNumber);
	}
}
