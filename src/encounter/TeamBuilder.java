package encounter;

import java.util.ArrayList;

import utilities.Logger;
import utilities.Pair;
import character.Character;
import character.CharacterBuilder;
import character.Team;

public class TeamBuilder {
	
	private static ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> squads;
	
	public static ArrayList<Character> build(String string, int size, Team team) {
		Logger.print("Creating team: " + team.getName());
		ArrayList<Pair<String, Integer>> characterTags = setupCharacterLists(string);
		return buildFromSquad(characterTags, size, team);
	}
	
	public static ArrayList<Character> buildWithoutLeader(String string, int size, Team team) {
		Logger.print("Creating team: " + team.getName());
		ArrayList<Pair<String, Integer>> characterTags = setupCharacterLists(string);
		characterTags.remove(0);
		return buildFromSquad(characterTags, size, team);
	}
	
	private static ArrayList<Pair<String, Integer>> setupCharacterLists(String string) {
		return getSquad(getSquads(), string);
	}
	
	private static ArrayList<Character> buildFromSquad(ArrayList<Pair<String, Integer>> characterTags, int size, Team team) {
		ArrayList<Character> characters = new ArrayList<Character>();
		int i = 1;
		for(Pair<String, Integer> pair: characterTags)
			if(i <= size) {
				characters.add(CharacterBuilder.makeCharacter(pair.getKey(), pair.getValue()));
				i++;
			}
		setTeam(characters, team);
		return characters;
	}
	
	private static ArrayList<Pair<String, Integer>> getSquad(ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> squads, String string) {
		for(Pair<String, ArrayList<Pair<String, Integer>>> pair: squads)
			if(pair.getKey() == string)
				return pair.getValue();
		return null;
	}
	
	private static ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> getSquads() {
		if(squads == null)
			buildSquads();
		return squads;
	}
	
	private static void buildSquads() {
		squads = new ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>();
		
		
		ArrayList<Pair<String, Integer>> teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Commander", 3));
		teamCharacters.add(new Pair<String, Integer>("Warrior", 2));
		teamCharacters.add(new Pair<String, Integer>("Warrior", 2));
		teamCharacters.add(new Pair<String, Integer>("General", 2));
		teamCharacters.add(new Pair<String, Integer>("Purger", 2));
		teamCharacters.add(new Pair<String, Integer>("Ranger", 0));
		teamCharacters.add(new Pair<String, Integer>("Ranger", 0));
		teamCharacters.add(new Pair<String, Integer>("Warrior", 1));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Bulwark", teamCharacters));
		
		teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Ninja", 8));
		teamCharacters.add(new Pair<String, Integer>("Assassin", 7));
		teamCharacters.add(new Pair<String, Integer>("Assassin", 7));
		teamCharacters.add(new Pair<String, Integer>("Warp", 8));
		teamCharacters.add(new Pair<String, Integer>("Assassin", 6));
		teamCharacters.add(new Pair<String, Integer>("Ranger", 6));
		teamCharacters.add(new Pair<String, Integer>("Warp", 5));
		teamCharacters.add(new Pair<String, Integer>("Assassin", 5));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Executioners", teamCharacters));
		
		teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Thief", 3));
		teamCharacters.add(new Pair<String, Integer>("Rogue", 2));
		teamCharacters.add(new Pair<String, Integer>("Warrior", 1));
		teamCharacters.add(new Pair<String, Integer>("Rogue", 1));
		teamCharacters.add(new Pair<String, Integer>("Rogue", 1));
		teamCharacters.add(new Pair<String, Integer>("Assassin", 0));
		teamCharacters.add(new Pair<String, Integer>("Warrior", 1));
		teamCharacters.add(new Pair<String, Integer>("Rogue", 0));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Brigands", teamCharacters));
		
		teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Paladin", 5));
		teamCharacters.add(new Pair<String, Integer>("Purger", 3));
		teamCharacters.add(new Pair<String, Integer>("Purger", 3));
		teamCharacters.add(new Pair<String, Integer>("Purger", 3));
		teamCharacters.add(new Pair<String, Integer>("General", 4));
		teamCharacters.add(new Pair<String, Integer>("Purger", 2));
		teamCharacters.add(new Pair<String, Integer>("General", 2));
		teamCharacters.add(new Pair<String, Integer>("Purger", 1));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Inquisition", teamCharacters));
		
		teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Blood Mage", 8));
		teamCharacters.add(new Pair<String, Integer>("Mage", 7));
		teamCharacters.add(new Pair<String, Integer>("Mage", 7));
		teamCharacters.add(new Pair<String, Integer>("Necromancer", 8));
		teamCharacters.add(new Pair<String, Integer>("Mage", 6));
		teamCharacters.add(new Pair<String, Integer>("Fate", 5));
		teamCharacters.add(new Pair<String, Integer>("Mage", 5));
		teamCharacters.add(new Pair<String, Integer>("Warlock", 6));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Circle of Magi", teamCharacters));
		
		teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Blood Mage", 5));
		teamCharacters.add(new Pair<String, Integer>("Fate", 4));
		teamCharacters.add(new Pair<String, Integer>("Necromancer", 3));
		teamCharacters.add(new Pair<String, Integer>("Warlock", 4));
		teamCharacters.add(new Pair<String, Integer>("Fate", 3));
		teamCharacters.add(new Pair<String, Integer>("Artificer", 2));
		teamCharacters.add(new Pair<String, Integer>("Fate", 3));
		teamCharacters.add(new Pair<String, Integer>("Warlock", 3));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Cultists", teamCharacters));
		
		teamCharacters = new ArrayList<Pair<String, Integer>>();
		teamCharacters.add(new Pair<String, Integer>("Demon", 10));
		teamCharacters.add(new Pair<String, Integer>("Fate", 6));
		teamCharacters.add(new Pair<String, Integer>("Necromancer", 5));
		teamCharacters.add(new Pair<String, Integer>("Warlock", 5));
		teamCharacters.add(new Pair<String, Integer>("Fate", 4));
		teamCharacters.add(new Pair<String, Integer>("Artificer", 5));
		teamCharacters.add(new Pair<String, Integer>("Fate", 5));
		teamCharacters.add(new Pair<String, Integer>("Warlock", 4));
		squads.add(new Pair<String, ArrayList<Pair<String, Integer>>>("Demonlord", teamCharacters));
	}
	
	private static void setTeam(ArrayList<Character> characters, Team team) {
		for(Character character: characters)
			character.setTeam(team);
	}
	
	public static ArrayList<String> getTeamNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(Pair<String, ArrayList<Pair<String, Integer>>> pair: getSquads())
			names.add(pair.getKey());
		return names;
	}

}
