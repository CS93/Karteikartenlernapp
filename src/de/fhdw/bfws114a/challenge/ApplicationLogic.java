/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import de.fhdw.bfws114a.Navigation.Navigation;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.dataInterface.DataInterface;
import de.fhdw.bfws114a.lernKartei.R;

public class ApplicationLogic {
	private Data mData;
	private Gui1 mGui1;
	private Gui2 mGui2;
	private Gui3 mGui3;
	private DataInterface mDataInterface;
	private int mCurrentQuestionType; //the current question type regulates which gui and processes are necessary 
	private Activity mActivity;
	
	ApplicationLogic(Data data, Activity act){
		mDataInterface = new DataInterface(act);
		mData=data;
		mActivity = act;
		applyDataToGui();
	}

	private void applyDataToGui(){
			//The usual case: Challenges has been loaded
			Challenge currentChallenge = mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge());
			mCurrentQuestionType = currentChallenge.getQuestionType();
			if(mCurrentQuestionType == 1){
				//initiating the gui for question type 1
				mGui1 = new Gui1(mActivity);
				applyDataToGui1(currentChallenge);
				new EventToListenerMapping(mGui1, this);
			}
			
			if(mCurrentQuestionType == 2){
				//initiating the gui for question type 2
				mGui2 = new Gui2(mActivity);
				applyDataToGui2(currentChallenge);
				new EventToListenerMapping(mGui2, this);
			}
			
			if(mCurrentQuestionType == 3){
				//initiating the gui for question type 2
				mGui3 = new Gui3(mActivity);
				applyDataToGui3(currentChallenge);
				new EventToListenerMapping(mGui3, this);
			}			
//		}
	}
	
	//Apply Data to the required GUI (it depends on the question type) ==> Here: Type 1
	private void applyDataToGui1(Challenge currentChallenge) {
		
		mGui1.getQuestionView().setText(currentChallenge.getQuestion());
		for(int i = 0; i < 6; i++){	
			mGui1.getOption(i).setText(currentChallenge.getAnswer(i));
		}		
	}
	
	//Apply Data to the required GUI (it depends on the question type) ==> Here: Type 2
	private void applyDataToGui2(Challenge currentChallenge) {
		mGui2.getQuestionView().setText(currentChallenge.getQuestion());
		mGui2.getUserAnswerEditText().setText("");
	}
		
	//Apply Data to the required GUI (it depends on the question type) ==> Here: Type 3
	private void applyDataToGui3(Challenge currentChallenge) {
		mGui3.getQuestionView().setText(currentChallenge.getQuestion());	
	}
		
	
	
	//Start solution with different information (depending on question type)	
	public void onContinueClicked(){
		
		
		if(mCurrentQuestionType == 1){
			//save checkbox-answers in boolean string
			boolean[] checkboxAnswer = new boolean[6];
			for(int i = 0; i < 6; i++){
				checkboxAnswer[i] = mGui1.getOption(i).isChecked();
			}
			
			//Start Solution with boolean Array, current challenge, current index and null (this parameter is only required for question type 2) 
			Navigation.startActivitySolution(mActivity, checkboxAnswer, mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()), null);
		}			
		
		if(mCurrentQuestionType == 2){
			//Start Solution with null (only required for type 1), current challenge, current index and user answer
			Navigation.startActivitySolution(mActivity, null, mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()), mGui2.getUserAnswerEditText().getText().toString());
			
		}
		
		if(mCurrentQuestionType == 3){
			//Start Solution with null (only required for type 1), current challenge, current index and null (only required for question type 2)
			Navigation.startActivitySolution(mActivity, null, mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()), null);
		}		
	}

	public void answerFromSolution(boolean userAnswerCorrect) {

		
		//update Timestamp
		mDataInterface.setCurrentTimestamp(mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
		
		//increase class of challenge whether answer was correct (and not class 6) and decrease it whether answer was wrong (and not class 1)
		if(userAnswerCorrect){
			mData.increaseNumberOfCorrectAnswers();
			//increase class of challenge whether answer was correct and not class 6
			if(mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()).getCurrentClass() != 6){
				mDataInterface.increaseClass(mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
			}
		} else {
			mData.decreaseNumberOfCorrectAnswers();
			//decrease classt whether answer was wrong and not class 1
			if(mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()).getCurrentClass() != 1){
				mDataInterface.decreaseClass(mData.getDueChallenges().get(mData.getIndexOfCurrentChallenge()), mData.getUser());
			}
		}
			
		Navigation.startActivityStatistics(mData.getActivity(), mData.getIndexOfCurrentChallenge(), mData.getDueChallenges().size(), mData.getNumberOfCorrectAnswers(), mData.getNumberOfWrongAnswers());

		
		//increase Index to load next challenge
		mData.increaseIndexOfCurrentChallenge();
		
		if(mData.getIndexOfCurrentChallenge() < mData.getDueChallenges().size()){
			//loading the next challenge and apply it to gui
			applyDataToGui();
		} else {
			mData.getActivity().finish();
		}
		 
	}

	public void finishLearnSession() {
		//Show confirm dialog whether user is sure to quit learn session
		new AlertDialog.Builder(mData.getActivity())
	    .setIcon(android.R.drawable.ic_dialog_alert)
	    .setTitle(R.string.quit_learn_session)
	    .setMessage(R.string.sure_to_quit_learn_session)
	    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	    	//The index has to be decreased because the current challenge hasn't been really played
			int indexOfCurrentChallenge = mData.getIndexOfCurrentChallenge()-1;
			//Change to statistics and finish this activity
			Navigation.startActivityStatistics(mData.getActivity(), indexOfCurrentChallenge, mData.getDueChallenges().size(), mData.getNumberOfCorrectAnswers(), mData.getNumberOfWrongAnswers());
			mData.getActivity().finish();   
	    }

	    })
	    .setNegativeButton("No", null)
	    .show();		
	}
}

