package zemian.jsfexample.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import zemian.service.logging.Logger;

/**
 * Demonstrate Selection Inputs in Form processing.
 *
 * @author zedeng
 */
@ManagedBean
public class FormSelections {
    private static final Logger LOGGER = new Logger(FormSelections.class);

    @PostConstruct
    public void init() {
        // Selection inputs Examples
        initCentrySelectionDefault();
        initScriptEngineNames();
        initScriptEngineNameSelectionDefault();
    }

    private String centrySelection; // Method1
    private String scriptEngineNameSelection; // Method2
    // selection list box map: key=label, value=value
    private Map<String, String> scriptEngineNames;
    // Method3:
    // A third method to display a selectionItems is to use custom POJO that provide
    // label and value fields and then in UI you may use the "var" attribute to access
    // it.

    public Map<String, String> getScriptEngineNames() {
        return scriptEngineNames;
    }

    public String getScriptEngineNameSelection() {
        return scriptEngineNameSelection;
    }

    public void setScriptEngineNameSelection(String scriptEngineNameSelection) {
        this.scriptEngineNameSelection = scriptEngineNameSelection;
    }

    public String getCentrySelection() {
        return centrySelection;
    }

    public void setCentrySelection(String centrySelection) {
        this.centrySelection = centrySelection;
    }

    private void initCentrySelectionDefault() {
        centrySelection = "2100";
        LOGGER.debug("Default centrySelection=%s", centrySelection);
    }

    private void initScriptEngineNames() {
        // TODO: Can we cache this?
        scriptEngineNames = new LinkedHashMap<>();
        for (ScriptEngineFactory fac : new ScriptEngineManager().getEngineFactories()) {
            String label = fac.getEngineName() + fac.getExtensions();
            scriptEngineNames.put(label, fac.getLanguageName());
        }
        LOGGER.debug("Found %s engine names.", scriptEngineNames.size());
    }

    private void initScriptEngineNameSelectionDefault() {
        // We would like to default to Groovy selection if possible, else JavaScript
        if (new ScriptEngineManager().getEngineByName("Groovy") != null) {
            scriptEngineNameSelection = "Groovy";
        } else {
            scriptEngineNameSelection = "ECMAScript"; // JavaScript.
        }
        LOGGER.debug("Default scriptEngineNameSelection=%s", scriptEngineNameSelection);
    }

    ////////////////////////////////////////////////////////////////////////////
    // Process form and support methods.
    ////////////////////////////////////////////////////////////////////////////
    public String processForm() {
        LOGGER.info("Processing " + this);
        LOGGER.info("Done.");
        return "form-selections"; // Display same form again after process.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Form(");
        sb.append(", centrySelection=").append(centrySelection);
        sb.append(", scriptEngineNameSelection=").append(scriptEngineNameSelection);
        sb.append(")");
        return sb.toString();
    }

}
