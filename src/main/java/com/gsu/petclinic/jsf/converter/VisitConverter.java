package com.gsu.petclinic.jsf.converter;

import com.gsu.petclinic.domain.Visit;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("com.gsu.petclinic.jsf.converter.VisitConverter")
@RooJsfConverter(entity = Visit.class)
public class VisitConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Visit.findVisit(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Visit ? ((Visit) value).getId().toString() : "";
    }
}
