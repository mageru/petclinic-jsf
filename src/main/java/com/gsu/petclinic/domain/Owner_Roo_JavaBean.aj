// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gsu.petclinic.domain;

import com.gsu.petclinic.domain.Owner;
import com.gsu.petclinic.domain.Pet;
import java.util.Set;

privileged aspect Owner_Roo_JavaBean {
    
    public Set<Pet> Owner.getPets() {
        return this.pets;
    }
    
    public void Owner.setPets(Set<Pet> pets) {
        this.pets = pets;
    }
    
}
