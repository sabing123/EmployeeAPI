package com.sabin.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sabin.employeeapi.api.EmployeeAPI;
import com.sabin.employeeapi.model.Employee;

import java.security.PrivateKey;

import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private EditText etEmpNo;
    private Button btnsearch;
    private TextView tvdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etEmpNo = findViewById(R.id.etsearch);
        tvdata = findViewById(R.id.tvdata);
        btnsearch = findViewById(R.id.btnsearh);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadData();

            }
        });
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeById(Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                Toast.makeText(SearchActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                String content = "";

                content += "ID : " + response.body().getId() + "\n";
                content += "Name : " + response.body().getEmployee_name() + "\n";
                content += "Age : " + response.body().getEmployee_age() + "\n";
                content += "Salary : " + response.body().getEmployee_salary() + "\n";

                tvdata.setText(content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(SearchActivity.this, "Not Found Emplyee ID", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
