import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../../../services/employee.service';
import {ToastrService} from 'ngx-toastr';
import {User} from '../../../model/user';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  email: string;
  password: string;
  confirmPassword: string;

  constructor(private employeeService: EmployeeService,
              private toastr: ToastrService) { }

  ngOnInit() {
  }

  success(message: string) {
    this.toastr.success(message, '');
  }

  error(message: string) {
    this.toastr.error(message, 'Lỗi');
  }

  resetPassword() {
    console.log(this.password, this.confirmPassword);
    if (this.password.trim() !== this.confirmPassword.trim()) {
      this.error('Mật khẩu không khớp!');
    } else {
      const modelReset = new User(null, null, this.email, this.password);
      console.log('modelUpdatePassword', modelReset);
      this.employeeService.resetPasswordAPI(modelReset).subscribe(
        data => {
          if (data['data'] === 'SUCCESS') {
            this.success('Đổi mật khẩu thành công');
            window.location.replace('login');
          } else {
            this.error('Email không tồn tại');
          }
        }, error1 => {
          this.error('Đã xả ra lỗi');
        }
      );
    }
  }
}
