package com.uc3m.pa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class Deck extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image seat;
    private Image door;
    private Image nose;
    private Image carpet;
    private Image jo;
    private boolean drawjo;
    private int jox, joy;
    private int nrows = 0;
    private int npass = 0;
    private HashMap<String, String> occupied_list;
    private int passX, passY;
    private static final int INITIAL_X = 500;
    private static final int INITIAL_Y = 100;
    private static final int DOOR_X = 400;
    private static final int DOOR_Y = 70;
    private static final int STAND_Y = INITIAL_Y + 90;
    private HashMap<String, Passenger> passengers;
    private Sprites sp;
    private Font font;
    private ArrayList<String> action;
    private int naction;
    private long timing;
    private long walk_timing;
    
    public Deck(int nrows, int npass, HashMap<String, String> oc_list) {
        this.nrows = nrows;
        this.npass = npass;
        timing = 1000;
        walk_timing = 10;
        action = new ArrayList<String>();
        
        sp = new Sprites();
        drawjo = false;
        jox = 0; joy = 0;
        passengers = new HashMap<String, Passenger>();
        for(int ii=1; ii<=npass; ii++)
        	passengers.put("pass"+ii, new Passenger(sp));
        
        occupied_list = oc_list;
        setBackground(Color.WHITE);
        
    	try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/pixel7.ttf"));
			font = font.deriveFont(Font.BOLD, 18);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public void init(){
        loadImage();
        setSurfaceSize();
        initPlane();
    }

    private void loadImage() {

        door = sp.getDoor();
        seat= sp.getSeat();
        nose = sp.getNose();
        carpet = sp.getCarpet();
        jo = sp.getJo();
    }
    
    private void setSurfaceSize() {
        
        Dimension d = new Dimension();
        d.width = (nrows * 40) + INITIAL_X + 50;
        d.height = 500;
        setPreferredSize(d);        
    }
    
    private void initPlane(){    	
    	for (String string : occupied_list.keySet()) {
    		Passenger p = passengers.get(string);
    		String value = occupied_list.get(string);
    		int y = letterToPos(value.substring(value.indexOf("-") + 1).toUpperCase());
    		int x = Integer.parseInt(value.substring(value.indexOf("row")+3, value.indexOf("-")));

    		p.move(INITIAL_X + ((x - 1) * 40), y);
    		p.sit();
    		p.setBoarded(true);
		}
    }
    
    private void drawPlane(Graphics g, int nrows){
    	
    	Graphics2D g2d = (Graphics2D) g;
    	int x = INITIAL_X;
    	int y = INITIAL_Y;

    	g2d.drawImage(nose, 0, 20, null);
    	g2d.drawImage(door, DOOR_X, DOOR_Y, null);
    	int boxx = 320;
    	g2d.drawImage(sp.getLogo(), boxx-200, 430, null);
    	
    	g2d.drawImage(sp.getBox(), boxx, 430, null);
    	
    	g2d.setPaint(new Color(6, 132, 154));
    	font = font.deriveFont(Font.BOLD, 18);
    	
    	g2d.setFont(font);
    	
    	g2d.drawString("A", x - 30, y + 30);
    	g2d.drawString("B", x - 30, y + 60);
    	g2d.drawString("C", x - 30, y + 90);
    	g2d.drawString("D", x - 30, y + 160);
    	g2d.drawString("E", x - 30, y + 190);
    	g2d.drawString("F", x - 30, y + 220);
    	
    	for(int ii = 0; ii<nrows; ii++){
    		g2d.drawString(""+(ii+1), x+5, y);
    		
    		g2d.drawImage(seat, x, y+10, null);
    		g2d.drawImage(seat, x, y+40, null);
    		g2d.drawImage(seat, x, y+70, null);
    		g2d.drawImage(carpet, x, y+105, null);
    		g2d.drawImage(seat, x, y+140, null);
    		g2d.drawImage(seat, x, y+170, null);
    		g2d.drawImage(seat, x, y+200, null);
    		
    		g2d.drawString(""+(ii+1), x+5, y+250);
    		x+=40;
    	}
    	
    	for(int ii=1; ii<=npass; ii++){
    		Passenger p = passengers.get("pass"+ii);
    		if(p.isBoarded()){
    			g2d.drawImage(p.getSprite(), p.getPosx(), p.getPosy(), null);
    		}
    	}
    	
    	if(drawjo)
    		g2d.drawImage(jo, jox, joy, null);
    	
    	g2d.setPaint(new Color(0, 196, 46));
    	font = font.deriveFont(Font.BOLD, 35);
    	g2d.setFont(font);
    	g2d.drawString(action.get(naction), boxx+30, 520);
    	
    	g2d.setPaint(new Color(6, 132, 154));
    	font = font.deriveFont(Font.BOLD, 25);
    	g2d.setFont(font);
    	if(naction > 1)
    		g2d.drawString(action.get(naction-2), boxx+60, 460);
    	if(naction > 0)
    		g2d.drawString(action.get(naction-1), boxx+60, 490);
    	if(naction < action.size() - 1)
    		g2d.drawString(action.get(naction + 1), boxx+60, 550);
    	if(naction < action.size() - 2)
    		g2d.drawString(action.get(naction + 2), boxx+60, 580);
    }

    public void walk(int from, int to, String pass){
    	drawjo = false;
    	Passenger passenger = passengers.get(pass);
    	passenger.setSeated(false);
    	passenger.setBoarded(true);

    	passX = DOOR_X;
    	passY = DOOR_Y + 15;
    	if(from > 0){
    		passX = INITIAL_X + ((from-1) * 40);
    		passY = STAND_Y;
    	}
    	int endpos = INITIAL_X + ((to-1) * 40);
    	boolean done = false;
    	passenger.front();
    	
    	while(!done){
    		if(passY < STAND_Y){
    			passY++;
    		}else{
    			if(from < to){
    				passX++;
    				passenger.right();
    				if(passX >= endpos)
        				done = true;
    			}
    			else if(from > to){
    				passX--;
    				passenger.left();
    				if(passX <= endpos)
        				done = true;
    			}
    			
    		}
    		passenger.move(passX, passY);
    		repaint();
    		delay(walk_timing);
    	}
    }
    
    private void delay(long arg){
    	try {
			Thread.sleep(arg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
    }
    
    public void stand(int row, boolean front, Passenger pass) {
    	drawjo = false;
    	Passenger p = pass;
    	p.setSeated(false);
    	p.move(INITIAL_X + ((row - 1) * 40), STAND_Y);
    	
    	if(front)
    		p.front();
    	else
    		p.back();

    	repaint();
		delay(timing);
    }
    
    public void sit(String row, String letter, String pass){
    	drawjo = false;
    	Passenger p = passengers.get(pass);
    	
    	letter = letter.toUpperCase();
    	int x = Integer.parseInt(row.substring(3));
    	stand(x, (letter.compareTo("D")<0 ? false : true), p);
    	
    	p.setSeated(true);
    	p.move(INITIAL_X + (x-1)*40, letterToPos(letter));
    	p.sit();

    	repaint();
		delay(timing);
    }
    
    public void setjo(int row, int seat){
    	drawjo = true;
    	jox = INITIAL_X + 15 + (row-1)*40;
    	joy = seat - 15;
    	
    }
    
    public void stand_up(String row, String letter, String pass){
   	
    	letter = letter.toUpperCase();
    	setjo(Integer.parseInt(row.substring(3)), letterToPos(letter));
    	
    	repaint();
    	delay(timing/2);
    	
    	Passenger p = passengers.get(pass);
    	
    	stand(Integer.parseInt(row.substring(3)), (letter.compareTo("D")<0 ? true : false), p);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
       	drawPlane(g, nrows);
    }
    
    private int letterToPos(String letter){
    	if(letter.equals("A"))
    		return INITIAL_Y + 10;
    	else if(letter.equals("B"))
    		return INITIAL_Y + 40;
    	else if(letter.equals("C"))
    		return INITIAL_Y + 70;
    	else if(letter.equals("D"))
    		return INITIAL_Y + 140;
    	else if(letter.equals("E"))
    		return INITIAL_Y + 170;
    	else if(letter.equals("F"))
    		return INITIAL_Y + 200;
    	else return INITIAL_Y + 10;
    }

	public void setAction(ArrayList<String> action) {
		this.action = action;
	}
	
	public void setNaction(int naction) {
		this.naction = naction;
	}

	public void setTiming(long timing) {
		this.timing = timing;
		if(timing < 600)
			walk_timing = 5;
		if(timing < 400)
			walk_timing = 2;
	}
}
