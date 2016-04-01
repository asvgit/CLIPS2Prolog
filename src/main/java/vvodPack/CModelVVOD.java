package vvodPack;

import mainPack.CModelVED;
import mainPack.Consts;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ArlSava on 22.10.2015.
 */
public class CModelVVOD {
    private CModelVED myModel;
    public CModelVVOD(CModelVED mm){
        myModel=mm;
    }
    public String readFile(){
        String s="";
        try {
            JFileChooser fileopen = new JFileChooser();
            fileopen.setFileFilter(new ExtFileFilter(Consts.FTYPE, Consts.FDESCR));
            if(fileopen.showDialog(null, Consts.TDOPENFILE)==0) {
                File wFile = fileopen.getSelectedFile();
                FileInputStream fStream = new FileInputStream(wFile);
                InputStreamReader iReader = new InputStreamReader(fStream, "windows-1251");
                BufferedReader reader = new BufferedReader(iReader);
                String line;
                ArrayList<String> lines = new ArrayList<String>();
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    s+=line+"\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
