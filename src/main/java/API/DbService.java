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

        bookmarkDelete(0, groupId);
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

    public void deleteHistory(int historyId) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        String sql = "DELETE FROM wifihistory WHERE historyId = ?;";

        try (Connection connection = DriverManager.getConnection(url, user, DBpassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Class.forName("org.mariadb.jdbc.Driver");

            preparedStatement.setInt(1, historyId);
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("History record deletion successful");
            } else {
                System.out.println("History record deletion failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting history record: ", e);
        }
    }



    public void bookmarkGroupUpdate(String name, String groupId,String sequence) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "update bookmarkGroup set name =  ?, " +
                "sequenceOrder = ?, revisionDate = now() where groupId = ?";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, DBpassword);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(sequence));
            preparedStatement.setInt(3, Integer.parseInt(groupId));

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


    public void insertHistory( double lat, double lng) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, DBpassword);

            // Get the next history ID
            String sql = "SELECT COALESCE(MAX(historyId),0) + 1 AS nextId FROM wifiHistory;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int nextId = 1; // default to 1 if no records exist
            if (resultSet.next()) {
                nextId = resultSet.getInt("nextId");
            }
            resultSet.close();
            preparedStatement.close();

            // Insert the new history record
            sql = "INSERT INTO wifiHistory (historyId, lat, lng, queryTime) VALUES (?,  ?, ?, NOW());";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(Integer.valueOf(1), nextId);
            preparedStatement.setDouble(Integer.valueOf(2), lat);
            preparedStatement.setDouble(Integer.valueOf(3), lng);

            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("History insertion successful.");
            } else {
                System.out.println("History insertion failed.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert history record", e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close resources", e);
            }
        }
    }

    public void insertBookmark(String wifiMgrNo, String bookmarkGroupId) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, DBpassword);

            // Get the next bookmark ID
            String sql = "SELECT COALESCE(MAX(bookmarkId), 0) + 1 AS nextId FROM bookmark;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int nextId = 1; // default to 1 if no records exist
           if (resultSet.next()) {
            nextId = resultSet.getInt("nextId");
               System.out.println(nextId);
            }



            sql = "SELECT name FROM bookmarkGroup where groupId = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(Integer.valueOf(1),bookmarkGroupId);
            resultSet = preparedStatement.executeQuery();
            String bookmarkGroupName = null; // default to 1 if no records exist
            if (resultSet.next()) {
                bookmarkGroupName = resultSet.getString("name");
            }


            resultSet.close();
            preparedStatement.close();

            // Insert the new bookmark record
            sql = "INSERT INTO bookmark (bookmarkId, X_SWIFI_MGR_NO, bookmarkGroupId, registerDate, bookmarkGroupName) VALUES (?, ?, ?, NOW(), ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(Integer.valueOf(1), nextId);
            preparedStatement.setString(Integer.valueOf(2), wifiMgrNo);
            preparedStatement.setString(Integer.valueOf(3), bookmarkGroupId);
            preparedStatement.setString(Integer.valueOf(4), bookmarkGroupName);


            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("Bookmark insertion successful.");
            } else {
                System.out.println("Bookmark insertion failed.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert bookmark record", e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close resources", e);
            }
        }
    }


    public void bookmarkGroupInsert(DTOBookmarkGroup dtoBookmarkGroup) {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, DBpassword);

            // Get the next user ID
            String nextUserId = "1"; // Default user ID if none exists
            String sql = "SELECT groupId FROM bookmarkGroup ORDER BY groupId DESC LIMIT 1;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String latestUserId = resultSet.getString("groupId");
                nextUserId = String.valueOf(Integer.parseInt(latestUserId) + 1);
            }

            resultSet.close();
            preparedStatement.close();


            sql = "INSERT INTO bookmarkGroup (groupId, name, sequenceOrder, registerDate, revisionDate, userId) " +
                    "VALUES (?, ?, ?, ?, ?,?);";
            preparedStatement = connection.prepareStatement(sql);


            List<DTOBookmarkGroup.BookmarkGroupInfo> bookmarkGroups = dtoBookmarkGroup.getBookmarkGroups();
            for (DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroup : bookmarkGroups) {
                bookmarkGroup.setGroupId(nextUserId);
                preparedStatement.setString(1, bookmarkGroup.getGroupId());
                preparedStatement.setString(2, bookmarkGroup.getName());
                preparedStatement.setString(3, bookmarkGroup.getSequenceOrder());
                preparedStatement.setString(4, bookmarkGroup.getRegisterDate());
                preparedStatement.setString(5, bookmarkGroup.getRevisionDate());
                preparedStatement.setString(6, bookmarkGroup.getUserId());
                preparedStatement.executeUpdate();
            }

            System.out.println("저장성공");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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


    public DTOBookmark bookmarkSelect() {
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

            String sql = "SELECT t1.bookmarkId, t1.X_SWIFI_MGR_NO, t2.name as bookmarkGroupName , t1.registerDate FROM bookmark t1\n" +
                    "join bookmarkGroup t2 on t1.bookmarkGroupId = t2.groupId ORDER BY registerDate DESC;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOBookmark.BookmarkInfo bookmarkInfo = new DTOBookmark.BookmarkInfo();
                bookmarkInfo.setBookmarkId(resultSet.getInt("bookmarkId"));
                bookmarkInfo.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
                bookmarkInfo.setBookmarkGroupName(resultSet.getString("bookmarkGroupname"));
                bookmarkInfo.setRegisterDate(resultSet.getString("registerDate"));
                list.add(bookmarkInfo);
            }
            dtoBookmark.setBookmarks(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dtoBookmark;
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

            String sql = "SELECT groupId,name,sequenceOrder,registerDate,revisionDate from bookmarkGroup bg Order by sequenceOrder  ;";

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



    public DTOWifi wifiCloseSelect(String LAT, String LNT, String tablename, int pagenum) {
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
                    " FORMAT(SQRT(POW(LAT - %s, 2) + POW(LNT - %s, 2)),5) AS Distance,\n" +
                    " w.*\n" +
                    "FROM %s w\n" +
                    "ORDER BY Distance\n" +
                    "LIMIT %d;", LAT, LNT, tablename, pagenum);

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOWifi.WifiInfo wifiInfo = new DTOWifi.WifiInfo();
                wifiInfo.setDistance(resultSet.getString("Distance"));
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
                    "from " + tablename + " " +
                    "Where X_SWIFI_MGR_NO = ?"
                    + " \nlimit ?" ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,X_SWIFI_MGR_NO);
            preparedStatement.setInt(2, pagenum);

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


    public DTOHistory historySelect() {
        String url = "jdbc:mariadb://localhost:3306/seoulwifi";
        String user = "wifiuser";
        String DBpassword = "!tkdghk6226";

        DTOHistory dtoHistory = new DTOHistory();
        List<DTOHistory.HistoryInfo> histories = new ArrayList<>();

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
            String sql = "SELECT historyId, lat, lng, queryTime FROM wifihistory ORDER BY queryTime DESC;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DTOHistory.HistoryInfo historyInfo = new DTOHistory.HistoryInfo();
                historyInfo.setId(resultSet.getInt("historyId"));
                historyInfo.setLatitude(resultSet.getDouble("lat"));
                historyInfo.setLongitude(resultSet.getDouble("lng"));
                historyInfo.setAccessTime(resultSet.getString("queryTime"));
                histories.add(historyInfo);
            }
            dtoHistory.setHistoryInfos(histories);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dtoHistory;
    }

}





