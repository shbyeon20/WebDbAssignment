package API;


import java.util.List;

public class Main {
    public static void main(String[] args) {


        DbService dbService = DbService.getInstance();

        DTOWifi dtoWifi;
        DTOWifi.TbPublicWifiInfo tbPublicWifiInfo;
        List<DTOWifi.WifiInfo> wifiInfos;

        dtoWifi = dbService.WifiCloseSelect("10","20","wifiinfo", 20);
        tbPublicWifiInfo = dtoWifi.getTbPublicWifiInfo();
        wifiInfos = tbPublicWifiInfo.getList();

        for (DTOWifi.WifiInfo wifiInfo : wifiInfos) {
            System.out.println(wifiInfo);
        }

    }


        }







