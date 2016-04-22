package queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import rules.Attack;
import utilities.EventSet;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;

public class Queue {
	
	/**
	 * Queue is a singleton class that keeps track of future events.
	 * 
	 * 
	 * 
	 */
	
	private static Queue instance = null;
	private static ArrayList<EventSet> events = new ArrayList<EventSet>();
	
	protected Queue() {
		// You can't instantiate Queue directly. It's a singleton.
	}
	
	public static Queue get() {
		if(instance == null) {
			instance = new Queue();
		}
		return instance;
	}
	
	public static void addAndRun(Event event, Character source, Character target, Condition condition) {
		EventSet eventSet = createEventSet(event, source, target, condition);
		add(eventSet);
		run(condition, eventSet.getLauncher());
	}
	
	public static void addAndRun(Event event, Character source, Character target, Condition condition, Character launcher) {
		add(event, source, target, condition, launcher);
		run(condition, launcher);
	}
	
	public static void addAndRun(EventSet eventSet) {
		add(eventSet);
		run(eventSet.getCondition(), eventSet.getLauncher());
	}
	
	public static void add(Event event, Character source, Character target, Condition condition) {
		add(createEventSet(event, source, target, condition));
	}
	
	public static EventSet createEventSet(Event event, Character source, Character target, Condition condition) {
		// Normally, an event executes when its target's relevant condition occurs.
		
		// If adding an Ability to execute right away, it should execute when the source character's
		// ActivateAbility condition occurs, not when the target's ActivateAbility condition occurs.
		
		if(event instanceof Ability && condition == Condition.ACTIVATE_ABILITY) {
			return new EventSet(event, source, target, condition, source);
		}
		if(event instanceof Attack && condition == Condition.ATTACK) {
			return new EventSet(event, source, target, condition, source);
		}
		return new EventSet(event, source, target, condition);
	}
	
	public static void add(Event event, Character source, Character target, Condition condition, Character launcher) {
		events.add(new EventSet(event, source, target, condition, launcher));
	}
	
	public static void add(EventSet eventSet) {
		events.add(eventSet);
	}
	
	public static void run(Condition condition, Character character) {
		//Logger.get().print(character.getName() + " running " + condition);
		if(condition != Condition.NOW && condition != Condition.AFTER) {
			run(Condition.NOW, character);
		}
		boolean done = false;
		EndRunEvent endRunEvent = new EndRunEvent();
		EventSet endRunSet = new EventSet(endRunEvent, character, character, condition);
		Queue.add(endRunSet);
		
		while(!done) {
			ArrayList<EventSet> eventSetList = getEventSetList(condition, character);
			Logger.print("Unsorted list:", -1);
			logEventList(eventSetList);
			if(eventSetList.size() <= 1) {
				done = true;
				break;
			}
			
			eventSetList = new ArrayList<EventSet>(eventSetList.subList(0, eventSetList.lastIndexOf(endRunSet)));
			if(eventSetList.size() <= 0) {
				done = true;
				break;
			}
			eventSetList = sortPriority(eventSetList);
			Logger.print("Sorted list:", -1);
			logEventList(eventSetList);
			
			EventSet eventSet = eventSetList.get(0);
			Logger.print(character.getName() + " running " + eventSet.getEvent() + " from the " + condition + " queue.", -1);
			remove(eventSet.getEvent());
			eventSet.getEvent().execute(eventSet.getSource(), eventSet.getTarget(), condition);
		}
		remove(endRunEvent);
		if(condition != Condition.AFTER) {
			run(Condition.AFTER, character);
		}
	}
	
	private static ArrayList<EventSet> sortPriority(ArrayList<EventSet> eventSetList) {
		ArrayList<EventSet> sortedList = new ArrayList<EventSet>();
		sortedList.addAll(eventSetList);
		Collections.sort(sortedList, new Comparator<EventSet>() { // Sort by descending priority
	        @Override public int compare(EventSet a, EventSet b) {
	            return b.getEvent().getPriority() - a.getEvent().getPriority();
	        }
	    });
		return sortedList;
	}
	
	public static ArrayList<EventSet> getEventSetList(Condition condition) {
		ArrayList<EventSet> eventSetList = new ArrayList<EventSet>();
		for(EventSet eventSet: events) {
			if(eventSet.getCondition() == condition) {
				eventSetList.add(eventSet);
			}
		}
		return eventSetList;
	}
	
	public static ArrayList<EventSet> getEventSetList(Condition condition, Character character) {
		ArrayList<EventSet> eventSetList = getEventSetList(condition);
		ArrayList<EventSet> eventSetListForCharacter = new ArrayList<EventSet>();
		for(EventSet eventSet: eventSetList) {
			if(eventSet.getLauncher() == character) {
				eventSetListForCharacter.add(eventSet);
			}
		}
		return eventSetListForCharacter;
	}
	
	public static ArrayList<EventSet> getEventSetList(Character character) {
		ArrayList<EventSet> eventSetListForCharacter = new ArrayList<EventSet>();
		for(EventSet eventSet: events) {
			if(eventSet.getLauncher() == character) {
				eventSetListForCharacter.add(eventSet);
			}
		}
		return eventSetListForCharacter;
		// TODO: CODE DUPLICATION. BAD BAD
	}
	
	public static void remove(Condition condition, int index) {
		ArrayList<EventSet> eventSetList = getEventSetList(condition);
		remove(eventSetList.get(index).getEvent());
	}
	
	public static void remove(Event event) {
		for(EventSet eventSet: getEventSets(event))
			events.remove(eventSet);
	}
	
	public static void remove(Class<?> class1) {
		ArrayList<Event> toRemove = new ArrayList<Event>();
		for(EventSet eventSet: events)
			if(class1.isInstance(eventSet.getEvent()))
				toRemove.add(eventSet.getEvent());
		for(Event event: toRemove)
			remove(event);
		Logger.print("To remove: " + toRemove);
		Logger.print("Event set list: " + getEventSetList(Condition.START_TURN));
	}
	
	private static ArrayList<EventSet> getEventSets(Event event) {
		ArrayList<EventSet> correctEvents = new ArrayList<EventSet>();
		for(EventSet eventSet: events)
			if(eventSet.getEvent() == event)
				correctEvents.add(eventSet);
		return correctEvents;
	}
	
	public static void clear() {
		events.clear();
	}
	
	public static void logEventList(ArrayList<EventSet> eventSetList) {
		for(EventSet set: eventSetList)
			Logger.print(set.getSource().getName() + " has " + set.getEvent() + " on " + set.getTarget().getName() + " in " + set.getCondition(), -1); 
	}
	
	public static Character getSource(Event event) {
		for(EventSet eventSet: events)
			if(eventSet.getEvent() == event)
				return eventSet.getSource();
		return null;
	}
	
	static class EndRunEvent implements Event {
		
		public int getPriority() {
			return 0;
		}

		public void execute(Character source, Character target, Condition condition) {
			
		}
	}
}
