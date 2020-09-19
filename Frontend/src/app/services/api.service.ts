import { Injectable } from '@angular/core';
import {appConfig} from '../config/app.config';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  loginFacebookAPI() {
    return this.http.get(appConfig.facebook_login_api);
  }

  accessTokenFbAPI(code: string) {
    return this.http.get(appConfig.access_token_FB_API + '?code' + code);
  }

  getUserData(userToken: string) {
    return this.http.get(appConfig.user_data_facebook_API + '/' + userToken + '/id,name,birthday,gender,address,email');
  }
}
