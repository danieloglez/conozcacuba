package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.*;
import dto.nom.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;
import util.Validator;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class ContractTransportModal extends DataManagerFormController {
    Validator p= new Validator();
    @FXML
    private JFXComboBox<ContractTypeDto> contract_jfxcombobox;

    @FXML
    private JFXDatePicker startdate_jfxdatepicker;

    @FXML
    private JFXDatePicker finishdate_jfxdatepicker;

    @FXML
    private JFXDatePicker conciliationndate_jfxdatepicker;

    @FXML
    private JFXComboBox<CompanyTransportDto> transportcompany_jfxcombobox;

    @FXML
    private CheckComboBox<VehicleDto> vehicleslist_combocheckbox;

    @FXML
    private JFXTextArea description_jfxtextarea;

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton update_jfxbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeComboBoxes();


            RegexValidator regexTextValidator = new RegexValidator("This field requires a text");
            RegexValidator regexNumericValidator = new RegexValidator("This field requires a Number");
            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
            regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
            regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");

            contract_jfxcombobox.getValidators().add(requiredFieldValidator);
            transportcompany_jfxcombobox.getValidators().add(requiredFieldValidator);
            description_jfxtextarea.getValidators().add(regexTextValidator);
            description_jfxtextarea.getValidators().add(requiredFieldValidator);


            contract_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) contract_jfxcombobox.validate();
            });

            transportcompany_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) transportcompany_jfxcombobox.validate();
            });

            description_jfxtextarea.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) description_jfxtextarea.validate();
            });




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initializeComboBoxes() throws SQLException {
        vehicleslist_combocheckbox.getItems().addAll(ServicesLocator.getVehicleServices().loadAll());
        transportcompany_jfxcombobox.getItems().addAll(ServicesLocator.getCompanyTransportServices().loadAll());
        contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractTypeServices().loadAll());
    }

    @Override
    public void insert(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, InstantiationException, ParseException {
        java.sql.Date startDate= Date.valueOf(startdate_jfxdatepicker.getValue());
        java.sql.Date finishDate=Date.valueOf(finishdate_jfxdatepicker.getValue());
        java.sql.Date conciliationDate=Date.valueOf(conciliationndate_jfxdatepicker.getValue());

        if (p.validateCombobox(contract_jfxcombobox) && p.validateTextOfTextArea(description_jfxtextarea) && p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationndate_jfxdatepicker)) {

            ContractTransportDto contractTransportDto = new ContractTransportDto(
                    0,
                    contract_jfxcombobox.getValue(),
                    startDate,
                    finishDate,
                    conciliationDate,
                    description_jfxtextarea.getText(),
                    transportcompany_jfxcombobox.getValue(),
                    vehicleslist_combocheckbox.getCheckModel().getCheckedItems()
            );

            ServicesLocator.getContractTransportServices().insert(contractTransportDto);
            ServicesLocator.getRelationContractTransportVehicleServices().insert(contractTransportDto);

            ((Stage) contract_jfxcombobox.getScene().getWindow()).close();
        }
    }

    @Override
    public void update(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        java.sql.Date startDate= Date.valueOf(startdate_jfxdatepicker.getValue());
        java.sql.Date finishDate=Date.valueOf(finishdate_jfxdatepicker.getValue());
        java.sql.Date conciliationDate=Date.valueOf(conciliationndate_jfxdatepicker.getValue());
        ContractTransportDto contractTransportDto = (ContractTransportDto) dto;
        contractTransportDto.setVehicles(vehicleslist_combocheckbox.getCheckModel().getCheckedItems());
        contractTransportDto.setContractTypeDto(contract_jfxcombobox.getValue());
        contractTransportDto.setStartDate(startDate);
        contractTransportDto.setFinishDate(finishDate);
        contractTransportDto.setConciliationDate(conciliationDate);
        contractTransportDto.setTransportCompany(transportcompany_jfxcombobox.getValue());

        if (p.validateCombobox(contract_jfxcombobox) && p.validateTextOfTextArea(description_jfxtextarea) && p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationndate_jfxdatepicker)) {


            ServicesLocator.getRelationContractTransportVehicleServices().update(contractTransportDto);

            ((Stage) contract_jfxcombobox.getScene().getWindow()).close();
        }

       /* ContractTransportDto contractTransportDto= new ContractTransportDto(
                0,
                contract_jfxcombobox.getValue(),
                startDate,
                finishDate,
                conciliationDate,
                description_jfxtextarea.getText(),
                transportcompany_jfxcombobox.getValue()
        );
        contractTransportDto.setVehicles(vehicleslist_combocheckbox.getCheckModel().getCheckedItems());
        ServicesLocator.getContractTransportServices().insert(contractTransportDto);
*/

    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if(dto != null) {
            ContractTransportDto contractDto = (ContractTransportDto) dto;

            startdate_jfxdatepicker.setValue(contractDto.getStartDate().toLocalDate());
            finishdate_jfxdatepicker.setValue(contractDto.getFinishDate().toLocalDate());
            conciliationndate_jfxdatepicker.setValue(contractDto.getConciliationDate().toLocalDate());
            description_jfxtextarea.setText(contractDto.getDescription());
            contract_jfxcombobox.getSelectionModel().select(contractDto.getContractTypeDto());

            ListIterator<VehicleDto> vehicleDtoListIterator= contractDto.getVehicles().listIterator();
            while (vehicleDtoListIterator.hasNext()) {
                vehicleslist_combocheckbox.getCheckModel().check(vehicleDtoListIterator.next());
            }
        }
    }



}
