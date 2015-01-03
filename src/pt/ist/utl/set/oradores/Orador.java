package pt.ist.utl.set.oradores;

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

public class Orador extends Fragment {

	TextView nome, descricao, experiencia;
	ImageView imagem;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_orador, container, false);
		
		nome = (TextView) v.findViewById(R.id.nome_orador);
		descricao=(TextView) v.findViewById(R.id.descricao_orador);
		experiencia = (TextView) v.findViewById(R.id.experiencia_orador);
		imagem=(ImageView) v.findViewById(R.id.imagem_orador);
		
		String nome_orador = getArguments().getString("nome_orador");
		String descricao_orador = getArguments().getString("descricao_orador");
		String experiencia_orador = getArguments().getString("experiencia_orador");
		String imagem_orador =  getArguments().getString("imagem_orador");
		
		nome.setText(nome_orador);
		descricao.setText(descricao_orador);
		experiencia.setText(experiencia_orador);
		
		Picasso.with(getActivity()).load(imagem_orador).into(imagem);
		
		return v;
	}
}

