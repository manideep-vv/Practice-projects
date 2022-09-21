package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer doctorId;
    String doctorName;
    String specialist;
    Double doctorSalary;

    public Doctor(String doctorName, String specialist) {
        this.doctorName = doctorName;
        this.specialist = specialist;
    }

    public Doctor() {
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public Double getDoctorSalary() {
        return doctorSalary;
    }

    public void setDoctorSalary(Double doctorSalary) {
        this.doctorSalary = doctorSalary;
    }
}
