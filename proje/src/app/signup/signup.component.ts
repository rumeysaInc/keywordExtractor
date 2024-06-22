import { Component } from '@angular/core';
import { UsersControllerService } from '../oapi_client/kExtractorApis';// UsersControllerService'nin yolunu doğru şekilde belirtin
import { Router } from '@angular/router';
import { RegisterDto } from '../oapi_client/kExtractorApis';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  username: string = ''; // Başlatıcı atama yapıldı
  surname: string = '';
  email: string = '';
  password: string = '';

  constructor(private usersControllerService: UsersControllerService, private router: Router) { }

  signup() {
    const RegisterDto = {
      username: this.username,
      surname: this.surname,
      email: this.email,
      password: this.password
    };

    this.usersControllerService.register(RegisterDto).subscribe(
      response => {
        console.log("Kayıt başarılı:", response); 
        this.router.navigate(['/search', { outlets: { primary: 'products'} }]);
      },
      error => {
        console.error("Kayıt hatası:", error); 
      }
    );
  }

  logingeridon() {
    this.router.navigate(['/login']);
  }
}
