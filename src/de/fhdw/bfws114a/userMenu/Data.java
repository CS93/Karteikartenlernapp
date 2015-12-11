/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.userMenu;

import android.app.Activity;
import de.fhdw.bfws114a.data.User;

public class Data {
	
	private User mUser;
	private Activity mActivity;
	
	public Data(Activity activity, User user){
	
		mActivity = activity;
		mUser = user;				
	}
	
	public Activity getActivity() {
		return mActivity;
	}
	
	public User getUser() {
		return mUser;
	}

	public void setUser(User updatedUser) {
		mUser = updatedUser;
	}	

}