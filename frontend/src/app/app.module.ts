import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import {RouterModule, Routes} from '@angular/router';
//import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HttpClientModule } from '@angular/common/http';

import { AuthGuardGuard }  from './services/security/auth-guard.guard';
import { JwtUtilsService } from './services/security/jwt-utils.service';
import { AuthenticationService } from './services/security/authentication-service.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './services/security/token-interceptor.service';
import { LoginGuardGuard } from './services/security/login-guard.guard';
import { UserService } from './services/user.service';
import { PublicationUploadComponent } from './components/publication-upload/publication-upload.component';
import { UserPublicationComponent } from './components/user-publication/user-publication.component';
import { HtmlContentComponent } from './components/user-publication/htmlContent';
import { WorkflowComponent } from './components/workflow/workflow.component';
import { AcceptedPublicationsComponent } from './components/accepted-publications/accepted-publications.component';
import { ReviewPublicationsComponent } from './components/review-publications/review-publications.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import { EditPublicationComponent } from './components/edit-publication/edit-publication.component';
import { ScientificPublicationService } from './services/scientific-publication.service';


const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate : [LoginGuardGuard]
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'main',
    component: MainComponent
  },
  {
    path: 'publication_upload',
    component: PublicationUploadComponent
  },
  {
    path: 'my_publications',
    component: UserPublicationComponent
  },
  {
    path: 'publicationHtmlContent',
    component: HtmlContentComponent
  },
  {
    path: 'workflow',
    component: WorkflowComponent
  },
  {
    path: 'accepted_publications',
    component: AcceptedPublicationsComponent
  },
  {
    path: 'review_publications',
    component: ReviewPublicationsComponent
  },
  {
    path: 'editPublication',
    component: EditPublicationComponent
  }

]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    RegisterComponent,
    LoginComponent,
    PublicationUploadComponent,
    UserPublicationComponent,
    HtmlContentComponent,
    WorkflowComponent,
    AcceptedPublicationsComponent,
    ReviewPublicationsComponent,
    ReviewFormComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    ),
    
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [
    AuthGuardGuard,
    JwtUtilsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    AuthenticationService,
    UserService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
