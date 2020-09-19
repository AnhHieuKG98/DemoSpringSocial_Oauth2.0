import {Component, OnInit, TemplateRef} from '@angular/core';
import {CategoryModel} from '../../../model/category.model';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {CategoryService} from '../../../services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  listCate: CategoryModel[];
  currentP = 1;
  pageSize = 10;
  private modalRef: BsModalRef;
  categoryId: number;
  categoryName: string;
  btn: boolean;
  check: number;
  click: boolean;
  cateId;

  constructor(private cateService: CategoryService,
              private modalService: BsModalService,
              private toastr: ToastrService) {
    this.getAllCate();
  }

  ngOnInit() {
  }

  onBack() {
    this.modalRef.hide();
  }

  openConfirm(pobjTemplate: TemplateRef<any>, id: number) {
    this.categoryId = id;
    this.modalRef = this.modalService.show(pobjTemplate, {
        ignoreBackdropClick: true
      }
    );
  }

  openInsert(pobjTemplate: TemplateRef<any>) {
    this.modalRef = this.modalService.show(pobjTemplate, {
        ignoreBackdropClick: true
      }
    );
  }

  getAllCate() {
    this.cateService.getAllCateAPI().subscribe(
      data => {
        this.listCate = data['data'];
      }
    );
  }

  removeCategory() {
    const modelDelete = new CategoryModel(this.categoryId);
    console.log('ID delete category', modelDelete);
    this.cateService.deleteCate(modelDelete).subscribe(
      data => {
        if (data['code'] === 200) {
          if (data['data'] === 'SUCCESS') {
            this.notiSuccess('Xóa thành công');
          } else {
            this.notiError('Xóa thất bại');
          }
        } else {
          this.notiError('Đã xảy ra lỗi');
        }
        this.onBack();
        this.getAllCate();
      }
    );
  }

  checkSpace() {
    if (this.categoryName.trim() === '') {
      this.btn = true;
      this.check = 1;
    } else {
      this.btn = false;
    }
  }

  pageChange(event) {
    this.currentP = 1;
  }

  notiSuccess(message: string) {
    this.toastr.success(message);
  }

  notiError(message: string) {
    this.toastr.error(message);
  }

  insertCate() {
    const modelInsert = new CategoryModel(null, null, this.categoryName);
    console.log('model Category', modelInsert);
    this.cateService.insertCate(modelInsert).subscribe(
      data => {
        if (data['code'] === 200) {
          if (data['data'] === 'SUCCESS') {
            this.notiSuccess('Thêm mới thành công');
          } else {
            this.notiError('Thêm mới thất bại');
          }
        } else {
          this.notiError('Đã xảy ra lỗi');
        }
        this.onBack();
        this.getAllCate();
        this.categoryName = '';
      }
    );
  }

  updateCate() {
    const modelUpdate = new CategoryModel(this.cateId, null, this.categoryName);
    console.log('data update category', modelUpdate);
    this.cateService.updateCateAPI(modelUpdate).subscribe(
      data => {
        if (data['code'] === 200) {
          if (data['data'] === 'SUCCESS') {
            this.notiSuccess('Cập nhật thành công');
          } else {
            this.notiError('Cập nhật thất bại');
          }
        } else {
          this.notiError('Đã xảy ra lỗi');
        }
        this.onBack();
        this.getAllCate();
        this.click = false;
        this.categoryName = '';
      }
    );
  }

  getData(id: number, cateName: string) {
    this.click = true;
    this.cateId = id;
    this.categoryName = cateName;
  }

}
