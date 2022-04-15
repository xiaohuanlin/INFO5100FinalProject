package com.finalproject.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Organization.class)
public abstract class Organization_ {

	public static volatile SingularAttribute<Organization, Enterprise> enterprise;
	public static volatile ListAttribute<Organization, Role> roles;
	public static volatile SingularAttribute<Organization, String> name;
	public static volatile SingularAttribute<Organization, Integer> id;

	public static final String ENTERPRISE = "enterprise";
	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String ID = "id";

}

