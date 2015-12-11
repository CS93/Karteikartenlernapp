/**
 * @author Ricardo La Valle
 */
package de.fhdw.bfws114a.dataInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Environment;
import de.fhdw.bfws114a.data.Card;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.File;
import de.fhdw.bfws114a.data.Statistics;
import de.fhdw.bfws114a.data.User;

public class DataInterface {
	
	private final static String FILEPATH = "/android/data/de.fhdw.LernKartei/";
	private final static String FILENAME = "karteien.xml";

	private DatabaseHandler db;
	private XMLPullParserHandler xmlHandler;
	private int[] timeToClasses;
	private Activity activity;

	public DataInterface(Activity activity) {
		db = new DatabaseHandler(activity);
		xmlHandler = new XMLPullParserHandler();
		this.activity = activity;
	}

	public ArrayList<User> loadUsers() {
		// Returns an User Object with all Users saved in the DB
		return (ArrayList<User>) db.getAllUsers();
	}

	// load one user
	public User getUser(String username) {
		// Returns a User, saved in the DB, specified by the username
		return db.getUser(username);
	}

	public void delUser(String username) {
		// Delets a User, saved in the DB, specified by the username
		User tempUser = db.getUser(username);
		db.deleteUser(username);
		db.deleteUserScores(tempUser);
	}

	public void addUser(String username) {
		// Adds a User into the DB and creates the initial UserScores in the UserScores Table
		db.addUser(username);
		initialUserScores(username);
	}

	public int[] getClassDurations(User user) {
		// Returns an Integer Array with all Class Durations for one specific User.
		timeToClasses = new int[6];
		// The durations are in the time unit minute (1h = 60mins)
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
		// Returns an Integer Array with Default Class Durations.
		int[] timeToClasses = new int[6];
		// The durations are in the time unit minute (1h = 60mins)
		timeToClasses[0] = 5; // 5mins
		timeToClasses[1] = 60; // 1h
		timeToClasses[2] = 1440; // 1d
		timeToClasses[3] = 10080; // 7d
		timeToClasses[4] = 43200; // 30d
		timeToClasses[5] = 259200; // 180d
		return timeToClasses;
	}

	public void updateUserClassDurations(User user, int[] ClassDurations) {
		// Updates the Class Durations for one specific User.
		db.updateUserClasses(user.getName(), ClassDurations[0],
				ClassDurations[1], ClassDurations[2], ClassDurations[3],
				ClassDurations[4], ClassDurations[5]);
	}

	public int getTimePeriod(int classNumber, User user) {
		// Returns the Duration the given Class for one specific User.
		if (timeToClasses == null) {
			getClassDurations(user);
		}

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
		// Increases the Class a challenge is graduated to for a specific user.
		// No validation (Class <1 or >6) is done. This need to be checked before calling this method.
		Card temp = new Card();
		temp.setId(currentChallenge.getCardID());
		temp.setQuestion(currentChallenge.getQuestion());
		temp.setFile(currentChallenge.getFileID());
		
		db.updateUserScore(currentChallenge.getFileID(), currentChallenge.getCardID(), user, db.getAssignedClass(temp, user)+1);

	}

	public void decreaseClass(Challenge currentChallenge, User user) {
		// Decreases the Class a challenge is graduated to for a specific user.
		// No validation (Class <1 or >6) is done. This need to be checked before calling this method.
		Card temp = new Card();
		temp.setId(currentChallenge.getCardID());
		temp.setQuestion(currentChallenge.getQuestion());
		temp.setFile(currentChallenge.getFileID());
		
		db.updateUserScore(currentChallenge.getFileID(), currentChallenge.getCardID(), user, db.getAssignedClass(temp, user)-1);
	}

	public void setCurrentTimestamp(Challenge currentChallenge, User user) {
		// Updates the TimeStamp for a played Challenge for a specific user.
		db.updateUserScore(currentChallenge.getFileID(), currentChallenge.getCardID(), user, currentChallenge.getCurrentClass());
	}

