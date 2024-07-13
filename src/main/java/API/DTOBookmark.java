package API;


import java.util.List;

public class DTOBookmark {


    private List<BookmarkInfo> bookmarks;

    public static class BookmarkInfo {

        private int bookmarkId;
        private int bookmarkGroupId;  // foreign key from group db
        private String X_SWIFI_MGR_NO; // foreign key from wi-fi db
        private String registerDate;

        public String getBookmarkGroupName() {
            return bookmarkGroupName;
        }

        public void setBookmarkGroupName(String bookmarkGroupName) {
            this.bookmarkGroupName = bookmarkGroupName;
        }

        private String bookmarkGroupName;

        public int getBookmarkId() {
            return this.bookmarkId;
        }

        public void setBookmarkId(int bookmarkId) {
            this.bookmarkId = bookmarkId;
        }

        public int getBookmarkGroupId() {
            return this.bookmarkGroupId;
        }

        public void setBookmarkGroupId(int bookmarkGroupId) {
            this.bookmarkGroupId = bookmarkGroupId;
        }

        public String getX_SWIFI_MGR_NO() {
            return X_SWIFI_MGR_NO;
        }

        public void setX_SWIFI_MGR_NO(String x_SWIFI_MGR_NO) {
            X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
        }

        public String getRegisterDate() {
            return this.registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }
    }

    public List<BookmarkInfo> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<BookmarkInfo> bookmarks) {
        this.bookmarks = bookmarks;
    }
}

