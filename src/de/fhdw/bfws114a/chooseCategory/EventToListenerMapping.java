/**
 * @author Carsten Schlender
 */
 
package de.fhdw.bfws114a.chooseCategory;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.fhdw.bfws114a.lernKartei.R;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		for(int i=0; i < gui.getCategoryButtons().length; i++){
			gui.getCategoryButton(i).setOnClickListener(this);
		}
		gui.getRefreshButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId()){
		case R.id.b_refresh_category:
			mApplicationLogic.onRefreshClicked();
			break;
		default:
			//View v gets casted to button, to hand the ButtonText to mApplicationLogic.onCategoryClicked. This is necessary because the chosen category is required for the next activity
			Button b = (Button) v;
			mApplicationLogic.onCategoryClicked(b.getText().toString());
			break;	
		}
	}
}
