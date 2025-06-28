package com.example.health.Model;


import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private String patient_Id;
    private String patient_name;
    private String patient_email;
    private String patient_mobile;
    @Column(name = "registeredDate")
    private Date registeredDate;

    public Patient(String patient_name, String patient_email, String patient_mobile, Date registeredDate) {
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_mobile = patient_mobile;
        this.registeredDate = registeredDate;
    }
    public Patient(){
        super();
    }
}

