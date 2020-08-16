import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly basePath = 'http://localhost:8081/user'

  constructor(private http: HttpClient) {
    this.http = http;
   }


   sendRegisterRequest(registrationRequest){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log("U servisu sam");
    return this.http.post(this.basePath + "/register", JSON.stringify(registrationRequest),
           {headers, responseType : 'text' as 'json'} );
  }

  postFile(fileToUpload: File) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);

    return this.http.post(this.basePath + "/save_as_XML", formData, { headers, responseType : 'text' as 'json' });
  }
}
