package de.fhdw.bfws114a.solution;

import android.app.Activity;
import android.graphics.Color;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.lernKartei.R.color;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;	
	private int currentQuestionType;
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mData=data;
		mActivity = act;
		applyDataToGui();
	}

	private void applyDataToGui(){		
		Challenge currentChallenge = mData.getCurrentChallenge();
		currentQuestionType = currentChallenge.getFrageTyp();
		if(currentQuestionType == 1){
			//initiating the gui for question type 1
			mGui1 = new Gui1(mActivity);
			applyDataToGui1(currentChallenge, mData.getUserAnswerCheckbox());
			new EventToListenerMapping(mGui1, this);
		}
		
		if(currentQuestionType == 2){
			//initiating the gui for question type 2
			mGui2 = new Gui2(mActivity);
			applyDataToGui2(currentChallenge, mData.getUserAnswerText());
			new EventToListenerMapping(mGui2, this);
		}
		
		if(currentQuestionType == 3){
			//initiating the gui for question type 3
			mGui3 = new Gui3(mActivity);
			applyDataToGui3(currentChallenge);
			new EventToListenerMapping(mGui3, this);
		}
	}
	
	//Whether its question type onn, this method is called
	private void applyDataToGui1(Challenge currentChallenge, boolean[] userAnswer) {
		
		mGui1.getQuestion().setText(currentChallenge.getFrage());
		for(int i = 0; i < 6; i++){
			mGui1.getOption(i).setText(currentChallenge.getAntwort(i));
			mGui1.getOption(i).setChecked(userAnswer[i]);			
			if(currentChallenge.getKorrekteAnwortenFuerCheckbox()[i] == true){				
				//this answer is correct for the question and marked green
				mGui1.getOption(i).setTextColor(Color.GREEN);
			} else {
				mGui1.getOption(i).setTextColor(Color.RED);				
			}
		}		
	}
	
	//Whether its question type two, this method is called
	private void applyDataToGui2(Challenge currentChallenge, String userAnswer) {
		mGui2.getQuestion().setText(currentChallenge.getFrage());
		mGui2.getUserAnswer().setText(userAnswer);
		mGui2.getCorrectAnswer().setText(currentChallenge.getAntwort(0));
		
		if(mGui2.getUserAnswer().getText().toString().toLowerCase().equals(mData.getCurrentChallenge().getAntwort(0).toLowerCase())){
			//user answer is correct for the question and marked green
			mGui2.getUserAnswerDescription().setTextColor(Color.GREEN);
			mGui2.getUserAnswer().setTextColor(Color.GREEN);
		} else {
			mGui2.getUserAnswerDescription().setTextColor(Color.RED);
			mGui2.getUserAnswer().setTextColor(Color.RED);
		}
		
	}

	//Whether its question type three, this method is called
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestion().setText(currentChallenge.getFrage());	
		mGui3.getAnswer().setText(currentChallenge.getAntwort(0));
	}
		
	
	
	//check whether user answer was correct and show statistics	
	public void onContinueClicked(String buttonText){
		boolean userAnswerCorrect = false;
		if(currentQuestionType == 1){
			
			userAnswerCorrect = true;
			//Check whether every checkbox is ticked/unticked correct
			for(int i = 0; i<6;i++){
				 
				if(mData.getUserAnswerCheckbox()[i] != mData.getCurrentChallenge().getKorrekteAnwortenFuerCheckbox()[i]){
					//Checkbox was ticked wrong
					userAnswerCorrect = false;
				}
			}
		}			
		
		if(currentQuestionType == 2){
			userAnswerCorrect = false;
			//check if user answer text is equals challenge answer			
			if(mGui2.getUserAnswer().getText().toString().toLowerCase().equals(mData.getCurrentChallenge().getAntwort(0).toLowerCase())){
				userAnswerCorrect = true;
			}	
			
		}
		
		if(currentQuestionType == 3){			
			if(buttonText.equals("Ja")){
				//user says he knew the answer
				userAnswerCorrect = true;
			} else {
				userAnswerCorrect = false;
			}
		}

		//save whether user answer was correct, start activity statistics and finish this activity
		Navigation.setActivitySolutionReturnValues(mData.getActivity(), userAnswerCorrect);
		mData.getActivity().finish();
		
	}
}

