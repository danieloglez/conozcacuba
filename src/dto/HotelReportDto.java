package dto;

import dto.nom.HotelFranchiseDto;
import dto.nom.LocalizationDto;
import dto.nom.ProvinceDto;

public class HotelReportDto implements Dto{
    private int id;
    private String name;
    private String address;
    private String category;
    private String telephoneNumber;
    private String fax;
    private String email;
    private float distToCity;
    private float distToAirport;
    private int roomsAmount;
    private int floorsAmount;

    // References
    private HotelFranchiseDto hotelFranchise;
    private ProvinceDto province;
    private LocalizationDto localization;

    public HotelReportDto(int id, String name, String address, String category, String telephoneNumber, String fax, String email, float distToCity, float distToAirport, int roomsAmount, int floorsAmount, HotelFranchiseDto hotelFranchise, ProvinceDto province, LocalizationDto localization) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.telephoneNumber = telephoneNumber;
        this.fax = fax;
        this.email = email;
        this.distToCity = distToCity;
        this.distToAirport = distToAirport;
        this.roomsAmount = roomsAmount;
        this.floorsAmount = floorsAmount;
        this.hotelFranchise = hotelFranchise;
        this.province = province;
        this.localization = localization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getDistToCity() {
        return distToCity;
    }

    public void setDistToCity(float distToCity) {
        this.distToCity = distToCity;
    }

    public float getDistToAirport() {
        return distToAirport;
    }

    public void setDistToAirport(float distToAirport) {
        this.distToAirport = distToAirport;
    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(int roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public int getFloorsAmount() {
        return floorsAmount;
    }

    public void setFloorsAmount(int floorsAmount) {
        this.floorsAmount = floorsAmount;
    }

    public HotelFranchiseDto getHotelFranchise() {
        return hotelFranchise;
    }

    public void setHotelFranchise(HotelFranchiseDto hotelFranchise) {
        this.hotelFranchise = hotelFranchise;
    }

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
        this.province = province;
    }

    public LocalizationDto getLocalization() {
        return localization;
    }

    public void setLocalization(LocalizationDto localization) {
        this.localization = localization;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
