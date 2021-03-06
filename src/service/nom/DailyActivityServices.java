package service.nom;

import dto.ContractServiceDto;
import dto.nom.CompanyTransportDto;
import dto.nom.DailyActivityDto;
import service.Relation;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DailyActivityServices implements Services<DailyActivityDto>, Relation<DailyActivityDto> {
    @Override
    public DailyActivityDto load(int id_daily_activity) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_daily_activity_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_daily_activity);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new DailyActivityDto(
                resultSet.getInt("id_daily_activity"),
                resultSet.getString("name")
        );
    }

    @Override
    public List<DailyActivityDto> loadAll() throws SQLException {
        List<DailyActivityDto> dailyActivityDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_daily_activity_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            dailyActivityDtos.add(new DailyActivityDto(
                    resultSet.getInt("id_daily_activity"),
                    resultSet.getString("name")
            ) {
            });
        }

        callableStatement.close();
        connection.close();
        return dailyActivityDtos;
    }

    @Override
    public void insert(DailyActivityDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_daily_activity_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(DailyActivityDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_daily_activity_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id_daily_activity) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_daily_activity_delete(?)}");
        callableStatement.setInt(1, id_daily_activity);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public List<DailyActivityDto> loadRelated(int id) throws SQLException {
        LinkedList<DailyActivityDto> activityDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_contract_service_n_daily_activity_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            activityDtoLinkedList.add(load(resultSet.getInt("id_daily_activity")));
        }

        callableStatement.close();
        connection.close();
        return activityDtoLinkedList;
    }
}
