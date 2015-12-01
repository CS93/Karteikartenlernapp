package de.fhdw.bfws114a.dataInterface;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "karteikartenDB";

	// Contacts table name
	private static final String TABLE_USER = "user";

	// Contacts Table Columns names
	private static final String KEY_USERID = "id";
	private static final String KEY_USERNAME = "name";
	private static final String KEY_USER_CLASS1_DURATION = "class1_duration";
	private static final String KEY_USER_CLASS2_DURATION = "class2_duration";
	private static final String KEY_USER_CLASS3_DURATION = "class3_duration";
	private static final String KEY_USER_CLASS4_DURATION = "class4_duration";
	private static final String KEY_USER_CLASS5_DURATION = "class5_duration";
	private static final String KEY_USER_CLASS6_DURATION = "class6_duration";
	private static final String KEY_USER_LAST_SEEN = "last_seen";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("LOG", "onCreate wurde Aufgerufen!! #########");
		createUserTable(db);
		//createCardTable(db);
		//createUserScoresTable(db);
	}
	
	private void createUserTable(SQLiteDatabase db){
		String create_user_table = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_USERID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
				+ KEY_USER_CLASS1_DURATION + " TEXT," 
				+ KEY_USER_CLASS2_DURATION + " TEXT," 
				+ KEY_USER_CLASS3_DURATION + " TEXT," 
				+ KEY_USER_CLASS4_DURATION + " TEXT," 
				+ KEY_USER_CLASS5_DURATION + " TEXT," 
				+ KEY_USER_CLASS6_DURATION + " TEXT,"
				+ KEY_USER_LAST_SEEN + " TEXT"
				+ ")";
		db.execSQL(create_user_table);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

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
		values.put(KEY_USERNAME, username); // User Name

		// Inserting Row
		db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection
	}

	// Getting single User
	User getUser(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_USER, new String[] { 
				KEY_USERID,
				KEY_USERNAME, 
				KEY_USER_CLASS1_DURATION, 
				KEY_USER_CLASS1_DURATION, 
				KEY_USER_CLASS1_DURATION,
				KEY_USER_CLASS1_DURATION, 
				KEY_USER_CLASS1_DURATION 
				}, 
				KEY_USERID + "=?",
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
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

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
		db.delete(TABLE_USER, KEY_USERID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}
	
	// Deleting user by name - ATTENTION: ALL USERS WITH THIS NAME WILL BE DELETED

	public void deleteUser(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, KEY_USERNAME + " = ?",
				new String[] { name });
		db.close();
	}


	// Getting user Count
	public int getUserCount() {
		String countQuery = "SELECT * FROM " + TABLE_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
