package com.ds.commandresponsebuilder;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author sreenivas
 * This Response class is used to store Temperature type, corresponding Reponse and description.
 * Temperature type and response are store in a map. Eg: {{"HOT" "sandals"}, {"COLD", "boots"}}, "Put on footwear"
 * If we want to add a new temperature type in future, we just need to add a <tempType, Response> pair to our typeResponseMap.
 */

public class Response {
	/**
	 * Response class
	 * 
	 */
	
	private Map<String, String> typeResponseMap;
	private String description;

	/** Construct a new Response
	 * Initializes members
	 * 
	 */
	public Response(){
		typeResponseMap = new HashMap<String, String>();
		description = null;
	}
	/**
	 * add a new response to typeResponseMap
	 *  
	 *  @param tempType
	 *  	    Type of temperature  Eg. "HOT"/ "COLD"
	 *  @param tempResponse
	 *  		Response corresponding to the temperature Eg. "sandals" / "boots" 
	 */
	
	public void buildResponse(String tempType, String tempResponse){
		typeResponseMap.put(tempType, tempResponse);
	}
	
	/**
	 *  Get the response corresponding to temperature
	 * @param tempType
	 * 		   Type of temperature  Eg. "HOT"/ "COLD"
	 * 
	 * @return tempResponse
	 * 			Response corresponding to the temperature Eg. "sandals" / "boots"
	 */
	
	
	public String getResponse(String tempType){
		return typeResponseMap.get(tempType);
	}
	
	/**
	 * Get the description of this response
	 * @return description
	 * 			Description corresponding to this response
	 */
	
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of this response
	 * @param description
	 * 			Description corresponding to this response
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
