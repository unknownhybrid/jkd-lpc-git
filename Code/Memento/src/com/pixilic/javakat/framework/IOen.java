package com.pixilic.javakat.framework;

import java.awt.Event;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Queue;

public class IOen implements KeyListener, MouseListener{	
	@Override
	public void keyPressed(KeyEvent arg0) {
		inputEvents.offer(arg0);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		inputEvents.remove(arg0);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		inputEvents.add(arg0);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		inputEvents.add(arg0);
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		inputEvents.remove(arg0);
	}
	
	
	protected Queue<InputEvent> inputEvents;
	protected InputEvent update(){
		return inputEvents.peek();
	}
}
