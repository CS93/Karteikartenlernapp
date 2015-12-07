package de.fhdw.bfws114a.dataInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.fhdw.bfws114a.data.Card;
import de.fhdw.bfws114a.data.File;
import de.fhdw.bfws114a.data.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	//TEST
	// General variables
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "karteikartenDB";
	
	private static final int CLASS1_DEFAULTDURATION = 1;	//1 Minute
	private static final int CLASS2_DEFAULTDURATION = 5;	//5 Minute
	private static final int CLASS3_DEFAULTDURATION = 10;	//10 Minute
	private static final int CLASS4_DEFAULTDURATION = 30;	//30 Minute
	private static final int CLASS5_DEFAULTDURATION = 60;	//1 Stunde
	private static final int CLASS6_DEFAULTDURATION = 1440;	//1 Tag


	// Table name
	private static final String TABLE_USERS= "users";
	private static final String TABLE_USERSCORES = "userscores";
	private static final String TABLE_FILES = "cardfiles";
	private static final String TABLE_CARDS = "cards";

	//// Table Columns names
	// Table Users
	private static final String KEY_USERS_USERID = "userid";
	private static final String KEY_USERS_USERNAME = "username";
	private static final String KEY_USERS_CLASS1_DURATION = "class1_duration";
	private static final String KEY_USERS_CLASS2_DURATION = "class2_duration";
	private static final String KEY_USERS_CLASS3_DURATION = "class3_duration";
	private static final String KEY_USERS_CLASS4_DURATION = "class4_duration";
	private static final String KEY_USERS_CLASS5_DURATION = "class5_duration";
	private static final String KEY_USERS_CLASS6_DURATION = "class6_duration";
	private static final String KEY_USERS_LAST_SEEN = "last_seen";

	// Table Userscores
	private static final String KEY_USERSCORES_USERID = "fk_userid";
	private static final String KEY_USERSCORES_FILEID= "fk_fileid";
	private static final String KEY_USERSCORES_CARDID= "fk_cardid";
	private static final String KEY_USERSCORES_ASSIGNEDCLASS= "assignedClass";
	private static final String KEY_USERSCORES_TIMESTAMP= "timestamp";

	// Table Files
	private static final String KEY_FILES_FILEID= "fileid";
	private static final String KEY_FILES_FILENAME= "filename";
	
	// Table Cards
	private static final String KEY_CARDS_CARDID= "cardid";
	private static final String KEY_CARDS_FILEID= "fk_fileid";
	private static final String KEY_CARDS_TYPE= "type";
	private static final String KEY_CARDS_QUESTION= "question";
	private static final String KEY_CARDS_ANSWER1= "answer1";
	private static final String KEY_CARDS_ANSWER2= "answer2";
	private static final String KEY_CARDS_ANSWER3= "answer3";
	private static final String KEY_CARDS_ANSWER4= "answer4";
	private static final String KEY_CARDS_ANSWER5= "answer5";
	private static final String KEY_CARDS_ANSWER6= "answer6";
	private static final String KEY_CARDS_SOLUTION= "solution";
	
	

	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
				
