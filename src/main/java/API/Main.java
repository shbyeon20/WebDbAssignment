package API;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //API.DbService dbService = new API.DbService();
        //dbService.DbSelect();

        // api 서비스를 호출, json으로 변화, datacontinaer에 삽입
        APIJsonReceive apiJsonReceive = new APIJsonReceive();
        DataContainer dataContainer = new DataContainer();
        DataContainer.TbPublicWifiInfo tbPublicWifiInfo = new DataContainer.TbPublicWifiInfo();
        List<DataContainer.WifiInfo> wifiInfos = new ArrayList<>();



        DbService dbService = new DbService();
        dataContainer = dbService.DbSelect();
        tbPublicWifiInfo = dataContainer.getTbPublicWifiInfo();
        wifiInfos = tbPublicWifiInfo.getList();

        for (DataContainer.WifiInfo wifiInfo : wifiInfos) {
            wifiInfo.getX_SWIFI_MAIN_NM();
        }




       /* int num =0;
        while (dataContainer !=null) { // api data 끝이 도달하면 null을 생성
            dataContainer = apiJsonReceive.getDataContainer(apiJsonReceive.JsonCreate(1+1000*num, 1000*(num+1)));
            num++;
            if (dataContainer != null) {
                dbService.DbInsert(dataContainer);
                System.out.println(dataContainer.getTbPublicWifiInfo().getList().size());
            }

        }*/






    }
}
