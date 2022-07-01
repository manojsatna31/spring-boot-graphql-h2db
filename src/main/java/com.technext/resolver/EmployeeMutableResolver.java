package com.technext.resolver;

import com.technext.domain.Department;
import com.technext.domain.Employee;
import com.technext.domain.EmployeeInput;
import com.technext.domain.Organization;
import com.technext.repository.DepartmentRepository;
import com.technext.repository.EmployeeRepository;
import com.technext.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMutableResolver implements GraphQLMutationResolver {

	DepartmentRepository departmentRepository;
	EmployeeRepository employeeRepository;
	OrganizationRepository organizationRepository;

	EmployeeMutableResolver(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
		this.organizationRepository = organizationRepository;
	}

	public Employee newEmployee(EmployeeInput employeeInput) {
		Department department = departmentRepository.findById(employeeInput.getDepartmentId()).get();
		Organization organization = organizationRepository.findById(employeeInput.getOrganizationId()).get();
		return employeeRepository.save(new Employee(null, employeeInput.getFirstName(), employeeInput.getLastName(),
				employeeInput.getPosition(), employeeInput.getAge(), employeeInput.getSalary(),
				department, organization));
	}

}
