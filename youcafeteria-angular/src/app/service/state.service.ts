import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class StateService {

    // Bots
    private authSubject = new BehaviorSubject<any>(this.loadFromStorage('auth_access_token') || []);
    loginAuth$ = this.authSubject.asObservable();

    // **Methods to update state with local storage**
    setAuthToken(loginAuth: any) {
        this.authSubject.next(loginAuth);
        this.saveToStorage('auth_access_token', loginAuth);
    }

   

    // **Helper Methods for Local Storage**
    private saveToStorage(key: string, value: any) {
        localStorage.setItem(key, JSON.stringify(value));
    }

    private loadFromStorage(key: string): any {
        const storedData = localStorage.getItem(key);
        return storedData ? JSON.parse(storedData) : null;
    }

    private removeFromStorage(key: string) {
        localStorage.removeItem(key);
    }
    
    getUsername(): string  {
        const token = this.loadFromStorage('auth_access_token');
        try {
          const payload = token.split('.')[1];
          const decodedPayload = JSON.parse(atob(payload));
          return decodedPayload.sub || '';
        } catch (error) {
          console.error('Error decoding token:', error);
          return '';
        }
      }
      

    // **Logout**
    logout() {
        this.authSubject.next(null);
        this.removeFromStorage('auth_access_token');
    }
}