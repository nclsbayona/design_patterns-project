import { Component } from '@angular/core';
import { Product } from '../../model/product.entity';
import { ProductsService } from '../../services/products.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent {

  constructor(private productS: ProductsService, private messageS: MessageService){}

  products:Product[] = []

  ngOnInit(): void {
    this.productS.getProducts().subscribe({
      next: (v: Product[])=>{
        this.products = v
      },
      error: (e: HttpErrorResponse)=>{
        this.messageS.add({
          severity: 'error',
          summary: e.message
        })
      }
    })
  }

}
