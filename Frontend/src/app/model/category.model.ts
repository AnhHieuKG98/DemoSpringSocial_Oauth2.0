export class CategoryModel {
  id: number;
  createDate: string;
  categoryName: string;

  constructor(id?: number, createDate?: string, categoryName?: string) {
    this.id = id;
    this.createDate = createDate;
    this.categoryName = categoryName;
  }
}
