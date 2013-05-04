package com.gsu.petclinic.jsf.converter;

import com.gsu.petclinic.domain.Pet;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@FacesConverter("com.gsu.petclinic.jsf.converter.PetConverter")
@Configurable
@RooJsfConverter(entity = Pet.class)
public class PetConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Pet.findPet(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Pet ? ((Pet) value).getId().toString() : "";
    }
}
