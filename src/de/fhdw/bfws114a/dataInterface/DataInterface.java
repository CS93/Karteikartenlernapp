package de.fhdw.bfws114a.dataInterface;

import java.util.ArrayList;
import java.util.Date;

import android.util.Log;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.StatisticsObject;

public class DataInterface {
	
	//Test zu den Usern
private static ArrayList<String> userList;
private DatabaseHandler db;



	public static ArrayList<String> loadUser(){
		//db.getDatabaseName();
		//List<User> users = db.getAllUsers();
		 
        //for (User u : users) {
           //userList.add(u.getName());
		//Test
		//}

		if (userList == null) {
			userList = new ArrayList<String>();
			userList.add("Samira");
			userList.add("Frank");
			userList.add("Carsten");
			userList.add("Ricardo");
		}
		return userList;
	}
	
	public static ArrayList<String> delUser(ArrayList<String> user, String delUser){
		//Test von http://www.sjmp.de/java/bestimmte-elemente-eines-arrays-loeschen/ angepasst
		if (user != null) {
			for (int i = 0; i < user.size(); i++) {
				if (user.get(i).equals(delUser)) {
					user.remove(i);
				}
			}
			userList = user;
			return user;
		} else {
			return user;
		}
		
	}
	
	public static ArrayList<String> addUser(ArrayList<String> user, String newUser){
		user.add(newUser);
		userList.add(newUser);
		return user;
	}

	//Test zu den Klassen
private static int[] timeToClasses;
	
	public static int[] loadTimeToClasses(String user){
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
	
	public static int[] loadDefaultTimeToClasses(String user){
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
	
	public static void saveTimeToClasses(String user, int[] timeToClass){
		//Zeiten der Klassen zu einem User in xml schreiben, Zeiten kommen in Millisec
		timeToClasses[0] = timeToClass[0];
		timeToClasses[1] = timeToClass[1];
		timeToClasses[2] = timeToClass[2];
		timeToClasses[3] = timeToClass[3];
		timeToClasses[4] = timeToClass[4];
		timeToClasses[5] = timeToClass[5]; 
	}
	
	public static int getTimePeriod(int classNumber, String user){
		if (timeToClasses == null){
			loadTimeToClasses(user);
		}
		return timeToClasses[classNumber-1];
	}
	
	//Bei richtiger Antwort muss die Klasse der Challenge erh�ht werden
	public static void increaseClass(Challenge currentChallenge){
		//Nur zum testen
		int alteKlasse = currentChallenge.getAktuelleKlasse();
		int neueKlasse = alteKlasse+1;
		Log.d("", "Die Challenge muss von " + alteKlasse + "auf " + neueKlasse  + "erh�ht werden");
	}
	
	//Bei fehlerhafter Antwort muss die Klasse der Challenge verringer werden
	public static void decreaseClass(Challenge currentChallenge){
		//Nur zum testen
		int alteKlasse = currentChallenge.getAktuelleKlasse();
		int neueKlasse = alteKlasse-1;
		Log.d("", "Die Challenge muss von " + alteKlasse + "auf " + neueKlasse  + "erh�ht werden");
	}
	
	//Zeitstempel der Challenge auf die aktuelle Zeit setzen
	public static void setCurrentTimestamp(Challenge currentChallenge, String user){
		Date timestamp = new Date();
		Log.d("", "Der Zeitstempel wird von " + currentChallenge.getZeitstempel() + "auf " + timestamp  + "erh�ht werden");
	}
	
	// Beginn der Karteien
	
	public static ArrayList<String> loadCategories(){
		//Karteien aus xml in mKarteien laden (es muss sichergestellt werden, dass die Anzahl der Karteien = 8 ist (siehe ApplicationLogic --> applyToData()
		//Au�erdem braucht man auch die dazugeh�rige Statistik
		
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

	//Beginn der Karteikarten
	
	public static ArrayList<Challenge> loadChallenges(String category, String user) {
		ArrayList<Challenge> alleChallenges = new ArrayList<Challenge>();
		String[] antworten; //Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortm�glichkeiten geeinigt
		boolean[] korrekteAntworten;
		Date zeitstempel = new Date();
		Challenge challenge;
		//laden der Challenges in Abh�ngigikeit der Kartei (category) und des users aus der xml
		
		
		//Test mit 3 Challenges der verschiedenen Typen	
		
			//1. Kartei (typ 1 --> Checkbox)
			antworten = new String[6];//Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortm�glichkeiten geeinigt
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
			antworten = new String[6];//Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortm�glichkeiten geeinigt
			antworten[0] = "33";
			korrekteAntworten = null;
			zeitstempel.setTime(0);
			challenge = new Challenge(category, 1, zeitstempel, "Wie viele Meter wird ein Blauwal maximal", 2, antworten, korrekteAntworten);
			alleChallenges.add(challenge);

			
			//3. Kartei (typ 3 --> Selbstkontrolle)
			antworten = new String[6];//Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortm�glichkeiten geeinigt
			antworten[0] = "200";
			korrekteAntworten = null;
			zeitstempel.setTime(0);
			challenge = new Challenge(category, 1, zeitstempel, "Wie viele Tonnen wiegt ein Blauwal maximal", 3, antworten, korrekteAntworten);
			alleChallenges.add(challenge);
			
		return alleChallenges;
	}
}
