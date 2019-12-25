package com.sabin.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabin.employeeapi.api.EmployeeAPI;
import com.sabin.employeeapi.model.Employee;
import com.sabin.employeeapi.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private Button btnsearch, btnupdate, btndelete;
private EditText etEMPno;
private EditText etempname,etempsalary,etempage;
Retrofit retrofit;
EmployeeAPI employeeAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        btnsearch =findViewById(R.id.btnmanagesearh);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        etempname = findViewById(R.id.etmanageemployeename);
        etempsalary = findViewById(R.id.etmanageemployeesalary);
        etempage = findViewById(R.id.etmanageemployeeage);
        etEMPno = findViewById(R.id.etempno);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaddata();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });


    }

    private void deleteEmployee() {


    }

    private void updateEmployee() {
        CreateInstance();
        EmployeeCUD employee = new EmployeeCUD(
                etempname.getText().toString(),
                Float.parseFloat(etempsalary.getText().toString()),
                Integer.parseInt(etempage.getText().toString())
        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEMPno.getText().toString()), employee);


        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ManageActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(ManageActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loaddata() {

        CreateInstance();

        Call<Employee> listCall = employeeAPI.getEmployeeById(Integer.parseInt(etEMPno.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etempname.setText(response.body().getEmployee_name());
                etempsalary.setText(Float.toString(response.body().getEmployee_salary()));
                etempage.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(ManageActivity.this, "Error" + t.getMessage() , Toast.LENGTH_SHORT).show();


            }
        });



    }

    private void CreateInstance() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        employeeAPI = retrofit.create(EmployeeAPI.class);

    }
}
