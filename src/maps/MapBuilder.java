package maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import encounter.Encounter;

public class MapBuilder {
	
	private static HashMap<String, CustomMap> maps;
	
	public static CustomMap build(String string, Encounter encounter) {
		CustomMap customMap = getMaps().get(string);
		customMap.setup(encounter);
		return customMap;
	}
	
	private static HashMap<String, CustomMap> getMaps() {
		if(maps == null)
			buildMaps();
		return maps;
	}
	
	public static ArrayList<String> getMapNames() {
		ArrayList<String> mapNames = new ArrayList<String>();
		for(String string: getMaps().keySet())
			mapNames.add(string);
		Collections.sort(mapNames, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});
		Collections.reverse(mapNames);
		return mapNames;
	}
	
	private static void buildMaps() {
		maps = new HashMap<String, CustomMap>();
		maps.put("Ruins", new Ruins());
		maps.put("Chokepoint", new Chokepoint());
		maps.put("Canyon", new Canyon());
		maps.put("Caverns", new Caverns());
		maps.put("Cathedral", new Cathedral());
		maps.put("Fort", new Fort());
		maps.put("Arena", new Arena());
	}

}
