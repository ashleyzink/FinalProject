
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { environment } from './../../environments/environment.prod';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dog } from '../models/dog';


@Injectable({
  providedIn: 'root'
})
export class DogService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) { }

    private url = environment.baseUrl + 'api/dogProfile'
    private authUrl = environment.baseUrl + 'api/auth/dogProfile'

    public index(): Observable<Dog[]> {
      const httpOptions = this.getHttpOptions();
      return this.http.get<Dog[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('DogService.index(): Error retrieving Dog List');
        })
      );
    }

      show(id: number): Observable<Dog>{
        const httpOptions = this.getHttpOptions();
        return this.http.get<Dog>(this.url + '/' + id, httpOptions).pipe(
          catchError((err: any) =>{
          console.log(err);
          return throwError('DogService.show(): Error getting dog id');
          })
        );
      }
      create(newDog: Dog): Observable<Dog>{
        if(! this.authService.checkLogin){
          console.log('User is not logged in at DogService.create()');
          this.router.navigateByUrl('/login');
        }
        const httpOptions = this.getHttpOptions();
        return this.http.post<Dog>(this.authUrl, newDog, httpOptions).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('DogService.create(): Error retrieving create');
          })
        );
      }
    update(dog: Dog): Observable<Dog>{
      if(! this.authService.checkLogin){
        console.log('User is not logged in at DogService.update()');
        this.router.navigateByUrl('/login');
      }
      const httpOptions = this.getHttpOptions();
      return this.http.put<Dog>(this.authUrl + dog.id, dog, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('DogService.update(): Error updating dog');
        })
      );
    }

    destroy(id: number): Observable<Dog>{
      if(! this.authService.checkLogin){
        console.log('User is not logged in at DogService.delete()');
        this.router.navigateByUrl('/login');
      }
      const httpOptions = this.getHttpOptions();
      return this.http.delete<Dog>(this.authUrl + id, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('DogService.destroy(): Error disabling dog');
        })
      );
    }

    getHttpOptions(): Object{
      const credentials = this.authService.getCredentials();
      const httpOptions = {
        headers: new HttpHeaders({
          Authorization: `Basic ${credentials}`,
          'X-Requested-With': 'XMLHttpRequest',
          'Content-type': 'application/json'
        })
      };
      return httpOptions;
    }


}
