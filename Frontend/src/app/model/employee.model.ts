export class EmployeeModel {
  id: number;
  code: string;
  email: string;
  password: string;
  firstName?: string;
  lastName?: string;
  gender: string;
  phoneNumber?: number;
  address?: string;
  manageCode?: string;
  salary: number;
  createDate: string;
  lastLogin: string;
  birth: string;

  // tslint:disable-next-line:max-line-length
  constructor(id: number, code: string, email: string, password: string, firstName: string, lastName: string, phoneNumber: number, address: string,
              managgeCode: string, salary: number, createDate: string, lastLogin: string, gender: string, birth: string) {
    this.id = id;
    this.code = code;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.manageCode = managgeCode;
    this.salary = salary;
    this.createDate = createDate;
    this.lastLogin = lastLogin;
    this.gender = gender;
    this.birth = birth;
  }
}
