import { DogParkReviewComponent } from './components/dog-park-review/dog-park-review.component';
import { DogParkReviewService } from './services/dog-park-review.service';
import { MeetupService } from './services/meetup.service';
import { DogParkCommentService } from './services/dog-park-comment.service';
import { DogParkService } from './services/dog-park.service';
import { AuthService } from './services/auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import { GoogleMapsModule } from '@angular/google-maps';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatListModule} from '@angular/material/list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NgbCarouselModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
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
import {MatFormFieldModule} from '@angular/material/form-field';
import { MeetupsPageComponent } from './components/meetups/meetups-page/meetups-page.component';
import { MeetupDetailsComponent } from './components/meetups/meetup-details/meetup-details.component';
import { MatNativeDateModule } from '@angular/material/core';
import { DogProfileComponent } from './components/dog-profile/dog-profile.component';
import { FriendProfileComponent } from './components/friend-profile/friend-profile.component';
import { TrackerComponent } from './components/maps/tracker/tracker.component';
import { MapDisplayComponent } from './components/maps/map-display/map-display.component';
import { WalksComponent } from './components/maps/walks/walks.component';
import { AdminComponent } from './components/admin/admin.component';



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
    DogParkReviewComponent,
    DogParkCommentsComponent,
    DogParkCommentDisplayComponent,
    UserProfileComponent,
    DogComponent,
    MeetupsPageComponent,
    MeetupDetailsComponent,
    DogProfileComponent,
    FriendProfileComponent,
    TrackerComponent,
    MapDisplayComponent,
    WalksComponent,
    AdminComponent
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
    GoogleMapsModule,
    NgbCarouselModule,
    MatFormFieldModule,
    MatExpansionModule,
    MatListModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule
  ],
  providers: [
    AuthService,
    DogParkService,
    DogParkReviewService,
    DogParkCommentService,
    MeetupService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
