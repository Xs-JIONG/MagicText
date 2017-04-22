package com.magic.text;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.MenuItem;
import android.view.Menu;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.content.DialogInterface;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {
	private ListView TextListView;
	private List<MagicText> MagicTextList=new ArrayList<MagicText>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		DataUtils.init(MainActivity.this);
        setContentView(R.layout.main);
		initId();
		
    }
	
	private void initId() {
		TextListView=(ListView) findViewById(R.id.main_text_list);
		loadListData();
	}
	
	private void loadListData() {
		int count=Integer.parseInt(DataUtils.readData(ConstData.MagicTextCountFile));
		for (int i=0;i<count;i++) {
			MagicTextList.add(TextToMT(DataUtils.readData(i+"."+ConstData.MagicTextFileAfter)));
		}
		updateList();
	}
	
	private void updateList() {
		ArrayAdapter adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, MagicTextList);
		TextListView.setAdapter(adapter);
	}
	
	private MagicText TextToMT(String text) {
		MagicText a=new MagicText();
		String[] q=text.split(",");
		a.setText(q[0]);
		a.setType(q[1]);
		return a;
	}
	
	private void saveData() {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getTitle().toString()) {
			case "添加魔字":
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("添加魔字");
				final EditText edit = new EditText(MainActivity.this);
				edit.setHint("文字");
				builder.setView(edit);
				builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int position) {
						
					}
				});
				builder.show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
