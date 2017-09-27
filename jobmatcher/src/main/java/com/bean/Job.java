package com.bean;

import java.util.List;

public class Job {

	private boolean driverLicenseRequired;
	private List<String> requiredCertificates;
	private Location location;
	private String billRate;
	private int workersRequired;
	// private Date startDate;

	private String about;
	private String jobTitle;
	private String company;
	private String guid;
	private int jobId;

	public boolean isDriverLicenseRequired() {
		return driverLicenseRequired;
	}

	public void setDriverLicenseRequired(boolean driverLicenseRequired) {
		this.driverLicenseRequired = driverLicenseRequired;
	}

	public List<String> getRequiredCertificates() {
		return requiredCertificates;
	}

	public void setRequiredCertificates(List<String> requiredCertificates) {
		this.requiredCertificates = requiredCertificates;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getBillRate() {
		return billRate;
	}

	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}

	public int getWorkersRequired() {
		return workersRequired;
	}

	public void setWorkersRequired(int workersRequired) {
		this.workersRequired = workersRequired;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public static class Location {
		private double longitude;
		private double latitude;

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		@Override
		public String toString() {
			return "Location [longitude=" + longitude + ", latitude=" + latitude + "]";
		}
	}

	@Override
	public String toString() {
		return "Job [driverLicenseRequired=" + driverLicenseRequired + ", requiredCertificates=" + requiredCertificates
				+ ", location=" + location + ", billRate=" + billRate + ", workersRequired=" + workersRequired
				+ ", about=" + about + ", jobTitle=" + jobTitle + ", company=" + company + ", guid=" + guid + ", jobId="
				+ jobId + "]";
	}
}
