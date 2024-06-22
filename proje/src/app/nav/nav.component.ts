import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  constructor(private router: Router) {}

  ucretsizDene(){
    this.router.navigate(['/upload']) }

    
    dokumanYukle(event: Event) {
    event.preventDefault();
    this.router.navigate(['/upload']);
  }

  dokumanAra(event: Event) {
    event.preventDefault();
    this.router.navigate(['/search', { outlets: { primary: ['products'] } }]);
  }
}

