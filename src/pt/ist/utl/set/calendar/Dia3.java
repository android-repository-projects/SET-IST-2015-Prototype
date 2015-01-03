package pt.ist.utl.set.calendar;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import pt.ist.utl.set.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class Dia3 extends Fragment {
	
	private int id = 999, dia=3;
	private View root;
	private ImageView time;
	private LayoutParams paramsFirstHora, paramsFirstEvento, paramsHora,
			paramsEvento;
	private TextView last;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.day3, null);

		// imagem timeLine
		time = (ImageView) root.findViewById(R.id.timeline);

		return root;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// align first right
		paramsFirstHora = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		paramsFirstHora.addRule(RelativeLayout.ALIGN_RIGHT, time.getId());
		paramsFirstHora.setMargins(10, 10, 50, 10);

		// align first left
		paramsFirstEvento = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		paramsFirstEvento.addRule(RelativeLayout.RIGHT_OF, time.getId());
		paramsFirstEvento.setMargins(10, 10, 10, 10);

		getEvents();
	}

	private void getEvents() {
		ParseQuery<ParseObject> eventos = ParseQuery.getQuery("Evento");
		eventos.whereEqualTo("dia_evento", dia);
		eventos.addAscendingOrder("hora_inicio");
		eventos.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> result, ParseException e) {
				if (e == null) {
					int i = 0;
					for (ParseObject parseObject : result) {
						if (i == 0) {

							TextView hora = new TextView(getActivity());
							hora.setText(parseObject.getString("hora_inicio")
									.toString());
							hora.setId(id++);
							hora.setLayoutParams(paramsFirstHora);

							TextView evento = new TextView(getActivity());
							evento.setText(parseObject.getString(
									"descricao_evento").toString());
							evento.setId(id++);
							evento.setLayoutParams(paramsFirstEvento);
							// para meter debaixo
							last = evento;
							((ViewGroup) root).addView(hora);
							((ViewGroup) root).addView(evento);
						} else {
							// align right
							paramsHora = new LayoutParams(LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
							paramsHora.addRule(RelativeLayout.ALIGN_RIGHT, time.getId());
							paramsHora.setMargins(10, 10, 50, 10);
							paramsHora.addRule(RelativeLayout.BELOW, last.getId());

							// align left
							paramsEvento = new LayoutParams(LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
							paramsEvento.addRule(RelativeLayout.RIGHT_OF, time.getId());
							paramsEvento.setMargins(10, 10, 10, 10);
							paramsEvento.addRule(RelativeLayout.BELOW, last.getId());
							
							Log.d("andre", "entrie1");
							TextView hora = new TextView(getActivity());
							hora.setText(parseObject.getString("hora_inicio")
									.toString());
							hora.setId(id++);
							hora.setLayoutParams(paramsHora);

							TextView evento = new TextView(getActivity());
							evento.setText(parseObject.getString(
									"descricao_evento").toString());
							evento.setId(id++);
							evento.setLayoutParams(paramsEvento);
							// para meter debaixo
							last = evento;
							((ViewGroup) root).addView(hora);
							((ViewGroup) root).addView(evento);
						}
						i++;
					}
				}
			}
		});

	}
}
