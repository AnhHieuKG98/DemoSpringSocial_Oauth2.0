import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {OauthService} from '../services/oauth.service';
import {Observable} from 'rxjs';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public auth: OauthService,
              public injector: Injector) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let requestOption: any = {};
    let access_token = this.auth.getToken();
    // if(this.auth.tokenExpired())
    // {
    //     this.auth.refreshToken();
    // }
    if (req.url.match(/oauth\/token/)) {
      return next.handle(req);
    }
    if (access_token) {
      // let translate = this.injector.get(TranslateService);
      // let lang = '';
      // if(translate!=undefined)
      // {
      //   lang = translate.currentLang;
      // }
      // lang = localStorage.getItem(appConfig.user_default_language);
      // if(lang==undefined)
      // {
      //   lang = appConfig.defaultLanguage;
      // }
      // if(lang==undefined)
      // {
      //   lang = '';
      // }
      requestOption.setHeaders = {
        Authorization: 'Bearer ' + access_token,
        'Access-Control-Allow-Origin' : '*',
        // 'Accept-Language':lang
      };
    }

    req = req.clone(requestOption);
    return next.handle(req);
  }
}
