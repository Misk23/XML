import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ScientificPublicationService {

  private readonly basePath = 'http://localhost:8081/scientificPublication'

  constructor(private http: HttpClient) {
    this.http = http;
   }

   postFile(fileToUpload: File) {
    var headers: HttpHeaders = new HttpHeaders();
  
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);

    return this.http.post(this.basePath + "/save_as_XML", formData, { headers });
  }

  getMyPublications(author: string){
    return this.http.get(this.basePath + '/my_publications/' + author, {responseType: 'json'});
  }

  withdrawPublication(publication: string){
    console.log(publication);
    return this.http.get(this.basePath + '/withdraw/' + publication, {responseType: 'text'});
  }

}