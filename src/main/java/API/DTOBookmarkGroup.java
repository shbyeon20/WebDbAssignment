package API;

import java.util.List;

public class DTOBookmarkGroup {


    List<BookmarkGroupInfo> bookmarkGroups;

    public static class BookmarkGroupInfo {
    String groupId;
    String name;
    String sequenceOrder;
    String registerDate;
    String revisionDate;
    String userId;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSequenceOrder() {
            return sequenceOrder;
        }

        public void setSequenceOrder(String sequenceOrder) {
            this.sequenceOrder = sequenceOrder;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getRevisionDate() {
            return revisionDate;
        }

        public void setRevisionDate(String revisionDate) {
            this.revisionDate = revisionDate;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public List<BookmarkGroupInfo> getBookmarkGroups() {
        return bookmarkGroups;
    }

    public void setBookmarkGroups(List<BookmarkGroupInfo> bookmarkGroups) {
        this.bookmarkGroups = bookmarkGroups;
    }
}
