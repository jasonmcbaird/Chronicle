package character;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import enums.Relationship;

public class Team {
	
	/** Let the player pick their team color!
	 * 
	 */
	
	private Map<Team, Relationship> relationships = new HashMap<Team, Relationship>();
	private static Team playerTeam;
	private static Team enemyTeam;
	private static Team neutralTeam;
	private String name;
	private Controller controller;
	private Color color;
	
	public Team() {
		name = "Bitch team";
		controller = Controller.getDefaultAIController();
		setRelationship(this, Relationship.ALLY);
		color = Color.RED;
	}
	
	public void setRelationship(Team team, Relationship relationship) {
		relationships.put(team, relationship);
	}
	
	public Relationship getRelationship(Team team) {
		if(relationships.keySet().contains(team)) {
			return relationships.get(team);
		}
		return Relationship.ENEMY;
	}
	
	public static Team getPlayerTeam() {
		if(playerTeam == null) {
			playerTeam = new Team();
			playerTeam.setName("Player");
			playerTeam.setController(Controller.getPlayerController());
			playerTeam.setColor(Color.BLUE);
		}
		return playerTeam;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public static Team getEnemyTeam() {
		if(enemyTeam == null) {
			enemyTeam = new Team();
			enemyTeam.setName("Enemy");
		}
		return enemyTeam;
	}
	
	public static Team getNeutralTeam() {
		if(neutralTeam == null) {
			neutralTeam = new Team();
			neutralTeam.setName("Neutral");
			neutralTeam.setRelationship(Team.getPlayerTeam(), Relationship.NEUTRAL);
			neutralTeam.setRelationship(Team.getEnemyTeam(), Relationship.NEUTRAL);
			neutralTeam.setColor(Color.YELLOW);
		}
		return neutralTeam;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Character> getCharacters(ArrayList<Character> characters) {
		ArrayList<Character> teamCharacters = new ArrayList<Character>();
		for(Character character: characters)
			if(character.getTeam() == this)
				teamCharacters.add(character);
		return teamCharacters;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
