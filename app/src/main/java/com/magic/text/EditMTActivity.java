package com.magic.text;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.util.Log;
import android.view.Menu;
import android.graphics.Bitmap;
import android.app.AlertDialog;
import android.widget.ImageView;

public class EditMTActivity extends Activity
{
	private MagicText mt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_text_layout);
		mt=MainActivity.publicmt;
		mt.setView((TextView) findViewById(R.id.edit_text_show_view));
		mt.getView().setText(mt.getText());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.edit_mt_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getTitle().toString()) {
			case "播放":
				mt.show();
				break;
			case "截图":
				mt.getView().setDrawingCacheEnabled(true);
				Bitmap bit=Bitmap.createBitmap(mt.getView().getDrawingCache());
				mt.getView().setDrawingCacheEnabled(false);
				AlertDialog.Builder a=new AlertDialog.Builder(EditMTActivity.this);
				a.setTitle("预览");
				ImageView iv=new ImageView(EditMTActivity.this);
				iv.setImageBitmap(bit);
				a.setView(iv);
				a.setPositiveButton("确定", null);
				a.show();
				AnimatedGifEncoder
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
