// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gsu.petclinic.domain;

import com.gsu.petclinic.domain.Visit;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Visit_Roo_Finder {
    
    public static TypedQuery<Visit> Visit.findVisitsByDescriptionAndVisitDate(String description, Date visitDate) {
        if (description == null || description.length() == 0) throw new IllegalArgumentException("The description argument is required");
        if (visitDate == null) throw new IllegalArgumentException("The visitDate argument is required");
        EntityManager em = Visit.entityManager();
        TypedQuery<Visit> q = em.createQuery("SELECT o FROM Visit AS o WHERE o.description = :description AND o.visitDate = :visitDate", Visit.class);
        q.setParameter("description", description);
        q.setParameter("visitDate", visitDate);
        return q;
    }
    
    public static TypedQuery<Visit> Visit.findVisitsByDescriptionLike(String description) {
        if (description == null || description.length() == 0) throw new IllegalArgumentException("The description argument is required");
        description = description.replace('*', '%');
        if (description.charAt(0) != '%') {
            description = "%" + description;
        }
        if (description.charAt(description.length() - 1) != '%') {
            description = description + "%";
        }
        EntityManager em = Visit.entityManager();
        TypedQuery<Visit> q = em.createQuery("SELECT o FROM Visit AS o WHERE LOWER(o.description) LIKE LOWER(:description)", Visit.class);
        q.setParameter("description", description);
        return q;
    }
    
    public static TypedQuery<Visit> Visit.findVisitsByVisitDateBetween(Date minVisitDate, Date maxVisitDate) {
        if (minVisitDate == null) throw new IllegalArgumentException("The minVisitDate argument is required");
        if (maxVisitDate == null) throw new IllegalArgumentException("The maxVisitDate argument is required");
        EntityManager em = Visit.entityManager();
        TypedQuery<Visit> q = em.createQuery("SELECT o FROM Visit AS o WHERE o.visitDate BETWEEN :minVisitDate AND :maxVisitDate", Visit.class);
        q.setParameter("minVisitDate", minVisitDate);
        q.setParameter("maxVisitDate", maxVisitDate);
        return q;
    }
    
}
