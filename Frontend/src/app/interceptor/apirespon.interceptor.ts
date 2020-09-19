import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {OauthService} from '../services/oauth.service';
import {Observable, throwError} from 'rxjs';
import {catchError, switchMap, tap} from 'rxjs/operators';


@Injectable()
export class ApiresponInterceptor implements HttpInterceptor {

  isRefreshingToken = false;

  constructor(public auth: OauthService) {

  }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(tap(() => {}, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err instanceof HttpErrorResponse && (<HttpErrorResponse>err).status === 401) {
          return this.handle401Error(req, next);
        } else {
          return throwError(err);
        }
      }
    }));
  }

  handle401Error(req: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshingToken) {
      this.isRefreshingToken = true;
      return this.auth.doRefreshToken().pipe(
        switchMap(res => {
          this.auth.refreshToken = res['refresh_token'];
          this.auth.accessToken = res['access_token'];
          return next.handle(this.alterToken(req));
        })
        , catchError( error => this.logoutUser(error)))
        .subscribe(result => this.isRefreshingToken = false);
    } else {
      this.auth.logout();
    }
  }

  alterToken(req: HttpRequest<any>): HttpRequest<any> {
    return req.clone({setHeaders: {Authorization: 'Bearer ' + this.auth.accessToken}});
  }

  logoutUser(error) {
    this.auth.logout();
    return throwError(error);
  }
}
