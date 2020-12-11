import { DogPark } from './../models/dog-park';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Meetup } from '../models/meetup';
import { AuthService } from './auth.service';
import { Dog } from '../models/dog';

@Injectable({
  providedIn: 'root'
})
export class MeetupService {

  private url = environment.baseUrl + 'api/meetups/'
  private authUrl = environment.baseUrl + 'api/auth/meetups/'

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {

  }

  index(dogPark?: DogPark): Observable<Meetup[]> {
    const httpOptions = this.authService.getHttpOptions();
    if (!dogPark) {
      return this.http.get<Meetup[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error getting meetups');
        })
      );
    } else {
      return this.http.get<Meetup[]>(environment.baseUrl + 'api/dogParks/' + dogPark.id + '/meetups/', httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error getting meetups');
        })
      );
    }
  }

  getDogs(meetup: Meetup): Observable<Dog[]> {
    const httpOptions = this.authService.getHttpOptions();
    return this.http.get<Dog[]>(this.url + meetup.id + '/dogs', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error getting dogs for meetup');
      })
    );
  }

  create(meetup: Meetup, dogPark?: DogPark): Observable<Meetup> {
    const httpOptions = this.authService.getAuthHttpOptions();
    if (dogPark) {
      meetup.dogPark = dogPark;
    }
    return this.http.post<Meetup>(this.authUrl, meetup, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error creating meetup');
      })
    );
  }



}
