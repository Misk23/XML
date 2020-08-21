import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScientificPublicationService } from 'src/app/services/scientific-publication.service';

@Component({
  selector: 'app-accepted-publications',
  templateUrl: './accepted-publications.component.html',
  styleUrls: ['./accepted-publications.component.css']
})
export class AcceptedPublicationsComponent implements OnInit {
  
  publications = [];

  constructor(private router: Router, private scientificPublicationService: ScientificPublicationService) { }

  ngOnInit(): void {
    this.scientificPublicationService.getAllAcceptedPublicatons()
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
