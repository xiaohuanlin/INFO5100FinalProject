package com.finalproject.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Enterprise.class)
public abstract class Enterprise_ {

	public static volatile SingularAttribute<Enterprise, String> name;
	public static volatile ListAttribute<Enterprise, Organization> organizations;
	public static volatile SingularAttribute<Enterprise, Integer> id;

	public static final String NAME = "name";
	public static final String ORGANIZATIONS = "organizations";
	public static final String ID = "id";

}

