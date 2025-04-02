import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private url = environment.apiUrl + 'stock';

  constructor(private http: HttpClient) {}

  getAllStocks(page: number = 0, size: number = 100): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get(`${this.url}/all`, { params });
  }

  getStock(name: string): Observable<any> {
    return this.http.get(`${this.url}/details/${name}`);
  }

  createStock(data: any): Observable<any> {
    return this.http.post(`${this.url}/create`, data);
  }

  updateStock(data: any): Observable<any> {
    return this.http.put(`${this.url}/update`, data);
  }

  deleteStock(name: string): Observable<any> {
    return this.http.delete(`${this.url}/delete/${name}`);
  }
}
