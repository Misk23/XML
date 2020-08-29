import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  public reviewDate;

  private messageSource = new BehaviorSubject({});
  currentMessage = this.messageSource.asObservable();


  constructor() { }

  changePublication(publication) {
    this.reviewDate = new Date();
    this.messageSource.next(publication);
  }
}
