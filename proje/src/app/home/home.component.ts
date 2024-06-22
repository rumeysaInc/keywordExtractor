import { Router } from '@angular/router';
import { RouterModule, Routes } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  taraAnahtarKelimeler() {
    // Ücretsiz anahtar kelime tara butonuna tıklandığında login sayfasına yönlendir
    this.router.navigate(['/login']);
  }
}
