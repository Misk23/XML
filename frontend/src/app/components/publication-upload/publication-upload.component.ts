import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScientificPublicationService } from 'src/app/services/scientific-publication.service';
import { CoverLetterService } from 'src/app/services/cover-letter.service';

@Component({
  selector: 'app-publication-upload',
  templateUrl: './publication-upload.component.html',
  styleUrls: ['./publication-upload.component.css']
})
export class PublicationUploadComponent implements OnInit {

  publicationToUpload: File = null;
  coverLetterToUpload: File = null;

  constructor(private scientificPublicationService : ScientificPublicationService, private router: Router,
    private coverLetterService: CoverLetterService) { }

  ngOnInit(): void {
  }

  handlePublicationInput(files: FileList) {
    this.publicationToUpload = files.item(0);
  }

  handleCoverLetterInput(files: FileList) {
    this.coverLetterToUpload = files.item(0);
  }

  uploadFileToActivity() {
    this.scientificPublicationService.postFile(this.publicationToUpload).subscribe(data => {
      }, error => {
        console.log(error);
      });
      this.coverLetterService.postFile(this.coverLetterToUpload).subscribe(data => {
      }, error => {
        console.log(error);
      });
      this.router.navigate(['/']);
  }

}
