package API;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

public class Main {
    public static void main(String[] args) {

        DbService dbService = DbService.getInstance();

        DTOHistory dtoHistory = dbService.historySelect();
        List<DTOHistory.HistoryInfo> list = dtoHistory.getHistoryInfos();
        for (DTOHistory.HistoryInfo historyInfo : list) {
            System.out.println(historyInfo.getId());

        }

        /*
        // wifi detailed information showing test
        DTOWifi dtowifi = dbService.wifiOneSelect("wifiinfo",1,"WF140027");
        DTOWifi.TbPublicWifiInfo tbPublicWifiInfo;
        List <DTOWifi.WifiInfo> list;
        tbPublicWifiInfo = dtowifi.getTbPublicWifiInfo();
             list =   tbPublicWifiInfo.getList();


        for (DTOWifi.WifiInfo wifiInfo : list) {
            System.out.print(wifiInfo.getDistance());
            System.out.print(wifiInfo.getX_SWIFI_MGR_NO());
            System.out.print(wifiInfo.getX_SWIFI_WRDOFC());
            System.out.print(wifiInfo.getLAT());
            System.out.print(wifiInfo.getLNT());
            System.out.println();
/*


        }

        /* bookmarkgroup delete testing
        //dbService.bookmarkGroupUpdate("니니월드","2");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = now.format(formatter);



        DTOBookmarkGroup dtoBookmarkGroup = new DTOBookmarkGroup();
        DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroupInfo = new DTOBookmarkGroup.BookmarkGroupInfo();
        bookmarkGroupInfo.setName("매일새롭게이렇게");
        bookmarkGroupInfo.setSequenceOrder("4");
        bookmarkGroupInfo.setRegisterDate(String.valueOf(currentDate));

        List<DTOBookmarkGroup.BookmarkGroupInfo> list = new ArrayList<>();
        list.add(bookmarkGroupInfo);
        dtoBookmarkGroup.setBookmarkGroups(list);

        dbService.bookmarkGroupInsert(dtoBookmarkGroup);

        dbService.bookmarkGroupUpdate("슈카월드","1","1");
*/



    }




        }







