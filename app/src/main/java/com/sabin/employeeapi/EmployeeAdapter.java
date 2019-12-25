package com.sabin.employeeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabin.employeeapi.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {


    Context mContext;
    List<Employee> employeeList;

           public EmployeeAdapter(Context mContext, List<Employee> employeeList){
               this.mContext = mContext;
               this.employeeList = employeeList;
           }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeeview,parent, false);

       return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {

               Employee employee = employeeList.get(position);
               holder.name.setText(employee.getEmployee_name());
               holder.salary.setText(Float.toString(employee.getEmployee_salary()));
               holder.age.setText(Integer.toString(employee.getEmployee_age()));

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView name, salary, age;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.ename);
            salary = itemView.findViewById(R.id.esalary);
            age = itemView.findViewById(R.id.eage);

        }
    }

}