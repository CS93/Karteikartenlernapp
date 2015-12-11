/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.User;

public class Init extends Activity {

	private Data mData;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Load current User and category from Intent to hand it over to Data
		initData(savedInstanceState, (User) getIntent().getSerializableExtra(Constants.KEY_PAR_CURRENT_USER_VALUE), getIntent().getStringExtra(Constants.KEY_PAR_CURRENT_CATEGORY_VALUE));
		//Instantiation of GUI and EventToListenerMapping is delegated to applogic, because applogic know which gui-type (depending on question type) should be shown
		
		initApplicationLogic();
		
	}
	
	//This method is called when the activity solution saves the result (whether the challenge has been answered correct or not) 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//putting the boolean answer from solution activity as a parameter in applicationlogic 
		mApplicationLogic.answerFromSolution(data.getBooleanExtra(Constants.KEY_RETURN_CHALLENGE_ANSWER_BOOL, false));
		
	}
	
	@Override
	public void onBackPressed() {
//		the former onBackPressed method has to be overrided, because we want to show the statistics whether learnSession is finished/quit
		mApplicationLogic.finishLearnSession();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		mData.saveDataInBundle(outState);
		super.onSaveInstanceState(outState);
	}


	private void initData(Bundle savedInstanceState, User user, String category) {
		mData = new Data(savedInstanceState, this, user, category);
		
	}
	
	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mData, this);
	}
}
