import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  private url = environment.baseUrl + 'api/users'
  private authUrl = environment.baseUrl + 'api/auth'

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    // private router: Router
  ) { }

  index(): Observable<User[]> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<User[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserProfileService.index(): Error retrieving user list');
      })
    );
  }

  show(): Observable<User> {
    const httpOptions = this.getAuthHttpOptions();
    return this.http.get<User>(this.authUrl + '/profile', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserProfileService.show(): Error retrieving user');
      })
    );
  }

  update(user: User): Observable<User> {
    console.log("Service update user method" + user.id);

    const httpOptions = this.getAuthHttpOptions()
    return this.http.put<User>(`${this.authUrl}` + "/users/" + `${user.id}`, user, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserProfileService.update(): Error updating user profile');
      })
    );
  }



  getAuthHttpOptions() {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }

  getHttpOptions(): Object {
    const httpOptions = {
      headers: new HttpHeaders({
        'X-Requested-With': 'XMLHttpRequest',
        'Content-type': 'application/json'
      })
    };
    return httpOptions;
  }

}
