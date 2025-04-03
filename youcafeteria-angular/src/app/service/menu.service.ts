import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 

@Injectable({
  providedIn: 'root'
})
export class MenuService {

    private url = environment.apiUrl;
    
    constructor(private http: HttpClient) { }

   
    getAllMenus(page: number = 0, size: number = 100): Observable<any> {
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());
      return this.http.get(`${this.url}menu/all`, { params });
    }
    
    createMenu(data: any): Observable<any> {
      return this.http.post(`${this.url}menu/create`, data);
    }
    
    updateMenu(data: any): Observable<any> {
      return this.http.put(`${this.url}menu/update`, data);
    }
    
    deleteMenu(name: string): Observable<any> {
      return this.http.delete(`${this.url}menu/delete/${name}`);
    }
    
}