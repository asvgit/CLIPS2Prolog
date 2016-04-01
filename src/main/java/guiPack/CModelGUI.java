package guiPack;

import mainPack.CModelVED;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Created by ArlSava on 21.10.2015.
 */
public class CModelGUI{
    CModelVED myModel;
    CMainForm mainForm;
    CMainMenu mainMenu;
    public CModelGUI(CModelVED mm, WindowAdapter wa){
        myModel=mm;
        mainForm = new CMainForm(this);
        setShutDown(wa);
        mainMenu=mainForm.getMenu();
    }
    public void assertVisible(){
        mainForm.assertVisible();
    }
    public void retracttVisible(){
        mainForm.retracttVisible();
    }
    public void setARun(ActionListener al){
        mainMenu.setARun(al);
    }
    public void setAOpen(ActionListener al){
        mainMenu.setAOpen(al);
    }
    public void setASave(ActionListener al){
        mainMenu.setASave(al);
    }
    public void setEnable(){
        mainMenu.setEnable();
    }
    public void setText(String s){
        mainForm.setText(s);
    }
    public void gangeContent(){
        mainForm.chabgeContent();
    }
    public void setText(String title,String text){
        mainForm.setText(title,text);
    }
    public String getText(){
        return mainForm.getText();
    }
    private void setShutDown(WindowAdapter wa){
        mainForm.setClosing(wa);
    }
}
