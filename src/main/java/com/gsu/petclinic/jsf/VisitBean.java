package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Pet;
import com.gsu.petclinic.domain.Vet;
import com.gsu.petclinic.domain.Visit;
import com.gsu.petclinic.jsf.converter.PetConverter;
import com.gsu.petclinic.jsf.converter.VetConverter;
import com.gsu.petclinic.jsf.util.MessageFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.LengthValidator;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "visitBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Visit.class, beanName = "visitBean")
public class VisitBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name = "Visits";

	private Visit visit;

	private List<Visit> allVisits;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("description");
        columns.add("petName");
        columns.add("visitDate");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Visit> getAllVisits() {
        return allVisits;
    }

	public void setAllVisits(List<Visit> allVisits) {
        this.allVisits = allVisits;
    }

	public String findAllVisits() {
        allVisits = Visit.findAllVisits();
        dataVisible = !allVisits.isEmpty();
        return null;
    }

	public boolean isDataVisible() {
        return dataVisible;
    }

	public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

	public HtmlPanelGrid getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }

	public void setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }

	public HtmlPanelGrid getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }

	public void setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }

	public HtmlPanelGrid getViewPanelGrid() {
        return populateViewPanel();
    }

	public void setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }

	public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel descriptionCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        descriptionCreateOutput.setFor("descriptionCreateInput");
        descriptionCreateOutput.setId("descriptionCreateOutput");
        descriptionCreateOutput.setValue("Description:");
        htmlPanelGrid.getChildren().add(descriptionCreateOutput);
        
        InputTextarea descriptionCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descriptionCreateInput.setId("descriptionCreateInput");
        descriptionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.description}", String.class));
        LengthValidator descriptionCreateInputValidator = new LengthValidator();
        descriptionCreateInputValidator.setMaximum(255);
        descriptionCreateInput.addValidator(descriptionCreateInputValidator);
        descriptionCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(descriptionCreateInput);
        
        Message descriptionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        descriptionCreateInputMessage.setId("descriptionCreateInputMessage");
        descriptionCreateInputMessage.setFor("descriptionCreateInput");
        descriptionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(descriptionCreateInputMessage);
        
        OutputLabel visitDateCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        visitDateCreateOutput.setFor("visitDateCreateInput");
        visitDateCreateOutput.setId("visitDateCreateOutput");
        visitDateCreateOutput.setValue("Visit Date:");
        htmlPanelGrid.getChildren().add(visitDateCreateOutput);
        
        Calendar visitDateCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        visitDateCreateInput.setId("visitDateCreateInput");
        visitDateCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.visitDate}", Date.class));
        visitDateCreateInput.setNavigator(true);
        visitDateCreateInput.setEffect("slideDown");
        visitDateCreateInput.setPattern("dd/MM/yyyy");
        visitDateCreateInput.setRequired(true);
        visitDateCreateInput.setMaxdate(new Date());
        htmlPanelGrid.getChildren().add(visitDateCreateInput);
        
        Message visitDateCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        visitDateCreateInputMessage.setId("visitDateCreateInputMessage");
        visitDateCreateInputMessage.setFor("visitDateCreateInput");
        visitDateCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(visitDateCreateInputMessage);
        
        OutputLabel petCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        petCreateOutput.setFor("petCreateInput");
        petCreateOutput.setId("petCreateOutput");
        petCreateOutput.setValue("Pet:");
        htmlPanelGrid.getChildren().add(petCreateOutput);
        
        AutoComplete petCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        petCreateInput.setId("petCreateInput");
        petCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.pet}", Pet.class));
        petCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{visitBean.completePet}", List.class, new Class[] { String.class }));
        petCreateInput.setDropdown(true);
        petCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "pet", String.class));
        petCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{pet.name} Owner:#{pet.ownerName}", String.class));
        petCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{pet}", Pet.class));
        petCreateInput.setConverter(new PetConverter());
        petCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(petCreateInput);
        
        Message petCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        petCreateInputMessage.setId("petCreateInputMessage");
        petCreateInputMessage.setFor("petCreateInput");
        petCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(petCreateInputMessage);
        
        OutputLabel vetCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        vetCreateOutput.setFor("vetCreateInput");
        vetCreateOutput.setId("vetCreateOutput");
        vetCreateOutput.setValue("Vet:");
        htmlPanelGrid.getChildren().add(vetCreateOutput);
        
        AutoComplete vetCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        vetCreateInput.setId("vetCreateInput");
        vetCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.vet}", Vet.class));
        vetCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{visitBean.completeVet}", List.class, new Class[] { String.class }));
        vetCreateInput.setDropdown(true);
        vetCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "vet", String.class));
        vetCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{vet.firstName} #{vet.lastName}", String.class));
        vetCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{vet}", Vet.class));
        vetCreateInput.setConverter(new VetConverter());
        vetCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(vetCreateInput);
        
        Message vetCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        vetCreateInputMessage.setId("vetCreateInputMessage");
        vetCreateInputMessage.setFor("vetCreateInput");
        vetCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(vetCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel descriptionEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        descriptionEditOutput.setFor("descriptionEditInput");
        descriptionEditOutput.setId("descriptionEditOutput");
        descriptionEditOutput.setValue("Description:");
        htmlPanelGrid.getChildren().add(descriptionEditOutput);
        
        InputTextarea descriptionEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descriptionEditInput.setId("descriptionEditInput");
        descriptionEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.description}", String.class));
        LengthValidator descriptionEditInputValidator = new LengthValidator();
        descriptionEditInputValidator.setMaximum(255);
        descriptionEditInput.addValidator(descriptionEditInputValidator);
        descriptionEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(descriptionEditInput);
        
        Message descriptionEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        descriptionEditInputMessage.setId("descriptionEditInputMessage");
        descriptionEditInputMessage.setFor("descriptionEditInput");
        descriptionEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(descriptionEditInputMessage);
        
        OutputLabel visitDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        visitDateEditOutput.setFor("visitDateEditInput");
        visitDateEditOutput.setId("visitDateEditOutput");
        visitDateEditOutput.setValue("Visit Date:");
        htmlPanelGrid.getChildren().add(visitDateEditOutput);
        
        Calendar visitDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        visitDateEditInput.setId("visitDateEditInput");
        visitDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.visitDate}", Date.class));
        visitDateEditInput.setNavigator(true);
        visitDateEditInput.setEffect("slideDown");
        visitDateEditInput.setPattern("dd/MM/yyyy");
        visitDateEditInput.setRequired(true);
        visitDateEditInput.setMaxdate(new Date());
        htmlPanelGrid.getChildren().add(visitDateEditInput);
        
        Message visitDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        visitDateEditInputMessage.setId("visitDateEditInputMessage");
        visitDateEditInputMessage.setFor("visitDateEditInput");
        visitDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(visitDateEditInputMessage);
        
        OutputLabel petEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        petEditOutput.setFor("petEditInput");
        petEditOutput.setId("petEditOutput");
        petEditOutput.setValue("Pet:");
        htmlPanelGrid.getChildren().add(petEditOutput);
        
        AutoComplete petEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        petEditInput.setId("petEditInput");
        petEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.pet}", Pet.class));
        petEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{visitBean.completePet}", List.class, new Class[] { String.class }));
        petEditInput.setDropdown(true);
        petEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "pet", String.class));
        petEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{pet.name} Owner:#{pet.ownerName}", String.class));
        petEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{pet}", Pet.class));
        petEditInput.setConverter(new PetConverter());
        petEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(petEditInput);
        
        Message petEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        petEditInputMessage.setId("petEditInputMessage");
        petEditInputMessage.setFor("petEditInput");
        petEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(petEditInputMessage);
        
        OutputLabel vetEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        vetEditOutput.setFor("vetEditInput");
        vetEditOutput.setId("vetEditOutput");
        vetEditOutput.setValue("Vet:");
        htmlPanelGrid.getChildren().add(vetEditOutput);
        
        AutoComplete vetEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        vetEditInput.setId("vetEditInput");
        vetEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.vet}", Vet.class));
        vetEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{visitBean.completeVet}", List.class, new Class[] { String.class }));
        vetEditInput.setDropdown(true);
        vetEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "vet", String.class));
        vetEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{vet.firstName} #{vet.lastName}", String.class));
        vetEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{vet}", Vet.class));
        vetEditInput.setConverter(new VetConverter());
        vetEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(vetEditInput);
        
        Message vetEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        vetEditInputMessage.setId("vetEditInputMessage");
        vetEditInputMessage.setFor("vetEditInput");
        vetEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(vetEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText descriptionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        descriptionLabel.setId("descriptionLabel");
        descriptionLabel.setValue("Description:");
        htmlPanelGrid.getChildren().add(descriptionLabel);
        
        InputTextarea descriptionValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descriptionValue.setId("descriptionValue");
        descriptionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.description}", String.class));
        descriptionValue.setReadonly(true);
        descriptionValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(descriptionValue);
        
        HtmlOutputText visitDateLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visitDateLabel.setId("visitDateLabel");
        visitDateLabel.setValue("Visit Date:");
        htmlPanelGrid.getChildren().add(visitDateLabel);
        
        HtmlOutputText visitDateValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        visitDateValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.visitDate}", Date.class));
        DateTimeConverter visitDateValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        visitDateValueConverter.setPattern("dd/MM/yyyy");
        visitDateValue.setConverter(visitDateValueConverter);
        htmlPanelGrid.getChildren().add(visitDateValue);
        
        HtmlOutputText petLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petLabel.setId("petLabel");
        petLabel.setValue("Pet:");
        htmlPanelGrid.getChildren().add(petLabel);
        
        HtmlOutputText petValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.pet}", Pet.class));
        petValue.setConverter(new PetConverter());
        htmlPanelGrid.getChildren().add(petValue);
        
        HtmlOutputText vetLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        vetLabel.setId("vetLabel");
        vetLabel.setValue("Vet:");
        htmlPanelGrid.getChildren().add(vetLabel);
        
        HtmlOutputText vetValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        vetValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{visitBean.visit.vet}", Vet.class));
        vetValue.setConverter(new VetConverter());
        htmlPanelGrid.getChildren().add(vetValue);
        
        return htmlPanelGrid;
    }

	public Visit getVisit() {
        if (visit == null) {
            visit = new Visit();
        }
        return visit;
    }

	public void setVisit(Visit visit) {
        this.visit = visit;
    }

	public List<Pet> completePet(String query) {
        List<Pet> suggestions = new ArrayList<Pet>();
        for (Pet pet : Pet.findAllPets()) {
            String petStr = String.valueOf(pet.getName() + " " +pet.getOwnerName());
            if (petStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(pet);
            }
        }
        return suggestions;
    }

	public List<Vet> completeVet(String query) {
        List<Vet> suggestions = new ArrayList<Vet>();
        for (Vet vet : Vet.findAllVets()) {
            String vetStr = String.valueOf(vet.getFirstName() +  " "  + vet.getLastName());
            if (vetStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(vet);
            }
        }
        return suggestions;
    }

	public String onEdit() {
        return null;
    }

	public boolean isCreateDialogVisible() {
        return createDialogVisible;
    }

	public void setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }

	public String displayList() {
        createDialogVisible = false;
        findAllVisits();
        return "visit";
    }

	public String displayCreateDialog() {
        visit = new Visit();
        createDialogVisible = true;
        return "visit";
    }

	public String persist() {
        String message = "";
        if (visit.getId() != null) {
            visit.merge();
            message = "message_successfully_updated";
        } else {
            visit.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Visit");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllVisits();
    }

	public String delete() {
        visit.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Visit");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllVisits();
    }

	public void reset() {
        visit = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
