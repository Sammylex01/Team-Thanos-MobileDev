package com.example.aquafit.ui.settings;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import com.example.aquafit.R;
import android.content.*;
import android.widget.*;

public class ActivityFragment extends Fragment
{

	private View view;

	private TextView counter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		//return super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.activity_fragment, container, false);
		setupView();
		return view;
	}
	public void setupView()
	{
		long nextTimer = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE).getLong("nextTimer", -1);
        view.findViewById(R.id.home_FragmentSetTimers).setVisibility(nextTimer > -1 ?View.GONE: View.VISIBLE);
		View done = view.findViewById(R.id.done);
		done.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent intent = new Intent(getContext(), Congratulations.class);
					startActivity(intent);
					p1.setEnabled(false);
				}
			});
		done.setEnabled(nextTimer > -1 && nextTimer < SystemClock.elapsedRealtime());
		counter = view.findViewById(R.id.home_activityChronometer);
		if (nextTimer > SystemClock.elapsedRealtime())
		{ 
			long a = nextTimer - SystemClock.elapsedRealtime();
			long hrs = a / (1000 * 60 * 60);
			long mins = (a - hrs) / (1000 * 60);
			counter.setText(String.format("%d:%d", (int)hrs, (int)mins));
		}
		else{
			counter.setText("__:__");
		}
		view.findViewById(R.id.home_FragmentSetTimers).setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					((SettingsActivity)getActivity()).viewPager.setCurrentItem(1,true);
				}
			});
		((TextView)view.findViewById(R.id.infoTextView)).setText(RandomQuotes.a());
		view.invalidate();
	}
}
