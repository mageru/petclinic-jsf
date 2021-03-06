package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Owner;
import com.gsu.petclinic.domain.Pet;
import com.gsu.petclinic.jsf.util.MessageFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "ownerBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Owner.class, beanName = "ownerBean")
public class OwnerBean implements Serializable  {

	private String name = "Owners";

	private Owner owner;

	private List<Owner> allOwners;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	private List<Pet> selectedPets;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("firstName");
        columns.add("lastName");
        columns.add("address");
        columns.add("city");
        columns.add("telephone");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Owner> getAllOwners() {
        return allOwners;
    }

	public void setAllOwners(List<Owner> allOwners) {
        this.allOwners = allOwners;
    }

	public String findAllOwners() {
        allOwners = Owner.findAllOwners();
        dataVisible = !allOwners.isEmpty();
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
        
        OutputLabel firstNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        firstNameCreateOutput.setFor("firstNameCreateInput");
        firstNameCreateOutput.setId("firstNameCreateOutput");
        firstNameCreateOutput.setValue("First Name:");
        htmlPanelGrid.getChildren().add(firstNameCreateOutput);
        
        InputText firstNameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        firstNameCreateInput.setId("firstNameCreateInput");
        firstNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.firstName}", String.class));
        LengthValidator firstNameCreateInputValidator = new LengthValidator();
        firstNameCreateInputValidator.setMinimum(3);
        firstNameCreateInputValidator.setMaximum(30);
        firstNameCreateInput.addValidator(firstNameCreateInputValidator);
        firstNameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(firstNameCreateInput);
        
        Message firstNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        firstNameCreateInputMessage.setId("firstNameCreateInputMessage");
        firstNameCreateInputMessage.setFor("firstNameCreateInput");
        firstNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(firstNameCreateInputMessage);
        
        OutputLabel lastNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        lastNameCreateOutput.setFor("lastNameCreateInput");
        lastNameCreateOutput.setId("lastNameCreateOutput");
        lastNameCreateOutput.setValue("Last Name:");
        htmlPanelGrid.getChildren().add(lastNameCreateOutput);
        
