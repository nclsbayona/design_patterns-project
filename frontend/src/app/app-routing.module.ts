import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Error404Component } from './modules/error/components/error404/error404.component';

const routes: Routes = [
  { path: '', redirectTo: "home", pathMatch: 'full' },
  { path: "home", loadChildren: () => import('src/app/modules/home/home.module').then(m => m.HomeModule), title: "Inicio" },
  { path: "**", loadChildren: () => import('src/app/modules/error/error.module').then(m => m.ErrorModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
