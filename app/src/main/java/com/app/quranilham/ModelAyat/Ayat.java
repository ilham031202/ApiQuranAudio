package com.app.quranilham.ModelAyat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Ayat{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("verses")
	private List<VersesItem> verses;

	public Meta getMeta(){
		return meta;
	}

	public List<VersesItem> getVerses(){
		return verses;
	}

	@Override
 	public String toString(){
		return 
			"Ayat{" + 
			"meta = '" + meta + '\'' + 
			",verses = '" + verses + '\'' + 
			"}";
		}
}