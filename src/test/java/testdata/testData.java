package testdata;

public class testData {
	public static final String VALID_LONG_JSON = "{'id':'1630215c-2608-44b9-aad4-9d56d8aafd4c'," +
            "'firstName':'Dorris'," +
            "'lastName':'Keeling'," +
            "'email':'Darby_Leffler68@gmail.com'," +
            "'address':{" +
            "'street':'193 Talon Valley'," +
            "'city':'South Tate furt'," +
            "'zip':'47069'," +
            "'state':'IA'," +
            "'country':'US'}," +
            "'dateCreated':'2016-03-15T07:02:40.896Z'," +
            "'company':{" +
            "'name':'Denesik Group'," +
            "'website':'http://jodie.org'}," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";

    public static final String VALID_LONG_JSON_UPDATED = "{'id':'1630215c-2608-44b9-aad4-9d56d8aafd4c'," +
            "'firstName':'Test'," +
            "'lastName':'Update'," +
            "'email':'Darby_Leffler68@gmail.com'," +
            "'address':{" +
            "'street':'193 Talon Valley'," +
            "'city':'South Tate furt'," +
            "'zip':'47069'," +
            "'state':'IA'," +
            "'country':'US'}," +
            "'dateCreated':'2016-06-24T07:02:40.896Z'," +
            "'company':{" +
            "'name':'Denesik Group'," +
            "'website':'http://jodie.org'}," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";

    public static final String VALID_SHORT_JSON = "{'id':'short'," +
            "'firstName':'Ekal'," +
            "'lastName':'Golas'," +
            "'email':'ekalgolas@gmail.com'," +
            "'dateCreated':'2016-06-15T07:02:40.896Z'," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";

    /*
    No ID present
     */
    public static final String INVALID_SHORT_JSON = "{'firstName':'Ekal'," +
            "'lastName':'Golas'," +
            "'email':'ekalgolas@gmail.com'," +
            "'dateCreated':'2016-06-15T07:02:40.896Z'," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";
}
