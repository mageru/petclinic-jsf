package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Vet;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Vet.class, beanName = "vetBean")
public class VetBean {
}
