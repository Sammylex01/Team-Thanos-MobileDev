package com.example.aquafit.ui.settings;
import android.content.*;
import android.app.*;
import com.example.aquafit.BuildConfig;
import android.os.*;
public class TimerManager
{
	private Context ctx;
	public static final String TIMER_ELAPSED_CODE = "com.example.aquafit.TIMERMANAGER";
	private PendingIntent pendingIntent;

	private AlarmManager alarmService;
	public TimerManager(Context ctx)
	{
		this.ctx = ctx;
		alarmService = ctx.getSystemService(AlarmManager.class);
		Intent intent = new Intent();
		intent.setClass(ctx, TimerService.class);
		intent.setAction(TIMER_ELAPSED_CODE);
		this.pendingIntent = PendingIntent.getService(ctx, 99, intent, 0);
	}
	public long setTimer(double time)
	{

		clearTimers();
		long timer = (long)(time * 60/*min*/ * 60/*secs*/ * 1000)+ SystemClock.elapsedRealtime();
		alarmService.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME, timer, pendingIntent);
		SharedPreferences.Editor edit = ctx.getSharedPreferences("settings", ctx.MODE_PRIVATE).edit();
		edit.putLong("nextTimer", timer );
		edit.commit();

		return timer ;
	}
	public long getNextTimer()
	{
		return ctx.getSharedPreferences("settings", ctx.MODE_PRIVATE).getLong("nextTimer", -1);
	}
	public void repeat()
	{
		int interval = ctx.getSharedPreferences("settings", ctx.MODE_PRIVATE).getInt("interval", -1);
		if (interval > -1)
			setTimer(interval);
	}
	public void clearTimers()
	{
		SharedPreferences.Editor edit = ctx.getSharedPreferences("settings", ctx.MODE_PRIVATE).edit();
		edit.putLong("nextTimer", -1);
		edit.commit();
		alarmService.cancel(pendingIntent);
	}
}
