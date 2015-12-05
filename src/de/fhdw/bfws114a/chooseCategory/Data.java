package de.fhdw.bfws114a.chooseCategory;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.Statistics;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private User mUser;
	private Activity mActivity;
	private DataInterface mDataInterface;
	private ArrayList<String> mCategories;
	private ArrayList<Statistics> mStatistics; //Statistics are required to give the user preview-information regarding to the different categories	
	
	public Data(Bundle b, Activity activity, User user){	
		mActivity = activity;
		mUser = user;
		mDataInterface = new DataInterface(activity);
		if (b == null ){
			//First start of the activity			
			loadCategories();
			loadStatistics();
		}
		else {
			restoreDataFromBundle(b);
		}
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void loadCategories(){
		//Categories are loaded from Datainterface (it has to be 8 categories to fit applyToData() in applicationLogic)
		mCategories = mDataInterface.getFileNames();	
		
	}

	private void loadStatistics(){
		//Create Statistics-objects with mCategories and getChallenges(mCategories, user) and due challenges (find out whether they are due through challenge.getTimeStamp, user.getClass[challenge.getClass]
		mStatistics = mDataInterface.getFileNames(mCategories, mUser);
		
		//Find out the Amount of Due Challenges to put them in statistics object
		for(Statistics currentCategoryStatistic : mStatistics){
			ArrayList<Challenge> allChallenges = mDataInterface.loadChallenges(currentCategoryStatistic.getKartei(), mUser);
			int amountOfDueChallenges = 0;
			for(Challenge currentChallengeInCategory : allChallenges){
				Date now = new Date();
				long difference = now.getTime() - currentChallengeInCategory.getZeitstempel().getTime();

				Log.d("The Challenge", currentChallengeInCategory.getFrage() + "in Category" + currentCategoryStatistic.getKartei());
				Log.d("Time", currentChallengeInCategory.getZeitstempel() + " nicht fällig weil kleiner " + mUser.getClass_durations()[currentChallengeInCategory.getAktuelleKlasse()] + "Minute");				
				if(difference > mUser.getClass_durations()[currentChallengeInCategory.getAktuelleKlasse()]*60*1000){
					//currentChallengeIsDue
					amountOfDueChallenges++;
				}
			}
			currentCategoryStatistic.setFaelligeChallenges(amountOfDueChallenges);
		}
		
	}
	
	public User getUser() {
		return mUser;
	}

	public ArrayList<String> getKarteien() {
		return mCategories;
	}

		
	public ArrayList<Statistics> getStatistik() {
		return mStatistics;
	}

	private void restoreDataFromBundle(Bundle b) {
		//The Serializable is used to put the User-Object in Bundle
		mCategories = (ArrayList<String>) b.getStringArrayList(Constants.KEY_KARTEIEN_VALUE);
		mStatistics = (ArrayList<Statistics>) b.getSerializable(Constants.KEY_STATISTICS_VALUE);
	}
	
	public void saveDataInBundle(Bundle b){
		//The Serializable is used to put the User-Object in Bundle
		b.putStringArrayList(Constants.KEY_KARTEIEN_VALUE, mCategories);
		b.putSerializable(Constants.KEY_STATISTICS_VALUE, mStatistics);
	}
	
}
