package de.fhdw.bfws114a.challenge;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private String mUser;
	private String mCategory;
	private Activity mActivity;
	private int mIndexOfCurrentChallenge = 0;
	private int mNumberOfCorrectAnswers = 0;
	private int mNumberOfWrongAnswers = 0;
	private ArrayList<Challenge> mFaelligeChallenges = new ArrayList<Challenge>();
	
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
		ArrayList<Challenge> alleChallenges = DataInterface.loadChallenges(mCategory, mUser);
		java.util.Date now = new java.util.Date();
		long difference;
		for(int i = 0; i< alleChallenges.size(); i++){
			//Berechnen der Differenz zwischen Zeitstempel der aktuellen Challenge und dem Systemdatum
			
			difference = now.getTime() - alleChallenges.get(i).getZeitstempel().getTime();
			
			//Anstelle von 1000 muss hier die Zeit entsprechend der Klassendefinition stehen:
			//Klasse der momentan bearbeiteten Challange und User mitgegeben, Rückgabe in Min, daher *60*100 -> Millisec
			if(difference > (DataInterface.getTimePeriod(alleChallenges.get(i).getAktuelleKlasse(), mUser)*60*1000)){
				mFaelligeChallenges.add(alleChallenges.get(i));
			}
		}		
	}
	
	public String getUser() {
		return mUser;
	}	
	
	public ArrayList<Challenge> getFaelligeChallenges() {
		return mFaelligeChallenges;
	}	

	public int getIndexOfCurrentChallenge() {
		return mIndexOfCurrentChallenge;
	}

	public int getNumberOfCorrectAnswers() {
		return mNumberOfCorrectAnswers;
	}

	public int getNumberOfWrongAnswers() {
		return mNumberOfWrongAnswers;
	}

	private void restoreDataFromBundle(Bundle b) {
		//Problem hinsichtlich des Typs Challenges muss noch gelöst werden
//		mFaelligeChallenges = b.getStringArrayList(Constants.KEY_DUE_CHALLENGES_VALUE);
		
	}
	
	public void saveDataInBundle(Bundle b){		
		//Problem hinsichtlich des Typs Challenges muss noch gelöst werden
//		b.putStringArrayList(Constants.KEY_DUE_CHALLENGES_VALUE, mFaelligeChallenges);		
	}

	public void increaseIndexOfCurrentChallenge() {
		mIndexOfCurrentChallenge++;
		
	}

	public void increaseNumberOfCorrectAnswers() {
		mNumberOfCorrectAnswers++;		
	}

	public void decreaseNumberOfCorrectAnswers() {
		mNumberOfWrongAnswers++;		
	}
	
}
