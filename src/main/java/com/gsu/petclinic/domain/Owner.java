package com.gsu.petclinic.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Owner extends AbstractPerson {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<Pet>();

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Set<Pet> getPets() {
        return this.pets;
    }

	public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

	public static long countOwners() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Owner o", Long.class).getSingleResult();
    }

	public static List<Owner> findAllOwners() {
        return entityManager().createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
    }

	public static Owner findOwner(Long id) {
        if (id == null) return null;
        return entityManager().find(Owner.class, id);
    }

	public static List<Owner> findOwnerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Owner o", Owner.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public Owner merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Owner merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}
	
	public static Owner fromJsonToOwner(String json) {
        return new JSONDeserializer<Owner>().use(null, Owner.class).deserialize(json);
    }
    
    public static String toJsonArray(Collection<Owner> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Owner> fromJsonArrayToOwners(String json) {
        return new JSONDeserializer<List<Owner>>().use(null, ArrayList.class).use("values", Owner.class).deserialize(json);
    }
}
