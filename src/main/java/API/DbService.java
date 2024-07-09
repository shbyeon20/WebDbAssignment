package API;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class DbService {


    public void Dbdelete() {
        String url = "jdbc:mariadb://localhost:3306/test1";
        String user = "testuser1";
        String DBpassword = "!tkdghk6226";

        String sql = "Delete from zerobase_member1 where email = ?  And password = ?;";
        String email = "jjh@gmail.com";
        String password = "1234";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, DBpassword);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("삭제성공");
            } else {
                System.out.println("삭제실패");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public void DbUpdate() {
        String url = "jdbc:mariadb://localhost:3306/test1";
        String user = "testuser1";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String email = "bsh6226@gmail(4).com";
        String password = "4321";
        String newpassword = "1234";


        String sql = "update zerobase_member1 set password = ? where email = ? and password = ?;";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, DBpassword);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newpassword);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);


            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("수정성공");
            } else {
                System.out.println("수정실패");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (preparedStatement != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public void DbInsert(DataContainer dataContainer) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, DBpassword);

            int affected = 0;

            String sql = "INSERT INTO wifiinfo (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);


            List<DataContainer.WifiInfo> wifiInfos = dataContainer.getTbPublicWifiInfo().getList();
            for (DataContainer.WifiInfo wifiInfo : wifiInfos) {
                preparedStatement.setString(1, wifiInfo.getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, wifiInfo.getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, wifiInfo.getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, wifiInfo.getX_SWIFI_ADRES1());
                preparedStatement.setString(5, wifiInfo.getX_SWIFI_ADRES2());
                preparedStatement.setString(6, wifiInfo.getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, wifiInfo.getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, wifiInfo.getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, wifiInfo.getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, wifiInfo.getX_SWIFI_CMCWR());
                preparedStatement.setInt(11, wifiInfo.getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, wifiInfo.getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, wifiInfo.getX_SWIFI_REMARS3());
                preparedStatement.setDouble(14, wifiInfo.getLAT());
                preparedStatement.setDouble(15, wifiInfo.getLNT());
                preparedStatement.setString(16, wifiInfo.getWORK_DTTM());
                affected = preparedStatement.executeUpdate();
            }

            if (affected > 0) {
                System.out.println("저장성공");
            } else {
                System.out.println("저장실패");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public DataContainer DbSelect() {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";


        DataContainer dataContainer = new DataContainer();
        DataContainer.TbPublicWifiInfo tbPublicWifiInfo = new DataContainer.TbPublicWifiInfo();
        List<DataContainer.WifiInfo> wifiInfos = new LinkedList<DataContainer.WifiInfo>();


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, user, DBpassword);

            String sql = "select *\n" +
                    "from wifiinfo \n" +
                    "limit 20";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DataContainer.WifiInfo wifiInfo = new DataContainer.WifiInfo();

                wifiInfo.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
                System.out.println(wifiInfo.getX_SWIFI_MGR_NO());
                wifiInfo.setX_SWIFI_WRDOFC(resultSet.getString("X_SWIFI_WRDOFC"));
                wifiInfo.setX_SWIFI_MAIN_NM(resultSet.getString("X_SWIFI_MAIN_NM"));
                wifiInfo.setX_SWIFI_ADRES1(resultSet.getString("X_SWIFI_ADRES1"));
                wifiInfo.setX_SWIFI_ADRES2(resultSet.getString("X_SWIFI_ADRES2"));
                wifiInfo.setX_SWIFI_INSTL_FLOOR(resultSet.getString("X_SWIFI_INSTL_FLOOR"));
                wifiInfo.setX_SWIFI_INSTL_TY(resultSet.getString("X_SWIFI_INSTL_TY"));
                wifiInfo.setX_SWIFI_INSTL_MBY(resultSet.getString("X_SWIFI_INSTL_MBY"));
                wifiInfo.setX_SWIFI_SVC_SE(resultSet.getString("X_SWIFI_SVC_SE"));
                wifiInfo.setX_SWIFI_CMCWR(resultSet.getString("X_SWIFI_CMCWR"));
                wifiInfo.setX_SWIFI_CNSTC_YEAR(resultSet.getInt("X_SWIFI_CNSTC_YEAR"));
                wifiInfo.setX_SWIFI_INOUT_DOOR(resultSet.getString("X_SWIFI_INOUT_DOOR"));
                wifiInfo.setX_SWIFI_REMARS3(resultSet.getString("X_SWIFI_REMARS3"));
                wifiInfo.setLAT(resultSet.getDouble("LAT"));
                wifiInfo.setLNT(resultSet.getDouble("LNT"));
                wifiInfo.setWORK_DTTM(resultSet.getString("WORK_DTTM"));
                wifiInfos.add(wifiInfo);
            }
            tbPublicWifiInfo.setList(wifiInfos);
            dataContainer.setTbPublicWifiInfo(tbPublicWifiInfo);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        return dataContainer;
    }
}

