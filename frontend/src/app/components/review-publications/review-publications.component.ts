import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WorkflowService } from 'src/app/services/workflow.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';
import { ScientificPublicationService } from 'src/app/services/scientific-publication.service';
import { TransferService } from 'src/app/services/transfer.service';

@Component({
  selector: 'app-review-publications',
  templateUrl: './review-publications.component.html',
  styleUrls: ['./review-publications.component.css']
})
export class ReviewPublicationsComponent implements OnInit {

  public publications = [];
  public publicationChosen = false;

  constructor(private router: Router, private workflowService: WorkflowService,
    private authService: AuthenticationService, private scientificPublicationService:ScientificPublicationService,
    private transferService: TransferService) { }

  ngOnInit(): void {
    this.workflowService.getPublicationsToReview(this.authService.getCurrentUser().username)
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

  onShowPublication(publication: string){

    this.scientificPublicationService.showPublication(publication).subscribe( publicationHtmlContent =>{
        localStorage.setItem('publicationHtmlContent', publicationHtmlContent);

        window.open('publicationHtmlContent','_blank');
    }
    );
  }

  getPdf(publication: string){

    this.scientificPublicationService.getPdf(publication).subscribe((pdf: Blob) => {
      const downloadURL = URL.createObjectURL(pdf);
      const a = document.createElement('a');
      a.href = downloadURL;
      a.download = publication + '.pdf';
      a.click();
    });
  }

  openForm(publication){
    this.publicationChosen = true;
    console.log(publication.publicationId);
    this.transferService.changePublication(publication);
  }

}
