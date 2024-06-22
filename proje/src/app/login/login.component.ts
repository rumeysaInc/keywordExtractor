import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsersControllerService } from '../oapi_client/kExtractorApis/services/UsersControllerService';
import { LoginDto } from '../oapi_client/kExtractorApis';
import { AuthResponseDTO } from '../oapi_client/kExtractorApis/models/AuthResponseDTO';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private usersControllerService: UsersControllerService,private router: Router) { }


  login(){
    const requestBody: LoginDto = {
      email: this.email,
      password: this.password
    };
    

    this.usersControllerService.login(requestBody).subscribe(
      (response: AuthResponseDTO) => {
        // Giriş başarılı olduğunda yönlendirme yapın
        const token = response.accessToken; // Token'ı al
        if (token) {
          localStorage.setItem('token', token); // Token'ı localStorage'a kaydet
          console.log("basarili");
          this.router.navigate(['/search', { outlets: { primary: 'products' } }]);
        } else {
          console.error('Token bulunamadı.');
        this.router.navigate(['/search', { outlets: { primary: 'products'} }]);
      }},
      error => {
        // Hata durumunu yönetin
        console.error('Giriş hatası:', error);
      }
    );
  }
  kayit(){
    this.router.navigate(['/signup']);

  }
}
