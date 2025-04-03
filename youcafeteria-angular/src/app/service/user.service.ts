import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 

@Injectable({
  providedIn: 'root'
})
export class UserService {

    private url = environment.apiUrl;
    
    constructor(private http: HttpClient) { }

    getUsers(page: number = 0, size: number = 100): Observable<any> {
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());
      return this.http.get(this.url + 'user', { params });
    }
  
    createUser(data: {
      username: string;
      firstName: string;
      lastName: string;
      password: string;
      email: string;
      role: string;
    }): Observable<any> {
      return this.http.post(`${this.url}auth/register`, data);
    }
    

    deleteUser(username: string): Observable<void> {
      return this.http.delete<void>(`${this.url}user/delete/${username}`);
    }
    
    updateUser(data: any): Observable<any> {
      return this.http.put(`${this.url}user/update`, data);
    }
    
}