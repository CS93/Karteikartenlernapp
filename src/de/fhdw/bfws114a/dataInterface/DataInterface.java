package de.fhdw.bfws114a.dataInterface;

import java.util.ArrayList;

public class DataInterface {
	public static ArrayList<String> loadUser(){
		//Test
		ArrayList<String> userList = new ArrayList<String>();
		userList.add("Samira");
		userList.add("Frank");
		userList.add("Ricardo");
		userList.add("Carsten");
		return userList;
	}
	
	public static ArrayList<String> loadCategories(){
		//Karteien aus xml in mKarteien laden (es muss sichergestellt werden, dass die Anzahl der Karteien = 8 ist (siehe ApplicationLogic --> applyToData()
		//Außerdem braucht man auch die dazugehörige Statistik
		
		//Test
		ArrayList<String> karteien = new ArrayList<String>();
		karteien.add("Biologie");
		karteien.add("Chemie");
		karteien.add("Physik");
		karteien.add("Informatik");
		karteien.add("Sport");
		karteien.add("Technik");
		karteien.add("Geschichte");
		karteien.add("Erdkunde");
		
		return karteien;
	}

	public static ArrayList<StatisticsObject> loadStatistics(ArrayList<String> karteien) {
		
		//Test
		ArrayList<StatisticsObject> statistik= new ArrayList<StatisticsObject>(); 
		StatisticsObject Objekt1 = new StatisticsObject(karteien.get(0), "10", "100");
		statistik.add(Objekt1);
		StatisticsObject Objekt2 = new StatisticsObject(karteien.get(1), "9", "100");
		statistik.add(Objekt2);
		StatisticsObject Objekt3 = new StatisticsObject(karteien.get(2), "2", "100");
		statistik.add(Objekt3);
		StatisticsObject Objekt4 = new StatisticsObject(karteien.get(3), "12", "100");
		statistik.add(Objekt4);
		StatisticsObject Objekt5 = new StatisticsObject(karteien.get(4), "4", "100");
		statistik.add(Objekt5);
		StatisticsObject Objekt6 = new StatisticsObject(karteien.get(5), "14", "100");
		statistik.add(Objekt6);
		StatisticsObject Objekt7 = new StatisticsObject(karteien.get(6), "5", "100");
		statistik.add(Objekt7);
		StatisticsObject Objekt8 = new StatisticsObject(karteien.get(7), "9", "100");
		statistik.add(Objekt8);
		return statistik;
		
	}
}
