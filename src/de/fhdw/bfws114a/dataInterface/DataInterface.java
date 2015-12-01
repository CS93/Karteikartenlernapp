package de.fhdw.bfws114a.dataInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.Statistics;

public class DataInterface {
	
	//Test zu den Usern
private ArrayList<String> userList;
private DatabaseHandler db;
private int[] timeToClasses;


	public DataInterface(Context context){
		db = new DatabaseHandler(context);
	}

	public ArrayList<String> loadUser(){
		userList = new ArrayList<String>();
		List<User> DBUsers = db.getAllUsers();
        for (User u : DBUsers) {
        	userList.add(u.getName());
		}
		return userList;
	}
	
	public  ArrayList<String> delUser(ArrayList<String> user, String delUser){
		db.deleteUser(delUser);
		return loadUser();
//		//Test von http://www.sjmp.de/java/bestimmte-elemente-eines-arrays-loeschen/ angepasst
//		if (user != null) {
//			for (int i = 0; i < user.size(); i++) {
//				if (user.get(i).equals(delUser)) {
//					user.remove(i);
//				}
//			}
//			userList = user;
//			return user;
//		} else {
//			return user;
//		}
	}
	
	public  ArrayList<String> addUser(ArrayList<String> user, String newUser){
		user.add(newUser);
		//Log.d("DEBUG",db.getDatabaseName());
		db.addUser(newUser);
		return user;

//		user.add(newUser);
//		userList.add(newUser);
//		return user;
	}

	//Test zu den Klassen
	
	public int[] loadTimeToClasses(String user){
		timeToClasses = new int[6];
		//Zeiten der Klassen zu einem User aus xml in timeToClasses laden
		//Die Zeiten sind in Minuten angegeben und werden in der Activity in Stunden und Tage umgerechnet
		timeToClasses[0] = 1;	//1 Minute
		timeToClasses[1] = 5;		//5 Minuten
		timeToClasses[2] = 10;		//10 Minuten
		timeToClasses[3] = 30;		//30 Minuten
		timeToClasses[4] = 60;		//1 Stunde
		timeToClasses[5] = 1440;	//1 Tag
		return timeToClasses;
	}
	
	public int[] loadDefaultTimeToClasses(String user){
		int[] timeToClasses = new int[6];
		//Zeiten der Klassen zu einem User aus xml in timeToClasses laden
		//Die Zeiten sind in Min angegeben und werden in der Activity in Stunden und Tage umgerechnet
		timeToClasses[0] = 5;		//5 Min
		timeToClasses[1] = 60;		//1 Stunde
		timeToClasses[2] = 1440;	//1 Tag
		timeToClasses[3] = 10080;	//7 Tage
		timeToClasses[4] = 43200;	//30 Tage
		timeToClasses[5] = 259200; //180 Tage
		return timeToClasses;
	}
	
	public void saveTimeToClasses(String user, int[] timeToClass){
		//Zeiten der Klassen zu einem User in xml schreiben, Zeiten kommen in Millisec
		timeToClasses[0] = timeToClass[0];
		timeToClasses[1] = timeToClass[1];
		timeToClasses[2] = timeToClass[2];
		timeToClasses[3] = timeToClass[3];
		timeToClasses[4] = timeToClass[4];
		timeToClasses[5] = timeToClass[5]; 
	}
	
	public int getTimePeriod(int classNumber, String user){
		if (timeToClasses == null){
			loadTimeToClasses(user);
		}
		return timeToClasses[classNumber-1];
	}
	
	//Bei richtiger Antwort muss die Klasse der Challenge erhöht werden
	public void increaseClass(Challenge currentChallenge){
		//Nur zum testen
		int alteKlasse = currentChallenge.getAktuelleKlasse();
		int neueKlasse = alteKlasse+1;
		Log.d("", "Die Challenge muss von " + alteKlasse + "auf " + neueKlasse  + "erhöht werden");
	}
	
	//Bei fehlerhafter Antwort muss die Klasse der Challenge verringer werden
	public void decreaseClass(Challenge currentChallenge){
		//Nur zum testen
		int alteKlasse = currentChallenge.getAktuelleKlasse();
		int neueKlasse = alteKlasse-1;
		Log.d("", "Die Challenge muss von " + alteKlasse + "auf " + neueKlasse  + "erhöht werden");
	}
	
