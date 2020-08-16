import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/security/authentication-service.service';
import { EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user;
  public wrongUsernameOrPass: boolean;

  @Output()
  changeDisplay: EventEmitter<any> = new EventEmitter();

  constructor(private authenticationService: AuthenticationService,
    private router: Router,
    private userService: UserService) {

      this.user = {};
      this.wrongUsernameOrPass = false;

     }

  ngOnInit(): void {
  }

  login(): void {
    this.authenticationService.login(this.user.username, this.user.password, this);
  }

  handleLogin(loggedIn) {
    if (loggedIn) {
      console.log('SUCCESSFUL COMPONENT');
      this.router.navigate(['/main']);
    } else {
      this.wrongUsernameOrPass = true;
      console.log('ERROR COMPONENT');
    }
  }

  openRegistration() {
    this.router.navigate(['/register']);

  }

}
