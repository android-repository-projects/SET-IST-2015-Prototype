package pt.ist.utl.set;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

public class Receiver extends ParsePushBroadcastReceiver{
	
	@Override
	protected void onPushOpen(Context context, Intent intent) {
		super.onPushOpen(context, intent);
		Log.e("andre", "push onPushOpen");
	}

	@Override
	protected void onPushReceive(Context arg0, Intent arg1) {
		super.onPushReceive(arg0, arg1);
		Log.e("andre", "push onPushReceive");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.e("andre", "push onReceive");
		
	}
	
	@Override
	protected void onPushDismiss(Context context, Intent intent) {
		super.onPushDismiss(context, intent);
		Log.e("andre", "push onPushDismiss");
	}
}
