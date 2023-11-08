import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './components/main/main.component';
import { ToastModule } from 'primeng/toast';
import { HomeComponent } from './home/home.component';
import { HeaderModule } from '../header/header.module';
import { FooterModule } from '../footer/footer.module';
import { HomeRoutingModule } from './home-routing.module';
import { HelpComponent } from './components/help/help.component';
import { FieldsetModule } from 'primeng/fieldset';
import { CarouselModule } from 'primeng/carousel';
import { ImageModule } from 'primeng/image';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { ExplorerComponent } from './components/explorer/explorer.component';
import { OrderComponent } from './components/order/order.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ShoplistComponent } from './components/shoplist/shoplist.component';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {InputTextModule } from 'primeng/inputtext'
import { QRCodeModule } from 'angularx-qrcode';


@NgModule({
  declarations: [
    MainComponent,
    HomeComponent,
    HelpComponent,
    ExplorerComponent,
    OrderComponent,
    PaymentComponent,
    ShoplistComponent
  ],
  imports: [
    CommonModule,
    ToastModule,
    HeaderModule,
    FooterModule,
    HomeRoutingModule,
    FieldsetModule,
    CarouselModule,
    ImageModule,
    TagModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule,
    QRCodeModule
  ]
})
export class HomeModule { }
