package pt.ist.utl.set.calendar;
import pt.ist.utl.set.R;
import pt.ist.utl.set.adapters.CalendarAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class Calendar extends Fragment {
 
    CalendarAdapter calendarAdapter;
    ViewPager mViewPager;
 
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        
        calendarAdapter = new CalendarAdapter(getChildFragmentManager());
        
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(calendarAdapter);
        
        return v;
    }     
}