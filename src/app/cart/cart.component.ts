import { Component, Input, OnInit } from '@angular/core';
import {ProductDetails} from '../product-detail/product-detail.component'
import {CartServiceService} from '../cart-service.service';
import {  ViewEncapsulation } from '@angular/core';
import {CheckoutComponent} from '../checkout/checkout.component';

import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./cart.component.less']
})
export class CartComponent implements OnInit {
  closeResult = '';
  total:number=0;
  cart :ProductDetails[] =[];
  checkoutnow: boolean=false;
  constructor(private service: CartServiceService, private modalService: NgbModal) { 
  }

  ngOnInit(): void {
  }
  addtoCart(product: ProductDetails){
    this.cart.push(product);
  }
  getCart(){
    this.cart = this.service.getCart();
    this.total=0;
    this.cart.forEach(x=>{
      
      this.total= this.total + x.price;
    })
  }
  open(content:any) {
    this.modalService.open(content, { size: 'lg' });
  }
  checkout(checkoutnow:boolean){
    this.checkoutnow=checkoutnow;
    
  }
  removeFromCart(i:number){
    this.cart.splice(i, 1);
    this.service.removeFromCart(i);
  }
  
 

}
