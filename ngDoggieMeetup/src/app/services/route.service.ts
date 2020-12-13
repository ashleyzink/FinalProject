import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Route } from '../models/route';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  private url = environment.baseUrl + 'api/';
  private authUrl = environment.baseUrl + 'api/auth/';

  constructor(
    private http: HttpClient,
    private authService: AuthService // private router: Router
  ) {}


  index(): Observable<Route[]> {
    const httpOptions = this.authService.getHttpOptions();
    return this.http.get<Route[]>(this.url + 'routes', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Error getting all routes'
        );
      })
    );
  }

  getUserRoutes(): Observable<Route[]> {
    const httpOptions = this.authService.getAuthHttpOptions();
    return this.http.get<Route[]>(this.authUrl + 'profile/routes', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Error getting user routes'
        );
      })
    );
  }

  addRoute(route: Route): Observable<Route> {
    const httpOptions = this.authService.getAuthHttpOptions();
    return this.http.post<Route>(this.authUrl + 'profile/routes', route, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Error adding route'
        );
      })
    );
  }








}
