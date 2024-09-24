import {ApplicationConfig, inject, Injectable} from '@angular/core';
import {Product} from "../interface/product.model";
import {HttpClient, HttpHeaders, provideHttpClient, withJsonpSupport} from "@angular/common/http";
import {Observable} from "rxjs";


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'  // Asegura que la respuesta sea JSON
  })
};

@Injectable({
  providedIn: 'root'
})

export class ProductsService {
  // private apiUrl = 'api';
  private apiUrl = 'http://localhost:8080/api/v1/products';
  http = inject(HttpClient);

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl, httpOptions);
  }

  getProduct(id: string): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/getProduct?id=42e4afab-d3d8-472f-b108-0321ba602fbb`, httpOptions);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, product);
  }

  updateProduct(id: string, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/${id}`, product);
  }

  deleteProduct(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
