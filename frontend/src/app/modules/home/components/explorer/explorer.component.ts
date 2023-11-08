import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../../services/products.service';
import { Product } from '../../model/product.entity';
import { HttpErrorResponse } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { ShopListService } from '../../services/shoplist.service';

@Component({
  selector: 'app-explorer',
  templateUrl: './explorer.component.html',
  styleUrls: ['./explorer.component.scss']
})
export class ExplorerComponent implements OnInit{
  products: Product[] = []
  constructor(private productS: ProductsService, private messageS: MessageService,
    private router: Router, private shoplistS: ShopListService){}

  ngOnInit(): void {
    this.productS.getProducts().subscribe({
      next: (v: Product[])=>{
        this.products = v
      },
      error: (e: HttpErrorResponse)=>{
        this.messageS.add({
          severity: "error",
          summary: e.message
        })
      }
    })
  }

  add(product: Product){
    this.messageS.add({
      severity: "success",
      summary: "Se ha agregado: "+product.name
    })
    this.shoplistS.add(product)    
  }
}
