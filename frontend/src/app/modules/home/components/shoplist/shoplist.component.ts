import { Component } from '@angular/core';
import { Product } from '../../model/product.entity';
import { ShopListService } from '../../services/shoplist.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, map, switchMap } from 'rxjs';
import { Order } from '../../model/order.entity';

@Component({
  selector: 'app-shoplist',
  templateUrl: './shoplist.component.html',
  styleUrls: ['./shoplist.component.scss']
})
export class ShoplistComponent {

  products: Product[] = []

  constructor(private shoplist: ShopListService, private router: Router){
    this.shoplist.products$.subscribe((newData) => {
      this.products = newData;
    });
  }


  get total(): number{
    return this.shoplist.total()
  }

  pay(){
    this.router.navigate(["/home/payment"])
  }

  sendPayment(method: string){
    
  }
}
