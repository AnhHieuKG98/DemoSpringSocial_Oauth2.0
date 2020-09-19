import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {User} from '../../../model/user';
import {ToastrService} from 'ngx-toastr';
import {EmployeeService} from '../../../services/employee.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  formRegister: FormGroup;
  listManage: User[];
  private modalRef: BsModalRef;

  constructor(private formBuilder: FormBuilder,
              private toastr: ToastrService,
              private modalService: BsModalService,
              private employeeService: EmployeeService) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
    this.formRegister = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      password: [''],
      confirmPassword: [''],
      phoneNumber: [''],
      manage: [null]
    });
  }

  register() {
    const firstName = this.formRegister.controls['firstName'].value === '' ? null :
      this.formRegister.controls['firstName'].value;
    const lastName = this.formRegister.controls['lastName'].value === '' ? null :
      this.formRegister.controls['lastName'].value;
    const email = this.formRegister.controls['email'].value === '' ? null :
      this.formRegister.controls['email'].value;
    const password = this.formRegister.controls['password'].value === '' ? null :
      this.formRegister.controls['password'].value;
    const phoneNumber = this.formRegister.controls['phoneNumber'].value === '' ? null :
      this.formRegister.controls['phoneNumber'].value;

    if (this.formRegister.controls['password'].value !== this.formRegister.controls['confirmPassword'].value) {
      this.error('Mật khẩu không khớp');
    } else {
      const modelUser = new User(null, null, email, password, firstName, lastName,
        phoneNumber, this.formRegister.controls['manage'].value, null, null, null, null, null, null);
      console.log('createEmployee', modelUser);
      this.employeeService.insertEmployeeAPI(modelUser).subscribe(
        data => {
          if (data['code'] === 200) {
            if (data['data'] === 'SUCCESS') {
              this.success('Đăng kí thành công');
              window.location.replace('/login');
            } else {
              this.error('Tài khoản đã tồn tại');
            }
          } else {
            this.error('Đã xảy ra lỗi');
          }
        }
      );
    }
    this.onBack();
  }

  success(message: string) {
    this.toastr.success(message, '');
  }

  error(message: string) {
    this.toastr.error(message, 'Lỗi');
  }

  onBack() {
    this.modalRef.hide();
  }

  openConfirm(pobjTemplate: TemplateRef<any>) {
    this.modalRef = this.modalService.show(pobjTemplate, {
        ignoreBackdropClick: true
      }
    );
  }
}
