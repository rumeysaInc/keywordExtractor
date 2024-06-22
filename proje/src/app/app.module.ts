import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NavComponent } from './nav/nav.component';
import { CategoryComponent } from './category/category.component';
import { ProductComponent } from './product/product.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { SignupComponent } from './signup/signup.component';
import { DocumentUploadComponent } from './document-upload/document-upload.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { DetailsComponent } from './product/details/details.component';
import { HttpClientModule } from '@angular/common/http';
import { UsersControllerService } from './oapi_client/kExtractorApis/services/UsersControllerService'; // Servis importu
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavComponent,
    CategoryComponent,
    ProductComponent,
    HomeComponent,
    SignupComponent,
    DocumentUploadComponent,
    DetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
  ],
  providers: [UsersControllerService,
    {provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true}
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
