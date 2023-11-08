import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../model/product.entity';
import { BehaviorSubject, Observable, Subject, combineLatest, finalize, forkJoin, of, switchMap, zip } from 'rxjs';
import { v4 as uuid } from 'uuid';
import { environment } from 'src/environments/environment';
import { Order } from '../model/order.entity';

@Injectable({
  providedIn: 'root'
})
export class ShopListService {

  private productsSubject: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);
  products$: Observable<Product[]> = this.productsSubject.asObservable();
  private productToAddSubject: Subject<Product> = new Subject<Product>();

  private _uuid = uuid() //Instanciar un uuid único para el id del cliente

  private orderInternal: any

  constructor(private http: HttpClient) {
    this.productToAddSubject.subscribe((newData) => {
      this.productsSubject.next([...this.productsSubject.value, newData]);
    });
  }

  add(product: Product) {
    this.productToAddSubject.next(product);
  }

  total(): number {
    return this.productsSubject.value.reduce((acum, item) => acum + item.price, 0)
  }

  getId() {
    return this._uuid
  }

  order(onlinePay: boolean): Observable<Order> {
    return this.createOrder().pipe(
      finalize(()=>this.productsSubject.next([])),
      switchMap( order => {
        this.orderInternal = order
        return this.addProducts()
      }),
      switchMap(() => this.pay(onlinePay)),
    );
  }

  private createOrder(): Observable<Order> {
    const params = new HttpParams().set("client_id", this._uuid)
    return this.http.post<Order>(`${environment.backendApi}/orders`, {}, { params: params })
  }

  private addProducts(): Observable<Order[]> {
    const items = this.productsSubject.value;
    const postObservables = items.map(item =>{
      return this.http.put<Order>(`${environment.backendApi}/orders/${this.orderInternal.id}/simple/${item.id}`, {})}
    );
    return forkJoin(postObservables).pipe(
      switchMap((result) => {
        console.log(result)
        return of(result)
      })
    )
  }

  private pay(pay: boolean): Observable<any> {
    return pay ? this.http.put<Order>(`${environment.backendApi}/orders/${this.orderInternal.id}`, {}): of(null)
  }


}
