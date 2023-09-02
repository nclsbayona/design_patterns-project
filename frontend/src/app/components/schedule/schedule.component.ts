import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Appointment } from 'src/app/entity/appointment.entity';
import { AppointmentService } from 'src/app/service/appointment.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent {

  appointment = new Appointment
  date = new Date


  constructor(private messageService: MessageService, private router: Router, private apService: AppointmentService){}


  protected onSubmit(){
    this.breakDate()    
    this.apService.postAppointment(this.appointment).subscribe({
      next: ()=>{
        this.messageService.add({
          severity: 'success',
          summary: "Hecho"
        })
        setTimeout(()=>{
          this.router.navigate(["/"])
        },1000)
      },
      error: (error: HttpErrorResponse)=>{
        this.messageService.add(
          {severity: "error", summary: "Error" +  error.status}
        )
      }
    })
  }

  protected today(){
    return new Date()
  }


  private breakDate(){
    this.appointment.date =`${this.date.getFullYear()}/${String(this.date.getMonth()+1).padStart(2, '0')}/${String(this.date.getDate()).padStart(2, '0')}`
    this.appointment.time =`${String(this.date.getHours()).padStart(2, '0')}:${String(this.date.getMinutes()).padStart(2, '0')}`
  }
}
