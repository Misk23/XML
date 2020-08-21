import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WorkflowService {

  private readonly basePath = 'http://localhost:8081/workflow'

  constructor(private http: HttpClient) {
    this.http = http;
   }


  getWorkflows(){
    return this.http.get(this.basePath + '/all', {responseType: 'json'});
  }

  acceptPublication(publication: string){
    return this.http.get(this.basePath + '/accept/' + publication, {responseType: 'text'});
  }

  rejectPublication(publication: string){
    return this.http.get(this.basePath + '/reject/' + publication, {responseType: 'text'});
  }

  sendReviewRequest(reviewRequest){
    var headers: HttpHeaders = new HttpHeaders({'Content-Type':'application/json'});
    return this.http.post(this.basePath + "/add_reviewer", JSON.stringify(reviewRequest),
          {headers, responseType: 'text' as 'json'} );

  }

  getPublicationsToReview(username){
    return this.http.get(this.basePath + '/get_to_review/'+username, {responseType: 'json'});
  }
}
