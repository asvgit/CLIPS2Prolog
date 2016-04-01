package guiPack;

import mainPack.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

/**
 * Created by ArlSava on 21.10.2015.
 */
public class CMainForm extends JFrame {
    private CModelGUI myModel;
    private CMainMenu myMenu;
    private JTextPane txtPane;
    private JTabbedPane tabbedPane;
    private ArrayList<JTextPane> texts;
    CMainForm(CModelGUI mm){
        super();
        myModel=mm;
        txtPane = new JTextPane();
        buildForm();
    }
    CMainMenu getMenu(){
        return myMenu;
    }
    void setClosing(WindowAdapter wa){
        this.addWindowListener(wa);
    }
    void assertVisible(){
        this.setVisible(true);
    }
    void retracttVisible(){
        this.setVisible(false);
    }
    void setText(String s){
        txtPane.setText(s);
    }
    void chabgeContent(){
        tabbedPane = new JTabbedPane();
        texts = new ArrayList<JTextPane>();
        texts.add(txtPane);
        addTab(Consts.TTINPUT,txtPane);
        this.setContentPane(tabbedPane);
    }
    void setText(String title,String text){
        if(tabbedPane!=null){
            addTab(title,text);
        }
    }
    String getText(){
        return txtPane.getText();
    }
    private void buildForm(){
        //Global
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Consts.TITLEMAINFORM);
        this.setSize(Consts.WIDHT,Consts.HEIGHT);
        this.setLocationRelativeTo(null);
        ScrollPane forTxtPane = new ScrollPane();
        forTxtPane.add(txtPane);
        this.setContentPane(forTxtPane);
        //--global
        myMenu=new CMainMenu();
        this.setMenuBar(myMenu.getMenu());
    }
    private void addTab(String title,String text){
        JTextPane txt = new JTextPane();
        ScrollPane for_txt = new ScrollPane();
        txt.setText(text);
        texts.add(txt);
        for_txt.add(txt);
        tabbedPane.addTab(title,for_txt);
    }
    private void addTab(String title,JTextPane text){
        ScrollPane for_txt = new ScrollPane();
        texts.add(text);
        for_txt.add(text);
        tabbedPane.addTab(title,for_txt);
    }
}
