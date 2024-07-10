package API;


class DTOBookmark {
    private String bookmarkId;
    private String bookmarkGroup;  // foreign key from group db
    private String X_SWIFI_MGR_NO; // foreign key from wi-fi db
    private String registerDate;
    private String userId;
}
