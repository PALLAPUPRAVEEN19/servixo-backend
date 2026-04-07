package com.servixo.dto;

public class DashboardStatsDTO {
    public long getTotalBookings() {
		return totalBookings;
	}
	public void setTotalBookings(long totalBookings) {
		this.totalBookings = totalBookings;
	}
	public long getActiveServices() {
		return activeServices;
	}
	public void setActiveServices(long activeServices) {
		this.activeServices = activeServices;
	}
	public long getCompletedJobs() {
		return completedJobs;
	}
	public void setCompletedJobs(long completedJobs) {
		this.completedJobs = completedJobs;
	}
	public long getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}
	public long getTotalProfessionals() {
		return totalProfessionals;
	}
	public void setTotalProfessionals(long totalProfessionals) {
		this.totalProfessionals = totalProfessionals;
	}
	private long totalBookings;
    private long activeServices;
    private long completedJobs;

    private long totalUsers;
    private long totalProfessionals;

    // getters & setters
}