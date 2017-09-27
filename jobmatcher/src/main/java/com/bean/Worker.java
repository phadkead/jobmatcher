package com.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Worker implements Serializable {
	private static final long serialVersionUID = -5045007242539690439L;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<String> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}

	public JobSearchAddress getJobSearchAddress() {
		return jobSearchAddress;
	}

	public void setJobSearchAddress(JobSearchAddress jobSearchAddress) {
		this.jobSearchAddress = jobSearchAddress;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public boolean isHasDriversLicense() {
		return hasDriversLicense;
	}

	public void setHasDriversLicense(boolean hasDriversLicense) {
		this.hasDriversLicense = hasDriversLicense;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	private int rating;
	private boolean isActive;
	private List<String> certificates;
	private JobSearchAddress jobSearchAddress;
	private List<String> skills;
	private String transportation;
	private boolean hasDriversLicense;
	private String phone;
	private String email;
	private int age;
	private String guid;
	private int userId;
	private Name name;

	public static class JobSearchAddress implements Serializable {
		
		public JobSearchAddress() {
		}
		
		private static final long serialVersionUID = -3727810155369626921L;
		private String unit;
		private int maxJobDistance;
		public JobSearchAddress(String unit, int maxJobDistance, double longitude, double latitude) {
			super();
			this.unit = unit;
			this.maxJobDistance = maxJobDistance;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		private double latitude;
		private double longitude;

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public int getMaxJobDistance() {
			return maxJobDistance;
		}

		public void setMaxJobDistance(int maxJobDistance) {
			this.maxJobDistance = maxJobDistance;
		}
		
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
	}

	public static class Name implements Serializable {
		private static final long serialVersionUID = -4903725994816218462L;
		private String last;

		public String getLast() {
			return last;
		}

		public void setLast(String last) {
			this.last = last;
		}

		public String getFirst() {
			return first;
		}

		public void setFirst(String first) {
			this.first = first;
		}

		private String first;

	}

}
