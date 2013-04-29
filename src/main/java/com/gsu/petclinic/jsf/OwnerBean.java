package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Owner;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Owner.class, beanName = "ownerBean")
public class OwnerBean {
}
