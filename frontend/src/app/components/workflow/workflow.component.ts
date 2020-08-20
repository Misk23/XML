import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WorkflowService } from 'src/app/services/workflow.service';

@Component({
  selector: 'app-workflow',
  templateUrl: './workflow.component.html',
  styleUrls: ['./workflow.component.css']
})
export class WorkflowComponent implements OnInit {

  public workflows = [];

  constructor(private workflowService: WorkflowService, private router: Router) { }

  ngOnInit(): void {
    this.workflowService.getWorkflows()
    .subscribe(success => {this.setWorkflows(success)});
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

}
