package com.magic.text;

public class MagicText
{
	private String text;
	private String type;
	
	public MagicText() {}
	public MagicText(String tex) {this.text=tex;}
	public MagicText(String tex, String typ) {this.text=tex; this.type=typ;}
	
	public void setText(String mText) {
		this.text=mText;
	}
	
	public void setType(String mType) {
		this.type=mType;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
}
