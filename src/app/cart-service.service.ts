import { Injectable } from '@angular/core';
import {ProductDetails} from './product-detail/product-detail.component'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {PaymentDetails} from './checkout/checkout.component'


@Injectable({
  providedIn: 'root'
})
export class CartServiceService {
  cart :ProductDetails[] =[];
  constructor(private http: HttpClient) { 
  }

  addtoCart(product: ProductDetails){
    this.cart.push(product);
  }

  paymentRequest(pay:PaymentDetails){
    var headers = { 'X-Api-Key': 'd82016f839e13cd0a79afc0ef5b288b3', 'X-Auth-Token': '3827881f669c11e8dad8a023fd1108c2'}
    
    const requestOptions = {                                                                                                                                                                                 
      headers:  new HttpHeaders(headers)
    };
    let params = new URLSearchParams();
    params.append("amount",pay.amount+"")
    params.append("purpose",pay.purpose)
    params.append("buyer_name",pay.buyer_name)
    params.append("email",pay.email)
    params.append("phone",pay.phone)

    return this.http.post("/pay/", params.toString, requestOptions);
  }
  getCart(){
    return this.cart;
  }
  removeFromCart(i:number){
    this.cart.splice(i, 1);
  }
}
