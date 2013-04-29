package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Pet;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Pet.class, beanName = "petBean")
public class PetBean {
}
