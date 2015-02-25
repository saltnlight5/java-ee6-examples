package zemian.service.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * A config service that reads from Java System Properties.
 * 
 * @author zedeng
 */
public class PropsConfig implements Config {
    private Config parent;
    private Properties props;
    
    public PropsConfig() {
        props = new Properties();
    }
    
    public PropsConfig(Properties props) {
        this.props = props;
    }
    
    public PropsConfig(File file) {
        FileReader reader = null;
        try {
            props = new Properties();
            reader = new FileReader(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Config file not found: " + file, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException("Failed to close Config file: " + file, ex);
            }
        }
    }

    @Override
    public String getConfigName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Set<String> getKeys() {
        return props.stringPropertyNames();
    }

    @Override
    public String getValue(String key) {
        return props.getProperty(key);
    }

    /**
     * Return all values from the properties that has keys prefix with groupKey 
     * value.
     * @param groupKey
     * @return 
     */
    @Override
    public List<String> getGroupValues(String groupKey) {
        List<String> result = new ArrayList<>();
        for (String key : getKeys()) {
            if (key.startsWith(groupKey)) {
                result.add(getValue(key));
            }
        }
        return result;
    }

    @Override
    public Config getParent() {
        return parent;
    }
}
