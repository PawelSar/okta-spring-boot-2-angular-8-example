import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DateSelectArg } from '@fullcalendar/angular';


@Injectable({providedIn: 'root'})
export class GoogleCalendarService {
  public API = '//localhost:8080';
  public CAR_API = this.API + '/cars';
  public CALENDAR_API_ADD_EVENT = this.API + '/calendar/addEvent';
  public CALENDAR_API_GET_EVENTS = this.API + '/calendar/getEvents';
  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.API + '/cool-cars');
  }

  get(id: string) {
    return this.http.get(this.CAR_API + '/' + id);
  }

  save(car: any): Observable<any> {
    let result: Observable<any>;
    if (car.href) {
      result = this.http.put(car.href, car);
    } else {
      result = this.http.post(this.CAR_API, car);
    }
    return result;
  }

  addEvent(title: string, selectInfo: DateSelectArg){

  let result: Observable<any>;

const formData = new FormData();
formData.append("title", title);
formData.append("startDate", selectInfo.startStr);
formData.append("endDate", selectInfo.endStr);

  result = this.http.post(this.CALENDAR_API_ADD_EVENT, formData);
  return result;
  }

  getEvents(){
 let result: Observable<any>;

  result = this.http.get(this.CALENDAR_API_GET_EVENTS);

  return result;

  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
