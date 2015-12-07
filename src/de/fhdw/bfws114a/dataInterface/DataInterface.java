package de.fhdw.bfws114a.dataInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import de.fhdw.bfws114a.data.Card;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.File;
import de.fhdw.bfws114a.data.Statistics;
import de.fhdw.bfws114a.data.User;

public class DataInterface {

	private DatabaseHandler db;
	private XMLPullParserHandler xmlHandler;
	private int[] timeToClasses;
	private Activity activity;

	public DataInterface(Activity activity) {
		db = new DatabaseHandler(activity);
		xmlHandler = new XMLPullParserHandler();
		this.activity = activity;
	}

	// load all users
	public ArrayList<User> loadUsers() {
		return (ArrayList<User>) db.getAllUsers();
	}

	// load one user
	public User getUser(String name) {
		return db.getUser(name);
	}

	// delete one User
	public void delUser(String delUser) {
		db.deleteUser(delUser);
	}

	// add one User
	public void addUser(String newUser) {
		db.addUser(newUser);
		initialUserScores(newUser);
	}

	// load the Time of Classes dependent on one User
	public int[] getClassDurations(User user) {
		timeToClasses = new int[6];
		// Die Zeiten sind in Minuten angegeben und werden in der Activity in umgerechnet
		timeToClasses[0] = user.getClass1_duration();
		timeToClasses[1] = user.getClass2_duration();
		timeToClasses[2] = user.getClass3_duration();
		timeToClasses[3] = user.getClass4_duration();
		timeToClasses[4] = user.getClass5_duration();
		timeToClasses[5] = user.getClass6_duration();
		return timeToClasses;
	}

	// load the default Time in minutes of Classes
	public int[] getDefaultClassDurations() {
		int[] timeToClasses = new int[6];
		// Die Zeiten sind in Min angegeben und werden in der Activity in
		// Stunden und Tage umgerechnet
		timeToClasses[0] = 5; // 5 Min
		timeToClasses[1] = 60; // 1 Stunde
		timeToClasses[2] = 1440; // 1 Tag
		timeToClasses[3] = 10080; // 7 Tage
		timeToClasses[4] = 43200; // 30 Tage
		timeToClasses[5] = 259200; // 180 Tage
		return timeToClasses;
	}

	// save Times of Classes in minutes dependent on one User
	// TESTED
	public void updateUserClassDurations(User user, int[] ClassDurations) {
		db.updateUserClasses(user.getName(), ClassDurations[0],
				ClassDurations[1], ClassDurations[2], ClassDurations[3],
				ClassDurations[4], ClassDurations[5]);
	}

	public int getTimePeriod(int classNumber, User user) {
		// Carsten: Hier benötige ich die Zeit in Minuten für die aktuelle
		// Klasse (um Fälligkeit der entsprechenden Kartei festzustellen)
		// DONE - NOT TESTED
		if (timeToClasses == null) {
			getClassDurations(user);
		}
		// return timeToClasses[classNumber-1];

		User mUser = db.getUser(user.getID());
		switch (classNumber) {
		case 1:
			return mUser.getClass1_duration();
		case 2:
			return mUser.getClass2_duration();
		case 3:
			return mUser.getClass3_duration();
		case 4:
			return mUser.getClass4_duration();
		case 5:
			return mUser.getClass5_duration();
		case 6:
			return mUser.getClass6_duration();
		default:
			return 0;
		}

	}

