package vvodPack;

import java.io.File;
/**
 * Created by ArlSava on 22.10.2015.
 */
public class ExtFileFilter extends javax.swing.filechooser.FileFilter {
    String ext;
    String description;

    ExtFileFilter(String ext, String descr) {
    this.ext = ext;
    description = descr;
    }
    public boolean accept(File f) {
            if (f != null) {
            if (f.isDirectory()) {
            return true;
        }
        String extension = getExtension(f);
        if (extension == null)
        return (ext.length() == 0);
        return ext.equals(extension);
        }
        return false;
    }
    public String getDescription() {
            return description;
    }
    public String getExtension(File f) {
            if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
            return filename.substring(i + 1).toLowerCase();
            }
            }
            return null;
    }
}
