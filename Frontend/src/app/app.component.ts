import {Component, DoCheck, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Oauth2';
  titleBtn: string;
  userCurrent: string;

  constructor(private toastr: ToastrService) {
    if (localStorage.getItem('session_token')) {
      this.titleBtn = 'Đăng xuất';
      const user = localStorage.getItem('currentUser');
      this.userCurrent = JSON.parse(user).email;
    } else {
      this.titleBtn = 'Đăng nhập';
      this.userCurrent = undefined;
    }
  }

  ngOnInit(): void {
  }

  getCategory() {
    if (localStorage.getItem('session_token')) {
      window.location.replace('/category');
    } else {
      this.toastr.info('Bạn cần phải đăng nhập để xem chức năng này');
    }
  }

  login() {
    if  (localStorage.getItem('session_token') != null) {
      localStorage.clear();
      this.toastr.info('Đăng xuất thành công');
      window.location.replace('/login');
    } else {
      window.location.replace('/login');
    }
  }

  register() {
    window.location.replace('/register');
  }

  forgotPassword() {
    window.location.replace('/forgot-password');
  }
}
