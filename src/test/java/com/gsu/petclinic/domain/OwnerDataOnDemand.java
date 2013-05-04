package com.gsu.petclinic.domain;

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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Component
@Configurable
@RooDataOnDemand(entity = Owner.class)
public class OwnerDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Owner> data;

	public Owner getNewTransientOwner(int index) {
        Owner obj = new Owner();
        setAddress(obj, index);
        setBirthDay(obj, index);
        setCity(obj, index);
        setEmail(obj, index);
        setFirstName(obj, index);
        setLastName(obj, index);
        setTelephone(obj, index);
        return obj;
    }

	public void setAddress(Owner obj, int index) {
        String address = "address_" + index;
        if (address.length() > 50) {
            address = address.substring(0, 50);
        }
        obj.setAddress(address);
    }

	public void setBirthDay(Owner obj, int index) {
        Date birthDay = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setBirthDay(birthDay);
    }

	public void setCity(Owner obj, int index) {
        String city = "city_" + index;
        if (city.length() > 30) {
            city = city.substring(0, 30);
        }
        obj.setCity(city);
    }

	public void setEmail(Owner obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 30) {
            email = email.substring(0, 30);
        }
        obj.setEmail(email);
    }

	public void setFirstName(Owner obj, int index) {
        String firstName = "firstName_" + index;
        if (firstName.length() > 30) {
            firstName = firstName.substring(0, 30);
        }
        obj.setFirstName(firstName);
    }

	public void setLastName(Owner obj, int index) {
        String lastName = "lastName_" + index;
        if (lastName.length() > 30) {
            lastName = lastName.substring(0, 30);
        }
        obj.setLastName(lastName);
    }

	public void setTelephone(Owner obj, int index) {
        String telephone = "telephone_" + index;
        obj.setTelephone(telephone);
    }

	public Owner getSpecificOwner(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Owner obj = data.get(index);
        Long id = obj.getId();
        return Owner.findOwner(id);
    }

	public Owner getRandomOwner() {
        init();
        Owner obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Owner.findOwner(id);
    }

	public boolean modifyOwner(Owner obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Owner.findOwnerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Owner' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Owner>();
        for (int i = 0; i < 10; i++) {
            Owner obj = getNewTransientOwner(i);
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
