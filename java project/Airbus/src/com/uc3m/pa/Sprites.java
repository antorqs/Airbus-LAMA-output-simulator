package com.uc3m.pa;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Sprites {
	private ArrayList<Image> front;
	private ArrayList<Image> back;
	private ArrayList<Image> left;
	private ArrayList<Image> right;
	private ArrayList<Image> head;
	private Image seat;
	private Image door;
	private Image jo;
	private Image nose;
	private Image carpet;
	private Image box;
	private Image logo;
	
	public Sprites(){
		front = new ArrayList<Image>();
		back = new ArrayList<Image>();
		left = new ArrayList<Image>();
		right = new ArrayList<Image>();
		head = new ArrayList<Image>();
		
		for(int ii = 1; ii<=6; ii++){
			front.add(new ImageIcon(getClass().getResource("/passengerFront"+ii+".png")).getImage());
			back.add(new ImageIcon(getClass().getResource("/passengerBack"+ii+".png")).getImage());
			left.add(new ImageIcon(getClass().getResource("/passengerLeft"+ii+".png")).getImage());
			right.add(new ImageIcon(getClass().getResource("/passengerRight"+ii+".png")).getImage());
			head.add(new ImageIcon(getClass().getResource("/head"+ii+".png")).getImage());
		}
		
        seat = new ImageIcon(getClass().getResource("/seat.png")).getImage();
        door = new ImageIcon(getClass().getResource("/door.png")).getImage();
        jo = new ImageIcon(getClass().getResource("/jo.png")).getImage();
        nose = new ImageIcon(getClass().getResource("/nose.png")).getImage();
        carpet = new ImageIcon(getClass().getResource("/carpet.png")).getImage();
        box = new ImageIcon(getClass().getResource("/box.png")).getImage();
        logo = new ImageIcon(getClass().getResource("/logo.png")).getImage();
	}
	
	public Image getDoor(){
		return door;
	}
	
	public Image getSeat(){
		return seat;
	}
	
	public Image getFront(int i){
		return front.get(i);
	}
	
	public Image getBack(int i){
		return back.get(i);
	}
	
	public Image getLeft(int i){
		return left.get(i);
	}
	
	public Image getRightt(int i){
		return right.get(i);
	}
	
	public Image getHead(int i){
		return head.get(i);
	}

	public Image getJo() {
		return jo;
	}

	public Image getNose() {
		return nose;
	}

	public Image getCarpet() {
		return carpet;
	}

	public Image getBox() {
		return box;
	}

	public Image getLogo() {
		return logo;
	}
}

