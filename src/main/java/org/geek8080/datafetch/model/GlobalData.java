package org.geek8080.datafetch.model;

public class GlobalData {
	public long cases;
	public long deaths;
	public long recovered;

	public long getCases() {
		return cases;
	}

	public void setCases(long cases) {
		this.cases = cases;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}

	public long getRecovered() {
		return recovered;
	}

	public void setRecovered(long recovered) {
		this.recovered = recovered;
	}

	@Override
	public String toString() {
		return "GlobalData{" +
				"cases=" + cases +
				", deaths=" + deaths +
				", recovered=" + recovered +
				'}';
	}
}