//		Log.d("DEBUG", Environment.getExternalStorageDirectory().getAbsolutePath());
//		super(context, "/storage/sdcard0/"+ DATABASE_NAME, null, DATABASE_VERSION);

	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		createUsersTable(db);
		createCardsTable(db);
		createUserScoresTable(db);
		createFilesTable(db);
	}
	
	private boolean isSDCardWriteable() {
		boolean rc = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			rc = true;
		}
	    return rc;
	}
	
	private void createUsersTable(SQLiteDatabase db){
		String create_users_table = 
				"CREATE TABLE " + TABLE_USERS + "("
				+ KEY_USERS_USERID + " INTEGER PRIMARY KEY," 
				+ KEY_USERS_USERNAME + " TEXT,"
				+ KEY_USERS_CLASS1_DURATION + " INTEGER," 
				+ KEY_USERS_CLASS2_DURATION + " INTEGER," 
				+ KEY_USERS_CLASS3_DURATION + " INTEGER," 
				+ KEY_USERS_CLASS4_DURATION + " INTEGER," 
				+ KEY_USERS_CLASS5_DURATION + " INTEGER," 
				+ KEY_USERS_CLASS6_DURATION + " INTEGER,"
				+ KEY_USERS_LAST_SEEN + " TEXT"
				+ ")";
		db.execSQL(create_users_table);
	}
	
	private void createUserScoresTable(SQLiteDatabase db){
		String create_userscores_table = 
				"CREATE TABLE " + TABLE_USERSCORES + "("
				+ KEY_USERSCORES_USERID + " INTEGER," 
				+ KEY_USERSCORES_FILEID + " INTEGER," 
				+ KEY_USERSCORES_CARDID + " INTEGER," // --> das habe ich aus den Anführungszeichen genommen:  PRIMARY KEY  
				+ KEY_USERSCORES_ASSIGNEDCLASS + " INTEGER,"
				+ KEY_USERSCORES_TIMESTAMP + " TEXT,"
				+ "PRIMARY KEY ("
				+ KEY_USERSCORES_USERID + ","
				+ KEY_USERSCORES_FILEID + ","
				+ KEY_USERSCORES_CARDID + ")"
				+ ")";
		db.execSQL(create_userscores_table);
	}
	
	private void createCardsTable(SQLiteDatabase db){
		String create_cards_table = 
				"CREATE TABLE " + TABLE_CARDS + "("
				+ KEY_CARDS_CARDID+ " INTEGER,"
				+ KEY_CARDS_FILEID+ " INTEGER,"
				+ KEY_CARDS_TYPE + " INTEGER," 
				+ KEY_CARDS_QUESTION + " TEXT,"
				+ KEY_CARDS_ANSWER1 + " TEXT,"
				+ KEY_CARDS_ANSWER2 + " TEXT,"
				+ KEY_CARDS_ANSWER3 + " TEXT,"
				+ KEY_CARDS_ANSWER4 + " TEXT,"
				+ KEY_CARDS_ANSWER5 + " TEXT,"
				+ KEY_CARDS_ANSWER6 + " TEXT,"
				+ KEY_CARDS_SOLUTION + " TEXT,"
				+ "PRIMARY KEY ("
				+ KEY_CARDS_CARDID + ","
				+ KEY_CARDS_FILEID + ")"
				+ ")";
		db.execSQL(create_cards_table);
	}
	
	private void createFilesTable(SQLiteDatabase db){
		String create_files_table = 
				"CREATE TABLE " + TABLE_FILES + "("
				+ KEY_FILES_FILEID + " INTEGER PRIMARY KEY," 
				+ KEY_FILES_FILENAME + " TEXT"
				+ ")";
		db.execSQL(create_files_table);
	}
	
	private void dropUserTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
		db.close();
	}
	
	private void dropFileTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_FILES);
		db.close();
	}
	
	private void dropCardTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_CARDS);
		db.close();
	}
	
	private void dropUserscoresTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
		db.close();
	}

	public void clearUserTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_USERS);
		db.close();
	}
	
	public void clearFileTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_FILES);
		db.close();
	}
	
	public void clearCard(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_CARDS);
		db.close();
	}
	
	public void clearUserscoreTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_USERSCORES);
		db.close();
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERSCORES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILES);

		// Create tables again
		onCreate(db);
	}

	/***********************
	 * All USER Operations *
	 ***********************
*/
	// Adding new user
	void addUser(String username) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERS_USERNAME, username); // User Name
		values.put(KEY_USERS_CLASS1_DURATION, CLASS1_DEFAULTDURATION); // Class1 Duration
		values.put(KEY_USERS_CLASS2_DURATION, CLASS2_DEFAULTDURATION); // Class2 Duration
		values.put(KEY_USERS_CLASS3_DURATION, CLASS3_DEFAULTDURATION); // Class3 Duration
		values.put(KEY_USERS_CLASS4_DURATION, CLASS4_DEFAULTDURATION); // Class4 Duration
		values.put(KEY_USERS_CLASS5_DURATION, CLASS5_DEFAULTDURATION); // Class5 Duration
		values.put(KEY_USERS_CLASS6_DURATION, CLASS6_DEFAULTDURATION); // Class6 Duration

		// Inserting Row
		db.insert(TABLE_USERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single User
	User getUser(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_USERS, new String[] { 
				KEY_USERS_USERID,
				KEY_USERS_USERNAME, 
				KEY_USERS_CLASS1_DURATION, 
				KEY_USERS_CLASS2_DURATION, 
				KEY_USERS_CLASS3_DURATION,
				KEY_USERS_CLASS4_DURATION, 
				KEY_USERS_CLASS5_DURATION,
				KEY_USERS_CLASS6_DURATION,
				KEY_USERS_LAST_SEEN
				}, 
				KEY_USERS_USERID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), //Name
				Integer.parseInt(cursor.getString(2)), //Class1_Duration
				Integer.parseInt(cursor.getString(3)), //Class2_Duration
				Integer.parseInt(cursor.getString(4)), //Class3_Duration
				Integer.parseInt(cursor.getString(5)), //Class4_Duration
				Integer.parseInt(cursor.getString(6)), //Class5_Duration
				Integer.parseInt(cursor.getString(7)), //Class6_Duration
				cursor.getString(8)); //Last_Seen
		db.close();
		return user;
	}
	
	User getUser(String name) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_USERS, new String[] { 
				KEY_USERS_USERID,
				KEY_USERS_USERNAME, 
				KEY_USERS_CLASS1_DURATION, 
				KEY_USERS_CLASS2_DURATION, 
				KEY_USERS_CLASS3_DURATION,
				KEY_USERS_CLASS4_DURATION, 
				KEY_USERS_CLASS5_DURATION,
				KEY_USERS_CLASS6_DURATION,
				KEY_USERS_LAST_SEEN
				}, 
				KEY_USERS_USERNAME + "=?",
				new String[] { name }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), //Name
				Integer.parseInt(cursor.getString(2)), //Class1_Duration
				Integer.parseInt(cursor.getString(3)), //Class2_Duration
				Integer.parseInt(cursor.getString(4)), //Class3_Duration
				Integer.parseInt(cursor.getString(5)), //Class4_Duration
				Integer.parseInt(cursor.getString(6)), //Class5_Duration
				Integer.parseInt(cursor.getString(7)), //Class6_Duration
				cursor.getString(8)); //Last_Seen
		db.close();
		return user;
	}
	
	// Getting All Users
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_USERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				User user = new User(Integer.parseInt(cursor.getString(0)),
						cursor.getString(1), //Name
						Integer.parseInt(cursor.getString(2)), //Class1_Duration
						Integer.parseInt(cursor.getString(3)), //Class2_Duration
						Integer.parseInt(cursor.getString(4)), //Class3_Duration
						Integer.parseInt(cursor.getString(5)), //Class4_Duration
						Integer.parseInt(cursor.getString(6)), //Class5_Duration
						Integer.parseInt(cursor.getString(7)), //Class6_Duration
						cursor.getString(8)); //Last_Seen			
				// Adding user to list
				userList.add(user);
			} while (cursor.moveToNext());
		}

		// return user list
		return userList;
	}
	
	public void updateUser(){
		
	}
	
	public void updateUserClasses(String user, int class1, int class2, int class3, int class4, int class5, int class6){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_USERS_CLASS1_DURATION, class1); 
		values.put(KEY_USERS_CLASS2_DURATION, class2); 
		values.put(KEY_USERS_CLASS3_DURATION, class3); 
		values.put(KEY_USERS_CLASS4_DURATION, class4); 
		values.put(KEY_USERS_CLASS5_DURATION, class5); 
		values.put(KEY_USERS_CLASS6_DURATION, class6); 
		String whereClause = KEY_USERS_USERNAME + " = ?"; 
		String[] whereArgs = new String[] {user};		
		db.update(TABLE_USERS, values, whereClause, whereArgs);
		db.close();

	}

	// Deleting single user by ID
	public void deleteUser(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_USERS_USERID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}
	
	// Deleting user by name - ATTENTION: ALL USERS WITH THIS NAME WILL BE DELETED

	public void deleteUser(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_USERS_USERNAME + " = ?",
				new String[] { name });
		db.close();
	}

	// Getting user Count
	public int getUserCount() {
		String countQuery = "SELECT * FROM " + TABLE_USERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	//***********************
	//* All FILE Operations *
	//***********************
	
	// Adding new File
		public void addFile(File input) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_FILES_FILEID, input.getId()); // FileID
			values.put(KEY_FILES_FILENAME, input.getName()); // FileName

			// Inserting Row
			db.insert(TABLE_FILES, null, values);
			db.close(); // Closing database connection
		}
		
		public ArrayList<File> getAllFiles(){
			ArrayList<File> result= new ArrayList<File>();
			
			String selectQuery = "SELECT * FROM " + TABLE_FILES;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					File file = new File();
					file.setId(Integer.parseInt(cursor.getString(0))); //FileID
					file.setName(cursor.getString(1)); //FileName
									
					// Adding file to resultlist
					result.add(file);
				} while (cursor.moveToNext());
			}

			db.close();

			
			// return file list
			return result;
			
		}
	
		public int getFileID(String name){
			SQLiteDatabase db = this.getReadableDatabase();

			String selection = KEY_FILES_FILENAME + " = ?"; 
			String[] selectionArgs = new String[] {name};
			String[] columns = new String[] {KEY_FILES_FILEID};
			
			Cursor cursor = db.query(TABLE_FILES, columns, selection, selectionArgs, null, null, null);
			if(cursor.moveToFirst()){
				return Integer.parseInt(cursor.getString(0));
			}
			else return -1;		
		}
		
	//***********************
	//* All CARD Operations *
	//***********************

		// Adding new Card
		public void addCard(Card input) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_CARDS_CARDID, input.getId()); // User Name
			values.put(KEY_CARDS_FILEID, input.getFile()); // User Name
			values.put(KEY_CARDS_TYPE, input.getType()); // User Name
			values.put(KEY_CARDS_QUESTION, input.getQuestion()); // User Name
			values.put(KEY_CARDS_ANSWER1, input.getAnswer1()); // User Name
			values.put(KEY_CARDS_ANSWER2, input.getAnswer2()); // User Name
			values.put(KEY_CARDS_ANSWER3, input.getAnswer3()); // User Name
			values.put(KEY_CARDS_ANSWER4, input.getAnswer4()); // User Name
			values.put(KEY_CARDS_ANSWER5, input.getAnswer5()); // User Name
			values.put(KEY_CARDS_ANSWER6, input.getAnswer6()); // User Name
			values.put(KEY_CARDS_SOLUTION, input.getSolution()); // User Name

			// Inserting Row
			db.insert(TABLE_CARDS, null, values);
			db.close(); // Closing database connection
		}
			
		public ArrayList<Card> getAllCards(){
			ArrayList<Card> result= new ArrayList<Card>();
			
			String selectQuery = "SELECT * FROM " + TABLE_CARDS;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Card card = new Card();
					card.setId(Integer.parseInt(cursor.getString(0))); 		//CardID
					card.setFile(Integer.parseInt(cursor.getString(1)));	//FileID
					card.setType(Integer.parseInt(cursor.getString(2)));	//Type					
					card.setQuestion((cursor.getString(3)));				//Question
					card.setAnswer1((cursor.getString(4)));					//Answer1
					card.setAnswer2((cursor.getString(5)));					//Answer2
					card.setAnswer3((cursor.getString(6)));					//Answer3
					card.setAnswer4((cursor.getString(7)));					//Answer4
					card.setAnswer5((cursor.getString(8)));					//Answer5
					card.setAnswer6((cursor.getString(9)));					//Answer6
					card.setSolution((cursor.getString(10))); 				//Solution
									
					// Adding card to resultlist
					result.add(card);
				} while (cursor.moveToNext());
			}
			db.close();
			
			// return file list
			return result;
			
		}
				
		public ArrayList<Card> getCardsByFile(String filename){
			//TESTED
			ArrayList<Card> result = new ArrayList<Card>();
			String fileID=Integer.toString(getFileID(filename));
			String[] columns = new String[] {
					KEY_CARDS_CARDID,
					KEY_CARDS_FILEID,
					KEY_CARDS_TYPE,
					KEY_CARDS_QUESTION,
					KEY_CARDS_ANSWER1,
					KEY_CARDS_ANSWER2,
					KEY_CARDS_ANSWER3,
					KEY_CARDS_ANSWER4,
					KEY_CARDS_ANSWER5,
					KEY_CARDS_ANSWER6,
					KEY_CARDS_SOLUTION};

					
			String selection = KEY_CARDS_FILEID + " = ?"; 
			String[] selectionArgs = new String[] {fileID};		

			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_CARDS, columns, selection, selectionArgs, null, null, null);			
				
					
			if (cursor.moveToFirst()) {
				do {
					Card card = new Card();
					card.setId(Integer.parseInt(cursor.getString(0))); //CardID
					card.setFile(Integer.parseInt(cursor.getString(1)));//FileID
					card.setType(Integer.parseInt(cursor.getString(2)));//Type					
					card.setQuestion((cursor.getString(3)));//Question
					card.setAnswer1((cursor.getString(4)));//Answer1
					card.setAnswer2((cursor.getString(5)));//Answer2
					card.setAnswer3((cursor.getString(6)));//Answer3
					card.setAnswer4((cursor.getString(7)));//Answer4
					card.setAnswer5((cursor.getString(8)));//Answer5
					card.setAnswer6((cursor.getString(9)));//Answer6
					card.setSolution((cursor.getString(10))); //Solution
											
					// Adding card to resultlist
					result.add(card);
//					Log.d("DEBUG", card.toString());

				} while (cursor.moveToNext());
			}		
			return result;
		}
	
	
	/****************************
	 * All USERSCORE Operations *
	 ****************************
*/
		
		public int getAssignedClass(Card mCard, User mUser){
			//TESTED
//			Log.d("DEBUG", "-- getAssignedClass wurde aufgerufen: --");
//			Log.d("DEBUG", "User: " + mUser.getName() + " (Name), " + mUser.getID() + " (ID)");
//			Log.d("DEBUG", "Card: " + mCard.getId() + " (ID), " + mCard.getQuestion() + " (Question)");

			int result=99;
			String sql=
					"SELECT " + KEY_USERSCORES_ASSIGNEDCLASS
					+ " FROM " + TABLE_USERSCORES
					+ " WHERE " + KEY_USERSCORES_USERID + "=?"
					+ " AND " + KEY_USERSCORES_FILEID + "=?"
					+ " AND " + KEY_USERSCORES_CARDID + "=?";
			
			String[] selectionArgs = new String[] {
					Integer.toString(mUser.getID()),
					Integer.toString(mCard.getFile()),
					Integer.toString(mCard.getId())
					};
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(sql, selectionArgs);
			if (cursor.moveToFirst()){
				result = Integer.parseInt(cursor.getString(0));
			}
			db.close();
			
//			if(result==99){
//				Log.d("DEBUG","Kein Eintrag für die Kombination gefunden");
//			}else 				Log.d("DEBUG","Assigned Class gefunden: " + result);
//			Log.d("DEBUG", "-- getAssignedClass Durchlauf beendet --");

			return result;
		}
		
		public long getUserScoreTimestamp(Card mCard, User mUser){
			//TESTED
			
			long result=0;
			String sql=
					"SELECT " + KEY_USERSCORES_TIMESTAMP
					+ " FROM " + TABLE_USERSCORES
					+ " WHERE " + KEY_USERSCORES_USERID + "=?"
					+ " AND " + KEY_USERSCORES_FILEID + "=?"
					+ " AND " + KEY_USERSCORES_CARDID + "=?";
			
			String[] selectionArgs = new String[] {
					Integer.toString(mUser.getID()),
					Integer.toString(mCard.getFile()),
					Integer.toString(mCard.getId())
					};
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(sql, selectionArgs);
			if (cursor.moveToFirst()){
				result = Long.parseLong(cursor.getString(0));
			}
			db.close();
			return result;
		}
		
		public void addUserScore(Card mCard, User mUser){
			//TESTED
			SQLiteDatabase db = this.getWritableDatabase();
			java.sql.Date epoche = new Date(0);
			
			ContentValues values = new ContentValues();
			values.put(KEY_USERSCORES_USERID, mUser.getID()); // UserID
			values.put(KEY_USERSCORES_FILEID, mCard.getFile()); // FileID
			values.put(KEY_USERSCORES_CARDID, mCard.getId()); // CardID
			values.put(KEY_USERSCORES_ASSIGNEDCLASS, 1); // AssignedClass
			values.put(KEY_USERSCORES_TIMESTAMP, epoche.getTime()); // TimeStamp

			// Inserting Row
			db.insert(TABLE_USERSCORES, null, values);
			db.close();
		}
		
		public void updateUserScore(int FileID, int CardID, User mUser, int newClass){
			// TESTED
			
			
			String sql=
					"UPDATE " + TABLE_USERSCORES
					+ " SET " + KEY_USERSCORES_ASSIGNEDCLASS + " = " + newClass 
					+ ", " + KEY_USERSCORES_TIMESTAMP + " = " + System.currentTimeMillis()
					+ " WHERE " + KEY_USERSCORES_USERID + " = " + Integer.toString(mUser.getID())
					+ " AND " + KEY_USERSCORES_FILEID + " = "+ FileID
					+ " AND " + KEY_USERSCORES_CARDID + " = "+ CardID;

			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL(sql);
			db.close();
		}
				
}
