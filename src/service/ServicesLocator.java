package service;

import service.functionality.UserServices;
import service.nomenclators.*;
import util.ConfigurationUtils;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static CompanyServiceServices companyServiceServices;
    private static CompanyTransportServices companyTransportServices;
    private static ContractTypeServices contractTypeServices;
    private static FoodPlanServices foodPlanServices;
    private static HotelFranchiseServices hotelFranchiseServices;
    private static LocalizationServices localizationServices;
    private static ModalityComertialServices modalityComertialServices;
    private static ProvinceServices provinceServices;
    private static RoomTypeServices roomTypeServices;
    private static UserServices userServices;
    private static VehicleServices vehicleServices;
    private static VehicleBrandServices vehicleBrandServices;
    private static ServiceTypeServices serviceTypeServices;

    // Connection
    public static Connection getConnection() throws SQLException {
        return new DatabaseUtils(
                ConfigurationUtils.getServer(),
                ConfigurationUtils.getDatabase(),
                ConfigurationUtils.getUser(),
                ConfigurationUtils.getPassword()
        ).getConnection();
    }

    // Services
    public static CompanyServiceServices getCompanyServiceServices() {
        if (companyServiceServices == null)
            companyServiceServices = new CompanyServiceServices();

        return companyServiceServices;
    }

    public static CompanyTransportServices getCompanyTransportServices() {
        if (companyTransportServices == null)
            companyTransportServices = new CompanyTransportServices();

        return companyTransportServices;
    }

    public static ContractTypeServices getContractTypeServices() {
        if (contractTypeServices == null)
            contractTypeServices = new ContractTypeServices();

        return contractTypeServices;
    }
    public static FoodPlanServices getFoodPlanServices() {
        if (foodPlanServices == null)
            foodPlanServices = new FoodPlanServices();

        return foodPlanServices;
    }

    public static HotelFranchiseServices getHotelFranchiseServices() {
        if (hotelFranchiseServices == null)
            hotelFranchiseServices = new HotelFranchiseServices();

        return hotelFranchiseServices;
    }

    public static LocalizationServices getLocalizationServices() {
        if (localizationServices == null)
            localizationServices = new LocalizationServices();

        return localizationServices;
    }

    public static ModalityComertialServices getModalityComertialServices() {
        if (modalityComertialServices == null)
            modalityComertialServices = new ModalityComertialServices();

        return modalityComertialServices;
    }

    public static ProvinceServices getProvinceServices() {
        if (provinceServices == null)
            provinceServices = new ProvinceServices();

        return provinceServices;
    }

    public static RoomTypeServices getRoomTypeServices() {
        if (roomTypeServices == null)
            roomTypeServices = new RoomTypeServices();

        return roomTypeServices;
    }

    public static UserServices getUserServices() {
        if (userServices == null)
            userServices = new UserServices();

        return userServices;
    }

    public static VehicleServices getVehicleServices() {
        if (vehicleServices == null)
            vehicleServices = new VehicleServices();

        return vehicleServices;
    }

    public static VehicleBrandServices getVehicleBrandServices() {
        if (vehicleBrandServices == null)
            vehicleBrandServices = new VehicleBrandServices();

        return vehicleBrandServices;
    }

    public static ServiceTypeServices getServiceTypeServices() {
        if (serviceTypeServices == null)
            serviceTypeServices = new ServiceTypeServices();

        return serviceTypeServices;
    }
}
