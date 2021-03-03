package se.jocke.util;

import se.jocke.api.EmployeeModel;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class ObjectUtility {

    private List<Object> EmployeeAttributes;

    public List ObjectUtility(Employee employee) {
        EmployeeAttributes = new ArrayList<>();
        EmployeeAttributes.add(employee.getEmployeeId());
        EmployeeAttributes.add(employee.getFirstName());
        EmployeeAttributes.add(employee.getLastName());
        EmployeeAttributes.add(employee.getDepartmentId());
        EmployeeAttributes.add(employee.getFullTime());
        EmployeeAttributes.add(employee.getSalary());
        return EmployeeAttributes;
    }

    public List ObjectUtility(EmployeeDatabaseEntry employeeDatabaseEntry) {
        EmployeeAttributes = new ArrayList<>();
        EmployeeAttributes.add(employeeDatabaseEntry.getEmployeeId());
        EmployeeAttributes.add(employeeDatabaseEntry.getFirstName());
        EmployeeAttributes.add(employeeDatabaseEntry.getLastName());
        EmployeeAttributes.add(employeeDatabaseEntry.getDepartmentId());
        EmployeeAttributes.add(employeeDatabaseEntry.getFullTime());
        EmployeeAttributes.add(employeeDatabaseEntry.getSalary());
        return EmployeeAttributes;
    }

    public List ObjectUtility(EmployeeModel employeeModel) {
        EmployeeAttributes = new ArrayList<>();
        EmployeeAttributes.add(employeeModel.getEmployeeId());
        EmployeeAttributes.add(employeeModel.getFirstName());
        EmployeeAttributes.add(employeeModel.getLastName());
        EmployeeAttributes.add(employeeModel.getDepartmentId());
        EmployeeAttributes.add(employeeModel.getFullTime());
        EmployeeAttributes.add(employeeModel.getSalary());
        return EmployeeAttributes;
    }


}
