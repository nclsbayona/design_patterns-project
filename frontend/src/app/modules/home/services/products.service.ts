import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { Product } from '../model/product.entity';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private static readonly MAX_CAROUSEL_ITEMS = 6

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(`${environment.backendApi}/products`).pipe(
      take(ProductsService.MAX_CAROUSEL_ITEMS)
    )
  }

  getAllProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(`${environment.backendApi}/products`)
  }
}
