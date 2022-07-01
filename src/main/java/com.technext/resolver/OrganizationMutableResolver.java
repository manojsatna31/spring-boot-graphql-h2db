package com.technext.resolver;

import com.technext.domain.Organization;
import com.technext.domain.OrganizationInput;
import com.technext.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMutableResolver implements GraphQLMutationResolver {

	OrganizationRepository repository;

	OrganizationMutableResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Organization newOrganization(OrganizationInput organizationInput) {
		return repository.save(new Organization(null, organizationInput.getName(), null, null));
	}

}
