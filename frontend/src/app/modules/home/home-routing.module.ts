import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './components/main/main.component';
import { HelpComponent } from './components/help/help.component';
import { ExplorerComponent } from './components/explorer/explorer.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ShoplistComponent } from './components/shoplist/shoplist.component';
import { OrderComponent } from './components/order/order.component';

const routes: Routes = [
  {path:"", component: HomeComponent, children:[
    {path:"", component: MainComponent},
    {path:"help", component: HelpComponent},
    {path:"explore", component: ExplorerComponent},
    {path:"payment", component: PaymentComponent},
    {path:"shoplist", component: ShoplistComponent},
    {path:"orders", component: OrderComponent},

]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
