import { Injectable } from '@angular/core';
import { HttpRequest,HttpHandler,HttpEvent } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Injector } from '@angular/core';
import {AuthenticationService } from './authentication-service.service'

@Injectable()
export class TokenInterceptorService {

  constructor(private inj: Injector) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authenticationService:AuthenticationService = this.inj.get(AuthenticationService); 
    request = request.clone({
      setHeaders: {
        'X-Auth-Token': `${authenticationService.getToken()}`
      }
    });

    return next.handle(request);
  }
}
