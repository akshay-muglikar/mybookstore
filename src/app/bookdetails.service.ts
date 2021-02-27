import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ProductDetails, ProductImage} from './product-detail/product-detail.component'
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BookdetailsService {

  constructor(private http: HttpClient) { }
  configUrl = 'assets/config.json'
  getBooks(queryParam:string) {
    return this.http.get<ProductDetails[]>(environment.BOOKS_URL+"/Products?" + queryParam);
  }
  getBookImages() {
    return this.http.get<ProductImage[]>(environment.IMAGE_URL);
  }

  
}
