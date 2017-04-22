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

public class MainActivity extends Activity {
	private ListView TextListView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initId();
		
    }
	
	private void initId() {
		TextListView = (ListView) findViewById(R.id.main_text_list);
		initListData();
	}
	
	private void initListData() {
		
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
		}
		return super.onOptionsItemSelected(item);
	}
}
