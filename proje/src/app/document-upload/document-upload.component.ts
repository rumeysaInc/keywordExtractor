import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KeywordControllerService, UsersControllerService } from '../oapi_client/kExtractorApis';
import { ArticleControllerService } from '../oapi_client/kExtractorApis';

@Component({
  selector: 'app-document-upload',
  templateUrl: './document-upload.component.html',
  styleUrls: ['./document-upload.component.css']
})
export class DocumentUploadComponent {
  userId: number | undefined;
  uploadResult: any;
  errorMessage: string | undefined;
  constructor(
    private router: Router,
    private keywordService: KeywordControllerService,
    private articleService: ArticleControllerService,
    private usersService: UsersControllerService
  ) { }

  title: string = '';
  content: string = '';
  ispublic: boolean = false;
  keywords: string[] = [];
  selectedCategory: string = ''; // Seçilen kategori için değişken

  

  submit() {
    // Gönder butonuna tıklandığında yapılacak işlemler
    // Örneğin, metin analizi, anahtar kelime çıkarma, vb.
    // Sonuçları this.keywords dizisine ekleyebilirsiniz.

    // Anahtar kelime çıkarma servisiyle birlikte çalışma örneği
    const requestBody = {
      title: this.title,
      content: this.content,
      ispublic: this.ispublic,
      category: this.selectedCategory // Seçilen kategoriyi ekle
    };
    this.keywordService.extractKeywords(requestBody).subscribe({
      next: (response: string[]) => {
        this.keywords = response;
        console.log('Anahtar kelimeler başarıyla çıkartıldı:', this.keywords);
      },
      error: (error) => {
        console.error('Anahtar kelime çıkartma hatası:', error);
      }
    });

    // Doküman yükleme servisiyle birlikte çalışma örneği
    const articleRequest = {
      title: this.title,
      content: this.content,
      ispublic: this.ispublic,
      category: this.selectedCategory // Seçilen kategoriyi ekle
    };
    this.articleService.add(articleRequest).subscribe({
      next: (response) => {
        console.log('Doküman başarıyla yüklendi:', response);
        // İşleme göre yönlendirme yapabilirsiniz
      },
      error: (error) => {
        console.error('Doküman yükleme hatası:', error);
      }
    });
  }
}
