import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Appointment } from 'src/app/entity/appointment.entity';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient) { }


  search(date: string): Observable<Appointment[]>{
    console.log(date)
    const params = new HttpParams().append("date", date)
    return this.http.get<Appointment[]>("http://localhost:8080/appointment/search", {params: params})
  }

  postAppointment(appointment: Appointment): Observable<any>{
    return this.http.post("http://localhost:8080/appointment/create", appointment)
  }
}
