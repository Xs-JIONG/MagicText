package com.magic.text;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import com.magic.text.MagicText;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends Activity
{
	public static MagicText publicmt;
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
		TextListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
				Intent intent=new Intent(MainActivity.this, EditMTActivity.class);
				publicmt=MagicTextList.get(position);
				startActivity(intent);
			}
		});
		loadListData();
	}
	
	private void loadListData() {
		if (!new File("/data/data/"+getPackageName()+"/files/"+ConstData.MagicTextCountFile).exists()) DataUtils.saveData(ConstData.MagicTextCountFile, "0");
		int count=Integer.parseInt(DataUtils.readData(ConstData.MagicTextCountFile));
		for (int i=0;i<count;i++) {
			MagicTextList.add(TextToMT(DataUtils.readData(i+"."+ConstData.MagicTextFileAfter)));
		}
		updateList();
	}
	
	private void updateList() {
		MTAdapter adapter=new MTAdapter(MainActivity.this, R.layout.mt_list_layout, MagicTextList);
		TextListView.setAdapter(adapter);
	}
	
	private MagicText TextToMT(String text) {
		MagicText a=new MagicText();
		String[] q=text.split(",");
		a.setText(q[0]);
		a.setType(q[1]);
		return a;
	}
	
	private String MTToText(MagicText mt) {
		String a="";
		a+=mt.getText();
		a+=",";
		a+=mt.getType();
		return a;
	}
	
	private void saveData() {
		File[] tar=new File("/data/data/"+getPackageName()+"/files/").listFiles();
		for (File one:tar) {
			String[] temp=one.getName().split(".");
			if (temp.length != 0) if (temp[temp.length].equals("mt")) one.delete();
		}
		DataUtils.saveData(ConstData.MagicTextCountFile, MagicTextList.size()+"");
		for (int i=0;i<MagicTextList.size();i++) DataUtils.saveData(i+"."+ConstData.MagicTextFileAfter, MTToText(MagicTextList.get(i)));
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
						MagicText a=new MagicText(edit.getText().toString(), MagicText.Type_闪);
						MagicTextList.add(a);
						saveData();
						updateList();
					}
				});
				builder.show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
