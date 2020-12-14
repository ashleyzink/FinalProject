import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Dog } from '../models/dog';
import { User } from '../models/user';
import { AuthService } from './auth.service';
import { UserProfileService } from './user-profile.service';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private authUrl = environment.baseUrl + 'api/auth/admin';
  private url = environment.baseUrl + 'api';

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private user: UserProfileService
  ) {}

  indexUsers(): Observable<User[]> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<User[]>(this.url + '/users').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.indexUsers(): Error retrieving user list'
        );
      })
    );
  }

  indexDogs(): Observable<Dog[]> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Dog[]>(this.url + '/dogs').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.indexDogs(): Error retrieving dog list'
        );
      })
    );
  }

  displayUser(id: number): Observable<User> {
    const httpOptions = this.getAuthHttpOptions();
    return this.http.get<User>(`${this.authUrl}/users/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.displayUser(): Error retrieving user ' + id
        );
      })
    );
  }

  update(user: User): Observable<User> {
    console.log('Service update user method' + user.id);

    const httpOptions = this.getAuthHttpOptions();
    return this.http
      .put<User>(
        `${this.authUrl}` + '/users/' + `${user.id}`,
        user,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'UserProfileService.update(): Error updating user profile'
          );
        })
      );
  }

  disable(id: number): Observable<boolean> {
    const httpOptions = this.getAuthHttpOptions();
    return this.http
      .delete<boolean>(`${this.authUrl}` + '/users/' + `${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Admin.disable(): Error updating user profile');
        })
      );
  }

  enable(id: number): Observable<boolean> {
    const httpOptions = this.getAuthHttpOptions();
    return this.http
      .put<boolean>(`${this.authUrl}` + '/users/rejoin/' + `${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Admin.enable(): Error enabling user profile');
        })
      );
  }
  displayDog(id: number): Observable<Dog> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Dog>(`${this.url}/dogs/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.displayDog(): Error retrieving dog ' + id
        );
      })
    );
  }

  destroyDog(id: number): Observable<Dog>{
    const httpOptions = this.getAuthHttpOptions();
    return this.http.delete<Dog>(this.authUrl + '/dogs/'+ id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('DogService.destroy(): Error disabling dog');
      })
    );
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

  getAuthHttpOptions() {
    const credentials = this.authService.getCredentials();
    console.log(credentials + 'Admin Service Credentials');

    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    console.log(httpOptions + 'Admin Service HTTPOptions');
    return httpOptions;
  }
}
