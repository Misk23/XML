import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './services/security/authentication-service.service'
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'XML Publications';

  constructor(private userService: UserService,
     private authService: AuthenticationService, public router: Router){

  }

  ngOnInit(){

  }

  loggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAuthor(){
    const roles = this.authService.getRoles();
    if(roles.includes('AUTHOR_ROLE')){
      return true;
    }else{
      return false;
    }
  }
  isEditor(){
    const roles = this.authService.getRoles();
    if(roles.includes('EDITOR_ROLE')){
      return true;
    }else{
      return false;
    }
  }
  isReviewer(){
    const roles = this.authService.getRoles();
    if(roles.includes('REVIEWER_ROLE')){
      return true;
    }else{
      return false;
    }
  }

  uploadPublication(){
    this.router.navigate(['/publication_upload']);
  }
  showPublications(){
    this.router.navigate(['/my_publications']);
  }
  showWorkflows(){
    this.router.navigate(['/workflow'])
  }
  acceptedPublications(){
    this.router.navigate(['/accepted_publications'])
  }
  showPublicationsToReview(){
    this.router.navigate(['/review_publications'])
  }

}
