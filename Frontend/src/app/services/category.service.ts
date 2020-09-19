import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CategoryModel} from '../model/category.model';
import {Observable} from 'rxjs';
import {appConfig} from '../config/app.config';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  getAllCateAPI(): Observable<CategoryModel[]> {
    return this.http.get<CategoryModel[]>(appConfig.get_all_cate_API);
  }

  deleteCate(category: CategoryModel) {
    return this.http.post(appConfig.delete_category_API, category);
  }

  insertCate(category: CategoryModel) {
    return this.http.post(appConfig.insert_category_API, category);
  }

  updateCateAPI(category: CategoryModel) {
    return this.http.post(appConfig.update_category_API, category);
  }
}
