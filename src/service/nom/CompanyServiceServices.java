package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.VehicleBrandDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyServiceServices implements Services<CompanyServiceDto> {
    @Override
    public CompanyServiceDto load(int id_company_service) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_company_service_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_company_service);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new CompanyServiceDto(
                resultSet.getInt("id_company_service"),
                resultSet.getString("name")
        );
    }

    @Override
    public List<CompanyServiceDto> loadAll() throws SQLException {
        List<CompanyServiceDto> companiesService = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_company_service_load}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            companiesService.add(new CompanyServiceDto(
                    resultSet.getInt("id_company_service"),
                    resultSet.getString("name")
            ));
        }

        return companiesService;
    }

    @Override
    public void insert(CompanyServiceDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_service_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();

    }

    @Override
    public void update(CompanyServiceDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_service_update(?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

    }

    @Override
    public void delete(int id_company_service) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_service_delete(?)}");
        callableStatement.setInt(1, id_company_service);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
