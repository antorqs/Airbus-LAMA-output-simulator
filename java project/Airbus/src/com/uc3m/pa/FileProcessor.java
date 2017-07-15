package com.uc3m.pa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor {

	final static Charset ENCODING = StandardCharsets.UTF_8;
	private ArrayList<String> actions;
	private int nrows;
	private int npass;
	private HashMap<String, String> occupied;
	
	public FileProcessor(){
		nrows = 0;
		npass = 0;
		actions = new ArrayList<String>();
	}
	
	public void readOutputPlan(String aFileName) throws IOException {
		boolean avoid = true;
		boolean done = false;
		
		Path path = Paths.get(aFileName);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine() && !done){
				String line = scanner.nextLine();
				if(line.equals("Solution found!")){
					line = scanner.nextLine();
					line = scanner.nextLine();
					avoid = false;
				}
				if(!avoid){
					if(line.startsWith("Plan length:")){
						done = true;
					}else{
						actions.add(line);
					}
				}
			}      
		}
	}
	
	public void readProblemFile(String aFileName) throws IOException {
		boolean objects = false;
		boolean init = false;
		boolean done = false;
		HashMap<String, String> occupied = new HashMap<String, String>();
		int nrows = 0;
		int npass = 0;
		int aux = 0;

		Path path = Paths.get(aFileName);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine() && !done){
				String line = scanner.nextLine();
				line = line.toLowerCase();
				if(line.contains("(:objects")){
					line = line.substring(line.indexOf("(:objects") + 9).trim();
					objects = true;
				}
				if(line.contains("(:init")){
					line = line.substring(line.indexOf("(:init") + 6).trim();
					init = true;
				}
				if(objects){
					if(line.contains(")")){
						objects = false;
					}
					if(line.contains("- row")){
						nrows += count_items(line.substring(0, line.indexOf("- row")));
						nrows += aux;
						aux = 0;
					}else if(line.contains("- passenger")){
						npass += count_items(line.substring(0, line.indexOf("- passenger")));
						npass += aux;
						aux = 0;
					}else{
						aux += count_items(line);
					}
				}
				if(init){
					if(line.contains("(:goal")){
						init= false;
						done = true;
					}else{
						if(line.contains("passenger-seated")){
							while(line.length() > 15 && line.contains("passenger-seated")){
								line = line.substring(line.indexOf("passenger-seated")+16).trim();
								String row = line.substring(0, line.indexOf(" "));
								String letter = line.substring(line.indexOf(" ")+1, line.indexOf(" ")+2).trim();
								line = line.substring(row.length() + letter.length() + 2).trim();
								String pass = line.substring(0, line.indexOf(")")).trim();
								occupied.put(pass, row+"-"+letter);
							}
						}
					}
				}
			}      
		}
		this.nrows = nrows;
		this.npass = npass;
		this.occupied = occupied;
	}
	
	public int count_items(String s){
		String trim = s.trim();
	    if (trim.isEmpty())
	        return 0;
	    return trim.split("\\s+").length; // separate string around spaces
	}
	
	public ArrayList<String> getActions() {
		return actions;
	}

	public void setActions(ArrayList<String> actions) {
		this.actions = actions;
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public int getNpass() {
		return npass;
	}

	public void setNpass(int npass) {
		this.npass = npass;
	}

	public HashMap<String, String> getOccupied() {
		return occupied;
	}

	public void setOccupied(HashMap<String, String> occupied) {
		this.occupied = occupied;
	}

}
