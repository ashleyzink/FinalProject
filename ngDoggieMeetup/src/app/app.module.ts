import { DogParkCommentService } from './services/dog-park-comment.service';
import { DogParkService } from './services/dog-park.service';
import { AuthService } from './services/auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import { GoogleMapsModule } from '@angular/google-maps';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { FormsModule } from '@angular/forms';

import { DogParksMaterialComponent } from './components/dog-parks/dog-parks-material/dog-parks-material.component';
import { DogParkCommentsComponent } from './components/dog-parks/dog-park-comments/dog-park-comments.component';
import { DogParkCommentDisplayComponent } from './components/dog-parks/dog-park-comment-display/dog-park-comment-display.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { CommonModule } from '@angular/common';
import { DogComponent } from './components/dog/dog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavigationComponent,
    NotFoundComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    DogParksMaterialComponent,
    DogParkCommentsComponent,
    DogParkCommentDisplayComponent,
    UserProfileComponent,
    DogComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    GoogleMapsModule
  ],
  providers: [
    AuthService,
    DogParkService,
    DogParkCommentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
