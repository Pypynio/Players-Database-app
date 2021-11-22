import java.io.FileInputStream;
import java.util.Properties;

public final class PropertiesLoader {
    private static String dataBaseUrl;
    private static String urlToJson;
    private static String insert;
    private static String secondJson;
    private static String urlToNickList;

    public static void readProperties() throws Exception{
        FileInputStream fis = new FileInputStream("C:\\Users\\marci\\OneDrive\\Pulpit\\ObjectToDB\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        urlToJson = prop.getProperty("PathToJson");
        dataBaseUrl = prop.getProperty("UrlToDataBase");
        insert = prop.getProperty("InsertPlayer");
        secondJson = prop.getProperty("PathToSecondInsert");
        urlToNickList = prop.getProperty("PathToNickList");

    }

    public static String getDataBaseUrl() {
        return dataBaseUrl;
    }
    public static String getUrlToNickList(){ return urlToNickList;}

    public static String getUrlToJson() {
        return urlToJson;
    }

    public static String getInsert() {
        return insert;
    }
    public static String getSecondJson(){
        return secondJson;
    }
}
