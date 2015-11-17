package de.fhdw.bfws114a.userMenu;

import android.app.Activity;

public class Data {
	
	private String mUser; //Eventuell eine Liste verwenden
	private Activity mActivity;
	
	public Data(Activity activity, String user){
	
		mActivity = activity;
		mUser = user;				
	}
	
	public Activity getActivity() {
		return mActivity;
	}
	
	public String getUser() {
		return mUser;
	}	

}