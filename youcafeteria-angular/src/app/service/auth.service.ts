import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 

@Injectable({
  providedIn: 'root'
})
export class AuthService {

    private url = environment.apiUrl + 'auth/';
    
    // **Constructor**
    constructor(private http: HttpClient) { }

    // **Methods**
    login(data: any) : Observable<any> {
        return this.http.post(this.url + 'login', data);
    }


  
}