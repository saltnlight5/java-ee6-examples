package zemian.mvcexample.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: zedeng
 * Date Time: 5/22/15 7:02 AM
 */
public class MView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public MView(String viewName) {
        this.viewName = viewName;
    }

    public void put(String name, Object data) {
        model.put(name, data);
    }

    public boolean hasData() {
        return model.size() > 0;
    }

    public String getViewName() {
        return viewName;
    }

    public Set<String> getNames() {
        return model.keySet();
    }

    public <T> T get(String name) {
        return (T)model.get(name);
    }

    @Override
    public String toString() {
        return "MView{" +
                "viewName='" + viewName + '\'' +
                ", model=" + model +
                '}';
    }
}
