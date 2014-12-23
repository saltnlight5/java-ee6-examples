package zemian.jsfexample.web.controller;

import java.io.File;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FileListing {

    private File dir = new File(".");

    public File getDir() {
        return dir;
    }

    public File[] getFiles() {
        return dir.listFiles();
    }
}
