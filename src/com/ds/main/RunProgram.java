package com.ds.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.ds.commandresponsebuilder.CommandBuilder;
import com.ds.input.ReadInput;
import com.ds.ruleengine.Rules;
/**
 *	This RunProgram class provides main method for running the program, 
 *	Provides a way to interact with user 
 * @author sreenivas
 *
 */

public class RunProgram {
	
	public static void main(String args[])
	{
		ReadInput ri = new ReadInput("input//ds_tempadvise_data.csv");
		CommandBuilder cb = ri.buildInput();
		Rules rules = new Rules();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Temperature Type(HOT/COLD) <space> followed by command numbers seperated by commas (no additional spaces please)");
		
		String tempType = in.next();
		while(!tempType.equals("HOT")&&!tempType.equals("COLD")){
			System.out.println("Please enter valid value for temperature (HOT/COLD)");
			tempType = in.next();
		}
		
		String commands = in.next();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		try{
			for(String s: commands.split(",")){
				list.add(Integer.parseInt(s));
			}
			System.out.println("Output:\t"+rules.applyRules(cb, tempType, list ));
		}
		catch (NumberFormatException e){
			System.out.println("Please enter numbers only, Remember! No spaces");
		}
		finally{
			in.close();
		}
	}
}