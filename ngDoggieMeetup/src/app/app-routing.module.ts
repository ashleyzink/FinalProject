import { MeetupsPageComponent } from './components/meetups/meetups-page/meetups-page.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DogParkReviewComponent } from './components/dog-park-review/dog-park-review.component';
import { DogParksMaterialComponent } from './components/dog-parks/dog-parks-material/dog-parks-material.component';
import { DogComponent } from './components/dog/dog.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { DogProfileComponent } from './components/dog-profile/dog-profile.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'userProfile', component: UserProfileComponent },
  { path: 'dogParks', component: DogParksMaterialComponent },
  { path: 'dogProfile', component: DogComponent },
  { path: 'meetups', component: MeetupsPageComponent },
  { path: 'dogParkReview', component: DogParkReviewComponent },
  { path: 'dogUserProfile/:id', component: DogProfileComponent },
  // { path: 'dogEditProfile/:id', component: DogProfileComponent },

  // ---Place all other routes above here---
  { path: '**', component: NotFoundComponent }, //page not found route
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
