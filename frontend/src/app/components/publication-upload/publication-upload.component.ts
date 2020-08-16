import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScientificPublicationService } from 'src/app/services/scientific-publication.service';

@Component({
  selector: 'app-publication-upload',
  templateUrl: './publication-upload.component.html',
  styleUrls: ['./publication-upload.component.css']
})
export class PublicationUploadComponent implements OnInit {

  publicationToUpload: File = null;

  constructor(private scientificPublicationService : ScientificPublicationService, private router: Router) { }

  ngOnInit(): void {
  }

  handleFileInput(files: FileList) {
    this.publicationToUpload = files.item(0);
  }

  uploadFileToActivity() {
    this.scientificPublicationService.postFile(this.publicationToUpload).subscribe(data => {
      }, error => {
        console.log(error);
      });
  }

}
