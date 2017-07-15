package com.uc3m.pa;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Airbus extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String planFile;
	private String problemFile;
	private ArrayList<String> actions;
	private Deck deck;
	private FileProcessor fp;
	
    public Airbus(String plan, String problem) throws IOException {
    	planFile = plan;
    	problemFile = problem;
    	
    	fp = new FileProcessor();
    	fp.readOutputPlan(planFile);
    	fp.readProblemFile(problemFile);

    	actions = fp.getActions();
    	
    	setPreferredSize(new Dimension(1200,650));    	
        setTitle("Fasten your seatbelts!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initUI() throws IOException {

    	deck = new Deck(fp.getNrows(), fp.getNpass(), fp.getOccupied());
    	JScrollPane jsp = new JScrollPane(deck);

        add(jsp);
        pack();

    }
    
    public void processAction(String action) throws InterruptedException{
    	    	
    	if(action.startsWith("sit_in_")){
    		String letter = action.substring(7,8).trim();
    		String row = action.substring(action.indexOf("row"),action.indexOf("pass")).trim();
    		String pass = action.substring(action.indexOf("pass"), action.indexOf("(")).trim();
    		
    		deck.sit(row, letter, pass);
			
    	}else if(action.startsWith("walk-to-row") ||
    			action.startsWith("move-to-other-row")){
    		String rowFrom = "row0";
    		String pass = "";
    		String rowTo = "row0";
    		int rowf = 0; 
    		int rowt = 0;
    		if(action.startsWith("walk-to-row")){
    			String sub = action.substring(11);
    			rowTo = sub.substring(sub.indexOf("row"),sub.indexOf("pass")).trim();
    			pass = sub.substring(sub.indexOf("pass"), sub.indexOf("(")).trim();
    			rowt = Integer.parseInt(rowTo.substring(3));
    		}else{
    			String sub = action.substring(17).trim();
    			rowFrom = sub.substring(0, sub.indexOf(" "));
    			rowf = Integer.parseInt(rowFrom.substring(3));
    			rowTo = sub.substring(sub.indexOf(" ")+1, sub.indexOf("pass")).trim();
    			pass = sub.substring(sub.indexOf("pass"), sub.indexOf("(")).trim();
    			rowt = Integer.parseInt(rowTo.substring(3));
    		}
    		
    		deck.walk(rowf, rowt, pass);
    		
    	}else if(action.startsWith("stand_up_from_")){
    		String letter = action.substring(14,15).trim();
    		String row = action.substring(action.indexOf("row"),action.indexOf("pass")).trim();
    		String pass = action.substring(action.indexOf("pass"), action.indexOf("(")).trim();
    		deck.stand_up(row, letter, pass);

    	}
    }
    
    public Deck getDeck(){
    	return deck;
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	Airbus ex = new Airbus(args[0], args[1]);

    	if(ex.actions.size() < 1){
    		System.out.println("No plan found...");
    		ex.dispose();
    		return;
    	}
        
        ex.setVisible(true);

    	ex.initUI();
    	ex.getDeck().init();
    	ex.getDeck().setAction(ex.actions);
    	 if(args.length > 2)
         	ex.getDeck().setTiming(Long.parseLong(args[2]));
    	 
        int ii=0;
        for (String action : ex.actions) {
        	System.out.println(action);
        	ex.getDeck().setNaction(ii);
			ex.processAction(action);
			ii++;
        }
	}
}