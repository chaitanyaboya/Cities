package com.test.one.ConnectingCities.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class RoadMap {
	File mapFile;
	HashMap<String, ArrayList<String>> roadMap;

	public RoadMap() {
		mapFile = new File("city.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(mapFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		roadMap = new HashMap<String, ArrayList<String>>();

		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			String[] strs = str.split(",");
			if (strs.length == 2) {
				strs[0] = strs[0].replaceAll("\\s+", "");
				strs[1] = strs[1].replaceAll("\\s+", "");
				ArrayList<String> arr = new ArrayList<String>();
				if (roadMap.containsKey(strs[0])) {
					arr = roadMap.get(strs[0]);
				}
				arr.add(strs[1]);
				roadMap.put(strs[0], arr);

				arr = new ArrayList<String>();
				if (roadMap.containsKey(strs[1])) {
					arr = roadMap.get(strs[1]);
				}
				arr.add(strs[0]);
				roadMap.put(strs[1], arr);

			}

		}
		
	}

	public boolean hasRoad(String origin, String destination) {
		boolean retn = false;
		if (roadMap.containsKey(origin)) {
			ArrayList<String> path = new ArrayList<String>();
			path.add(origin);
			path = path(origin, destination, path);
			if(path == null) {
				return false;
			}
			if (path.contains(destination)) {
				return true;
			}
		}
		return retn;
	}

	private ArrayList<String> path(String init, String destination, ArrayList<String> path) {
		ArrayList<String> arr = roadMap.get(init);
		for (String str : arr) {
			if (str.equals(destination)) {
				path.add(str);
				return path;
			} else if (roadMap.containsKey(str) && !path.contains(str)) {
				path.add(str);
				path(str, destination, path);
			}
		}
		return path;
	}
}
