import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 

@Injectable({ providedIn: 'root' })
export class PlatService {
  private url = environment.apiUrl + 'dish';

  constructor(private http: HttpClient) {}

  getAllDishes(page: number = 0, size: number = 100): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get(`${this.url}/all`, { params });
  }

  createDish(data: any): Observable<any> {
    return this.http.post(`${this.url}/create`, data);
  }

  updateDish(data: any): Observable<any> {
    return this.http.put(`${this.url}/update`, data);
  }

  deleteDish(name: string): Observable<any> {
    return this.http.delete(`${this.url}/delete/${name}`);
  }

  getDish(name: string): Observable<any> {
    return this.http.get(`${this.url}/find/${name}`);
  }
  getDishesByMenu(menuName: string, page: number = 0, size: number = 100): Observable<any> {
    return this.http.get(`${this.url}/menu/${menuName}?page=${page}&size=${size}`);
  }
  
}
