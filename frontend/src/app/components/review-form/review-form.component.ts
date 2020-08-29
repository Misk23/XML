import { Component, OnInit } from '@angular/core';
import { TransferService } from 'src/app/services/transfer.service';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';
import { ReviewService } from 'src/app/services/review.service';


enum CriteriaGrade{
  ABSOLUTELY_INSUFFICIENT = 'ABSOLUTELY_INSUFFICIENT',
  GREAT_WEAKNESSES = 'GREAT_WEAKNESSES',
  SMALL_WEAKNESSES = 'SMALL_WEAKNESSES',
  GOOD = 'GOOD',
  VERY_GOOD = 'VERY_GOOD',
  EXCELLENT = 'EXCELLENT'
}

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  public publication;
  public criteriaGrade = CriteriaGrade;
  public criteriaGradeOptions;
  public review = {
    publicationId : '',
    submissionDate:Date,
    reviewedBy: '',
    relevanceOfResearchProblem : '',
    conceptualQuality: '',
    methodologicalQuality: '',
    readability: '',
    originality: '',
    overallEvaluation: '',
    commentToAuthor: '',
    commentsToEditor: ''


  };

  constructor(private transferService: TransferService, private router:Router,
    private authService: AuthenticationService, private reviewService: ReviewService) {

    this.transferService.currentMessage.subscribe(message => {
      this.publication = message;
      this.review.publicationId = this.publication.publicationId;
      this.review.reviewedBy = this.authService.getCurrentUser().username;
      this.review.submissionDate = this.transferService.reviewDate;
    })


   }

  ngOnInit(): void {
    this.criteriaGradeOptions = Object.keys(this.criteriaGrade);
  }

  submitReview(){
    console.log(this.review);

    this.reviewService.saveReview(this.review).subscribe(success => {
      this.router.navigate(['/']);
    }, err => {
      alert(err.error);
      console.log(err);
    });

  }

}
