import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private readonly basePath = 'http://localhost:8081/review'

  constructor(private http: HttpClient) {
    this.http = http;
   }

   saveReview(review){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.basePath + "/save", JSON.stringify(review),
           {headers, responseType : 'text' as 'json'} );
  }
}
