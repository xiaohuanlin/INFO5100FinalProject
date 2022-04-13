package com.finalproject.model;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.BitSet;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile SetAttribute<Permission, Role> roles;
	public static volatile SingularAttribute<Permission, String> className;
	public static volatile SingularAttribute<Permission, Integer> id;
	public static volatile SingularAttribute<Permission, BitSet> bitset;

	public static final String ROLES = "roles";
	public static final String CLASS_NAME = "className";
	public static final String ID = "id";
	public static final String BITSET = "bitset";

}

