package com.magic.text;

import android.content.Context;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class DataUtils
{
	private static Context ctx=null;
	
	public static void init(Context con) {
		ctx=con;
	}
	
	public static String readData(String name) {
		if (ctx == null) return "";
		FileInputStream input;
		BufferedReader reader=null;
		StringBuilder result=new StringBuilder();
		try {
			input = ctx.openFileInput(name);
			reader = new BufferedReader(new InputStreamReader(input));
			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}
	
	public static void saveData(String name, String data) {
		if (ctx == null) return;
		FileOutputStream output;
		BufferedWriter writer=null;
		try {
			output = ctx.openFileOutput(name, Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(output));
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
