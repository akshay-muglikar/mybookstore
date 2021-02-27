import { Component, OnInit, Input } from '@angular/core';
import {CartServiceService} from '../cart-service.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.less']
})
export class ProductDetailComponent implements OnInit {
  @Input() product: ProductDetails={bookID:0,
    title: "",
    authors: "",
    average_rating: 0,
    isbn: 0,
    language_code: "",
    ratings_count:0,
    price:0};
   
    @Input() image:string=""
    alerts:Alert[] =[];
    
    constructor( private cart : CartServiceService) { 
    }
   
  ngOnInit(): void {

  }
  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  addtoCart(){
    this.cart.addtoCart(this.product);
    this.alerts.push({
      type:"warning",
      message:"Added to cart",
      display:false

    });
    var _this =this;
    setTimeout(function(){
      if(_this.alerts.length>0){
        _this.alerts.pop();
      }
    }, 3000);
  }

}
export interface ProductDetails{
  bookID: number ;
  title: string ;
  authors: string;
  average_rating: number;
  isbn: number;
  language_code: string;
  ratings_count:number ;
  price:number;
}
export interface ProductImage {
  Image:string
}
export interface Alert {
  type:string;
  message:string;
  display:boolean;
}