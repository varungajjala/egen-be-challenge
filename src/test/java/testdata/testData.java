package testdata;

public class testData {
	public static final String JSON_LONG = "{'id':'16sdfs123-1232c'," +
            "'firstName':'Micheal'," +
            "'lastName':'Clarke'," +
            "'email':'michealclarke@gmail.com'," +
            "'address':{" +
            "'street':'1255 E University Drive'," +
            "'city':'Tempe'," +
            "'zip':'85281'," +
            "'state':'Arizona'," +
            "'country':'US'}," +
            "'dateCreated':'2016-06-01T07:02:40.896Z'," +
            "'company':{" +
            "'name':'Egen Solution'," +
            "'website':'https://egen.solutions'}," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";

    public static final String JSON_LONG_UPDATED = "{'id':'16sdfs123-1232c'," +
            "'firstName':'Mike'," +
            "'lastName':'Tyson'," +
            "'email':'miketyson@gmail.com'," +
            "'address':{" +
            "'street':'1255 E University Drive'," +
            "'city':'Tempe'," +
            "'zip':'85281'," +
            "'state':'Arizona'," +
            "'country':'US'}," +
            "'dateCreated':'2016-06-02T07:12:40.896Z'," +
            "'company':{" +
            "'name':'Egen Solution'," +
            "'website':'https://egen.solutions'}," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";

    public static final String JSON_SHORT = "{'id':'16sdf'," +
            "'firstName':'Roger'," +
            "'lastName':'Federer'," +
            "'email':'rfederer@gmail.com'," +
            "'dateCreated':'2016-06-01T12:12:42.896Z'," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";

    /*
    No ID present
     */
    public static final String JSON_INVALID = "{'firstName':'Roger'," +
            "'lastName':'Federer'," +
            "'email':'rfederer@gmail.com'," +
            "'dateCreated':'2016-06-01T12:12:42.896Z'," +
            "'profilePic':'http://lorempixel.com/640/480/people'}";
}
