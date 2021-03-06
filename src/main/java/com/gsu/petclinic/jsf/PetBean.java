package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Owner;
import com.gsu.petclinic.domain.Pet;
import com.gsu.petclinic.jsf.converter.OwnerConverter;
import com.gsu.petclinic.jsf.util.MessageFactory;
import com.gsu.petclinic.reference.PetType;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.validator.DoubleRangeValidator;
import javax.faces.validator.LengthValidator;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "petBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Pet.class, beanName = "petBean")
public class PetBean implements Serializable {

	private String name = "Pets";

	private Pet pet;

	private List<Pet> allPets;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("ownerName");
        columns.add("name");
        columns.add("weight");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Pet> getAllPets() {
        return allPets;
    }

	public void setAllPets(List<Pet> allPets) {
        this.allPets = allPets;
    }

	public String findAllPets() {
        allPets = Pet.findAllPets();
        dataVisible = !allPets.isEmpty();
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
        
        /**
        OutputLabel ownerNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        ownerNameCreateOutput.setFor("ownerNameCreateInput");
        ownerNameCreateOutput.setId("ownerNameCreateOutput");
        ownerNameCreateOutput.setValue("Owner Name:");
        htmlPanelGrid.getChildren().add(ownerNameCreateOutput);
        
        
        InputText ownerNameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        ownerNameCreateInput.setId("ownerNameCreateInput");
        ownerNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.ownerName}", String.class));
        ownerNameCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ownerNameCreateInput);
        
        Message ownerNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ownerNameCreateInputMessage.setId("ownerNameCreateInputMessage");
        ownerNameCreateInputMessage.setFor("ownerNameCreateInput");
        ownerNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ownerNameCreateInputMessage);
        **/
        OutputLabel sendRemindersCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        sendRemindersCreateOutput.setFor("sendRemindersCreateInput");
        sendRemindersCreateOutput.setId("sendRemindersCreateOutput");
        sendRemindersCreateOutput.setValue("Send Reminders:");
        htmlPanelGrid.getChildren().add(sendRemindersCreateOutput);
        
        SelectBooleanCheckbox sendRemindersCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        sendRemindersCreateInput.setId("sendRemindersCreateInput");
        sendRemindersCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.sendReminders}", Boolean.class));
        sendRemindersCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(sendRemindersCreateInput);
        
        Message sendRemindersCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        sendRemindersCreateInputMessage.setId("sendRemindersCreateInputMessage");
        sendRemindersCreateInputMessage.setFor("sendRemindersCreateInput");
        sendRemindersCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(sendRemindersCreateInputMessage);
        
