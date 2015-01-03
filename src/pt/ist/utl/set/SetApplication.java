package pt.ist.utl.set;

import pt.ist.utl.set.ui.MainMenu;
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class SetApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "t5gRwOF9ZuH49PXNqBjB8x8XkoWENPXpT91TLDqb",
				"Ws2JqgZuVOmCYbpOIMJpRWlCQFSdbRGr3wlR9gkt");

		PushService.setDefaultPushCallback(this, MainMenu.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
}
