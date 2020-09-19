import {Component, NgZone, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {first} from 'rxjs/operators';
import {OauthService} from '../../../services/oauth.service';
import {ApiService} from '../../../services/api.service';
import {HttpClient} from '@angular/common/http';
import {appConfig} from '../../../config/app.config';
import {User} from '../../../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isLoginFail = false;
  loginForm: FormGroup;
  urlFacebook: string;

  user: User = new User();
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', Validators.required);
  hide: boolean;

  constructor(private router: Router,
              private oauthService: OauthService,
              private fb: FormBuilder,
              private zone: NgZone,
              private apiService: ApiService,
              private toast: ToastrService,
              private http: HttpClient) {
    this.hide = true;
  }

  ngOnInit() {
  }

  login() {
    this.oauthService.login(this.email.value, this.password.value).pipe(first())
      .subscribe(
        () => {
          this.success('Đăng nhập thành công');
          window.location.replace('/category');
        },
        error => {
          this.isLoginFail = true;
          this.error('Đã xảy ra lỗi! Vui lòng thử lại sau.');
        }
      );
  }

  success(message: string) {
    this.toast.success(message, 'Thông báo');
  }

  error(message: string) {
    this.toast.error(message, 'Lỗi');
  }
}
