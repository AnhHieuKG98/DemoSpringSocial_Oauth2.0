export class User {
  id: number;
  code: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  phoneNumber?: number;
  manageCode: string;
  salary?: number;
  createDate?: string;
  address?: string;
  token: string;
  role: string;
  lastLogin: string;
  manageName: string;

  constructor(id?: number, code?: string, email?: string, password?: string, firstName?: string, lastName?: string, phoneNumber?: number, manageCode?: string,
              salary?: number, createDate?: string, address?: string, token?: string, role?: string, lastLogin?: string, manageName?: string){
    this.id = id;
    this.code = code;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.manageCode = manageCode;
    this.salary = salary;
    this.createDate = createDate;
    this.address = address;
    this.token = token;
    this.role = role;
    this.lastLogin = lastLogin;
    this.manageName = manageName;
  }
}