        InputText lastNameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        lastNameCreateInput.setId("lastNameCreateInput");
        lastNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.lastName}", String.class));
        LengthValidator lastNameCreateInputValidator = new LengthValidator();
        lastNameCreateInputValidator.setMinimum(3);
        lastNameCreateInputValidator.setMaximum(30);
        lastNameCreateInput.addValidator(lastNameCreateInputValidator);
        lastNameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(lastNameCreateInput);
        
        Message lastNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        lastNameCreateInputMessage.setId("lastNameCreateInputMessage");
        lastNameCreateInputMessage.setFor("lastNameCreateInput");
        lastNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(lastNameCreateInputMessage);
        
        OutputLabel addressCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addressCreateOutput.setFor("addressCreateInput");
        addressCreateOutput.setId("addressCreateOutput");
        addressCreateOutput.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressCreateOutput);
        
        InputTextarea addressCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressCreateInput.setId("addressCreateInput");
        addressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.address}", String.class));
        LengthValidator addressCreateInputValidator = new LengthValidator();
        addressCreateInputValidator.setMinimum(1);
        addressCreateInputValidator.setMaximum(50);
        addressCreateInput.addValidator(addressCreateInputValidator);
        addressCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addressCreateInput);
        
        Message addressCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addressCreateInputMessage.setId("addressCreateInputMessage");
        addressCreateInputMessage.setFor("addressCreateInput");
        addressCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addressCreateInputMessage);
        
        OutputLabel cityCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        cityCreateOutput.setFor("cityCreateInput");
        cityCreateOutput.setId("cityCreateOutput");
        cityCreateOutput.setValue("City:");
        htmlPanelGrid.getChildren().add(cityCreateOutput);
        
        InputText cityCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        cityCreateInput.setId("cityCreateInput");
        cityCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.city}", String.class));
        LengthValidator cityCreateInputValidator = new LengthValidator();
        cityCreateInputValidator.setMaximum(30);
        cityCreateInput.addValidator(cityCreateInputValidator);
        cityCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(cityCreateInput);
        
        Message cityCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        cityCreateInputMessage.setId("cityCreateInputMessage");
        cityCreateInputMessage.setFor("cityCreateInput");
        cityCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(cityCreateInputMessage);
        
        OutputLabel telephoneCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        telephoneCreateOutput.setFor("telephoneCreateInput");
        telephoneCreateOutput.setId("telephoneCreateOutput");
        telephoneCreateOutput.setValue("Telephone:");
        htmlPanelGrid.getChildren().add(telephoneCreateOutput);
        
        InputText telephoneCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        telephoneCreateInput.setId("telephoneCreateInput");
        telephoneCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.telephone}", String.class));
        telephoneCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(telephoneCreateInput);
        
        Message telephoneCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        telephoneCreateInputMessage.setId("telephoneCreateInputMessage");
        telephoneCreateInputMessage.setFor("telephoneCreateInput");
        telephoneCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(telephoneCreateInputMessage);
        
        OutputLabel emailCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        emailCreateOutput.setFor("emailCreateInput");
        emailCreateOutput.setId("emailCreateOutput");
        emailCreateOutput.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailCreateOutput);
        
        InputText emailCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        emailCreateInput.setId("emailCreateInput");
        emailCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.email}", String.class));
        LengthValidator emailCreateInputValidator = new LengthValidator();
        emailCreateInputValidator.setMinimum(6);
        emailCreateInputValidator.setMaximum(30);
        emailCreateInput.addValidator(emailCreateInputValidator);
        emailCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(emailCreateInput);
        
        Message emailCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailCreateInputMessage.setId("emailCreateInputMessage");
        emailCreateInputMessage.setFor("emailCreateInput");
        emailCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailCreateInputMessage);
        
        OutputLabel birthDayCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        birthDayCreateOutput.setFor("birthDayCreateInput");
        birthDayCreateOutput.setId("birthDayCreateOutput");
        birthDayCreateOutput.setValue("Birth Day:");
        htmlPanelGrid.getChildren().add(birthDayCreateOutput);
        
        Calendar birthDayCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        birthDayCreateInput.setId("birthDayCreateInput");
        birthDayCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.birthDay}", Date.class));
        birthDayCreateInput.setNavigator(true);
        birthDayCreateInput.setEffect("slideDown");
        birthDayCreateInput.setPattern("dd/MM/yyyy");
        birthDayCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(birthDayCreateInput);
        
        Message birthDayCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        birthDayCreateInputMessage.setId("birthDayCreateInputMessage");
        birthDayCreateInputMessage.setFor("birthDayCreateInput");
        birthDayCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(birthDayCreateInputMessage);
        
        HtmlOutputText petsCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petsCreateOutput.setId("petsCreateOutput");
        petsCreateOutput.setValue("Pets:");
        htmlPanelGrid.getChildren().add(petsCreateOutput);
        
        HtmlOutputText petsCreateInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petsCreateInput.setId("petsCreateInput");
        petsCreateInput.setValue("This relationship is managed from the Pet side");
        htmlPanelGrid.getChildren().add(petsCreateInput);
        
        Message petsCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        petsCreateInputMessage.setId("petsCreateInputMessage");
        petsCreateInputMessage.setFor("petsCreateInput");
        petsCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(petsCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel firstNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        firstNameEditOutput.setFor("firstNameEditInput");
        firstNameEditOutput.setId("firstNameEditOutput");
        firstNameEditOutput.setValue("First Name:");
        htmlPanelGrid.getChildren().add(firstNameEditOutput);
        
        InputText firstNameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        firstNameEditInput.setId("firstNameEditInput");
        firstNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.firstName}", String.class));
        LengthValidator firstNameEditInputValidator = new LengthValidator();
        firstNameEditInputValidator.setMinimum(3);
        firstNameEditInputValidator.setMaximum(30);
        firstNameEditInput.addValidator(firstNameEditInputValidator);
        firstNameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(firstNameEditInput);
        
        Message firstNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        firstNameEditInputMessage.setId("firstNameEditInputMessage");
        firstNameEditInputMessage.setFor("firstNameEditInput");
        firstNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(firstNameEditInputMessage);
        
        OutputLabel lastNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        lastNameEditOutput.setFor("lastNameEditInput");
        lastNameEditOutput.setId("lastNameEditOutput");
        lastNameEditOutput.setValue("Last Name:");
        htmlPanelGrid.getChildren().add(lastNameEditOutput);
        
        InputText lastNameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        lastNameEditInput.setId("lastNameEditInput");
        lastNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.lastName}", String.class));
        LengthValidator lastNameEditInputValidator = new LengthValidator();
        lastNameEditInputValidator.setMinimum(3);
        lastNameEditInputValidator.setMaximum(30);
        lastNameEditInput.addValidator(lastNameEditInputValidator);
        lastNameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(lastNameEditInput);
        
        Message lastNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        lastNameEditInputMessage.setId("lastNameEditInputMessage");
        lastNameEditInputMessage.setFor("lastNameEditInput");
        lastNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(lastNameEditInputMessage);
        
        OutputLabel addressEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addressEditOutput.setFor("addressEditInput");
        addressEditOutput.setId("addressEditOutput");
        addressEditOutput.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressEditOutput);
        
        InputTextarea addressEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressEditInput.setId("addressEditInput");
        addressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.address}", String.class));
        LengthValidator addressEditInputValidator = new LengthValidator();
        addressEditInputValidator.setMinimum(1);
        addressEditInputValidator.setMaximum(50);
        addressEditInput.addValidator(addressEditInputValidator);
        addressEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addressEditInput);
        
        Message addressEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addressEditInputMessage.setId("addressEditInputMessage");
        addressEditInputMessage.setFor("addressEditInput");
        addressEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addressEditInputMessage);
        
        OutputLabel cityEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        cityEditOutput.setFor("cityEditInput");
        cityEditOutput.setId("cityEditOutput");
        cityEditOutput.setValue("City:");
        htmlPanelGrid.getChildren().add(cityEditOutput);
        
        InputText cityEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        cityEditInput.setId("cityEditInput");
        cityEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.city}", String.class));
        LengthValidator cityEditInputValidator = new LengthValidator();
        cityEditInputValidator.setMaximum(30);
        cityEditInput.addValidator(cityEditInputValidator);
        cityEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(cityEditInput);
        
        Message cityEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        cityEditInputMessage.setId("cityEditInputMessage");
        cityEditInputMessage.setFor("cityEditInput");
        cityEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(cityEditInputMessage);
        
        OutputLabel telephoneEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        telephoneEditOutput.setFor("telephoneEditInput");
        telephoneEditOutput.setId("telephoneEditOutput");
        telephoneEditOutput.setValue("Telephone:");
        htmlPanelGrid.getChildren().add(telephoneEditOutput);
        
        InputText telephoneEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        telephoneEditInput.setId("telephoneEditInput");
        telephoneEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.telephone}", String.class));
        telephoneEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(telephoneEditInput);
        
        Message telephoneEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        telephoneEditInputMessage.setId("telephoneEditInputMessage");
        telephoneEditInputMessage.setFor("telephoneEditInput");
        telephoneEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(telephoneEditInputMessage);
        
        OutputLabel emailEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        emailEditOutput.setFor("emailEditInput");
        emailEditOutput.setId("emailEditOutput");
        emailEditOutput.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailEditOutput);
        
        InputText emailEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        emailEditInput.setId("emailEditInput");
        emailEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.email}", String.class));
        LengthValidator emailEditInputValidator = new LengthValidator();
        emailEditInputValidator.setMinimum(6);
        emailEditInputValidator.setMaximum(30);
        emailEditInput.addValidator(emailEditInputValidator);
        emailEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(emailEditInput);
        
        Message emailEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailEditInputMessage.setId("emailEditInputMessage");
        emailEditInputMessage.setFor("emailEditInput");
        emailEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailEditInputMessage);
        
        OutputLabel birthDayEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        birthDayEditOutput.setFor("birthDayEditInput");
        birthDayEditOutput.setId("birthDayEditOutput");
        birthDayEditOutput.setValue("Birth Day:");
        htmlPanelGrid.getChildren().add(birthDayEditOutput);
        
        Calendar birthDayEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        birthDayEditInput.setId("birthDayEditInput");
        birthDayEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.birthDay}", Date.class));
        birthDayEditInput.setNavigator(true);
        birthDayEditInput.setEffect("slideDown");
        birthDayEditInput.setPattern("dd/MM/yyyy");
        birthDayEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(birthDayEditInput);
        
        Message birthDayEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        birthDayEditInputMessage.setId("birthDayEditInputMessage");
        birthDayEditInputMessage.setFor("birthDayEditInput");
        birthDayEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(birthDayEditInputMessage);
        
        HtmlOutputText petsEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petsEditOutput.setId("petsEditOutput");
        petsEditOutput.setValue("Pets:");
        htmlPanelGrid.getChildren().add(petsEditOutput);
        
        HtmlOutputText petsEditInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petsEditInput.setId("petsEditInput");
        petsEditInput.setValue("This relationship is managed from the Pet side");
        htmlPanelGrid.getChildren().add(petsEditInput);
        
        Message petsEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        petsEditInputMessage.setId("petsEditInputMessage");
        petsEditInputMessage.setFor("petsEditInput");
        petsEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(petsEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText firstNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        firstNameLabel.setId("firstNameLabel");
        firstNameLabel.setValue("First Name:");
        htmlPanelGrid.getChildren().add(firstNameLabel);
        
        HtmlOutputText firstNameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        firstNameValue.setId("firstNameValue");
        firstNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.firstName}", String.class));
        htmlPanelGrid.getChildren().add(firstNameValue);
        
        HtmlOutputText lastNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        lastNameLabel.setId("lastNameLabel");
        lastNameLabel.setValue("Last Name:");
        htmlPanelGrid.getChildren().add(lastNameLabel);
        
        HtmlOutputText lastNameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        lastNameValue.setId("lastNameValue");
        lastNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.lastName}", String.class));
        htmlPanelGrid.getChildren().add(lastNameValue);
        
        HtmlOutputText addressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addressLabel.setId("addressLabel");
        addressLabel.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressLabel);
        
        InputTextarea addressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressValue.setId("addressValue");
        addressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.address}", String.class));
        addressValue.setReadonly(true);
        addressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(addressValue);
        
        HtmlOutputText cityLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cityLabel.setId("cityLabel");
        cityLabel.setValue("City:");
        htmlPanelGrid.getChildren().add(cityLabel);
        
        HtmlOutputText cityValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cityValue.setId("cityValue");
        cityValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.city}", String.class));
        htmlPanelGrid.getChildren().add(cityValue);
        
        HtmlOutputText telephoneLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        telephoneLabel.setId("telephoneLabel");
        telephoneLabel.setValue("Telephone:");
        htmlPanelGrid.getChildren().add(telephoneLabel);
        
        HtmlOutputText telephoneValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        telephoneValue.setId("telephoneValue");
        telephoneValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.telephone}", String.class));
        htmlPanelGrid.getChildren().add(telephoneValue);
        
        HtmlOutputText emailLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailLabel.setId("emailLabel");
        emailLabel.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailLabel);
        
        HtmlOutputText emailValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailValue.setId("emailValue");
        emailValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.email}", String.class));
        htmlPanelGrid.getChildren().add(emailValue);
        
        HtmlOutputText birthDayLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        birthDayLabel.setId("birthDayLabel");
        birthDayLabel.setValue("Birth Day:");
        htmlPanelGrid.getChildren().add(birthDayLabel);
        
        HtmlOutputText birthDayValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        birthDayValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ownerBean.owner.birthDay}", Date.class));
        DateTimeConverter birthDayValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        birthDayValueConverter.setPattern("dd/MM/yyyy");
        birthDayValue.setConverter(birthDayValueConverter);
        htmlPanelGrid.getChildren().add(birthDayValue);
        
        HtmlOutputText petsLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petsLabel.setId("petsLabel");
        petsLabel.setValue("Pets:");
        htmlPanelGrid.getChildren().add(petsLabel);
        
        HtmlOutputText petsValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        petsValue.setId("petsValue");
        petsValue.setValue("This relationship is managed from the Pet side");
        htmlPanelGrid.getChildren().add(petsValue);
        
        return htmlPanelGrid;
    }

	public Owner getOwner() {
        if (owner == null) {
            owner = new Owner();
        }
        return owner;
    }

	public void setOwner(Owner owner) {
        this.owner = owner;
    }

	public List<Pet> getSelectedPets() {
        return selectedPets;
    }

	public void setSelectedPets(List<Pet> selectedPets) {
        if (selectedPets != null) {
            owner.setPets(new HashSet<Pet>(selectedPets));
        }
        this.selectedPets = selectedPets;
    }

	public String onEdit() {
        if (owner != null && owner.getPets() != null) {
            selectedPets = new ArrayList<Pet>(owner.getPets());
        }
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
        findAllOwners();
        return "owner";
    }

	public String displayCreateDialog() {
        owner = new Owner();
        createDialogVisible = true;
        return "owner";
    }

	public String persist() {
        String message = "";
        if (owner.getId() != null) {
            owner.merge();
            message = "message_successfully_updated";
        } else {
            owner.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Owner");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllOwners();
    }

	public String delete() {
        owner.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Owner");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllOwners();
    }

	public void reset() {
        owner = null;
        selectedPets = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
