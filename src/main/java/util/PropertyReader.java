package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;
    public PropertyReader(String configFile) {
        InputStream input = null;
        try {
            input = this.getClass().getClassLoader().getResourceAsStream(configFile);
            properties = new Properties();
            // load a properties file from class path, inside static method
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }


}
