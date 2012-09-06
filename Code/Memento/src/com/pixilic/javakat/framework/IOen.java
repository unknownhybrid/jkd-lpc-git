package com.pixilic.javakat.framework;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

public class IOen implements KeyListener, MouseListener{	
	@Override
	public void keyPressed(KeyEvent arg0) {
		inputEvents.push(arg0);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		inputEvents.remove(arg0);
	}
	@Override
	public void keyTyped(KeyEvent arg0) { }
	@Override
	public void mouseClicked(MouseEvent arg0) {
		inputEvents.add(arg0);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		inputEvents.add(arg0);
	}
	@Override
	public void mouseExited(MouseEvent arg0) { }
	@Override
	public void mousePressed(MouseEvent arg0) { }
	@Override
	public void mouseReleased(MouseEvent arg0) {
		inputEvents.remove(arg0);
	}
	
	
	protected Stack<InputEvent> inputEvents;
	public IOen(){
		inputEvents = new Stack<InputEvent>();
	}
	public InputEvent update(){
		return !inputEvents.empty() ? inputEvents.pop() : null;
	}
	public boolean isEmpty(){
		return inputEvents.empty();
	}
}
