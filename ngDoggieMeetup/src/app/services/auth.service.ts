import { UserProfileService } from 'src/app/services/user-profile.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private user: User = null;

  constructor(private http: HttpClient) { }

  getAuthHttpOptions(): Object {
    const credentials = this.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
        'Content-type': 'application/json'
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

  login(username: string, password: string): Observable<User> {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    // create request to authenticate credentials
    return this.http
      .get<User>(this.baseUrl + 'authenticate', httpOptions)
      .pipe(
        tap((res) => {
          localStorage.setItem('credentials' , credentials);
          this.user = res;
          console.log(this.user);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.login(): Error logging in.');
        })
      );
  }

  register(user: User): Observable<User> {
    // create request to register a new account
    return this.http.post<User>(this.baseUrl + 'register', user)
    .pipe(
      catchError((err: any) => {
        console.log('Error in AuthService.register(): User: ')
        console.log(user)
        console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  checkUserLoggedIn(user: User): boolean {
    let credentials = atob(this.getCredentials());
    let username = credentials.split(':')[0];
    return username === user.username;
  }

  checkLoggedInUserIsAdmin(): boolean {
    let lazyLoadUser = this.user;
    let isAdmin = false;
    if (lazyLoadUser !== null) {
      if (lazyLoadUser.role === 'admin') {
        isAdmin = true;
      }
    }
    return isAdmin
  }

  getLoggedInUser(): User {
    let lazyLoadUser = this.user;
    return this.user;
  }

}
