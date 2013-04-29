package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Visit;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Visit.class, beanName = "visitBean")
public class VisitBean {
}
