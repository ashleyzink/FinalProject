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
import { DogParkReviewId } from '../models/dog-park-review-id';

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
  // auth/dogParks/{dogParkId}/dogParkReviews"

  //auth/dogParks/{dogParkId}/dogParkReviews/{userId}

  // auth/dogParks/{dogParkId}/dogParkReviews/{userId}
  update(
    dogParkReview: DogParkReview,
    dogParkId: number,
    dogPark: DogPark
  ): Observable<DogParkReview> {
    if (!this.authService.checkLogin) {
      console.log(
        'not logged in at DogParkReview.update() : redirect to /login'
      );
      this.router.navigateByUrl('/login');
    }
    const httpOptions = this.getAuthHttpOptions();
    return this.http
      .put<DogParkReview>(
        this.authUrl +
          dogParkId +
          '/dogParkReviews/' +
          dogParkReview.id.userId,
        dogParkReview,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error updating dog park review: ' + dogParkReview);
        })
      );
  }
  destroy(
    dogParkReview: DogParkReview,
    // dogParkId: number,
    // dogPark: DogPark,
  ): Observable<boolean> {
    if (!this.authService.checkLogin) {
      console.log('not logged in');
      this.router.navigateByUrl('/login');
    }
    const httpOptions = this.getAuthHttpOptions();
    return this.http.delete<boolean>(this.authUrl + dogParkReview.id.dogParkId + '/dogParkReviews/' + dogParkReview.id.userId, httpOptions ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'dogParkReviewService.destroy(): Error deleting Review'
        );

      })
    );
  }
}
