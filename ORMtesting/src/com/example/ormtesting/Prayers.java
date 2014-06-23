package com.example.ormtesting;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "prayers")
public class Prayers {
	@DatabaseField
	private String prayerdate;
	@DatabaseField
	private String url;
	@DatabaseField
	private String headurl;
	@DatabaseField
	private int prayertipe;

	public String getPrayerdate() {
		return prayerdate;
	}

	public void setPrayerdate(String prayerdate) {
		this.prayerdate = prayerdate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public int getPrayertipe() {
		return prayertipe;
	}

	public void setPrayertipe(int prayertipe) {
		this.prayertipe = prayertipe;
	}
}
