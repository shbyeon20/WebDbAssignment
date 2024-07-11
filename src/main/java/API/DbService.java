package API;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DbService {

    private static DbService instance = new DbService();

    public static DbService getInstance() {
        return instance;
    }


    // 북마크id 혹은 그룹id 둘중 하나만 선택해도 삭제가능
    public void bookmarkDelete(int bookmarkId, int groupId) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        String sql = "Delete from bookmark where bookmarkId = ? or bookmarkGroupId = ? ;";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, DBpassword);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(bookmarkId));
            preparedStatement.setString(2, String.valueOf(groupId));


            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("북마크삭제성공");
            } else {
                System.out.println("북마크삭제실패");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


// 북마크 그룹을 삭제하기전 북마크 그룹에 연동된 북마크를 우선 삭제하도록 설정
    public void bookmarkGroupDelete(int groupId) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        bookmarkDelete(0,groupId);
        String sql = "Delete from bookmarkGroup where groupId = ? ;";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, DBpassword);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(groupId));
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("북마크그룹삭제성공");
            } else {
                System.out.println("북마크그룹삭제실패");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



    public void bookmarkGroupUpdate(String name, String groupId) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "update bookmarkGroup set name = ?, revisionDate = now() where groupId = ? ;";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, DBpassword);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, groupId);

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



    public void bookmarkGroupInsert(DTOBookmarkGroup dtoBookmarkGroup) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, DBpassword);

            int affected = 0;

            String sql = "INSERT INTO wifiinfo (groupId, name, sequenceOrder, registerDate, revisionDate, userId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);


            List<DTOBookmarkGroup.BookmarkGroupInfo> bookmarkGroups = dtoBookmarkGroup.getBookmarkGroups();
            for (DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroup : bookmarkGroups) {
                preparedStatement.setString(1, bookmarkGroup.getGroupId());
                preparedStatement.setString(2, bookmarkGroup.getName());
                preparedStatement.setString(3, bookmarkGroup.getSequenceOrder());
                preparedStatement.setString(4, bookmarkGroup.getRegisterDate());
                preparedStatement.setString(5, bookmarkGroup.getRevisionDate());
                preparedStatement.setString(6,bookmarkGroup.getUserId());
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


    public void dbWifiInsert(DTOWifi DTOWifi) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, DBpassword);

            int affected = 0;

            String sql = "INSERT INTO wifiinfo (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);


            List<DTOWifi.WifiInfo> wifiInfos = DTOWifi.getTbPublicWifiInfo().getList();
            for (DTOWifi.WifiInfo wifiInfo : wifiInfos) {
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




    public DTOBookmarkGroup bookmarkGroupSelect() {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";

        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";


        DTOBookmarkGroup dtoBookmarkGroup = new DTOBookmarkGroup();
        List<DTOBookmarkGroup.BookmarkGroupInfo> list = new ArrayList<>();


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

            String sql = "SELECT groupId,name,sequenceOrder,registerDate,revisionDate from bookmarkGroup bg;";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroupInfo = new DTOBookmarkGroup.BookmarkGroupInfo();
                bookmarkGroupInfo.setGroupId(resultSet.getString("groupId"));
                bookmarkGroupInfo.setName(resultSet.getString("name"));
                bookmarkGroupInfo.setSequenceOrder(resultSet.getString("sequenceOrder"));
                bookmarkGroupInfo.setRegisterDate(resultSet.getString("registerDate"));
                bookmarkGroupInfo.setRevisionDate(resultSet.getString("revisionDate"));
                list.add(bookmarkGroupInfo);
            }
            dtoBookmarkGroup.setBookmarkGroups(list);


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
        return dtoBookmarkGroup;
    }

    public DTOBookmark bookmarkSelect(int groupid) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";

        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";


        DTOBookmark dtoBookmark = new DTOBookmark();
        List<DTOBookmark.BookmarkInfo> list = new ArrayList<>();


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

            String sql = "SELECT bookmarkId ,X_SWIFI_MGR_NO, registerDate from bookmark b\n" +
                    "where bookmarkGroupId = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,groupid);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOBookmark.BookmarkInfo bookmarkInfo = new DTOBookmark.BookmarkInfo();
                bookmarkInfo.setBookmarkId(resultSet.getString("bookmarkId"));
                bookmarkInfo.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
                bookmarkInfo.setRegisterDate(resultSet.getString("registerDate"));
                list.add(bookmarkInfo);
            }
            dtoBookmark.setBookmarks(list);


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
        return dtoBookmark;
    }

    public DTOWifi wifiCloseSelect(String LAT, String LTD, String tablename, int pagenum) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";

        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";


        DTOWifi DTOWifi = new DTOWifi();
        DTOWifi.TbPublicWifiInfo tbPublicWifiInfo = new DTOWifi.TbPublicWifiInfo();
        List<DTOWifi.WifiInfo> wifiInfos = new LinkedList<DTOWifi.WifiInfo>();


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

            String sql = String.format("SELECT \n" +
                    " SQRT(POW(LAT - %s, 2) + POW(LNT - -%s, 2)) AS distance,\n" +
                    " w.*\n" +
                    "FROM %s w\n" +
                    "ORDER BY distance\n" +
                    "LIMIT %d;",LAT,LTD,tablename,pagenum);

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOWifi.WifiInfo wifiInfo = new DTOWifi.WifiInfo();

                wifiInfo.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
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
            DTOWifi.setTbPublicWifiInfo(tbPublicWifiInfo);

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
        return DTOWifi;
    }

    public DTOWifi wifiOneSelect(String tablename, int pagenum, String X_SWIFI_MGR_NO) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";


        DTOWifi DTOWifi = new DTOWifi();
        DTOWifi.TbPublicWifiInfo tbPublicWifiInfo = new DTOWifi.TbPublicWifiInfo();
        List<DTOWifi.WifiInfo> wifiInfos = new LinkedList<DTOWifi.WifiInfo>();


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
                    "from " + tablename +" "+
                    "Where X_SWIFI_MGR_NO ="+
                    X_SWIFI_MGR_NO +
                    "\nlimit " + pagenum;

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOWifi.WifiInfo wifiInfo = new DTOWifi.WifiInfo();

                wifiInfo.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
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
            DTOWifi.setTbPublicWifiInfo(tbPublicWifiInfo);

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
        return DTOWifi;
    }
}




