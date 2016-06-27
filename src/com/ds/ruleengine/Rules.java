package com.ds.ruleengine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ds.commandresponsebuilder.CommandBuilder;

/**
 * Rules class is the core class which implements all the business rules in apply rules method.
 * @author sreenivas
 *
 */

public class Rules {
	private StringBuilder output;
	private Map<String, Set<Integer>> mandatoryClothingMap;
	

	/** Construct a new Rules
	 * 	Initializes members
	 * 
	 */
	public Rules(){
		output = new StringBuilder();
		intializeMandatoryClothingMap();
	}
	
	/**
	 * Initializes mandatory clothing required for temperature types
	 */
	
	private void intializeMandatoryClothingMap(){
		mandatoryClothingMap = new HashMap<String, Set<Integer>>();
		Set<Integer> hotSet = new HashSet<Integer>();
		hotSet.add(1);
		hotSet.add(2);
		hotSet.add(4);
		hotSet.add(6);
		Set<Integer> coldSet = new HashSet<Integer>();
		coldSet.addAll(hotSet);
		coldSet.add(3);
		coldSet.add(5);
		mandatoryClothingMap.put("HOT", hotSet);
		mandatoryClothingMap.put("COLD", coldSet);
	}

	/**
	 * Applies all business rules based on input list of commands and generates an output
	 *  
	 * @param commandBuilder
	 * 			Command Builder with commands and corresponding responses
	 * @param tempType
	 * 			temperature type "HOT" / "COLD"
	 * @param input
	 * 			list of input commands
	 * @return
	 * 			output string which is a concatenation of responses which were made so far based on the list of input commands 
	 */
	
	public String applyRules(CommandBuilder commandBuilder,String tempType,  List<Integer> input){
		Set<Integer> clothing = new HashSet<Integer>();
		boolean done = false;
		int commandNumber = 8;
		
		/**
		 * We scan input list and check if the first command is 8 corresponding to the 
		 * business rule of "Removing PJs" first  
		 */
		
		if(input.get(0) != commandNumber){
			output.append("fail");
			done = true;
		}else{
			output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType)+", ");
		}
		
		/**
		 * Our method of application of rules
		 * 
		 * If first input is correct, we move on to process the remaining inputs, one at a time.
		 * For each input command, 
		 * 			We apply business rules based on the command number
		 * 			If any business rule fails, we append fail to output string and
		 * 				 set done flag to stop further processing of input commands.
		 * 			If business rules work as expected, then we concatenate all the responses and return the output. 
		 */
		
		for(int i = 1; i<input.size()&&!done;i++){
			commandNumber = input.get(i); 
			if(clothing.contains(commandNumber)){
				output.append("fail");
				return output.toString();
			}
			switch(commandNumber){
				case 1: if (tempType.equals("HOT") || (clothing.contains(6) && clothing.contains(3))){
							clothing.add(commandNumber);
							output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType)+", ");
						}else{
							output.append("fail");
							done = true;
						}
						break;
	
				case 2: if(clothing.contains(4)){
							clothing.add(commandNumber);
							output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType)+", ");
						}else{
							output.append("fail");
							done = true;
						}
						break;
					
				case 3: if(!tempType.equals("HOT")){
							clothing.add(commandNumber);
							output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType)+", ");
						}else{
							output.append("fail");
							done = true;
						}
						break;
				case 5: if(!tempType.equals("HOT") && clothing.contains(4)){
							clothing.add(commandNumber);
							output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType)+", ");
						}else{
							output.append("fail");
							done = true;
						}
						break;
				case 4:
				case 6:	clothing.add(commandNumber);
						output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType) +", ");
						break;

				case 7: if(checkClothing(clothing , tempType)){
							output.append(commandBuilder.retrieveResponse(commandNumber).getResponse(tempType));
						}else{
							output.append("fail");
						}
						done = true;
						break;
						
				default:output.append("fail");
						done = true;
						break;
				}		
			}
		return output.toString();
	}
	
	/**
	 * Checks to see if all mandatory clothing has been put on before leaving the house
	 * 
	 * @param clothing
	 * 			Clothing worn so far
	 * @param tempType
	 * 			Temperature type
	 * @return
	 * 			true if all mandatory clothing is put on
	 * 			false otherwise
	 */
	
	private boolean checkClothing(Set<Integer> clothing, String tempType){
		Set<Integer> mandatoryClothingSet =  mandatoryClothingMap.get(tempType);
		return clothing.equals(mandatoryClothingSet);
	}
}
