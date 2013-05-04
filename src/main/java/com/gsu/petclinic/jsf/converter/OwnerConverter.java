package com.gsu.petclinic.jsf.converter;

import com.gsu.petclinic.domain.Owner;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("com.gsu.petclinic.jsf.converter.OwnerConverter")
@RooJsfConverter(entity = Owner.class)
public class OwnerConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Owner.findOwner(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Owner ? ((Owner) value).getId().toString() : "";
    }
}
