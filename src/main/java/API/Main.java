package API;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        DbService dbService = DbService.getInstance();
        /* bookmarkgroup select testing
        DTOBookmarkGroup dtoBookmarkGroup = dbService.bookmarkGroupSelect();
        List<DTOBookmarkGroup.BookmarkGroupInfo> bookmarkgroups = dtoBookmarkGroup.getBookmarkGroups();

        DTOBookmark dtoBookmark = dbService.bookmarkSelect(1);
        List<DTOBookmark.BookmarkInfo> bookmarks = dtoBookmark.getBookmarks();


        for (DTOBookmark.BookmarkInfo bookmark : bookmarks) {
            System.out.println(bookmark.getX_SWIFI_MGR_NO());

        }*/

        // bookmarkgroup delete testing
        dbService.bookmarkGroupUpdate("니니월드","2");




    }




        }







