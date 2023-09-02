import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Appointment } from 'src/app/entity/appointment.entity';
import { AppointmentService } from 'src/app/service/appointment.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent{
  appointments: Appointment[] = []
  search: Date = new Date
  
  constructor(private aService: AppointmentService, private mService: MessageService){}
  
  protected onSubmit(){
    this.aService.search(this.breakDate(this.search)).subscribe({
      next: (v)=>{
        this.appointments = v
        if(this.appointments.length === 0){
          this.mService.add({
            severity: 'info',
            summary: "No hay resultados"
          })
        }
      },
      error: (error: HttpErrorResponse)=>{
        this.mService.add({
          severity: 'error',
          summary: "Error: "+error.status
        })
      }   
    })
  }

  private breakDate(date: Date): string {
    return `${date.getFullYear()}/${String(date.getMonth()+1).padStart(2, '0')}/${String(date.getDate()).padStart(2, '0')}`
  }
}
