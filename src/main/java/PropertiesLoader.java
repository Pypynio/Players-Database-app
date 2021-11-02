import java.io.FileInputStream;
import java.util.Properties;

public final class PropertiesLoader {
    private static String dataBaseUrl;
    private static String urlToJson;
    private static String insert;

    public static void readProperties() throws Exception{
        FileInputStream fis = new FileInputStream("C:\\Users\\marci\\OneDrive\\Pulpit\\ObjectToDB\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        urlToJson = prop.getProperty("PathToJson");
        dataBaseUrl = prop.getProperty("UrlToDataBase");
        insert = prop.getProperty("InsertPlayer");

    }

    public static String getDataBaseUrl() {
        return dataBaseUrl;
    }

    public static String getUrlToJson() {
        return urlToJson;
    }

    public static String getInsert() {
        return insert;
    }
}
