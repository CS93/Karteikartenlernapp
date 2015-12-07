package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;
	private DataInterface mDataInterface;
	private int currentQuestionType; //the current question type regulates which gui and processes are necessary 
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mDataInterface = new DataInterface(act);
		mData=data;
		mActivity = act;
		applyDataToGui();
	}

	private void applyDataToGui(){		
//		//Test whether there are no due challenge or there was an issue with loading them
//		if((mData.getFaelligeChallenges().size() == 0) || mData.getFaelligeChallenges() == null){
//			//Show an allert to inform the user, that there are no due challenges
//			new AlertDialog.Builder(mData.getActivity())
//		    .setIcon(android.R.drawable.ic_dialog_alert)
//		    .setTitle("Achtung")
//		    .setMessage("Keine fälligen Karteikarten in dieser Kartei vorhanden!")
//		    .setPositiveButton("OK", new DialogInterface.OnClickListener(){
//		    @Override
//		    public void onClick(DialogInterface dialog, int which) {
//		    	mData.getActivity().finish();   
//		    }
//
//		    })
//		    .show();			
//		} else {
			//The usual case: Challenges has been loaded
			Challenge currentChallenge = mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge());
			currentQuestionType = currentChallenge.getFrageTyp();
			if(currentQuestionType == 1){
				//initiating the gui for question type 1
				mGui1 = new Gui1(mActivity);
				applyDataToGui1(currentChallenge);
				new EventToListenerMapping(mGui1, this);
			}
			
			if(currentQuestionType == 2){
				//initiating the gui for question type 2
				mGui2 = new Gui2(mActivity);
				applyDataToGui2(currentChallenge);
				new EventToListenerMapping(mGui2, this);
			}
			
			if(currentQuestionType == 3){
				//initiating the gui for question type 2
				mGui3 = new Gui3(mActivity);
				applyDataToGui3(currentChallenge);
				new EventToListenerMapping(mGui3, this);
			}			
//		}
	}
	
	//Apply Data to the required GUI (it depends on the question type) ==> Here: Type 1
	private void applyDataToGui1(Challenge currentChallenge) {
		
		mGui1.getQuestion().setText(currentChallenge.getFrage());
		for(int i = 0; i < 6; i++){	
			mGui1.getOption(i).setText(currentChallenge.getAntwort(i));
		}		
	}
	
	//Apply Data to the required GUI (it depends on the question type) ==> Here: Type 2
	private void applyDataToGui2(Challenge currentChallenge) {
		mGui2.getQuestion().setText(currentChallenge.getFrage());
		mGui2.getUserAnswer().setText("");
	}
		
	//Apply Data to the required GUI (it depends on the question type) ==> Here: Type 3
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
	}
		
	
	
	//Start solution with different information (depending on question type)	
	public void onContinueClicked(){
		
		
		if(currentQuestionType == 1){
			//save checkbox-answers in boolean string
			boolean[] checkboxAnswer = new boolean[6];
			for(int i = 0; i < 6; i++){
				checkboxAnswer[i] = mGui1.getOption(i).isChecked();
			}
			
			//Start Solution with boolean Array, current challenge, current index and null (this parameter is only required for question type 2) 
			Navigation.startActivitySolution(mActivity, checkboxAnswer, mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), null);
		}			
		
		if(currentQuestionType == 2){
			//Start Solution with null (only required for type 1), current challenge, current index and user answer
			Navigation.startActivitySolution(mActivity, null, mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), mGui2.getUserAnswer().getText().toString());
			
		}
		
		if(currentQuestionType == 3){
			//Start Solution with null (only required for type 1), current challenge, current index and null (only required for question type 2)
			Navigation.startActivitySolution(mActivity, null, mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), null);
		}		
	}

	public void answerFromSolution(boolean userAnswerCorrect) {

		
		//update Timestamp
		mDataInterface.setCurrentTimestamp(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
		
		//increase class of challenge whether answer was correct (and not class 6) and decrease it whether answer was wrong (and not class 1)
		if(userAnswerCorrect){
			mData.increaseNumberOfCorrectAnswers();
			//increase class of challenge whether answer was correct and not class 6
			if(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()).getAktuelleKlasse() != 6){
				mDataInterface.increaseClass(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
			}
		} else {
			mData.decreaseNumberOfCorrectAnswers();
			//decrease classt whether answer was wrong and not class 1
			if(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()).getAktuelleKlasse() != 1){
				mDataInterface.decreaseClass(mData.getFaelligeChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
			}
		}
			
		Navigation.startActivityStatistics(mData.getActivity(), mData.getIndexOfCurrentChallenge(), mData.getFaelligeChallenges().size(), mData.getNumberOfCorrectAnswers(), mData.getNumberOfWrongAnswers());

		
		//increase Index to load next challenge
		mData.increaseIndexOfCurrentChallenge();
		
		if(mData.getIndexOfCurrentChallenge() < mData.getFaelligeChallenges().size()){
			//loading the next challenge and apply it to gui
			applyDataToGui();
		} else {
			mData.getActivity().finish();
		}
		 
	}

	public void FinishLearnSession() {
		//Show confirm dialog whether user is sure to quit learn session
		new AlertDialog.Builder(mData.getActivity())
	    .setIcon(android.R.drawable.ic_dialog_alert)
	    .setTitle("Lernmodus beenden")
	    .setMessage("Sind Sie sicher, dass Sie den Lernmodus beenden wollen?")
	    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	    	//Der Index muss verringert werden, da die aktuelle Challenge gar nicht bearbeitet wurde
			int indexOfCurrentChallenge = mData.getIndexOfCurrentChallenge()-1;
			//Wechseln in statistics und challenge activity beenden
			Navigation.startActivityStatistics(mData.getActivity(), indexOfCurrentChallenge, mData.getFaelligeChallenges().size(), mData.getNumberOfCorrectAnswers(), mData.getNumberOfWrongAnswers());
			mData.getActivity().finish();   
	    }

	    })
	    .setNegativeButton("No", null)
	    .show();		
	}
}

