package API;


import java.util.List;

public class DTOBookmark {


    private List<BookmarkInfo> bookmarks;

     static class BookmarkInfo{

        private String bookmarkId;
        private String bookmarkGroup;  // foreign key from group db
        private String X_SWIFI_MGR_NO; // foreign key from wi-fi db
        private String registerDate;

        public String getBookmarkId() {
            return this.bookmarkId;
        }

        public void setBookmarkId(String bookmarkId) {
            this.bookmarkId = bookmarkId;
        }

        public String getBookmarkGroup() {
            return this.bookmarkGroup;
        }

        public void setBookmarkGroup(String bookmarkGroup) {
            this.bookmarkGroup = bookmarkGroup;
        }

        public String getX_SWIFI_MGR_NO() {
            return X_SWIFI_MGR_NO;
        }

        public void setX_SWIFI_MGR_NO(String x_SWIFI_MGR_NO) {
            X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
        }

        public String getRegisterDate(String registerDate) {
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

