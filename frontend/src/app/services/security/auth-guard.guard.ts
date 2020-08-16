import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication-service.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (this.authenticationService.isLoggedIn()) {
      const allowedRoles = next.data.roles as Array<string>;
      const myRoles = this.authenticationService.getRoles();
      console.log(allowedRoles);
      console.log(myRoles);

      for (const i in myRoles) {
        if (allowedRoles.includes(myRoles[i])) {
          return true;
        }
      }

      this.router.navigate(['/main']);
      return false;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
