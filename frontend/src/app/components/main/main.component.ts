import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public roles;

  constructor(private userService: UserService, private authService: AuthenticationService,
    private router: Router) { }

  ngOnInit(): void {

    this.roles = this.authService.getRoles();
    console.log(this.roles);
  }

}
