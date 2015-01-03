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

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

public class EventosAdapter extends ArrayAdapter<ParseObject>{
	
	protected Context mContext;
	protected List<ParseObject> mEventos;
	
	public EventosAdapter(Context context, List<ParseObject> eventos) {
		super(context, R.layout.activity_home, eventos);
		mContext = context;
		mEventos = eventos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.evento_item, null);
			holder = new ViewHolder();
			holder.userImageView = (ImageView)convertView.findViewById(R.id.imageItem);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		Uri imagesUrl = Uri.parse(mEventos.get(position).getParseFile("imagem_evento").getUrl());
		Picasso.with(mContext).load(imagesUrl).into(holder.userImageView);
		
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView userImageView;
	}
	
	public void refill(List<ParseObject> eventos) {
		eventos.clear();
		mEventos.addAll(eventos);
		notifyDataSetChanged();
	}
}
