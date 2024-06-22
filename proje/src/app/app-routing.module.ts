import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { NavComponent } from './nav/nav.component';
import { CategoryComponent } from './category/category.component';
import { ProductComponent } from './product/product.component';
import { DocumentUploadComponent } from './document-upload/document-upload.component';

const routes: Routes = [
  { path: 'home',  component: HomeComponent},
  { path: '',  redirectTo: 'home', pathMatch: 'full'},
  { path: 'login',  component: LoginComponent},
  { path: 'signup',  component: SignupComponent},
  { 
    path: 'search', component: NavComponent,
    children: [
      { path: 'products', component: ProductComponent, outlet: 'primary'},
      
    ]
  },
  { path: 'upload', component: DocumentUploadComponent},
  { path: 'category/:id', component: ProductComponent},

  //{ path: '**', redirectTo: 'home' }  // Tüm bilinmeyen yolları ana sayfaya yönlendirin 

  // login bileşeni için yol tanımlama
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
