package API;

import java.util.List;

public class DTOHistory {
    private List<HistoryInfo> HistoryInfos;

    public List<HistoryInfo> getHistoryInfos() {
        return HistoryInfos;
    }

    public void setHistoryInfos(List<HistoryInfo> historyInfos) {
        HistoryInfos = historyInfos;
    }

    public static class HistoryInfo {
        private int id;
        private double latitude;
        private double longitude;
        private String accessTime; // Assuming access time is stored as a String


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getAccessTime() {
            return accessTime;
        }

        public void setAccessTime(String accessTime) {
            this.accessTime = accessTime;
        }
    }
}