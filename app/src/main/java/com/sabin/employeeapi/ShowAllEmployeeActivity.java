package com.sabin.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sabin.employeeapi.api.EmployeeAPI;
import com.sabin.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowAllEmployeeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_employee);
        recyclerView = findViewById(R.id.recyclerview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<List<Employee>> listCall = employeeAPI.getAllEmployee();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ShowAllEmployeeActivity.this, "Code Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeesList = response.body();
                for (Employee employee : employeesList) {
                    String data = " ";
                    data += "Employee Name : " + employee.getEmployee_name() + "/n";
                    data += "Employee Salary : " + employee.getEmployee_salary() + "/n";
                    data += "Employee Age : " + employee.getEmployee_age() + "/n";
                    data += "------------------------------------------------------";

                    EmployeeAdapter employeeAdapter = new EmployeeAdapter(getBaseContext(), employeesList);
                    recyclerView.setAdapter(employeeAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                    Log.d("data", "onResponse: " + data);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Mero msg", "Onfailure" + t.getLocalizedMessage());
                Toast.makeText(ShowAllEmployeeActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
