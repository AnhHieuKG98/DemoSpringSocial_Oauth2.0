package com.doan.admin.helper;

public class ApiResponse {

    private int mintCode;
    private Boolean mblnStatus;
    private String mstrErrors;
    private Object data;
    private Integer mintPage;
    private Integer mintPageSize;
    private Integer mintTotalRow;

    public ApiResponse(int code, String errors, Object data) {
        super();
        this.mintCode = code;
        this.mstrErrors = errors;
        this.data = data;
    }

    public ApiResponse(int code, String errors) {
        super();
        this.mintCode = code;
        this.mstrErrors = errors;
    }

    public ApiResponse() {
        // TODO Auto-generated constructor stub
        super();
    }

    public Boolean getStatus() {
        return mblnStatus;
    }

    public void setStatus(Boolean status) {
        this.mblnStatus = status;
    }

    public int getCode() {
        return mintCode;
    }

    public void setCode(int code) {
        this.mintCode = code;
    }

    public String getErrors() {
        return mstrErrors;
    }

    public void setErrors(String errors) {
        this.mstrErrors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPage() {
        return mintPage;
    }

    public void setPage(Integer page) {
        this.mintPage = page;
    }

    public Integer getPageSize() {
        return mintPageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.mintPageSize = pageSize;
    }

    public Integer getTotalRow() {
        return mintTotalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.mintTotalRow = totalRow;
    }

    public static ApiResponse build(int code, boolean status,String errors, Object data)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setStatus(status);
        apiResponse.setData(data);
        apiResponse.setErrors(errors);
        return apiResponse;
    }
}
