package de.fhdw.bfws114a.Navigation;

import android.app.Activity;
import android.content.Intent;
import de.fhdw.bfws114a.data.Constants;

public class Navigation {
	
	/*
	private static final Class<?> ACTIVITY_MAIN_CLASS = 
			de.fhdw.bfws114a.he.activities.main.Init.class;
			*/
	private static final Class<?> ACTIVITY_LOGIN_CLASS = de.fhdw.bfws114a.login.Init.class, 
									ACTIVITY_USER_MENU_CLASS = de.fhdw.bfws114a.userMenu.Init.class,
									ACTIVITY_PROFILE_MANAGEMENT_CLASS = de.fhdw.bfws114a.profileManagement.Init.class,
									ACTIVITY_CHOOSE_CATEGORY_CLASS = de.fhdw.bfws114a.chooseCategory.Init.class,
									ACTIVITY_CLASS_MANAGEMENT_CLASS = de.fhdw.bfws114a.classManagement.Init.class;	
	/*
	public static void startActivityLogin(Activity callingActivity, int counterValue) {
		startActivity(callingActivity, ACTIVITY_Class_CLASS,
				Constants.KEY_PAR_COUNTER_VALUE, counterValue);
	}
	*/
	
	public static void startActivityLogin(Activity callingActivity){
		startActivity(callingActivity, ACTIVITY_LOGIN_CLASS);
		
	}
	
	public static void startActivityUserMenu(Activity callingActivity, String user){
		startActivityWithParam(callingActivity, ACTIVITY_USER_MENU_CLASS, Constants.KEY_PAR_CURRENT_USER_VALUE, user);		
	}	

	public static void startActivityProfileManagement(Activity callingActivity) {
		startActivity(callingActivity, ACTIVITY_PROFILE_MANAGEMENT_CLASS);		
	}
	
	public static void startActivityChooseCategory(Activity callingActivity, String user){
		startActivityWithParam(callingActivity, ACTIVITY_CHOOSE_CATEGORY_CLASS, Constants.KEY_PAR_CURRENT_USER_VALUE, user);		
	}	

	public static void startActivityClassManagement(Activity callingActivity, String user){
		startActivityWithParam(callingActivity, ACTIVITY_CLASS_MANAGEMENT_CLASS, Constants.KEY_PAR_CURRENT_USER_VALUE, user);		
	}	
	
	
	private static void startActivity(Activity callingActivity,
			Class <?> classOfActivityToStart){
		Intent intent;
		
		intent = new Intent();
		intent.setClass(callingActivity, classOfActivityToStart);
		callingActivity.startActivity(intent);
	} 

	//Diese Methode dient der Übergabe eines Parameters (z.B. des aktuellen Users) für die aufzurufende Activity
	private static void startActivityWithParam(Activity callingActivity,
			Class <?> classOfActivityToStart,
			String key, String value){
		Intent intent;		
		intent = new Intent();
		intent.putExtra(key, value);	
		intent.setClass(callingActivity, classOfActivityToStart);		
		callingActivity.startActivity(intent);
	}
	
	
	
	
	
//	private static void startActivityForResult(Activity callingActivity,
//			Class <?> classOfActivityToStart,
//			String key, int value){
//		Intent intent;
//		
//		intent = new Intent();
//		intent.setClass(callingActivity, classOfActivityToStart);
//		intent.putExtra(key, value);
//		callingActivity.startActivityForResult(intent, Constants.REQUESTCODE_ACTIVITY_EDIT);
//	}
	
//	public static void setActivityEditReturnValues(Activity returningActivity, int counterValue) {
//		setActivityReturnValues(returningActivity, Constants.KEY_RETURN_COUNTER_VALUE, counterValue);
//	}
//	
//	private static void setActivityReturnValues(Activity returningActivity,
//			String key, int value) {
//		Intent intent;
//		
//		intent = new Intent();
//		intent.putExtra(key, value);
//		returningActivity.setResult(Activity.RESULT_OK, intent);
//		
//	}
	
}
