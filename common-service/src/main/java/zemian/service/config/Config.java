package zemian.service.config;

import java.util.List;
import java.util.Set;

/**
 * A config service that provide configuration properties for an application.
 * 
 * <p>A implementation should take care to load the data from a certain source
 * such as Properties file or database.</p>
 * 
 * @author zedeng
 */
public interface Config {
    String getConfigName();
    Set<String> getKeys();
    String getValue(String key);
    List<String> getGroupValues(String groupKey);
    Config getParent();
}
