package com.example.health.controller;

import com.example.health.Model.Appointment;
import com.example.health.Model.Patient;
import com.example.health.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/register")
    public String saveBooking(@RequestBody Appointment a){
        boolean isSaved = appointmentService.saveAppointment(a);
        if(isSaved){
            return "Booking successful";
        }
        return "Booking failure";
    }

    @GetMapping("/list")
    public List<Appointment> getAllAppointment(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/view/{appointmentId}")
    public Optional<Appointment> getAppointmentByAppId(@PathVariable("appointmentId") String appId){
        return appointmentService.getAppointmentByAppId(appId);
    }
    @GetMapping("/list/{patientid}")
    public List<Appointment> getAppointmentByPatiId(@PathVariable("patientid") String patientId){
        return appointmentService.getAppointmentByPatientId(patientId);
    }
    @DeleteMapping("/delete/{appointmentId}")
    public void deleteAppointment(@PathVariable("appointmentId") String appId){
        appointmentService.deleteAppointment(appId);
    }
}
