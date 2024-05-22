package com.binar.Batch7.Service;

import com.binar.Batch7.Entity.Employee;

import java.util.Map;

public interface EmployeeTrainingService {

    Map save(Employee request);
    Map edit(Employee request);
    Map delete(Employee request);
    Map list();

}

