package com.technext.resolver;

import com.technext.domain.Department;
import com.technext.domain.DepartmentInput;
import com.technext.domain.Organization;
import com.technext.repository.DepartmentRepository;
import com.technext.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMutableResolver implements GraphQLMutationResolver {

	DepartmentRepository departmentRepository;
	OrganizationRepository organizationRepository;

	DepartmentMutableResolver(DepartmentRepository departmentRepository, OrganizationRepository organizationRepository) {
		this.departmentRepository = departmentRepository;
		this.organizationRepository = organizationRepository;
	}

	public Department newDepartment(DepartmentInput departmentInput) {
		Organization organization = organizationRepository.findById(departmentInput.getOrganizationId()).get();
		return departmentRepository.save(new Department(null, departmentInput.getName(), null, organization));
	}

}
