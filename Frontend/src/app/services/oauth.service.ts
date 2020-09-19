import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../model/user';
import {appConfig} from '../config/app.config';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OauthService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  private url = appConfig.apiLogin;

  constructor(private router: Router, private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': appConfig.contentType,
        'Authorization': appConfig.requestAuthorization
      })
    };
    let creds = 'username=' + username + '&password=' + password + '&grant_type=password';
    return this.http.post<any>(this.url, creds, httpOptions).pipe(map(token => {
      localStorage.setItem(appConfig.session, JSON.stringify(token));
      if (token && token.access_token) {
        var user: User = new User();
        user.id = token.userInfo.id;
        user.code = token.userInfo.code;
        user.email = token.userInfo.email;
        user.firstName = token.userInfo.firstName;
        user.lastName = token.userInfo.lastName;
        user.phoneNumber = token.userInfo.phoneNumber;
        user.address = token.userInfo.address;
        user.manageCode = token.userInfo.manageCode;
        user.salary = token.userInfo.salary;
        user.manageName = token.userInfo.manageName;
        user.lastLogin = token.userInfo.lastLogin;
        user.role = token.authorities;
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem(appConfig.currentUser, JSON.stringify(user));
        localStorage.setItem(appConfig.access_token, token.access_token);
        localStorage.setItem(appConfig.refresh_token, token.refresh_token);
        localStorage.setItem(appConfig.token_expired_time, '' + token.expires_in * 1000);
        localStorage.setItem(appConfig.token_get_time, '' + Date.now());
        this.currentUserSubject.next(user);
      }
      console.log('userInfo', user);
      return user;
    }));
  }

  logout() {
    localStorage.removeItem(appConfig.currentUser);
    localStorage.removeItem(appConfig.session);
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  public getToken(): string {
    return localStorage.getItem(appConfig.access_token);
  }

  public tokenExpired() {
    let tokenExpiredTime: number = Number.parseInt(localStorage.getItem(appConfig.token_expired_time), 0);
    let tokenGetTime: number = Number.parseInt(localStorage.getItem(appConfig.token_get_time), 0);
    if (tokenExpiredTime && tokenGetTime) {
      return (tokenGetTime + tokenExpiredTime) < Date.now();
    } else {
      return true;
    }
  }

  get accessToken(): any {
    return localStorage.getItem(appConfig.access_token);
  }
  set accessToken(value) {
    localStorage.setItem(appConfig.access_token, value);
  }
  get refreshToken(): any {
    return localStorage.getItem(appConfig.refresh_token);
  }

  set refreshToken(value) {
    localStorage.setItem(appConfig.refresh_token, value);
  }

  public doRefreshToken() {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': appConfig.contentType,
        'Authorization': appConfig.requestAuthorization,
        'Access-Control-Allow-Origin' : '*'
      })
    };

    let creds = 'refresh_token=' + this.refreshToken + '&grant_type=refresh_token';
    return this.http.put(appConfig.apiLogin, creds, httpOptions);
  }

  public isUserAuthenticated() {
    return !this.tokenExpired();
  }
}