	//Zeitstempel der Challenge auf die aktuelle Zeit setzen
	public void setCurrentTimestamp(Challenge currentChallenge, String user){
		Date timestamp = new Date();
		Log.d("", "Der Zeitstempel wird von " + currentChallenge.getZeitstempel() + "auf " + timestamp  + "erhöht werden");
	}
	
	// Beginn der Karteien
	
	public ArrayList<String> loadCategories(){
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

	//Beginn der Statistik
	
	public ArrayList<Statistics> loadStatistics(ArrayList<String> karteien) {
		
		//Test
		ArrayList<Statistics> statistik= new ArrayList<Statistics>(); 
		Statistics Objekt1 = new Statistics(karteien.get(0), "10", "100");
		statistik.add(Objekt1);
		Statistics Objekt2 = new Statistics(karteien.get(1), "9", "100");
		statistik.add(Objekt2);
		Statistics Objekt3 = new Statistics(karteien.get(2), "2", "100");
		statistik.add(Objekt3);
		Statistics Objekt4 = new Statistics(karteien.get(3), "12", "100");
		statistik.add(Objekt4);
		Statistics Objekt5 = new Statistics(karteien.get(4), "4", "100");
		statistik.add(Objekt5);
		Statistics Objekt6 = new Statistics(karteien.get(5), "14", "100");
		statistik.add(Objekt6);
		Statistics Objekt7 = new Statistics(karteien.get(6), "5", "100");
		statistik.add(Objekt7);
		Statistics Objekt8 = new Statistics(karteien.get(7), "9", "100");
		statistik.add(Objekt8);
		return statistik;
		
	}

	//Beginn der Karteikarten
	
	public ArrayList<Challenge> loadChallenges(String category, String user) {
		ArrayList<Challenge> alleChallenges = new ArrayList<Challenge>();
		String[] antworten; //Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortmöglichkeiten geeinigt
		boolean[] korrekteAntworten;
		Date zeitstempel = new Date();
		Challenge challenge;
		//laden der Challenges in Abhängigikeit der Kartei (category) und des users aus der xml
		
		
		//Test mit 3 Challenges der verschiedenen Typen	
		
			//1. Kartei (typ 1 --> Checkbox)
			antworten = new String[6];//Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortmöglichkeiten geeinigt
			antworten[0] = "10 Jahre";
			antworten[1] = "30 Jahre";
			antworten[2] = "50 Jahre";
			antworten[3] = "70 Jahre";
			antworten[4] = "90 Jahre";
			antworten[5] = "110 Jahre";
			korrekteAntworten = new boolean[6];
			korrekteAntworten[0] = false;
			korrekteAntworten[1] = false;
			korrekteAntworten[2] = false;
			korrekteAntworten[3] = false;
			korrekteAntworten[4] = true;
			korrekteAntworten[5] = false;
			zeitstempel.setTime(0);
			challenge = new Challenge(category, 1, zeitstempel, "Wie hoch ist die maximale Lebenserwartung von Blauwalen", 1, antworten, korrekteAntworten);
			alleChallenges.add(challenge);

			//2. Kartei (typ 2 --> text-eingabe)
			antworten = new String[6];//Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortmöglichkeiten geeinigt
			antworten[0] = "33";
			korrekteAntworten = null;
			zeitstempel.setTime(0);
			challenge = new Challenge(category, 1, zeitstempel, "Wie viele Meter wird ein Blauwal maximal", 2, antworten, korrekteAntworten);
			alleChallenges.add(challenge);

			
			//3. Kartei (typ 3 --> Selbstkontrolle)
			antworten = new String[6];//Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortmöglichkeiten geeinigt
			antworten[0] = "200";
			korrekteAntworten = null;
			zeitstempel.setTime(0);
			challenge = new Challenge(category, 1, zeitstempel, "Wie viele Tonnen wiegt ein Blauwal maximal", 3, antworten, korrekteAntworten);
			alleChallenges.add(challenge);
			
		return alleChallenges;
	}
}
