package com.magic.text;

import android.os.Handler;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import android.util.Log;

public class MagicText
{
	private String text;
	private String type;
	private TextView view;
	
	public final static String Type_闪="闪字";
	
	public MagicText() {}
	public MagicText(TextView vie) {this.view=vie;}
	public MagicText(String tex) {this.text=tex;}
	public MagicText(String tex, String typ) {this.text=tex; this.type=typ;}
	public MagicText(String tex, String typ, TextView vie) {this.text=tex; this.type=typ;this.view=vie;}
	
	public void setText(String mText) {
		this.text=mText;
	}
	
	public void setType(String mType) {
		this.type=mType;
	}
	
	public void setView(TextView mView) {
		this.view=mView;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
	
	public TextView getView() {
		return this.view;
	}
	
	public void show() {
		now="start";
		view.setText("");
		showcode.run();
	}
	
	public void stop() {
		handler.removeCallbacks(showcode);
		view.setText(text);
	}
	
	private boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
			|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
			|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
			|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) return true;
		return false;
	}
	
	private boolean isNumber(char n) {
		return Character.isDigit(n);
	}
	
	private char getRandomChinese() {
        String str = "";
        int hightPos;
        int lowPos;
        Random random = new Random();
        hightPos=(176+Math.abs(random.nextInt(39)));
        lowPos=(161+Math.abs(random.nextInt(93)));
        byte[] b=new byte[2];
        b[0]=(Integer.valueOf(hightPos)).byteValue();
        b[1]=(Integer.valueOf(lowPos)).byteValue();
        try {
			str=new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }
	
	private char getRandomNumber() {
		int w=(int) (Math.random() * 10);
		return (char) (w + 48);
	}
	
	private char getRandomWord() {
		String q="qwertyuioplkjhgfdsazxcvbnm";
		return q.charAt((int) (Math.random() * 26));
	}
	
	private String now;
	
	private Handler handler=new Handler();
	
	private Runnable showcode=new Runnable() {
		@Override
		public void run()
		{
			boolean conti=true;
			String result="";
			switch (type) {
				case Type_闪:
					try {
					if (now.equals("start")) {
						if (isChinese(text.charAt(0))) view.setText(getRandomChinese()+""); else if (isNumber(text.charAt(0))) view.setText(getRandomNumber()+""); else view.setText(getRandomWord()+"");
						result="0,1";
					} else {
						String[] ye=now.split(",");
						int p1=Integer.parseInt(ye[0]);
						int p2=Integer.parseInt(ye[1]);
						String temp=view.getText().toString();
						StringBuilder q=new StringBuilder(temp);
						/*char[] t=temp.toCharArray();
						t[p1]=getRandomChar();*/
						if (isChinese(text.charAt(p1))) q.replace(p1, p1+1, getRandomChinese()+""); else if (isNumber(text.charAt(p1))) q.replace(p1, p1+1, getRandomNumber()+""); else q.replace(p1, p1+1, getRandomWord()+"");
						p2++;
						if (p2 == 5) {
							p2=0;
							p1++;
							//t[p1-1]=text.charAt(p1-1);
							q.replace(p1-1, p1, text.charAt(p1-1)+"");
						}
						//view.setText(new String(t));
						view.setText(q.toString());
						if (p1 == text.length()) conti=false;
						result=p1+",";
						result+=p2;
					}
					} catch (Exception e) {Log.e("MT", e.toString());}
					break;
				/*--------------------*/
			}
			now=result;
			if (conti) handler.postDelayed(this, ConstData.DelayTime);
		}
	};
}
