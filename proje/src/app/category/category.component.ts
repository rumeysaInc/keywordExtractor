import { Component,EventEmitter,Output } from '@angular/core';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  @Output() categorySelected = new EventEmitter<string>();

  selectCategory(category: string, event:Event) {
    event.preventDefault(); // Sayfanın yeniden yüklenmesini engeller
    this.categorySelected.emit(category);
  }
}
