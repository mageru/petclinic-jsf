// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Owner;
import com.gsu.petclinic.domain.Pet;
import com.gsu.petclinic.jsf.PetBean;
import com.gsu.petclinic.jsf.converter.OwnerConverter;
import com.gsu.petclinic.jsf.util.MessageFactory;
import com.gsu.petclinic.reference.PetType;
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

privileged aspect PetBean_Roo_ManagedBean {
    
    declare @type: PetBean: @ManagedBean(name = "petBean");
    
    declare @type: PetBean: @SessionScoped;
    
    private String PetBean.name = "Pets";
    
    private Pet PetBean.pet;
    
    private List<Pet> PetBean.allPets;
    
    private boolean PetBean.dataVisible = false;
    
    private List<String> PetBean.columns;
    
    private HtmlPanelGrid PetBean.createPanelGrid;
    
    private HtmlPanelGrid PetBean.editPanelGrid;
    
    private HtmlPanelGrid PetBean.viewPanelGrid;
    
    private boolean PetBean.createDialogVisible = false;
    
    @PostConstruct
    public void PetBean.init() {
        columns = new ArrayList<String>();
        columns.add("name");
        columns.add("weight");
    }
    
    public String PetBean.getName() {
        return name;
    }
    
    public List<String> PetBean.getColumns() {
        return columns;
    }
    
    public List<Pet> PetBean.getAllPets() {
        return allPets;
    }
    
    public void PetBean.setAllPets(List<Pet> allPets) {
        this.allPets = allPets;
    }
    
    public String PetBean.findAllPets() {
        allPets = Pet.findAllPets();
        dataVisible = !allPets.isEmpty();
        return null;
    }
    
    public boolean PetBean.isDataVisible() {
        return dataVisible;
    }
    
    public void PetBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid PetBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void PetBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid PetBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void PetBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid PetBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void PetBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid PetBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
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
    
    public HtmlPanelGrid PetBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
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
    
    public HtmlPanelGrid PetBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
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
    
    public Pet PetBean.getPet() {
        if (pet == null) {
            pet = new Pet();
        }
        return pet;
    }
    
    public void PetBean.setPet(Pet pet) {
        this.pet = pet;
    }
    
    public List<Owner> PetBean.completeOwner(String query) {
        List<Owner> suggestions = new ArrayList<Owner>();
        for (Owner owner : Owner.findAllOwners()) {
            String ownerStr = String.valueOf(owner.getFirstName() +  " "  + owner.getLastName() +  " "  + owner.getAddress() +  " "  + owner.getCity());
            if (ownerStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(owner);
            }
        }
        return suggestions;
    }
    
    public List<PetType> PetBean.completeType(String query) {
        List<PetType> suggestions = new ArrayList<PetType>();
        for (PetType petType : PetType.values()) {
            if (petType.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(petType);
            }
        }
        return suggestions;
    }
    
    public String PetBean.onEdit() {
        return null;
    }
    
    public boolean PetBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void PetBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String PetBean.displayList() {
        createDialogVisible = false;
        findAllPets();
        return "pet";
    }
    
    public String PetBean.displayCreateDialog() {
        pet = new Pet();
        createDialogVisible = true;
        return "pet";
    }
    
    public String PetBean.persist() {
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
    
    public String PetBean.delete() {
        pet.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Pet");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPets();
    }
    
    public void PetBean.reset() {
        pet = null;
        createDialogVisible = false;
    }
    
    public void PetBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
