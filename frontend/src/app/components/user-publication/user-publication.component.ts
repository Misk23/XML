import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScientificPublicationService } from 'src/app/services/scientific-publication.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';
import { EditPublicationComponent } from '../edit-publication/edit-publication.component';

@Component({
  selector: 'app-user-publication',
  templateUrl: './user-publication.component.html',
  styleUrls: ['./user-publication.component.css']
})
export class UserPublicationComponent implements OnInit {

  public publications = [];

  constructor(private router: Router, private scientificPublicationService: ScientificPublicationService,
    private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.scientificPublicationService.getMyPublications(this.authService.getCurrentUser().username)
    .subscribe(success => {this.setPublications(success)});
  }

  setPublications(data){
    if (data.length !== 0){
      this.publications = data;
      console.log(this.publications[0])
    }
    else{
      console.log("Nema nijedne publikacije")
    }
  }

  onWithdraw(publication: string){

    this.scientificPublicationService.withdrawPublication(publication).subscribe( success =>{
      console.log(success);
      this.ngOnInit();
    }
    );
  }


  onShowPublication(publication: string){

    this.scientificPublicationService.showPublication(publication).subscribe( publicationHtmlContent =>{
        localStorage.setItem('publicationHtmlContent', publicationHtmlContent);

        window.open('publicationHtmlContent', '_blank');
    }
    );
  }

  public getPdf(publication: string){

    this.scientificPublicationService.getPdf(publication).subscribe((pdf: Blob) => {
      const downloadURL = URL.createObjectURL(pdf);
      const a = document.createElement('a');
      a.href = downloadURL;
      a.download = publication + '.pdf';
      a.click();
    });
  }

  public onEdit(id: string){
    localStorage.setItem('publicationIdForEdit', id);
    window.open('editPublication', '_blank');
  }
}
