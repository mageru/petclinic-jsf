package com.gsu.petclinic.domain;

import com.gsu.petclinic.reference.PetType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(sequenceName = "PET_SEQ", finders = { "findPetsByNameAndWeight", "findPetsByOwner", "findPetsBySendRemindersAndWeightLessThan", "findPetsByTypeAndNameLike" })
public class Pet {

    @NotNull
    private boolean sendReminders;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Min(0L)
    private Float weight;

    @ManyToOne
    private Owner owner;

    @NotNull
    @Enumerated
    private PetType type;
    
    private String ownerName;
    

	public String getOwnerName() {
		if (this.owner != null)
			return this.owner.getFirstName()+" "+this.owner.getLastName();
		else 
			return "None";
	}
	
	public String setOwnerName() {
		if (this.owner != null)
			return this.owner.getFirstName()+" "+this.owner.getLastName();
		else 
			return "None";
	}
    
    
}
