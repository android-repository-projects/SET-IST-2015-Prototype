package pt.ist.utl.set.adapters;

import java.util.List;

import pt.ist.utl.set.R;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

public class OradorAdapter extends ArrayAdapter<ParseObject>{
	
	protected Context mContext;
	protected List<ParseObject> mOrador;
	
	public OradorAdapter(Context context, List<ParseObject> oradores) {
		super(context, R.layout.activity_home, oradores);
		mContext = context;
		mOrador = oradores;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.orador_item, null);
			holder = new ViewHolder();
			holder.userImageView = (ImageView)convertView.findViewById(R.id.imagemOrador);
			holder.nome=(TextView) convertView.findViewById(R.id.nomeOrador);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}

		holder.nome.setText(mOrador.get(position).getString("nome_orador"));
		
		//imagem
		Uri imagesUrl = Uri.parse(mOrador.get(position).getParseFile("imagem_orador").getUrl());
		Picasso.with(mContext).load(imagesUrl).into(holder.userImageView);
		
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView userImageView;
		TextView nome;
	}
	
	public void refill(List<ParseObject> eventos) {
		eventos.clear();
		mOrador.addAll(eventos);
		notifyDataSetChanged();
	}
}
