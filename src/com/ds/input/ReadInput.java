package com.ds.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.ds.commandresponsebuilder.CommandBuilder;
import com.ds.commandresponsebuilder.Response;

/**
 * ReadInput class to read input from a file, this input is used to build commands based on which our business rules are processed. 
 * @author sreenivas
 *
 */
public class ReadInput {
	private String filePath;
	
	/**
	 * Builds a read input class
	 * @param path
	 * 			The file path where out input commands are stored
	 */
	public ReadInput(String path){
			this.filePath = path;
	}
	
	/**
	 * Reads input file and builds command builder. The input file is expected in a specific format
	 * It should be a csv with the following format.
	 * 
	 * [Command Number] [Description]	  [Temperature Type1]	[Response]  [Temperature Type2]		[Response]
	 *         1		Put on footwear     HOT					sandals	    		COLD				boots
	 *  
	 * @return
	 * 			Command Builder that is built from input file
	 */
	public CommandBuilder buildInput(){
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		CommandBuilder cb = new CommandBuilder();

		try {

			br = new BufferedReader(new FileReader(this.filePath));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvSplitBy);
				Response resp = new Response();
				resp.setDescription(data[1]);
				resp.buildResponse(data[2], data[3]);
				resp.buildResponse(data[4], data[5]);
				cb.addCommand(Integer.parseInt(data[0]), resp);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cb;
	}
}