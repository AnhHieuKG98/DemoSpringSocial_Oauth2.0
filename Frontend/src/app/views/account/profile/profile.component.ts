import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {User} from '../../../model/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  formUser: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(this.currentUser);
    this.createForm();
  }

  createForm() {
    this.formUser = this.fb.group({
      firstName: [this.currentUser.firstName],
      lastName: [this.currentUser.lastName],
      phoneNumber: [this.currentUser.phoneNumber],
      manage: [this.currentUser.manageName],
      address: [this.currentUser.address],
      email: [this.currentUser.email],
      password: [this.currentUser.password],
      salary: [this.currentUser.salary],
      lastLogin: [this.currentUser.lastLogin]
    });
  }
}
