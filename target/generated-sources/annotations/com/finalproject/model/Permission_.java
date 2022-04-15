package com.finalproject.model;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile SingularAttribute<Permission, PermissionType> permissionType;
	public static volatile SetAttribute<Permission, Role> roles;
	public static volatile SingularAttribute<Permission, String> name;
	public static volatile SingularAttribute<Permission, Integer> id;

	public static final String PERMISSION_TYPE = "permissionType";
	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String ID = "id";

}

