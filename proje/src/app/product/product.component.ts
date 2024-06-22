import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DetailsComponent } from './details/details.component';
import { ArticleListControllerService } from '../oapi_client/kExtractorApis/services/ArticleListControllerService';
import { Article } from '../oapi_client/kExtractorApis/models/Article';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  documents: Article[] = [];
  filteredProducts: Article[] = [];

  constructor(
    private articleService: ArticleListControllerService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.fetchDocuments();
  }

  fetchDocuments(): void {
    const requestBody = {}; // Tüm dokümanları çekmek için istek gövdesini boş bırakıyoruz.

    this.articleService.listAllArticles(requestBody).subscribe(
      (data: Article[]) => {
        console.log('Veriler başarıyla çekildi:', data);
        this.documents = data;
        this.filteredProducts = this.documents;
      },
      error => {
        console.error('Veri çekme hatası:', error);
      }
    );
  }

  openDialog(document: Article): void {
    this.dialog.open(DetailsComponent, {
      data: { text: document.content } // Article modelinde content alanı olduğunu varsayıyoruz
    });
  }

  onCategorySelected(category: string) {
    this.filterProductsByCategory(category);
  }

  filterProductsByCategory(category: string) {
    this.filteredProducts = this.documents.filter(document => document.category === category);
  }

  filterDocuments(event: Event) {
    const searchText = (event.target as HTMLInputElement).value;
    if (searchText) {
      this.filteredProducts = this.documents.filter(document =>
        document.title?.toLowerCase().includes(searchText.toLowerCase()) ||
        document.content?.toLowerCase().includes(searchText.toLowerCase())
      );
    } else {
      this.filteredProducts = this.documents; // Eğer metin yoksa, tüm dokümanları göster
    }
  }
}
