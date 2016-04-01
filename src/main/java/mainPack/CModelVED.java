package mainPack;

import dbPack.CModelDB;
import guiPack.CModelGUI;
import lexPack.CModelLEX;
import syntPack.CModelSYNT;
import vvodPack.CModelVVOD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;

/**
 * Created by ArlSava on 21.10.2015.
 */
public class CModelVED {
    private CModelGUI guiModel;
    private CModelDB dbModel;
    private CModelVVOD vvodModel;
    private CModelSYNT syntModel;
    private CModelLEX lexModel;
    private String myResult = "";

    public CModelVED(){
        guiModel = new CModelGUI(this,WA);
        dbModel = new CModelDB(this);
        vvodModel = new CModelVVOD(this);
        syntModel = new CModelSYNT(this);
        lexModel = new CModelLEX(this);
        guiModel.setARun(ARun);
        guiModel.setAOpen(AOpen);
        guiModel.setASave(ASave);
    }
    public void showGUI(){
        guiModel.assertVisible();
    }
    public String[][] getRules(){
        return dbModel.getRules();
    }
    public void Save(){
        LibSaveFile save = new LibSaveFile(myResult);
    }
    //actions
    private WindowAdapter WA = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            try {
                dbModel.delTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            dbModel.shutdownBD();
            super.windowClosing(e);
        }
    };
    private ActionListener ARun = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(!guiModel.getText().equals("")) {
                String[][] base;
                guiModel.gangeContent();
                //guiModel.setText(Consts.TTSYNT,syntModel.addSpases(guiModel.getText()));
                try {
                    base = syntModel.getChange(syntModel.addSpases(guiModel.getText()), dbModel.getDictionary());
                    dbModel.setSYNTResult(base);
                    lexModel.setBase(base);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                //guiModel.setText(Consts.TTSYNT,syntModel.getChange(syntModel.addSpases(guiModel.getText()),dbModel.getDictionary()));
                guiModel.setText(Consts.TTSYNT, syntModel.getChange());
                guiModel.setText(Consts.TTLEX, lexModel.getResult());
                myResult = lexModel.getInter();
                guiModel.setText(Consts.TTINTER, myResult);
                guiModel.setEnable();
            }else{
                int x = JOptionPane.showConfirmDialog(null,"Введена пустая последовательность!","Error",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
            }
        }
    };
    private ActionListener ASave = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Save();
        }
    };
    private ActionListener AOpen = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            guiModel.setText(vvodModel.readFile());
        }
    };

    private class LibSaveFile {
        final String MTASK="Файл с таким именем уже существует!";
        final String MASK="Заменить этот файл?";
        final String MTERROR="Ошибка!";
        final String MERROR="Произошла ошибка.\nСледует проверить способ эксплуатации или обратиться к программисту.\nsava_arl@mail.ru";

        public LibSaveFile(String text) {
            try {
                String s="";
                String fn;
                JFileChooser fileopen = new JFileChooser();
                if(fileopen.showSaveDialog(null) ==0){
                    File wFile = fileopen.getSelectedFile();
                    if(wFile.exists()){
                        JOptionPane qp = new JOptionPane();
                        int x = JOptionPane.showConfirmDialog(null,MASK,MTASK,JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(x==JOptionPane.CANCEL_OPTION)
                            return;
                    }else{
                        FileWriter wf = new FileWriter(wFile);
                        wf.write(text.toCharArray());
                        wf.close();
                    }
                }
            } catch (Exception e) {
                showMessEr();
            }
        }

        public void showMessEr(){
            int x = JOptionPane.showConfirmDialog(null,MERROR,MTERROR,JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
        }

    }
}
