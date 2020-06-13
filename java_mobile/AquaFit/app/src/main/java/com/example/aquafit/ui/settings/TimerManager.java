package com.example.aquafit.ui.settings;
import android.content.*;
import android.app.*;
import com.example.aquafit.BuildConfig;
public class TimerManager
{
	private Context ctx;
	public static final String TIMER_ELAPSED_CODE = "com.example.aquafit.TIMERMANAGER";
	private PendingIntent pendingIntent;
	public TimerManager(Context ctx){
		this.ctx = ctx;
		Intent intent = new Intent();
		intent.setClass(ctx,SettingsActivity.class);
		intent.setAction(TIMER_ELAPSED_CODE);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.pendingIntent = PendingIntent.getActivity(ctx, 99, intent, 0);
	}
	public void setTimer(double time,double amount){

		if(BuildConfig.DEBUG)
			time=0.001;
		AlarmManager b = ctx.getSystemService(AlarmManager.class);
		b.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,(long)(time*60/*min*/*60/*secs*/*1000/*ms*/),pendingIntent);
	}
}
