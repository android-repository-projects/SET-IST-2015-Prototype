package pt.ist.utl.set.evento;

import com.squareup.picasso.Picasso;

import pt.ist.utl.set.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Evento extends Fragment {

	TextView descricao, localizacao;
	ImageView imagem;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_evento, container, false);
		
		// referencia
		descricao = (TextView) v.findViewById(R.id.descricao);
		localizacao = (TextView) v.findViewById(R.id.localizacao);
		imagem = (ImageView) v.findViewById(R.id.imagem);

		// obter dados
		String descricao_evento = getArguments().getString("descricao_evento");
		String localizacao_evento = getArguments().getString(
				"localizacao_evento");
		String imagem_evento = getArguments().getString("imagem_evento");

		// set dados
		descricao.setText(descricao_evento);
		localizacao.setText(localizacao_evento);
		Picasso.with(getActivity()).load(imagem_evento).into(imagem);
		return v;
	}
}
