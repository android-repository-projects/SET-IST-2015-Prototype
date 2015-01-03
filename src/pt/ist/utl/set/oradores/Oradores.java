package pt.ist.utl.set.oradores;

import java.util.List;

import pt.ist.utl.set.R;
import pt.ist.utl.set.adapters.OradorAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Oradores extends Fragment {

	List<ParseObject> mOradores;
	GridView gridOradores;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_orador, container, false);

		gridOradores = (GridView) v.findViewById(R.id.gridOrador);

		getAllOradores();

		gridOradores.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getOrador(position);
			}
		});

		return v;
	}

	private void getAllOradores() {
		ParseQuery<ParseObject> oradores = ParseQuery.getQuery("Orador");
		oradores.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> results, ParseException e) {
				if (e == null) {
					mOradores = results;
					OradorAdapter adapter = new OradorAdapter(getActivity(),
							mOradores);
					gridOradores.setAdapter(adapter);
				}
			}
		});
	}

	private void getOrador(int position) {
		ParseQuery<ParseObject> oradores = ParseQuery.getQuery("Orador");
		oradores.setSkip(position);
		oradores.setLimit(1);
		oradores.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> results, ParseException e) {
				if (e == null) {
					ParseObject orador = results.get(0);
					//enviar dados
					Bundle args = new Bundle();
					args.putString("nome_orador", orador.getString("nome_orador"));
					args.putString("descricao_orador", orador.getString("descricao_orador"));
					args.putString("experiencia_orador", orador.getString("experiencia"));
					args.putString("imagem_orador", orador.getParseFile("imagem_orador").getUrl().toString());
					
					//
					Orador oradr = new Orador();
					oradr.setArguments(args);
					
					//transacao
					android.support.v4.app.FragmentManager manager =getFragmentManager();
					android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
					
					transaction.replace(R.id.mainContent, oradr, "evento");
					transaction.addToBackStack("voltar");
					transaction.commit();
				}
			}
		});
	}
}