	public ArrayList<String> getFileNames() {
		// Returns an String ArrayList of the Length 8 with the FileNames / Categories of all in the 
		// DB stored Files. 
		// If less then 8, the empty ones are filled with: "Leere Kategorie".
		// If more then 8, the first 8 will be returned.

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

	public ArrayList<Statistics> getFileNames(ArrayList<String> file_names, User mUser) {
		// Returns an ArrayList with Statistics-Objects.
		// Due challenges can not be calculated here. That need to be done later.
		// Because of that, the Statistics Objects are filled with the value "-1". 

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
			
			//prepare StringArray answers:
			//Together with Mr. Seifert, we arranged, that 6 possible answers are enough.
			String[] answers = new String[6]; 
			answers[0]=cards.get(i).getAnswer1();
			answers[1]=cards.get(i).getAnswer2();
			answers[2]=cards.get(i).getAnswer3();
			answers[3]=cards.get(i).getAnswer4();
			answers[4]=cards.get(i).getAnswer5();
			answers[5]=cards.get(i).getAnswer6();

			//prepare BooleanArray correctAnswers:
			boolean[] correctAnswers = new boolean[6];
			for(int j=1;j<=6;j++){
				CharSequence cs = Integer.toString(j);
				if(tempCard.getSolution() != null && tempCard.getSolution().contains(cs)){
					correctAnswers[j-1]=true;
				}
				else correctAnswers[j-1]=false;
			}

			Challenge tempChallenge = new Challenge(
					category, 
					tempCard.getId(),
					tempCard.getFile(),
					db.getAssignedClass(tempCard, user),
					timestamp, 
					tempCard.getQuestion(), 
					tempCard.getType(), 
					answers, 
					correctAnswers);
			
			allChallenges.add(tempChallenge);
		}

		return allChallenges;
	}

	public boolean importXMLtoDB() {
		// Imports the XMLFile from the ExternalStorage into the DB.
		// All existing Data stored in the DB, excepting the users, will be deleted before.
		copyDefaultXMLintoExternalFolder();
		db.clearFileTable();
		db.clearCardTable();
		db.clearUserscoreTable();
		List<Card> importedCards = new ArrayList<Card>();
		List<File> importedFiles = new ArrayList<File>();
		List<User> allUsers = db.getAllUsers();
		String uri = Environment.getExternalStorageDirectory().toString() + FILEPATH;
       
        java.io.File xmlFile = new java.io.File(uri, FILENAME);
        try {
        	FileInputStream fis1 = new FileInputStream(xmlFile);
        	FileInputStream fis2 = new FileInputStream(xmlFile);

        	importedFiles = xmlHandler.parseFiles(fis1);
			importedCards = xmlHandler.parseCards(fis2);
			
			for (int i = 0; i < importedFiles.size(); i++) {
				// Write the new Files / Categories into the DB.
				db.addFile(importedFiles.get(i));
			}
			for (int i = 0; i < importedCards.size(); i++) {
				// Write the new cards into the DB.
				db.addCard(importedCards.get(i));
			}
			
			// Initial the UserScores for the new Challenges and the existing User.
			for (int i = 0; i < allUsers.size(); i++){
				initialUserScores(allUsers.get(i).getName());
			}
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// If there is no XML-File in the external Storage, the default file from the Project-Directory will be copied.
	public void copyDefaultXMLintoExternalFolder(){
		String uri = Environment.getExternalStorageDirectory().toString() + FILEPATH;
        java.io.File xmlFile = new java.io.File(uri, FILENAME);
        
        if(!xmlFile.getParentFile().exists()){
        	try {
        		xmlFile.getParentFile().mkdirs();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        
        if(!xmlFile.exists()){
        	try {
				if(xmlFile.createNewFile()){
					InputStream is = activity.getAssets().open("karteien.xml");
					OutputStream out = new FileOutputStream(xmlFile);
					byte[] buf = new byte[1024];
					int len;
					while((len=is.read(buf))>0){			
						out.write(buf,0,len);
					}		
					out.close();
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	public void initialUserScores(String Username) {
		// Creates the initial UserScores for the specific user.
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
}
