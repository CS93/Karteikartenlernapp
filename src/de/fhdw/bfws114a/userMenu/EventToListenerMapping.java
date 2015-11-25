package de.fhdw.bfws114a.userMenu;

import android.view.View;
import android.view.View.OnClickListener;
import de.fhdw.bfws114asc.counter1.R;

public class EventToListenerMapping implements OnClickListener {

	private ApplicationLogic mApplicationLogic;

	public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
		mApplicationLogic = applicationLogic;
		gui.getChooseCategoryButton().setOnClickListener(this);
		gui.getClassManagementButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId()){
		case R.id.b_category_user_menu:

			mApplicationLogic.onChooseCategoryButtonClicked();
			break;
		case R.id.b_class_management_user_menu:

			mApplicationLogic.onClassManagementButtonClicked();
			break;
		}
		
	}
	
}
