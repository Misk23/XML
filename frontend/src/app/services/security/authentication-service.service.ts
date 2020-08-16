import { Injectable } from '@angular/core';
import { Observable,throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtUtilsService } from './jwt-utils.service';
import { catchError } from 'rxjs/operators';

@Injectable()
export class AuthenticationService {

  private readonly loginPath = 'http://localhost:8081/user/login';

  constructor(private http: HttpClient, private jwtUtilsService: JwtUtilsService) {
  }

  login(name: string, password: string, callback) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    var _this = this;
    
    var observer = {
      next(value) {
        console.log(value);
        if (value !== "Invalid login") {
          localStorage.setItem('currentUser', JSON.stringify({ 
                                    username: name,
                                    roles: _this.jwtUtilsService.getRoles(value), 
                                    token: value
                                  }));
          callback.handleLogin(true);
        }
      },
        error(msg) {
          callback.handleLogin(false);
        }
    }

    this.http.post(this.loginPath, JSON.stringify({ 
      username : name, 
      password : password })
    , { headers , responseType : 'text' as 'json'})
          // pipe hvata los zahtev i baca error koji subscribe obradjuje
          .pipe(catchError(err => {
          return throwError(err);
          }))
          .subscribe(observer);
  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token : "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    if (this.getToken() != '') return true;
    else return false;
  }

  getCurrentUser() {
    if (localStorage.currentUser) {
      return JSON.parse(localStorage.currentUser);
    }
    else {
      return undefined;
    }
  }

  getRoles(){
    var token = this.getToken().toString();
    return this.jwtUtilsService.getRoles(token);
  }
}
