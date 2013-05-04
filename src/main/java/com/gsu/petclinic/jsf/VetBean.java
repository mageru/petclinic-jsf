package com.gsu.petclinic.jsf;

import com.gsu.petclinic.domain.Vet;
import com.gsu.petclinic.jsf.util.MessageFactory;
import com.gsu.petclinic.reference.Specialty;
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
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@ManagedBean(name = "vetBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = Vet.class, beanName = "vetBean")
public class VetBean implements Serializable {

	private String name = "Vets";

	private Vet vet;

	private List<Vet> allVets;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

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

	public List<Vet> getAllVets() {
        return allVets;
    }

	public void setAllVets(List<Vet> allVets) {
        this.allVets = allVets;
    }

	public String findAllVets() {
        allVets = Vet.findAllVets();
        dataVisible = !allVets.isEmpty();
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
        firstNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.firstName}", String.class));
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
        lastNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.lastName}", String.class));
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
        addressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.address}", String.class));
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
        cityCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.city}", String.class));
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
        telephoneCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.telephone}", String.class));
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
        emailCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.email}", String.class));
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
        birthDayCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.birthDay}", Date.class));
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
        
        OutputLabel employedSinceCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        employedSinceCreateOutput.setFor("employedSinceCreateInput");
        employedSinceCreateOutput.setId("employedSinceCreateOutput");
        employedSinceCreateOutput.setValue("Employed Since:");
        htmlPanelGrid.getChildren().add(employedSinceCreateOutput);
        
        Calendar employedSinceCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        employedSinceCreateInput.setId("employedSinceCreateInput");
        employedSinceCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.employedSince}", Date.class));
        employedSinceCreateInput.setNavigator(true);
        employedSinceCreateInput.setEffect("slideDown");
        employedSinceCreateInput.setPattern("dd/MM/yyyy");
        employedSinceCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(employedSinceCreateInput);
        
        Message employedSinceCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        employedSinceCreateInputMessage.setId("employedSinceCreateInputMessage");
        employedSinceCreateInputMessage.setFor("employedSinceCreateInput");
        employedSinceCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(employedSinceCreateInputMessage);
        
        OutputLabel specialtyCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        specialtyCreateOutput.setFor("specialtyCreateInput");
        specialtyCreateOutput.setId("specialtyCreateOutput");
        specialtyCreateOutput.setValue("Specialty:");
        htmlPanelGrid.getChildren().add(specialtyCreateOutput);
        
        AutoComplete specialtyCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        specialtyCreateInput.setId("specialtyCreateInput");
        specialtyCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.specialty}", Specialty.class));
        specialtyCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{vetBean.completeSpecialty}", List.class, new Class[] { String.class }));
        specialtyCreateInput.setDropdown(true);
        specialtyCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(specialtyCreateInput);
        
        Message specialtyCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        specialtyCreateInputMessage.setId("specialtyCreateInputMessage");
        specialtyCreateInputMessage.setFor("specialtyCreateInput");
        specialtyCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(specialtyCreateInputMessage);
        
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
        firstNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.firstName}", String.class));
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
        lastNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.lastName}", String.class));
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
        addressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.address}", String.class));
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
        cityEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.city}", String.class));
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
        telephoneEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.telephone}", String.class));
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
        emailEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.email}", String.class));
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
        birthDayEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.birthDay}", Date.class));
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
        
        OutputLabel employedSinceEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        employedSinceEditOutput.setFor("employedSinceEditInput");
        employedSinceEditOutput.setId("employedSinceEditOutput");
        employedSinceEditOutput.setValue("Employed Since:");
        htmlPanelGrid.getChildren().add(employedSinceEditOutput);
        
        Calendar employedSinceEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        employedSinceEditInput.setId("employedSinceEditInput");
        employedSinceEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.employedSince}", Date.class));
        employedSinceEditInput.setNavigator(true);
        employedSinceEditInput.setEffect("slideDown");
        employedSinceEditInput.setPattern("dd/MM/yyyy");
        employedSinceEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(employedSinceEditInput);
        
        Message employedSinceEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        employedSinceEditInputMessage.setId("employedSinceEditInputMessage");
        employedSinceEditInputMessage.setFor("employedSinceEditInput");
        employedSinceEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(employedSinceEditInputMessage);
        
        OutputLabel specialtyEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        specialtyEditOutput.setFor("specialtyEditInput");
        specialtyEditOutput.setId("specialtyEditOutput");
        specialtyEditOutput.setValue("Specialty:");
        htmlPanelGrid.getChildren().add(specialtyEditOutput);
        
        AutoComplete specialtyEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        specialtyEditInput.setId("specialtyEditInput");
        specialtyEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.specialty}", Specialty.class));
        specialtyEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{vetBean.completeSpecialty}", List.class, new Class[] { String.class }));
        specialtyEditInput.setDropdown(true);
        specialtyEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(specialtyEditInput);
        
        Message specialtyEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        specialtyEditInputMessage.setId("specialtyEditInputMessage");
        specialtyEditInputMessage.setFor("specialtyEditInput");
        specialtyEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(specialtyEditInputMessage);
        
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
        firstNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.firstName}", String.class));
        htmlPanelGrid.getChildren().add(firstNameValue);
        
        HtmlOutputText lastNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        lastNameLabel.setId("lastNameLabel");
        lastNameLabel.setValue("Last Name:");
        htmlPanelGrid.getChildren().add(lastNameLabel);
        
        HtmlOutputText lastNameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        lastNameValue.setId("lastNameValue");
        lastNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.lastName}", String.class));
        htmlPanelGrid.getChildren().add(lastNameValue);
        
        HtmlOutputText addressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addressLabel.setId("addressLabel");
        addressLabel.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressLabel);
        
        InputTextarea addressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressValue.setId("addressValue");
        addressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.address}", String.class));
        addressValue.setReadonly(true);
        addressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(addressValue);
        
        HtmlOutputText cityLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cityLabel.setId("cityLabel");
        cityLabel.setValue("City:");
        htmlPanelGrid.getChildren().add(cityLabel);
        
        HtmlOutputText cityValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cityValue.setId("cityValue");
        cityValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.city}", String.class));
        htmlPanelGrid.getChildren().add(cityValue);
        
        HtmlOutputText telephoneLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        telephoneLabel.setId("telephoneLabel");
        telephoneLabel.setValue("Telephone:");
        htmlPanelGrid.getChildren().add(telephoneLabel);
        
        HtmlOutputText telephoneValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        telephoneValue.setId("telephoneValue");
        telephoneValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.telephone}", String.class));
        htmlPanelGrid.getChildren().add(telephoneValue);
        
        HtmlOutputText emailLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailLabel.setId("emailLabel");
        emailLabel.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailLabel);
        
        HtmlOutputText emailValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailValue.setId("emailValue");
        emailValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.email}", String.class));
        htmlPanelGrid.getChildren().add(emailValue);
        
        HtmlOutputText birthDayLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        birthDayLabel.setId("birthDayLabel");
        birthDayLabel.setValue("Birth Day:");
        htmlPanelGrid.getChildren().add(birthDayLabel);
        
        HtmlOutputText birthDayValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        birthDayValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.birthDay}", Date.class));
        DateTimeConverter birthDayValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        birthDayValueConverter.setPattern("dd/MM/yyyy");
        birthDayValue.setConverter(birthDayValueConverter);
        htmlPanelGrid.getChildren().add(birthDayValue);
        
        HtmlOutputText employedSinceLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        employedSinceLabel.setId("employedSinceLabel");
        employedSinceLabel.setValue("Employed Since:");
        htmlPanelGrid.getChildren().add(employedSinceLabel);
        
        HtmlOutputText employedSinceValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        employedSinceValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.employedSince}", Date.class));
        DateTimeConverter employedSinceValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        employedSinceValueConverter.setPattern("dd/MM/yyyy");
        employedSinceValue.setConverter(employedSinceValueConverter);
        htmlPanelGrid.getChildren().add(employedSinceValue);
        
        HtmlOutputText specialtyLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        specialtyLabel.setId("specialtyLabel");
        specialtyLabel.setValue("Specialty:");
        htmlPanelGrid.getChildren().add(specialtyLabel);
        
        HtmlOutputText specialtyValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        specialtyValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{vetBean.vet.specialty}", String.class));
        htmlPanelGrid.getChildren().add(specialtyValue);
        
        return htmlPanelGrid;
    }

	public Vet getVet() {
        if (vet == null) {
            vet = new Vet();
        }
        return vet;
    }

	public void setVet(Vet vet) {
        this.vet = vet;
    }

	public List<Specialty> completeSpecialty(String query) {
        List<Specialty> suggestions = new ArrayList<Specialty>();
        for (Specialty specialty : Specialty.values()) {
            if (specialty.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(specialty);
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
        findAllVets();
        return "vet";
    }

	public String displayCreateDialog() {
        vet = new Vet();
        createDialogVisible = true;
        return "vet";
    }

	public String persist() {
        String message = "";
        if (vet.getId() != null) {
            vet.merge();
            message = "message_successfully_updated";
        } else {
            vet.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Vet");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllVets();
    }

	public String delete() {
        vet.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Vet");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllVets();
    }

	public void reset() {
        vet = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
