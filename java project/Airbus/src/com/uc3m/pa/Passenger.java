package com.uc3m.pa;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

public class Passenger {
	private Image sprite;
    private Image passengerL;
    private Image passengerR;
    private Image passengerF;
    private Image passengerB;
    private Image head;
    private boolean seated;
    private boolean boarded;
    private Sprites sprites;

	private int posx, posy;
	
	public Passenger(Sprites sp){
		posx = 0;
		posy = 0;
		sprites = sp;
		int num = ThreadLocalRandom.current().nextInt(0, 6);
        passengerR = sprites.getRightt(num);
        passengerL = sprites.getLeft(num);
        passengerF = sprites.getFront(num);
        passengerB = sprites.getBack(num);
        head = sprites.getHead(num);
        sprite = passengerF;
        seated = false;
        boarded = false;
	}
	
	public void move(int x, int y){
		posx = x;
		posy = y;
	}
	
	public void front(){
		sprite = passengerF;
	}
	
	public void back(){
		sprite = passengerB;
	}
	
	public void right(){
		sprite = passengerR;
	}
	
	public void left(){
		sprite = passengerL;
	}

	public void sit(){
		sprite = head;
	}
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public boolean isSeated() {
		return seated;
	}

	public void setSeated(boolean seated) {
		this.seated = seated;
	}

	public boolean isBoarded() {
		return boarded;
	}

	public void setBoarded(boolean boarded) {
		this.boarded = boarded;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}
}
