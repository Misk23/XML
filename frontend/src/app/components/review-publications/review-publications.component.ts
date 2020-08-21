import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WorkflowService } from 'src/app/services/workflow.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';

@Component({
  selector: 'app-review-publications',
  templateUrl: './review-publications.component.html',
  styleUrls: ['./review-publications.component.css']
})
export class ReviewPublicationsComponent implements OnInit {

  public publications = [];

  constructor(private router: Router, private workflowService: WorkflowService,
    private authService: AuthenticationService) { }

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

}
