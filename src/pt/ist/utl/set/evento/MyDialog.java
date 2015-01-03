package pt.ist.utl.set.evento;

import com.squareup.picasso.Picasso;

import pt.ist.utl.set.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDialog extends DialogFragment {

	TextView dialogNome, dialogResumo;
	ImageView dialogImagem;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//inflate
		View v = inflater.inflate(R.layout.dialog_orador, null);
		
		//referencia
		dialogResumo= (TextView) v.findViewById(R.id.dialogResumo);
		dialogNome= (TextView) v.findViewById(R.id.dialogNome);
		dialogImagem = (ImageView) v.findViewById(R.id.dialogImagem);
		
		//ober dados enviados
		String nome = getArguments().getString("nome");
		String resumo = getArguments().getString("resumo_evento");
		String url = getArguments().getString("url");
		
		//set dados
		dialogNome.setText(nome);
		dialogResumo.setText(resumo);
		
		//picasso
		Picasso.with(getActivity()).load(url).into(dialogImagem);
		
		//sem titulo
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		return v;
	}
}
