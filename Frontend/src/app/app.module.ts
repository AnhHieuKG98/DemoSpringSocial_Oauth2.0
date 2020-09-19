import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ToastrModule} from 'ngx-toastr';
import {ApiService} from './services/api.service';
import {MAT_DATE_LOCALE, MatFormFieldModule, MatIconModule, MatInputModule} from '@angular/material';
import {ApiresponInterceptor} from './interceptor/apirespon.interceptor';
import {TokenInterceptor} from './interceptor/token.interceptor';
import {AuthGuard} from './interceptor/auth.guard';
import {OauthService} from './services/oauth.service';
import {ChartsModule} from 'ng2-charts';
import {LoginComponent} from './views/account/login/login.component';
import {ForgotPasswordComponent} from './views/account/forgot-password/forgot-password.component';
import {ProfileComponent} from './views/account/profile/profile.component';
import {RegisterComponent} from './views/account/register/register.component';
import {CategoryComponent} from './views/management/category/category.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {BsModalService, ModalModule} from 'ngx-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ForgotPasswordComponent,
    ProfileComponent,
    RegisterComponent,
    CategoryComponent
],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ChartsModule,
    FormsModule,
    MatFormFieldModule,
    HttpClientModule,
    ToastrModule.forRoot({timeOut: 2000, progressBar: false, positionClass: 'toast-top-center'}),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatIconModule,
    NgxPaginationModule,
    MatInputModule,
    ModalModule.forRoot()
  ],
  providers: [
    ApiService, OauthService, AuthGuard,
    { provide: MAT_DATE_LOCALE, useValue: 'vi-VI'},
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ApiresponInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
