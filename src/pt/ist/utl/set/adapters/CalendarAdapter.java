package pt.ist.utl.set.adapters;

import pt.ist.utl.set.calendar.Dia1;
import pt.ist.utl.set.calendar.Dia2;
import pt.ist.utl.set.calendar.Dia3;
import pt.ist.utl.set.calendar.Dia4;
import pt.ist.utl.set.calendar.Dia5;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CalendarAdapter extends FragmentPagerAdapter {

	public CalendarAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position == 0) {
			fragment = new Dia1();
		} else if (position == 1) {
			fragment = new Dia2();
		} else if (position == 2) {
			fragment = new Dia3();
		} else if (position == 3) {
			fragment = new Dia4();
		} else if (position == 4) {
			fragment = new Dia5();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Dia 1";
		case 1:
			return "Dia 2";
		case 2:
			return "Dia 3";
		case 3:
			return "Dia 4";
		case 4:
			return "Dia 5";
		default:
			break;
		}
		return null;
	}
}