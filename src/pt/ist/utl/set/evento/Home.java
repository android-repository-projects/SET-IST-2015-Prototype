package pt.ist.utl.set.evento;

import java.util.List;

import pt.ist.utl.set.R;
import pt.ist.utl.set.adapters.EventosAdapter;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Home extends Fragment {

	protected String mainDay = "wtF6mvWXMI";
	protected List<ParseObject> mEventosDia;
	protected GridView gridEventos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_home, container, false);

		// lista todos oradores
		//listEventos = (ListView) v.findViewById(R.id.gridOradores);
		gridEventos = (GridView) v.findViewById(R.id.gridEventos);

		// click evento
		gridEventos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getEventosDia(position);
				
			}
		});
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getEventosDia();

	}
	
	/*
	 * obtem eventos do dia actual
	 */
	private void getEventosDia() {
		ParseQuery<ParseObject> getDay = ParseQuery.getQuery("Dia");
		getDay.setLimit(1);
		getDay.getInBackground(mainDay, new GetCallback<ParseObject>() {
			public void done(ParseObject day, ParseException e) {
				if (e == null) {
					//obter dia actual
					int dia = day.getInt("dia_actual");
					getEventos(dia);
				} else {
					Log.e("andre", "erro obter dia");
				}
			}
		});
	}
	
	/*
	 * obtem eventos determidado dia
	 */
	private void getEventos(int day) {
		ParseQuery<ParseObject> eventos = ParseQuery.getQuery("Evento");
		eventos.whereEqualTo("dia_evento", day);
		eventos.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> results, ParseException e) {
				if (e == null) {
					//set dados
					mEventosDia = results;
					//onde guardar
					String[] descricao_evento = new String[mEventosDia.size()];
					String[] localizacao_evento = new String[mEventosDia.size()];
					Uri[] imagem_evento = new Uri[mEventosDia.size()];
					int i = 0;
					for (String string : descricao_evento) {
						descricao_evento[i] = mEventosDia.get(i).get("descricao_evento").toString();
						localizacao_evento[i] = mEventosDia.get(i).get("localizacao_evento").toString();
						imagem_evento[i] = Uri.parse(mEventosDia.get(i).getParseFile("imagem_evento").getUrl());
						i++;
					}
					//adapter
					EventosAdapter adapter = new EventosAdapter(getActivity(), mEventosDia);
					gridEventos.setAdapter(adapter);
				} else {
					Log.e("andre", "erro obter oradores");
				}
			}
		});
	}

	private void getEventosDia(final int position) {
		ParseQuery<ParseObject> getDay = ParseQuery.getQuery("Dia");
		getDay.setLimit(1);
		getDay.getInBackground(mainDay, new GetCallback<ParseObject>() {
			public void done(ParseObject day, ParseException e) {
				if (e == null) {
					int dia = day.getInt("dia_actual");
//					Log.d("andre", "dia "+dia+" posicao "+position);
					getEvento(position, dia);
					
				} else {
					Log.e("andre", "erro obter dia");
				}
			}
		});
	}

	private void getEvento(int position, int day) {
		ParseQuery<ParseObject> evento = ParseQuery.getQuery("Evento");
		evento.whereEqualTo("dia_evento", day);
		evento.setSkip(position);
		evento.setLimit(1);
		evento.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> results, ParseException e) {
				if (e == null) {
					for (ParseObject parseObject : results) {
//						MyDialog dialog = new MyDialog();
						Bundle args = new Bundle();
						
//						//mandar daods dialog
						args.putString("descricao_evento", parseObject.getString("descricao_evento"));
						args.putString("localizacao_evento", parseObject.getString("localizacao_evento"));
						args.putString("imagem_evento", parseObject.getParseFile("imagem_evento").getUrl().toString());
//						
//						dialog.setArguments(args);
//						dialog.show(getFragmentManager(), "dialog");
//						Log.d("andre", "descricao "+parseObject.getString("descricao_evento"));
//						Log.d("andre", "descricao "+parseObject.getString("localizacao_evento"));
						android.support.v4.app.FragmentManager manager =getFragmentManager();
						android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
						Evento evento = new Evento();
						evento.setArguments(args);
						transaction.replace(R.id.mainContent, evento, "evento");
						transaction.addToBackStack("voltar");
						transaction.commit();
					}
				} else {
					Log.e("andre", "erro obter orador x");
				}
			}
		});
	}

}
