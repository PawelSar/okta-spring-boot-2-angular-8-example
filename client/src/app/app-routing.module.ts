import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarListComponent } from './car-list/car-list.component';
import { CarEditComponent } from './car-edit/car-edit.component';
import { GoogleCalendarComponent } from './google-calendar/google-calendar.component';

const routes: Routes = [
  {
    path: 'car-list',
    component: CarListComponent
  },
  {
    path: 'car-add',
    component: CarEditComponent
  },
    {
      path: 'gcal',
      component: GoogleCalendarComponent
    },
  {
    path: 'car-edit/:id',
    component: CarEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
