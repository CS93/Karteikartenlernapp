package de.fhdw.bfws114a.chooseCategory;

import android.app.Activity;
import android.widget.TextView;
import de.fhdw.bfws114asc.counter1.R;

public class Gui {
	
	private TextView[] categories = new TextView[5];


	public Gui(Activity act) {
		act.setContentView(R.layout.activity_category_choose);
		categories[0] = (TextView) act.findViewById(R.id.b_cardfile1_category);
		categories[1] = (TextView) act.findViewById(R.id.b_cardfile2_category);
		categories[2] = (TextView) act.findViewById(R.id.b_cardfile3_category);
		categories[3] = (TextView) act.findViewById(R.id.b_cardfile4_category);
		categories[4] = (TextView) act.findViewById(R.id.b_cardfile5_category);
	}

	public TextView[] getCategories() {
		return categories;
	}

	public TextView getCategory(int index) {
		return categories[index];
	}
	
	
	public void setCategories(TextView[] categories) {
		this.categories = categories;
	}
	
}
