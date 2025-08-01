package com.aurionpro.model;

public class Dashboard {
	private int srNo;
	private int studentId;
	private String name;
	private String course;
	private double paidFee;
	private double pendingFee;
	private double totalFee;
	private String subjects;
	private String teachers;
	
	public Dashboard() {
		super();
	}
	// Getters and Setters
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public double getPaidFee() {
		return paidFee;
	}
	public void setPaidFee(double paidFee) {
		this.paidFee = paidFee;
	}
	public double getPendingFee() {
		return pendingFee;
	}
	public void setPendingFee(double pendingFee) {
		this.pendingFee = pendingFee;
	}
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	
}
