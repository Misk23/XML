import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WorkflowService } from 'src/app/services/workflow.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-workflow',
  templateUrl: './workflow.component.html',
  styleUrls: ['./workflow.component.css']
})
export class WorkflowComponent implements OnInit {

  public workflows = [];
  public reviewers = [];
  public reviewRequest;

  constructor(private workflowService: WorkflowService, private router: Router,
    private userService: UserService) {
      this.reviewRequest = {
        publication: '',
        reviewer: ''
      };
     }

  ngOnInit(): void {
    this.workflowService.getWorkflows()
    .subscribe(success => {this.setWorkflows(success)});

    this.userService.getAllReviewers()
    .subscribe(success => {this.setReviewers(success)});
  }

  setWorkflows(data){
    if (data.length !== 0){
      this.workflows = data;
      console.log(this.workflows[0])
    }
    else{
      console.log("Nema nijenog workflowa")
    }
  }
  setReviewers(data){
    if (data.length !== 0){
      this.reviewers = data;
      console.log(this.reviewers)
    }
    else{
      console.log("Nema nijenog reviewra")
    }
  }

  onAccept(publication: string){
    this.workflowService.acceptPublication(publication).subscribe( success =>{
      console.log(success);
      this.ngOnInit();
    }
    
    );
  }

  onReject(publication: string){
    this.workflowService.rejectPublication(publication).subscribe( success =>{
      console.log(success);
      this.ngOnInit();
    }
    );
  }

  onOptionsSelected(r){
    this.reviewRequest.reviewer = r;
  }

  onAddReviewer(publication){
    
    this.reviewRequest.publication = publication;
    console.log(this.reviewRequest);
    this.workflowService.sendReviewRequest(this.reviewRequest).subscribe(success => {
      window.location.reload();
    }, err => {
      alert(err.error);
      console.log(err);
    });

  }


  processed(workflow){
    if (workflow.status == "accepted")
      return true
    if (workflow.status == "rejected")
      return true
    if (workflow.status == "withdrawn")
      return true
    if (workflow.status == "revising")
      return true
    if (workflow.status == "submitted")
      return false
    if (workflow.status == "reviewing")
      return false
  }

}
