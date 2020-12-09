import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { DogParkComment } from '../models/dog-park-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DogParkCommentService {

  private url = environment.baseUrl + 'api/dogParks/'
  private authUrl = environment.baseUrl + 'api/auth/dogParks/'

  constructor(private http: HttpClient,
    private authService: AuthService,
    private router: Router) { }

  index(dogParkId: number): Observable<DogParkComment[]> {
    const httpOptions = this.authService.getHttpOptions();
    return this.http.get<DogParkComment[]>(this.url + dogParkId + '/dogParkComments', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error getting dog park comments');
      })
    );
  }

  create(dogParkId: number, comment: DogParkComment): Observable<DogParkComment> {
    const httpOptions = this.authService.getAuthHttpOptions();
    return this.http.post<DogParkComment>(this.authUrl + dogParkId + '/dogParkComments', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error creating dog park comment');
      })
    );
  }

  update(dogParkId: number, comment: DogParkComment): Observable<DogParkComment> {
    const httpOptions = this.authService.getAuthHttpOptions();
    return this.http.put<DogParkComment>(this.authUrl + dogParkId + '/dogParkComments/' + comment.id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error updating dog park comment');
      })
    );
  }

  delete(dogParkId: number, commentId: number): Observable<Boolean> {
    const httpOptions = this.authService.getAuthHttpOptions();
    return this.http.delete<Boolean>(this.authUrl + dogParkId + '/dogParkComments/' + commentId, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error deleting dog park comment');
      })
    );
  }


}
