package com.sabin.employeeapi.api;

import com.sabin.employeeapi.model.Employee;
import com.sabin.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //Get all the employee
    @GET("employees")
    Call<List<Employee>> getAllEmployee();

    //Get employee on the basis of id
    @GET("employee/{empID}")
    Call<Employee> getEmployeeById(@Path("empID") int empId);

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

    //Update employee on the basis of EmpID
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId, @Body EmployeeCUD emp);

    //Delete
    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee (@Path("empID") int empId);






}
