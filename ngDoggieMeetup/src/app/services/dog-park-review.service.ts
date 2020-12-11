import { User } from './../models/user';
import { DogParkReview } from './../models/dog-park-review';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { DogPark } from '../models/dog-park';

@Injectable({
  providedIn: 'root',
})
export class DogParkReviewService {
  deleteDogParkReview: any;
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}
  private url = environment.baseUrl + 'api/dogParks/';
  private authUrl = environment.baseUrl + 'api/auth/dogParks/';

  getAuthHttpOptions(): Object {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
        'Content-type': 'application/json',
      }),
    };
    return httpOptions;
  }
  getHttpOptions(): Object {
    const httpOptions = {
      headers: new HttpHeaders({
        'X-Requested-With': 'XMLHttpRequest',
        'Content-type': 'application/json',
      }),
    };
    return httpOptions;
  }

  index(dogParkId: number): Observable<DogParkReview[]> {
    const httpOptions = this.getHttpOptions();
    return this.http
      .get<DogParkReview[]>(
        this.url + dogParkId + '/dogParkReviews',
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error getting to the dog park review index');
        })
      );
  }
  // show(dogParkId: number, userId: number): Observable<DogParkReview> {
  //   const httpOptions = this.getHttpOptions();
  //   return this.http
  //     .get<DogParkReview>(this.url + '/' + dogParkId + "/users/" + userId, httpOptions)
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError(
  //           'error getting reviews for dog parks: ' + dogParkId + userId
  //         );
  //       })
  //     );
  // }

  create(dogParkReview: DogParkReview, dogParkId: number) {
    const httpOptions = this.getAuthHttpOptions();
    return this.http
      .post<DogParkReview>(
        this.authUrl + dogParkId + '/dogParkReviews',
        dogParkReview,
        httpOptions
      )
      .pipe(
        catchError((theError) => {
          console.error('error creating dog park review');
          console.error(theError);
          return throwError('error in creating dog park review');
        })
      );
  }
}
