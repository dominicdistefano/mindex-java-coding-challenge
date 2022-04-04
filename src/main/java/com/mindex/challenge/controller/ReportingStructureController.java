package com.mindex.challenge.controller;
import java.util.ArrayList;
import java.util.List;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/reportingstructure/{id}")
    public ReportingStructure read(@PathVariable String id){
        LOG.debug("Received ReportingStructure get request for id [{}]", id);

        Employee employee = employeeService.read(id);
        int numOfReports = CalculateNumberOfReports(employee, new ArrayList<Employee>());
        
        return new ReportingStructure(employee, numOfReports);
    }

    private int CalculateNumberOfReports(Employee employee, ArrayList<Employee> visitedEmployees){
        int numOfReports = 0;
        List<Employee> directReports = employee.getDirectReports();
        
        if(directReports != null){
            for(Employee reporter: directReports){
                //This ensures we will not count the same reporter twice.
                //Also prevents infinite recursion if two people report to eachother.
                if(!visitedEmployees.contains(reporter)){
                    visitedEmployees.add(reporter);
                    numOfReports += CalculateNumberOfReports(reporter, visitedEmployees);
                    numOfReports += 1;
                }
            }
        }

        return numOfReports;
    }
}
