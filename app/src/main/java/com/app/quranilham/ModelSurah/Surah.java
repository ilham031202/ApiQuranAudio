package com.app.quranilham.ModelSurah;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Surah{

	@SerializedName("chapters")
	private List<ChaptersItem> chapters;

	public List<ChaptersItem> getChapters(){
		return chapters;
	}

	@Override
 	public String toString(){
		return 
			"Surah{" + 
			"chapters = '" + chapters + '\'' + 
			"}";
		}
}