import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { SearchComponent } from './components/search/search.component';
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  {path: "", component: MainComponent, title: "Agendamiento de citas"},
  {path: "schedule", component: ScheduleComponent, title: "Agendar cita"},
  {path: "search", component: SearchComponent, title: "Buscar"},
  {path:"**", redirectTo:"" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
