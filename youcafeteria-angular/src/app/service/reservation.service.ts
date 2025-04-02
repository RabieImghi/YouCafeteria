import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { StateService } from './state.service';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private url = `${environment.apiUrl}reservation`;

  constructor(private http: HttpClient, private stateService: StateService) {}

  createReservation(data: { reservationDate: string, dishName: string }): Observable<any> {
    const username = this.stateService.getUsername();
    return this.http.post(`${this.url}/create/${username}`, data);
  }

  getUserReservations(username: string): Observable<any> {
    return this.http.get(`${this.url}/user/${username}`);
  }

  cancelReservation(id: number): Observable<any> {
    return this.http.delete(`${this.url}/cancel/${id}`);
  }
}