	public void increaseClass(Challenge currentChallenge, User user) {
		// TESTED
		// Carsten: Wenn eine richtige Antwort gegeben wurde rufe ich diese
		// Methode auf und möchte dass die Klasse in der sich die übergebene
		// Challenge befindet um 1 erhöht
		Log.d("DEBUG", "-- increaseClass wurde aufgerufen: --");
		Log.d("DEBUG", currentChallenge.getCardID() + " (CardID)");
		Log.d("DEBUG", currentChallenge.getFileID() + " (FileID)");
		Log.d("DEBUG", currentChallenge.getFrage() + " (Question)");


		
		int vorher = currentChallenge.getAktuelleKlasse();
		Card temp = new Card();
		temp.setId(currentChallenge.getCardID());
		temp.setQuestion(currentChallenge.getFrage());
		temp.setFile(currentChallenge.getFileID());
		
		Log.d("DEBUG", "Challange Klasse soll erhöht werde von Klasse " + db.getAssignedClass(temp, user) + " auf Klasse " + (db.getAssignedClass(temp, user)+1));
		db.updateUserScore(currentChallenge.getFileID(), currentChallenge.getCardID(), user, currentChallenge.getAktuelleKlasse()+1);
		Log.d("DEBUG", "Challange Klasse wurde erhöht von Klasse " + vorher + " auf Klasse " + db.getAssignedClass(temp, user));

	}

	public void decreaseClass(Challenge currentChallenge, User user) {
		// TESTED
		// Carsten: Wenn eine falsche Antwort gegeben wurde rufe ich diese
		// Methode auf und möchte dass die Klasse in der sich die übergebene
		// Challenge befindet um 1 verringert wird
		int vorher = currentChallenge.getAktuelleKlasse();
		Card temp = new Card();
		temp.setId(currentChallenge.getCardID());
		temp.setQuestion(currentChallenge.getFrage());
		temp.setFile(currentChallenge.getFileID());
		db.updateUserScore(currentChallenge.getFileID(), currentChallenge.getCardID(), user, currentChallenge.getAktuelleKlasse()-1);
		Log.d("DEBUG", "Challange Klasse wurde verringert von Klasse " + vorher + " auf Klasse " + db.getAssignedClass(temp, user));
	}

	public void setCurrentTimestamp(Challenge currentChallenge, User user) {
		// Carsten: Hier muss der Zeitstempel der Challenge auf die aktuelle
		// Zeit gesetzt werden
		Date timestamp = new Date();
		Log.d("",
				"Der Zeitstempel wird von " + currentChallenge.getZeitstempel()
						+ "auf " + timestamp + "erhöht werden");
	}

	public ArrayList<String> getFileNames() {
		// Carsten: Hier benötige ich die 8 Karteien (Kategorien) in denen sich der User "beweisen" kann.
		// WICHTIG: Es müssen genau 8 sein!! Zur Not mit leeren Strings auffüllen

		ArrayList<File> files = db.getAllFiles();	
		ArrayList<String> file_names = new ArrayList<String>();

		if(files.size()<8){
			for(int i=0;i<files.size();i++){
				file_names.add(files.get(i).getName());
			}
			for(int i=files.size();i<8;i++){
				file_names.add("Leere Kategorie");
			}
			return file_names;
		}
		else{

			for(int i=0;i<8;i++){
				file_names.add(files.get(i).getName());
			}
			return file_names;
		}
	}

	// Beginn der Statistik

	public ArrayList<Statistics> getFileNames(ArrayList<String> file_names, User mUser) {
		// Statistik zu den einzelnen Karteien/Kategorien nach dem Muster:
		// "Karteiname - Fällige_Challenges (abhängig vom User) - Gesamte_Challenges (in dieser Kartei)"

		ArrayList<Statistics> statistik = new ArrayList<Statistics>();
		statistik.add(new Statistics(file_names.get(0), -1, db.getCardsByFile(file_names.get(0)).size()));
		statistik.add(new Statistics(file_names.get(1), -1, db.getCardsByFile(file_names.get(1)).size()));
		statistik.add(new Statistics(file_names.get(2), -1, db.getCardsByFile(file_names.get(2)).size()));
		statistik.add(new Statistics(file_names.get(3), -1, db.getCardsByFile(file_names.get(3)).size()));
		statistik.add(new Statistics(file_names.get(4), -1, db.getCardsByFile(file_names.get(4)).size()));
		statistik.add(new Statistics(file_names.get(5), -1, db.getCardsByFile(file_names.get(5)).size()));
		statistik.add(new Statistics(file_names.get(6), -1, db.getCardsByFile(file_names.get(6)).size()));
		statistik.add(new Statistics(file_names.get(7), -1, db.getCardsByFile(file_names.get(7)).size()));
		return statistik;

	}

