import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
}
