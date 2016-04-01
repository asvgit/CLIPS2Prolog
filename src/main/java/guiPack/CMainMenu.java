package guiPack;

import mainPack.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ArlSava on 21.10.2015.
 */
public class CMainMenu {
    private MenuBar mainMenu;
    private Menu mFile = new Menu(Consts.TMFILE);
    private MenuItem miOpen = new MenuItem(Consts.TMIOPEN);
    private MenuItem miSave = new MenuItem(Consts.TMISAVE);
    private Menu mExecution = new Menu(Consts.TMEXECUTION);
    private MenuItem miRun = new MenuItem(Consts.TMIRUN);

    CMainMenu(){
        mainMenu = new MenuBar();
        buildMenu();
    }
    MenuBar getMenu(){
        return mainMenu;
    }
    void setAOpen(ActionListener al){
        miOpen.addActionListener(al);
    }
    void setARun(ActionListener al){
        miRun.addActionListener(al);
    }
    void setASave(ActionListener al){
        miSave.addActionListener(al);
        miSave.setEnabled(false);
    }
    void setEnable(){
        miSave.setEnabled(true);
    }
    private void buildMenu(){
        mainMenu.add(mFile);
        mFile.add(miOpen);
        mFile.add(miSave);
        mainMenu.add(mExecution);
        mExecution.add(miRun);
    }
}
