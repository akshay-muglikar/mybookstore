import { Component, OnInit } from '@angular/core';
import {BookdetailsService} from '../bookdetails.service';
import {ProductDetails, ProductImage} from '../product-detail/product-detail.component'
import {NgbPaginationConfig} from '@ng-bootstrap/ng-bootstrap';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.less']
})
export class HomepageComponent implements OnInit {
  title = 'my-store';
  products : ProductDetails[] = [];
  productImages : ProductImage[] =[];
  productsToDisplay: ProductDetails[] = [];
  page=1;
  pageSize =6;
  totalPages=0;

  minPrice:number=0;
  maxprice: number=0;
  name:string="";
  avgRating:number=0;
  sortByField:string="";
  sortDir:string="";
  loading:boolean =false;

 
  constructor(private bookService :BookdetailsService, config: NgbPaginationConfig) { 
    config.size = 'sm';
    config.rotate =true;
  }

  ngOnInit(): void {
    this.getBooks();
    this.bookService.getBookImages().subscribe(ProductImage =>{
      this.productImages = ProductImage;
    })
  }
  getBooks(){
    var param = this.getFilter();
    this.loading=true;
    this.bookService.getBooks(param).subscribe(products =>{
      this.products = products;
      this.productsToDisplay = products;
      this.totalPages=100  ;
      this.loading=false;
    });
  }
  log() : void {
    console.log(this.products);
  }
  sortBy=["Featured", "Name"]
  sort ="Featured";
  loadPage(page: number) {
   
      this.page = page;
      this.productsToDisplay = this.products.slice((this.page -1)*6, (this.page)*this.pageSize)
      this.getBooks()
    
  }

  getFilter(){
    var queryParam =  "page="+this.page;
    if(this.name!=""){
      queryParam+=  "&name="+encodeURIComponent(this.name);
    }
    if(this.minPrice!=0){
      queryParam+=  "&minPrice="+this.minPrice;
    }
    if(this.maxprice!=0){
      queryParam+=  "&maxPrice="+this.maxprice;
    }
    if(this.avgRating!=0){
      queryParam+=  "&avgRating="+this.avgRating;
    }
    if(this.sortByField!=""){
      queryParam+=  "&sortBy="+this.sortDir;
    }
    
    return queryParam;
  }

  filterRating(rating:number){
    this.avgRating = rating;
    this.getBooks();
  }
  clearRating(){
    this.page=1;
    this.filterRating(0)

  }
  filterPrice(min:number, max:number){
    this.minPrice = min;
    this.maxprice =max;
    this.getBooks();
  }
  clearPrice(){
    this.page=1;
   this.filterPrice(0,0);
  }
  sortData(option:string){
    if(option=="Name"){
        this.sortByField =option;
        this.sort="Name";
        this.sortDir="ASC";
    }else{
      this.sortByField="";
      this.sort="Featured"
      this.sortDir="";
    }
    this.page=1;
    this.getBooks();
  }
  sorDirUpdate(){
    if(this.sortDir=="ASC"){
      this.sortDir ="DSC";
    }else{
      this.sortDir="ASC";
    }
    this.page=1;
    this.getBooks();
  }
  clearSearch(){
    this.name="";
    this.getBooks();
  }
  search(){
    this.getBooks();
  } 
    
}
