package ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.swing.JPanel;

import utilities.Logger;

public class UIStack {
	
	private static ArrayList<UIComponent> components = new ArrayList<UIComponent>();
	private static JPanel mainPanel;
	private static Input input = new Input();
	
	public UIStack() {
		
	}
	
	public static void setMainPanel(JPanel newMainPanel) {
		mainPanel = newMainPanel;
	}
	
	public static void push(UIComponent component) {
		components.add(0, component);
		Logger.print("Pushing " + component + " onto the stack.", -1);
		display();
		responderChanged();
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}
	
	public static void pop() {
		if(components.size() <= 0) {
			throw new EmptyStackException();
		}
		Logger.print("Popping " + components.get(0), -1);
		components.get(0).remove();
		components.remove(0);
		responderChanged();
	}
	
	public static void popResponder() {
		((UIComponent) getResponder()).remove();
		components.remove(getResponder());
		Logger.print("Popping " + getResponder(), -1);
		responderChanged();
	}
	
	public static void popCharacterDisplay() {
		ArrayList<UIComponent> toRemove = new ArrayList<UIComponent>();
		for(UIComponent component: components)
			if(component instanceof CharacterDisplay)
				toRemove.add(component);
		for(UIComponent component: toRemove) {
			Logger.print("Popping " + component, -1);
			component.remove();
			components.remove(component);
			responderChanged();
		}
	}
	
	public static int getSize() {
		return components.size();
	}
	
	public static UIComponent getTop() {
		if(components.size() <= 0) {
			throw new EmptyStackException();
		}
		return components.get(0);
	}
	
	private static void display() {
		//mainPanel.removeAll();
		ArrayList<UIComponent> componentsToAdd = new ArrayList<UIComponent>();
		UIComponent fullscreenComponent = null;
		for(UIComponent component: components) {
			componentsToAdd.add(component);
			if(component.isFullscreen()) {
				fullscreenComponent = component;
				break;
			}
		}
		for(UIComponent component: componentsToAdd) {
			Logger.print("Displaying " + component, -1);
			if(fullscreenComponent != null && component != fullscreenComponent) {
				displayComponent(component, fullscreenComponent);
			} else {
				displayComponent(component, mainPanel);
			}
		}
		if(fullscreenComponent != null)
			fullscreenComponent.requestFocusInWindow();
	}
	
	private static void displayComponent(UIComponent component, JPanel panel) {
		for(Component jComponent: panel.getComponents())
			if(component == jComponent)
				panel.remove(component);
		component.display(panel);
	}
	
	public static InputResponder getResponder() {
		for(UIComponent component: components) {
			if(component instanceof InputResponder) {
				return (InputResponder) component;
			}
		}
		return null;
	}
	
	public static void responderChanged() {
		input.addBindings();
	}
	
	public static void clear() {
		mainPanel.removeAll();
		components.clear();
	}
	
	public static void pop(UIComponent component) {
		Logger.print("Popping " + component, -1);
		component.remove();
		components.remove(component);
		responderChanged();
	}
	
	public static void pop(Class<?> class1) {
		Logger.print("Popping all " + class1, -1);
		ArrayList<UIComponent> toRemove = new ArrayList<UIComponent>();
		for(UIComponent component: components)
			if(class1.isInstance(component))
				toRemove.add(component);
		for(UIComponent component: toRemove) {
			pop(component);
		}
		responderChanged();
	}
	
	public static boolean contains(UIComponent component) {
		return components.contains(component);
	}
}
