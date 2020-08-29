import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ScientificPublicationEditDTO } from '../interfaces/scientific-publication-edit-dto';

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
    return this.http.get(this.basePath + '/withdraw/' + publication, {responseType: 'text'});
  }

  showPublication(publication: string){
    return this.http.get(this.basePath + '/showPublication/' + publication, {responseType: 'text'});
  }

  getPdf(publication: string){
    return this.http.get(this.basePath + '/getScientificPublicationPDF/' + publication, {responseType: 'blob'});
  }

  getAllAcceptedPublicatons(){
    return this.http.get(this.basePath + '/all_accepted', {responseType: 'json'});
  }

  getPublicationForEdit(publication: string){
    return this.http.get(this.basePath + '/getPublicationForEdit/' + publication, {responseType: 'json'});
  }

  editPublication(publicationEditDTO: ScientificPublicationEditDTO){
    const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.basePath + '/editPublication', JSON.stringify(publicationEditDTO), {headers, responseType: 'json'});
  }


}
