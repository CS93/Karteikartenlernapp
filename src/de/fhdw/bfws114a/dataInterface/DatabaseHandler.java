package de.fhdw.bfws114a.dataInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// General variables
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "karteikartenDB";

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
	//private static final String KEY_USERSCORES_FILEID= "fk_fileid";
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
	

	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		if(isSDCardWriteable()) Log.d("LOG", "SD Karte ist Beschreibbar");
		
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
		String create_users_table = "CREATE TABLE " + TABLE_USERS + "("
				+ KEY_USERS_USERID + " INTEGER PRIMARY KEY," 
				+ KEY_USERS_USERNAME + " TEXT,"
				+ KEY_USERS_CLASS1_DURATION + " TEXT," 
				+ KEY_USERS_CLASS2_DURATION + " TEXT," 
				+ KEY_USERS_CLASS3_DURATION + " TEXT," 
				+ KEY_USERS_CLASS4_DURATION + " TEXT," 
				+ KEY_USERS_CLASS5_DURATION + " TEXT," 
				+ KEY_USERS_CLASS6_DURATION + " TEXT,"
				+ KEY_USERS_LAST_SEEN + " TEXT"
				+ ")";
		db.execSQL(create_users_table);
	}
	
	private void createUserScoresTable(SQLiteDatabase db){
		String create_userscores_table = "CREATE TABLE " + TABLE_USERSCORES + "("
				+ KEY_USERSCORES_USERID + " INTEGER PRIMARY KEY," 
//				+ KEY_USERSCORES_FILEID + " INTEGER PRIMARY KEY," 
				+ KEY_USERSCORES_CARDID + " INTEGER PRIMARY KEY," 
				+ KEY_USERSCORES_ASSIGNEDCLASS + " INTEGER,"
				+ KEY_USERSCORES_TIMESTAMP + " TEXT"
				+ ")";
		db.execSQL(create_userscores_table);
	}
	
	private void createCardsTable(SQLiteDatabase db){
		String create_cards_table = "CREATE TABLE " + TABLE_CARDS + "("
				+ KEY_CARDS_CARDID+ " INTEGER PRIMARY KEY,"
				+ KEY_CARDS_FILEID+ " INTEGER,"
				+ KEY_CARDS_TYPE + " INTEGER," 
				+ KEY_CARDS_QUESTION + " TEXT,"
				+ KEY_CARDS_ANSWER1 + " TEXT,"
				+ KEY_CARDS_ANSWER2 + " TEXT,"
				+ KEY_CARDS_ANSWER3 + " TEXT,"
				+ KEY_CARDS_ANSWER4 + " TEXT,"
				+ KEY_CARDS_ANSWER5 + " TEXT,"
				+ KEY_CARDS_ANSWER6 + " TEXT"
				+ ")";
		db.execSQL(create_cards_table);
	}
	
	private void createFilesTable(SQLiteDatabase db){
		String create_files_table = "CREATE TABLE " + TABLE_FILES + "("
				+ KEY_FILES_FILEID + " INTEGER PRIMARY KEY," 
				+ KEY_FILES_FILENAME + " TEXT"
				+ ")";
		db.execSQL(create_files_table);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new user
	void addUser(String username) {
		Log.d("DEBUGLOG", "addUser wurde aufgerufen!");
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERS_USERNAME, username); // User Name

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
				KEY_USERS_CLASS1_DURATION, 
				KEY_USERS_CLASS1_DURATION,
				KEY_USERS_CLASS1_DURATION, 
				KEY_USERS_CLASS1_DURATION 
				}, 
				KEY_USERS_USERID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), //Name
				cursor.getString(2), //Class1_Duration
				cursor.getString(3), //Class2_Duration
				cursor.getString(4), //Class3_Duration
				cursor.getString(5), //Class4_Duration
				cursor.getString(6), //Class5_Duration
				cursor.getString(7), //Class6_Duration
				cursor.getString(8)); //Last_Seen
		// return user
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
						cursor.getString(2), //Class1_Duration
						cursor.getString(3), //Class2_Duration
						cursor.getString(4), //Class3_Duration
						cursor.getString(5), //Class4_Duration
						cursor.getString(6), //Class5_Duration
						cursor.getString(7), //Class6_Duration
						cursor.getString(8)); //Last_Seen				
				// Adding user to list
				userList.add(user);
			} while (cursor.moveToNext());
		}

		// return user list
		return userList;
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

}
