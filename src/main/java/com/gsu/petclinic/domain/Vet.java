package com.gsu.petclinic.domain;

import com.gsu.petclinic.reference.Specialty;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Vet extends AbstractPerson {

	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date employedSince;
	
    @Enumerated
    private Specialty specialty;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public static long countVets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Vet o", Long.class).getSingleResult();
    }

	public static List<Vet> findAllVets() {
        return entityManager().createQuery("SELECT o FROM Vet o", Vet.class).getResultList();
    }

	public static Vet findVet(Long id) {
        if (id == null) return null;
        return entityManager().find(Vet.class, id);
    }

	public static List<Vet> findVetEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Vet o", Vet.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public Vet merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Vet merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Date getEmployedSince() {
        return this.employedSince;
    }

	public void setEmployedSince(Date employedSince) {
        this.employedSince = employedSince;
    }

	public Specialty getSpecialty() {
        return this.specialty;
    }

	public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Vet fromJsonToVet(String json) {
        return new JSONDeserializer<Vet>().use(null, Vet.class).deserialize(json);
    }
    
    public static String toJsonArray(Collection<Vet> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Vet> fromJsonArrayToVets(String json) {
        return new JSONDeserializer<List<Vet>>().use(null, ArrayList.class).use("values", Vet.class).deserialize(json);
    }
}
