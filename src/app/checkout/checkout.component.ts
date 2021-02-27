import { Component, Input, OnInit, ViewChild } from '@angular/core';
import {CartServiceService} from '../cart-service.service';

import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';  
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.less']
})
export class CheckoutComponent implements OnInit {
@Input() checkoutnow:boolean=false;
@Input() total:number=0;
@ViewChild('test') private content:any;
  constructor(private modalService: NgbModal, private service: CartServiceService) { }

  ngOnInit(): void {
    const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.total = this.total;
  }
  open(content:any) {
    this.modalService.open(content, { size: 'lg' });
  }
  close(){
    this.checkoutnow=false;
    }
}
@Component({
  selector: 'ngbd-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Payment</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>Total {{total}}</p>
      <p>Name Akshay Muglikar</p>

    </div>
    <div class="modal-footer">
    <button type="button" class="btn btn-outline-dark" (click)="checkout()">Test</button>
    
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `
})
export class NgbdModalContent {
  @Input() total:number=0;
  test: PaymentDetails = {
    amount:0,
    purpose:"string",
    buyer_name:"string",
    email:"string",
    phone:"string"
  }
  constructor(public activeModal: NgbActiveModal, private service: CartServiceService) {}

  checkout(){
     
    this.service.paymentRequest(this.test).subscribe((res:any)=>{
      console.log(res);
    });
  }
}
export interface PaymentDetails {
  amount:number;
  purpose:string;
  buyer_name:string;
  email:string;
  phone:string;

}
