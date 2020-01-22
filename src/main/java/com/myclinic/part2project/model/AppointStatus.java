package com.myclinic.part2project.model;

public enum AppointStatus {
	PENDING("Pending"),
	ACCEPTED("Accepted"),
	CLEARED("Cleared"),
	DECLINED("Declined");
	
	String appointStatus;

	private AppointStatus(String appointStatus) {
		this.appointStatus = appointStatus;
	}

	public String getAppointStatus() {
		return appointStatus;
	}

}
