import { Routes } from '@angular/router';
import { HomeComponent } from './components/home.component';
import { AdminComponent } from './components/admin.component';
export const routes: Routes=[{path:'',component:HomeComponent},{path:'admin',component:AdminComponent},{path:'**',redirectTo:''}];
