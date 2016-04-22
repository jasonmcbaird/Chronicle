package utilities;

import queue.Event;
import character.Character;
import enums.Condition;

public class EventSet {
    private Event event;
    private Character source;
    private Character target;
    private Condition condition;
    private Character launcher;
    
    
    public EventSet(Event event, Character source, Character target, Condition condition) {
        this.event = event;
        this.source = source;
        this.target = target;
        this.condition = condition;
        launcher = target;
    }
    
    public EventSet(Event event, Character source, Character target, Condition condition, Character launcher) {
        this.event = event;
        this.source = source;
        this.target = target;
        this.condition = condition;
        this.launcher = launcher;
    }
    
    public Event getEvent(){
    	return event; 
    }
    
    public Character getSource(){
    	return source;
    }
    
    public Character getTarget(){
    	return target;
    }
    
    public Condition getCondition(){
    	return condition;
    }
    
    public Character getLauncher(){
    	return launcher;
    }
    
    public void setEvent(Event event){
    	this.event = event;
    }
    
    public void setSource(Character source){
    	this.source = source;
    }
    
    public void setTarget(Character target){
    	this.target = target;
    }
    
    public void setCondition(Condition condition){
    	this.condition = condition;
    }
    
    public void setLauncher(Character launcher){
    	this.launcher = launcher;
    }
    
    
}