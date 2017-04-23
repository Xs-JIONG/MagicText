package com.magic.text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.magic.text.R;
import java.util.List;

public class MTAdapter extends ArrayAdapter<MagicText>
{
	private int textid;

	public MTAdapter(Context con, int textViewId, List<MagicText> content) {
		super(con, textViewId, content);
		textid=textViewId;
	}

	@Override
	public View getView(int position, View contentview, ViewGroup parent) {
		MagicText mt=getItem(position);
		View view=LayoutInflater.from(getContext()).inflate(textid, null);
		TextView c=(TextView) view.findViewById(R.id.mt_list_content_view);
		c.setText(mt.getText());
		return view;
	}
}
