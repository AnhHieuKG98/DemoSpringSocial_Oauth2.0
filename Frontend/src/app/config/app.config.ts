const URL = 'http://localhost:8082';
const URL_BASE = URL + '/api';

export const appConfig = {
  apiLogin: URL + '/oauth/token',
  contentType: 'application/x-www-form-urlencoded',
  requestAuthorization: 'Basic ZGV2Z2xhbi1jbGllbnQ6Y2h1bmdwaHVj',
  session: 'session_token',
  currentUser: 'currentUser',
  access_token: 'access_token',
  refresh_token: 'refresh_token',
  token_expired_time: 'token_expired_time',
  token_get_time: 'token_get_time',
  facebook_login_api: URL + '/facebook/facebookGenerateUrl',
  access_token_FB_API: URL + '/facebook/facebook',
  user_data_facebook_API: URL + '/facebook/getUserData',
  get_all_cate_API: URL_BASE + '/category/getAll',
  delete_category_API: URL_BASE + '/category/delete',
  insert_category_API: URL_BASE + '/category/insert',
  update_category_API: URL_BASE + '/category/update',
  insert_employee_API: URL_BASE + '/employee/newEmployee',
  get_all_employee_API: URL_BASE + '/employee/getEmployee',
  get_manage_API: URL_BASE + '/employee/getManage',
  reset_password_API: URL_BASE + '/employee/reset-pass',
  delete_employee_API: URL_BASE + '/employee/delete',
  search_employee_API: URL_BASE + '/employee/search'
};
