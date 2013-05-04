package com.gsu.petclinic.domain;

import com.gsu.petclinic.reference.PetType;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = Pet.class)
public class PetDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Pet> data;

	@Autowired
    OwnerDataOnDemand ownerDataOnDemand;

	public Pet getNewTransientPet(int index) {
        Pet obj = new Pet();
        setName(obj, index);
        setOwnerName(obj, index);
        setSendReminders(obj, index);
        setType(obj, index);
        setWeight(obj, index);
        return obj;
    }

	public void setName(Pet obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public void setOwnerName(Pet obj, int index) {
        String ownerName = "ownerName_" + index;
        obj.setOwnerName(ownerName);
    }

	public void setSendReminders(Pet obj, int index) {
        Boolean sendReminders = true;
        obj.setSendReminders(sendReminders);
    }

	public void setType(Pet obj, int index) {
        PetType type = PetType.class.getEnumConstants()[0];
        obj.setType(type);
    }

	public void setWeight(Pet obj, int index) {
        Float weight = new Integer(index).floatValue();
        obj.setWeight(weight);
    }

	public Pet getSpecificPet(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Pet obj = data.get(index);
        Long id = obj.getId();
        return Pet.findPet(id);
    }

	public Pet getRandomPet() {
        init();
        Pet obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Pet.findPet(id);
    }

	public boolean modifyPet(Pet obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Pet.findPetEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Pet' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Pet>();
        for (int i = 0; i < 10; i++) {
            Pet obj = getNewTransientPet(i);
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
