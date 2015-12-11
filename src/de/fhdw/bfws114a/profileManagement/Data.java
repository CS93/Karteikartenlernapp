/**
 * @author Samira Schorre
 */
package de.fhdw.bfws114a.profileManagement;

import java.util.ArrayList;
import android.app.Activity;
import de.fhdw.bfws114a.dataInterface.DataInterface;
import de.fhdw.bfws114a.data.User;

public class Data {

	private ArrayList<User> mUserList;
	private Activity mActivity;
	private DataInterface mDataInterface;
	
	public Data(Activity activity){	
		mActivity = activity;
		mDataInterface = new DataInterface(activity);
		loadUser();
	}
	
	public DataInterface getDataInterface() {
		return mDataInterface;
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void loadUser(){
		//load all User from db and save it
		mUserList = mDataInterface.loadUsers();	
		
	}
	
	public ArrayList<User> getUser(){
		return mUserList;
	}
	
}

