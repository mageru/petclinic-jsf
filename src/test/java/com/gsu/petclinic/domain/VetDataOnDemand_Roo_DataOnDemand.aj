// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gsu.petclinic.domain;

import com.gsu.petclinic.domain.Vet;
import com.gsu.petclinic.domain.VetDataOnDemand;
import com.gsu.petclinic.reference.Specialty;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect VetDataOnDemand_Roo_DataOnDemand {
    
    declare @type: VetDataOnDemand: @Component;
    
    private Random VetDataOnDemand.rnd = new SecureRandom();
    
    private List<Vet> VetDataOnDemand.data;
    
    public Vet VetDataOnDemand.getNewTransientVet(int index) {
        Vet obj = new Vet();
        setAddress(obj, index);
        setBirthDay(obj, index);
        setCity(obj, index);
        setEmail(obj, index);
        setEmployedSince(obj, index);
        setFirstName(obj, index);
        setLastName(obj, index);
        setSpecialty(obj, index);
        setTelephone(obj, index);
        return obj;
    }
    
    public void VetDataOnDemand.setAddress(Vet obj, int index) {
        String address = "address_" + index;
        if (address.length() > 50) {
            address = address.substring(0, 50);
        }
        obj.setAddress(address);
    }
    
    public void VetDataOnDemand.setBirthDay(Vet obj, int index) {
        Date birthDay = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setBirthDay(birthDay);
    }
    
    public void VetDataOnDemand.setCity(Vet obj, int index) {
        String city = "city_" + index;
        if (city.length() > 30) {
            city = city.substring(0, 30);
        }
        obj.setCity(city);
    }
    
    public void VetDataOnDemand.setEmail(Vet obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 30) {
            email = email.substring(0, 30);
        }
        obj.setEmail(email);
    }
    
    public void VetDataOnDemand.setEmployedSince(Vet obj, int index) {
        Calendar employedSince = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
        obj.setEmployedSince(employedSince);
    }
    
    public void VetDataOnDemand.setFirstName(Vet obj, int index) {
        String firstName = "firstName_" + index;
        if (firstName.length() > 30) {
            firstName = firstName.substring(0, 30);
        }
        obj.setFirstName(firstName);
    }
    
    public void VetDataOnDemand.setLastName(Vet obj, int index) {
        String lastName = "lastName_" + index;
        if (lastName.length() > 30) {
            lastName = lastName.substring(0, 30);
        }
        obj.setLastName(lastName);
    }
    
    public void VetDataOnDemand.setSpecialty(Vet obj, int index) {
        Specialty specialty = Specialty.class.getEnumConstants()[0];
        obj.setSpecialty(specialty);
    }
    
    public void VetDataOnDemand.setTelephone(Vet obj, int index) {
        String telephone = "telephone_" + index;
        obj.setTelephone(telephone);
    }
    
    public Vet VetDataOnDemand.getSpecificVet(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Vet obj = data.get(index);
        Long id = obj.getId();
        return Vet.findVet(id);
    }
    
    public Vet VetDataOnDemand.getRandomVet() {
        init();
        Vet obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Vet.findVet(id);
    }
    
    public boolean VetDataOnDemand.modifyVet(Vet obj) {
        return false;
    }
    
    public void VetDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Vet.findVetEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Vet' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Vet>();
        for (int i = 0; i < 10; i++) {
            Vet obj = getNewTransientVet(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
