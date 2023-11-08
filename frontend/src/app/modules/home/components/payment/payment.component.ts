import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ShopListService } from '../../services/shoplist.service';
import { MessageService } from 'primeng/api';
import { Order } from '../../model/order.entity';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent {

  protected readonly PAYMENT_METHODS = [
    "QR",
    "NEQUI",
    "DAVIPLATA",
    "EFECTIVO"
  ]

  selectedMethod: any

  paymentForm: FormGroup

  constructor(private form: FormBuilder, private paymentS: ShopListService, private messageS: MessageService, private router: Router) {
    this.paymentForm = this.form.group({})
  }

  genForm() {
    switch (this.selectedMethod) {
      case 'NEQUI' || 'DAVIPLATA': {
        this.paymentForm = this.form.group({
          payment: ['', Validators.required]
        })
        break
      }
      case 'QR' || 'EFECTIVO':{
        this.paymentForm = this.form.group({})
        break
      }
    }
  }

  order() {
    this.paymentS.order(this.selectedMethod !== 'EFECTIVO').subscribe({
      next: (v: Order)=>{
        this.messageS.add({
          severity: 'success',
          summary: `Se ha aceptado la orden: ${v.id}`
        })
        setTimeout(()=>this.router.navigate(["/home"]), 1500)
      },
      error: (e: HttpErrorResponse)=>{
        this.messageS.add({
          severity: 'error',
          summary: `${e.message}`
        })
      }
    })
  }

  get clientId(){
    return this.paymentS.getId()
  }

}
