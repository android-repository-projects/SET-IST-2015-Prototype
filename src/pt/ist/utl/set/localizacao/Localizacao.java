package pt.ist.utl.set.localizacao;

import pt.ist.utl.set.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Localizacao extends Fragment {

	Button gps;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragmento_localizacao, container,
				false);

		// gps
		gps = (Button) v.findViewById(R.id.botao_gps);
		gps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// definimos intent com a acao standart de vermos algo
				// ACTION_VIEW
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

				// qeremos lançar mapa com algumas coodenadas
				intent.setData(Uri.parse("google.navigation:q=38.737341, -9.303124"));

				// nos emuladores nao temos app para abrir
				// temos criar um chooser
				Intent chooser = intent.createChooser(intent, "Set");
				startActivity(chooser);
			}
		});

		return v;
	}
}
