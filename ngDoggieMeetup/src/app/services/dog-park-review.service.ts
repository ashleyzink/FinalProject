import { User } from './../models/user';
import { DogParkReview } from './../models/dog-park-review';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
// import { DogPark } from '../models/dog-park';
import { AuthService } from './auth.service';
import { DogPark } from '../models/dog-park';
import { Dog } from '../models/dog';

@Injectable({
  providedIn: 'root',
})
export class DogParkReviewService {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}
  private url = environment.baseUrl + 'api/dogParkReview/';
  private authUrl = environment.baseUrl + 'api/auth/dogParkReview/';

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

  index(): Observable<DogParkReview[]> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<DogParkReview[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error getting to the dog park review index');
      })
    );
  }
  show(dog: Dog, user: User): Observable<DogParkReview> {
    const httpOptions = this.getHttpOptions();
    return this.http
      .get<DogParkReview>(this.url + '/' + dog + user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'error getting reviews for dog parks: ' + dog + user
          );
        })
      );
  }
}
