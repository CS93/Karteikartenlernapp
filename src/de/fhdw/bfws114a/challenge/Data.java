package de.fhdw.bfws114a.challenge;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.dataInterface.Challenge;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private String mUser;
	private String mCategory;
	private Activity mActivity;
	private ArrayList<Challenge> mFaelligeChallenges;
	
	public Data(Bundle b, Activity activity, String user, String category){	
		mActivity = activity;
		mUser = user;
		mCategory = category;
		if (b == null ){
			//Erstes Aufrufen dieser Activity			
			ladeFaelligeChallenges();
		}
		else {
			restoreDataFromBundle(b);
		}
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void ladeFaelligeChallenges(){
		ArrayList<Challenge> mFaelligeChallenges = new ArrayList<Challenge>();
		ArrayList<Challenge> alleChallenges = DataInterface.loadChallenges(mCategory, mUser);
		java.util.Date now = new java.util.Date();
		for(int i = 0; i< alleChallenges.size(); i++){
			//Berechnen der Differenz zwischen Zeitstempel der aktuellen Challenge und dem Systemdatum
			long difference = now.getTime() - alleChallenges.get(i).getZeitstempel().getTime();
			
			//Anstelle von 1000 muss hier die Zeit entsprechend der Klassendefinition stehen:
			//Am besten ein Aufruf wie: dataInterface.ClassDefinition.getTimePeriod(int Klasse)
			if(difference < 1000){
				mFaelligeChallenges.add(alleChallenges.get(i));
			}
		}		
	}
	
	public String getUser() {
		return mUser;
	}


	private void restoreDataFromBundle(Bundle b) {
		//Problem hinsichtlich des Typs Challenges muss noch gelöst werden
//		mFaelligeChallenges = b.getStringArrayList(Constants.KEY_DUE_CHALLENGES_VALUE);
		
	}
	
	public void saveDataInBundle(Bundle b){		
		//Problem hinsichtlich des Typs Challenges muss noch gelöst werden
//		b.putStringArrayList(Constants.KEY_DUE_CHALLENGES_VALUE, mFaelligeChallenges);		
	}
	
}
