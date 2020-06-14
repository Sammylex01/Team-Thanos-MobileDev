package com.example.aquafit.ui.settings;
import android.app.*;
import android.content.*;
import android.os.*;
import com.example.aquafit.R;
import android.support.v4.app.*;
import android.widget.*;
public class TimerService extends IntentService
{
	public static String CHANNEL_ID="com.example.aquafit.TimerService";
	public static final int NOTIFY_ID = 1;

	public TimerService()
	{
		super("TimerService");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// TODO: Implement this method
		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	protected void onHandleIntent(Intent p1)
	{
		// TODO: Implement this method
		showNotification();
			}


	private void createNotificationChannel()
	{
		// Create the NotificationChannel, but only on API 26+ because
		// the NotificationChannel class is new and not in the support library
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
		{
			CharSequence name = "Aquafit Water Reminder";
			String description = "Stay hydrated by allowing Aquafit remind you to drink water";
			int importance = NotificationManager.IMPORTANCE_HIGH;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription(description);
			// Register the channel with the system; you can't change the importance
			// or other notification behaviors after this
			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}
	public void showNotification()
	{
		createNotificationChannel();
		String randomText = RandomQuotes.a();
		Intent snoozeIntent = new Intent(this, Congratulations.class);
		snoozeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent congratulationIntent = 
			PendingIntent.getActivity(this, 0, snoozeIntent, 0);
		Intent openApp = new Intent(this, SettingsActivity.class);
		openApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		PendingIntent openAppPending = PendingIntent.getActivity(this,SettingsActivity.TIMER_ELAPSED,openApp,0);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("Time to Drink Water")
			.setContentText(randomText).
			setContentIntent(openAppPending).
			setTicker(randomText)
		.setStyle(new NotificationCompat.BigTextStyle()
		 .bigText("Time to Drink Water\n"+randomText))
		 .setPriority(NotificationCompat.PRIORITY_DEFAULT)
		 .addAction(R.drawable.ic_stars_white_24dp, "Done. I am now rehydrated.",
		 congratulationIntent);
		NotificationManagerCompat.from(this).notify(CHANNEL_ID, NOTIFY_ID, builder.build());
	}
}
