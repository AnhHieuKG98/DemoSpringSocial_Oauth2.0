import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user';
import {appConfig} from '../config/app.config';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  insertEmployeeAPI(employee: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Connection': 'keep-alive',
        'Content-Type': 'application/json',
      })
    };
    return this.http.post(appConfig.insert_employee_API, employee, httpOptions);
  }

  resetPasswordAPI(employee: User) {
    return this.http.post(appConfig.reset_password_API, employee);
  }
}
