package pt.ist.utl.set.ui;

import pt.ist.utl.set.R;
import pt.ist.utl.set.calendar.Calendar;
import pt.ist.utl.set.evento.Home;
import pt.ist.utl.set.localizacao.Localizacao;
import pt.ist.utl.set.oradores.Oradores;
import pt.ist.utl.set.set.Set;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class MainMenu extends FragmentActivity {

	private DrawerLayout drawerLayout;
	private ListView menu;
	private String[] menuItems;
	private android.support.v4.app.FragmentManager manager;
	private android.support.v4.app.FragmentTransaction transaction;

	@SuppressWarnings("deprecation")
	private ActionBarDrawerToggle drawerListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// navegacao inicial
		manager = getSupportFragmentManager();

		// ober referencia
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		menu = (ListView) findViewById(R.id.drawerList);

		actionBarConfig();
		fillMenu();
		clickMenu();
	}

	private void actionBarConfig() {
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_closed) {

			// quando menu abre
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			// quando menu fecha
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		// activar listener
		drawerLayout.setDrawerListener(drawerListener);

		// para podermos clicar home button
		getActionBar().setHomeButtonEnabled(true);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/*
	 * prencher menu lateral
	 */
	private void fillMenu() {
		menuItems = getResources().getStringArray(R.array.menu);
		menu.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuItems));
	}

	/*
	 * click menu lateral
	 */
	private void clickMenu() {
		menu.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				navigateTo(position);
			}

		});
	}

	private void navigateTo(int position) {
		menu.setItemChecked(position, true);
		getActionBar().setTitle(menuItems[position]);
		transaction = manager.beginTransaction();
		switch (position) {
		case 0:
			Home home = new Home();
			transaction.replace(R.id.mainContent, home, "home");
			transaction.commit();
			break;

		case 1:
			Oradores orador = new Oradores();
			transaction.replace(R.id.mainContent, orador, "orador");
			transaction.commit();
			break;

		case 2:
			Calendar calendar = new Calendar();
			transaction.replace(R.id.mainContent, calendar, "calendar");
			transaction.commit();
			break;

		case 3:
			Localizacao localizacao = new Localizacao();
			transaction.replace(R.id.mainContent, localizacao, "calendar");
			transaction.commit();
			break;
		case 4:
			Set set = new Set();
			transaction.replace(R.id.mainContent, set, "set");
			transaction.commit();
			break;

		default:
			break;
		}

		drawerLayout.closeDrawers();
	}

	/*
	 * sincronizar ic_drawer
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}

	/*
	 * abrir menu quando clicamos home button
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * poder clicar 2 vezs e fechar temos fazer set as novas configuracoes
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}

}