	public ArrayList<Challenge> loadChallenges(String category, User user) {
		// Carsten: Hier benötige ich alleChallenges in einer ArrayList vom Typ
		// Challenge
		// Sie sollen in Abhängigkeit von Kartei und User geladen werden
		
		ArrayList<Challenge> allChallenges = new ArrayList<Challenge>();
		ArrayList<Card> cards = db.getCardsByFile(category);
		
		for(int i=0;i<cards.size();i++){
			Card tempCard = cards.get(i);
			Date timestamp = new Date(db.getUserScoreTimestamp(tempCard, user));
			
			//StringArray antworten vorbereiten:
			String[] antworten = new String[6]; // Hinweise: Es wurde sich mit Herrn Seifert auf max. 6 Antwortmoeglichkeiten geeinigt
			antworten[0]=cards.get(i).getAnswer1();
			antworten[1]=cards.get(i).getAnswer2();
			antworten[2]=cards.get(i).getAnswer3();
			antworten[3]=cards.get(i).getAnswer4();
			antworten[4]=cards.get(i).getAnswer5();
			antworten[5]=cards.get(i).getAnswer6();

			//BooleanArray korrekteAntworten vorbereiten:
			boolean[] korrekteAntworten = new boolean[6];
			for(int j=1;j<=6;j++){
				CharSequence cs = Integer.toString(j);
				if(tempCard.getSolution().contains(cs)){
					korrekteAntworten[j-1]=true;
				}
				else korrekteAntworten[j-1]=false;
			}

			Challenge tempChallenge = new Challenge(
					category, 
					tempCard.getId(),
					tempCard.getFile(),
					db.getAssignedClass(tempCard, user),
					timestamp, 
					tempCard.getQuestion(), 
					tempCard.getType(), 
					antworten, 
					korrekteAntworten);
			
			allChallenges.add(tempChallenge);
		}

		return allChallenges;
	}

	/*****************************
	 * All XML Import Operations *
	 *****************************
	 */

	public void importXMLtoDB() {
		db.clearFileTable();
		db.clearCard();
		db.clearUserscoreTable();

		List<Card> importedCards = null;
		List<File> importedFiles = null;
		List<User> allUsers = db.getAllUsers();

		try {
			importedCards = xmlHandler.parseCards(activity.getAssets().open(
					"karteien.xml"));
			importedFiles = xmlHandler.parseFiles(activity.getAssets().open(
					"karteien.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < importedFiles.size(); i++) {
			// Kartei (ohne Karten) --> Nur Kategorie in DB schreiben
			db.addFile(importedFiles.get(i));
		}
		for (int i = 0; i < importedCards.size(); i++) {
			// Karten in die DB Schreiben
			db.addCard(importedCards.get(i));
		}
		
		// Initialisieren der neuen UserScores für bereits vorhandene Benutzer
		for (int i = 0; i < allUsers.size(); i++){
			initialUserScores(allUsers.get(i).getName());
		}
	}
	
	public void initialUserScores(String Username) {
		ArrayList<File> Files = db.getAllFiles();
		User user = getUser(Username);
		for(int i=0;i<Files.size();i++){
			File tempFile = Files.get(i);
			ArrayList<Card> tempCards= db.getCardsByFile(tempFile.getName());
			for(int j=0;j<tempCards.size();j++){
				Card tempCard = tempCards.get(j);
				db.addUserScore(tempCard, user);
			}
		}
	}

	public String isToString(InputStream is) {
		if (is != null) {
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();

			String line;
			try {

				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			return sb.toString();
		} else
			return "";
	}

}