        OutputLabel nameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameCreateOutput.setFor("nameCreateInput");
        nameCreateOutput.setId("nameCreateOutput");
        nameCreateOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameCreateOutput);
        
        InputText nameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nameCreateInput.setId("nameCreateInput");
        nameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.name}", String.class));
        LengthValidator nameCreateInputValidator = new LengthValidator();
        nameCreateInputValidator.setMinimum(1);
        nameCreateInput.addValidator(nameCreateInputValidator);
        nameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameCreateInput);
        
        Message nameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameCreateInputMessage.setId("nameCreateInputMessage");
        nameCreateInputMessage.setFor("nameCreateInput");
        nameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameCreateInputMessage);
        
        OutputLabel weightCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        weightCreateOutput.setFor("weightCreateInput");
        weightCreateOutput.setId("weightCreateOutput");
        weightCreateOutput.setValue("Weight:");
        htmlPanelGrid.getChildren().add(weightCreateOutput);
        
        InputText weightCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        weightCreateInput.setId("weightCreateInput");
        weightCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.weight}", Float.class));
        weightCreateInput.setRequired(true);
        DoubleRangeValidator weightCreateInputValidator = new DoubleRangeValidator();
        weightCreateInputValidator.setMinimum(0.0);
        weightCreateInput.addValidator(weightCreateInputValidator);
        htmlPanelGrid.getChildren().add(weightCreateInput);
        
        Message weightCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        weightCreateInputMessage.setId("weightCreateInputMessage");
        weightCreateInputMessage.setFor("weightCreateInput");
        weightCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(weightCreateInputMessage);
        
        OutputLabel ownerCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        ownerCreateOutput.setFor("ownerCreateInput");
        ownerCreateOutput.setId("ownerCreateOutput");
        ownerCreateOutput.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerCreateOutput);
        
        AutoComplete ownerCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        ownerCreateInput.setId("ownerCreateInput");
        ownerCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.owner}", Owner.class));
        ownerCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{petBean.completeOwner}", List.class, new Class[] { String.class }));
        ownerCreateInput.setDropdown(true);
        ownerCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "owner", String.class));
        ownerCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{owner.firstName} #{owner.lastName} #{owner.address} #{owner.city}", String.class));
        ownerCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{owner}", Owner.class));
        ownerCreateInput.setConverter(new OwnerConverter());
        ownerCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ownerCreateInput);
        
        Message ownerCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ownerCreateInputMessage.setId("ownerCreateInputMessage");
        ownerCreateInputMessage.setFor("ownerCreateInput");
        ownerCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ownerCreateInputMessage);
        
        OutputLabel typeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        typeCreateOutput.setFor("typeCreateInput");
        typeCreateOutput.setId("typeCreateOutput");
        typeCreateOutput.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeCreateOutput);
        
        AutoComplete typeCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        typeCreateInput.setId("typeCreateInput");
        typeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.type}", PetType.class));
        typeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{petBean.completeType}", List.class, new Class[] { String.class }));
        typeCreateInput.setDropdown(true);
        typeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(typeCreateInput);
        
        Message typeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        typeCreateInputMessage.setId("typeCreateInputMessage");
        typeCreateInputMessage.setFor("typeCreateInput");
        typeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(typeCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        /**
        OutputLabel ownerNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        ownerNameEditOutput.setFor("ownerNameEditInput");
        ownerNameEditOutput.setId("ownerNameEditOutput");
        ownerNameEditOutput.setValue("Owner Name:");
        htmlPanelGrid.getChildren().add(ownerNameEditOutput);
        
        InputText ownerNameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        ownerNameEditInput.setId("ownerNameEditInput");
        ownerNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.ownerName}", String.class));
        ownerNameEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ownerNameEditInput);
        
        Message ownerNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ownerNameEditInputMessage.setId("ownerNameEditInputMessage");
        ownerNameEditInputMessage.setFor("ownerNameEditInput");
        ownerNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ownerNameEditInputMessage);
        **/
        OutputLabel sendRemindersEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        sendRemindersEditOutput.setFor("sendRemindersEditInput");
        sendRemindersEditOutput.setId("sendRemindersEditOutput");
        sendRemindersEditOutput.setValue("Send Reminders:");
        htmlPanelGrid.getChildren().add(sendRemindersEditOutput);
        
        SelectBooleanCheckbox sendRemindersEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        sendRemindersEditInput.setId("sendRemindersEditInput");
        sendRemindersEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.sendReminders}", Boolean.class));
        sendRemindersEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(sendRemindersEditInput);
        
        Message sendRemindersEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        sendRemindersEditInputMessage.setId("sendRemindersEditInputMessage");
        sendRemindersEditInputMessage.setFor("sendRemindersEditInput");
        sendRemindersEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(sendRemindersEditInputMessage);
        
        OutputLabel nameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameEditOutput.setFor("nameEditInput");
        nameEditOutput.setId("nameEditOutput");
        nameEditOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameEditOutput);
        
        InputText nameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nameEditInput.setId("nameEditInput");
        nameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.name}", String.class));
        LengthValidator nameEditInputValidator = new LengthValidator();
        nameEditInputValidator.setMinimum(1);
        nameEditInput.addValidator(nameEditInputValidator);
        nameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameEditInput);
        
        Message nameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameEditInputMessage.setId("nameEditInputMessage");
        nameEditInputMessage.setFor("nameEditInput");
        nameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameEditInputMessage);
        
        OutputLabel weightEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        weightEditOutput.setFor("weightEditInput");
        weightEditOutput.setId("weightEditOutput");
        weightEditOutput.setValue("Weight:");
        htmlPanelGrid.getChildren().add(weightEditOutput);
        
        InputText weightEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        weightEditInput.setId("weightEditInput");
        weightEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.weight}", Float.class));
        weightEditInput.setRequired(true);
        DoubleRangeValidator weightEditInputValidator = new DoubleRangeValidator();
        weightEditInputValidator.setMinimum(0.0);
        weightEditInput.addValidator(weightEditInputValidator);
        htmlPanelGrid.getChildren().add(weightEditInput);
        
        Message weightEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        weightEditInputMessage.setId("weightEditInputMessage");
        weightEditInputMessage.setFor("weightEditInput");
        weightEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(weightEditInputMessage);
        
        OutputLabel ownerEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        ownerEditOutput.setFor("ownerEditInput");
        ownerEditOutput.setId("ownerEditOutput");
        ownerEditOutput.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerEditOutput);
        
        AutoComplete ownerEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        ownerEditInput.setId("ownerEditInput");
        ownerEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.owner}", Owner.class));
        ownerEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{petBean.completeOwner}", List.class, new Class[] { String.class }));
        ownerEditInput.setDropdown(true);
        ownerEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "owner", String.class));
        ownerEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{owner.firstName} #{owner.lastName} #{owner.address} #{owner.city}", String.class));
        ownerEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{owner}", Owner.class));
        ownerEditInput.setConverter(new OwnerConverter());
        ownerEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ownerEditInput);
        
        Message ownerEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ownerEditInputMessage.setId("ownerEditInputMessage");
        ownerEditInputMessage.setFor("ownerEditInput");
        ownerEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ownerEditInputMessage);
        
        OutputLabel typeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        typeEditOutput.setFor("typeEditInput");
        typeEditOutput.setId("typeEditOutput");
        typeEditOutput.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeEditOutput);
        
        AutoComplete typeEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        typeEditInput.setId("typeEditInput");
        typeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.type}", PetType.class));
        typeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{petBean.completeType}", List.class, new Class[] { String.class }));
        typeEditInput.setDropdown(true);
        typeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(typeEditInput);
        
        Message typeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        typeEditInputMessage.setId("typeEditInputMessage");
        typeEditInputMessage.setFor("typeEditInput");
        typeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(typeEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        /**
        HtmlOutputText ownerNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerNameLabel.setId("ownerNameLabel");
        ownerNameLabel.setValue("Owner Name:");
        htmlPanelGrid.getChildren().add(ownerNameLabel);
        
        HtmlOutputText ownerNameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerNameValue.setId("ownerNameValue");
        ownerNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.ownerName}", String.class));
        htmlPanelGrid.getChildren().add(ownerNameValue);
        **/
        HtmlOutputText sendRemindersLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        sendRemindersLabel.setId("sendRemindersLabel");
        sendRemindersLabel.setValue("Send Reminders:");
        htmlPanelGrid.getChildren().add(sendRemindersLabel);
        
        HtmlOutputText sendRemindersValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        sendRemindersValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.sendReminders}", String.class));
        htmlPanelGrid.getChildren().add(sendRemindersValue);
        
        HtmlOutputText nameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameLabel.setId("nameLabel");
        nameLabel.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameLabel);
        
        HtmlOutputText nameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameValue.setId("nameValue");
        nameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.name}", String.class));
        htmlPanelGrid.getChildren().add(nameValue);
        
        HtmlOutputText weightLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        weightLabel.setId("weightLabel");
        weightLabel.setValue("Weight:");
        htmlPanelGrid.getChildren().add(weightLabel);
        
        HtmlOutputText weightValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        weightValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.weight}", String.class));
        htmlPanelGrid.getChildren().add(weightValue);
        
        HtmlOutputText ownerLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerLabel.setId("ownerLabel");
        ownerLabel.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerLabel);
        
        HtmlOutputText ownerValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.owner}", Owner.class));
        ownerValue.setConverter(new OwnerConverter());
        htmlPanelGrid.getChildren().add(ownerValue);
        
        HtmlOutputText typeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        typeLabel.setId("typeLabel");
        typeLabel.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeLabel);
        
        HtmlOutputText typeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        typeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{petBean.pet.type}", String.class));
        htmlPanelGrid.getChildren().add(typeValue);
        
        return htmlPanelGrid;
    }

	public Pet getPet() {
        if (pet == null) {
            pet = new Pet();
        }
        return pet;
    }

	public void setPet(Pet pet) {
        this.pet = pet;
    }

	public List<Owner> completeOwner(String query) {
        List<Owner> suggestions = new ArrayList<Owner>();
        for (Owner owner : Owner.findAllOwners()) {
            String ownerStr = String.valueOf(owner.getFirstName() +  " "  + owner.getLastName() +  " "  + owner.getAddress() +  " "  + owner.getCity());
            if (ownerStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(owner);
            }
        }
        return suggestions;
    }

	public List<PetType> completeType(String query) {
        List<PetType> suggestions = new ArrayList<PetType>();
        for (PetType petType : PetType.values()) {
            if (petType.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(petType);
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
        findAllPets();
        return "pet";
    }

	public String displayCreateDialog() {
        pet = new Pet();
        createDialogVisible = true;
        return "pet";
    }

	public String persist() {
        String message = "";
        if (pet.getId() != null) {
            pet.merge();
            message = "message_successfully_updated";
        } else {
            pet.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Pet");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPets();
    }

	public String delete() {
        pet.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Pet");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPets();
    }

	public void reset() {
        pet = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
