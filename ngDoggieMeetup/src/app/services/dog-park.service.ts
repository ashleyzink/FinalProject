import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { DogPark } from '../models/dog-park';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DogParkService {

  constructor(private http: HttpClient,
    private authService: AuthService,
    private router: Router) { }
    private url = environment.baseUrl + 'api/dogParks'

    getAuthHttpOptions(): Object {
      const credentials = this.authService.getCredentials();
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


    public index(): Observable<DogPark[]> {
      if(!this.authService.checkLogin) {
        this.router.navigateByUrl('/login');
      }
      const httpOptions = this.getHttpOptions();
      return this.http.get<DogPark[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error loading dog parks');
        })
      );
    }
}
