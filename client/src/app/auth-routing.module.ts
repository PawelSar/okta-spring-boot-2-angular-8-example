import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MatButtonModule, MatCardModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'home',
    component: HomeComponent
  }
];

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MatButtonModule,
    MatCardModule
  ],
  providers: [],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
