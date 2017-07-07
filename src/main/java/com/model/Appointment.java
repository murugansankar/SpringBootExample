package com.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class Appointment {

	private String appointmentDate;
	public Appointment(String appointmentDate, String clinicId) {
		super();
		this.appointmentDate = appointmentDate;
		this.clinicId = clinicId;
	}
	public Appointment() {
		// TODO Auto-generated constructor stub
	}
	private String patientName;
	private String doctorName;
    @Indexed(unique = true)
	private String clinicId;
	private String currentDate;
private String appointmentNumber;
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getClinicId() {
		return clinicId;
	}
	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentDate=" + appointmentDate + ", patientName=" + patientName + ", doctorName="
				+ doctorName + ", clinicId=" + clinicId + ", currentDate=" + currentDate + ", appointmentNumber="
				+ appointmentNumber + "]";
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getAppointmentNumber() {
		return appointmentNumber;
	}
	public void setAppointmentNumber(String appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
	
	
	
}
