package lexPack;

import mainPack.Consts;

import java.util.ArrayList;

/**
 * Created by ArlSava on 03.12.2015.
 */
public class CCell {
    private CModelLEX myModel;
    private String name;
    private String value;
    private boolean isNonTerminal;
    ArrayList<CCell> cells;
    /*public CCell(String n, String v){
        name = n;
        value = v;
        isNonTerminal=false;
    }*/
    public CCell(String n, String v,CModelLEX mm){
        name = n;
        value = v;
        isNonTerminal=false;
        myModel=mm;
    }
    public CCell(String n){
        name = n;
        value = "";
        isNonTerminal=true;
        cells=new ArrayList<CCell>();
        /*for(int i=0;i<c.size();i++)
            cells.add(c.get(i));*/
    }
    public String toString(String tab){
        String s = "";
        s=tab+name+" ("+value+")\n";
        if(isNonTerminal) {
            for (int i = 0; i < cells.size(); i++)
                s += cells.get(i).toString(tab + "  ");
            s += tab + "\\" + name + "\n";
        }
        return s;
    }
    public String getName(){
        return name;
    }
    public String getValue(){
        return value;
    }
    public boolean isNonTerminal(){
        return  isNonTerminal;
    }
    public void addCell(CCell cell){
        if(!isNonTerminal()) {
            System.out.print("WTF?!");
            return;
        }
        cells.add(0,cell);
    }
    public String getInter(){
        String s="";
        s+=myInter();
        return s;
    }
    private String myInter(){
        String s="";
        if(name.equals(Consts.BRSTART))
            return "";
        if(name.equals(Consts.BREND))
            return "";
        if(name.equals(Consts.PRINTNAMECALLING))
            return "write(";
        if(name.equals(Consts.LNCALLING))
            return ",nl";
        if(name.equals(Consts.IDCALLING))
            return ""+(char)(value.charAt(0)-32);
        if(name.equals(Consts.STRINGCALLING)) {
            myModel.addFact("+1");
            return value;
        }
        if(name.equals(Consts.FACTCALLING)) {
            myModel.addFact(value);
            return value + "(";
        }
        if(name.equals(Consts.FACTSCALLING)) {
            myModel.addRule(value);
            return value + ":- ";
        }
        if(name.equals(Consts.TEXTCALLING))
            return "\'"+value+"\'";
        if(name.equals(Consts.CONSTSCALLING))
            return makeConst();
        if(name.equals(Consts.THEFACTCALLING))
            return makeTheFact();
        if(name.equals(Consts.FACTSCOLLING))
            return makeFacts();
        if(name.equals(Consts.DEFFACTSCALLING))
            return makeDefFacts();
        if(name.equals(Consts.DEFRULESCALLING))
            return makeDefRule();
        if(name.equals(Consts.RULECALLING)) {
            myModel.addRule(value);
            return value + ":- ";
        }
        if(name.equals(Consts.PRECONDITIONSCALLING))
            return makePreconditions();
        if(name.equals(Consts.PRECONDITIONCALLING))
            return makePrecondition();
        if(name.equals(Consts.OPERANDSCALLING))
            return makeOperands();
        if(name.equals(Consts.OPERANDCALLING))
            return makeOperand();
        if(name.equals(Consts.ACTIONSCALLING))
            return makeActions();
        if(name.equals(Consts.ACTIONCALLING))
            return makeAction();
        if(name.equals(Consts.PRINTCALLING))
            return makePrinting();
        if(name.equals(Consts.ASSIGNMENTCALLING))
            return makeAssignment();
        if(name.equals(Consts.PROGCALLING))
            return makeProg();

        return "";
    }
    private String makeProg(){
        String s="";
        for(int i=0;i<cells.size();i++){
            s+=cells.get(i).getInter();
        }
        return s;
    }
    private String makeConst(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter();
        else
            s+=cells.get(0).getInter()+", "+cells.get(1).getInter();
        return s;
    }
    private String makeTheFact(){
        String s="";
        for(int i=0;i<cells.size();i++){
            s+=cells.get(i).getInter();
        }
        return "asserta("+s+"))";
    }
    private String makeFacts(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter()+".\n";
        else
            s+=cells.get(0).getInter()+", \n"+cells.get(1).getInter();
        return s;
    }
    private String makeDefFacts(){
        String s="";
        for(int i=0;i<cells.size();i++){
            s+=cells.get(i).getInter();
        }
        return s;
    }
    private String makeDefRule(){
        String s="";
        for(int i=0;i<cells.size();i++){
            s+=cells.get(i).getInter();
        }
        return s;
    }
    private String makePreconditions(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter()+",";
        else
            s+=cells.get(0).getInter()+", "+cells.get(1).getInter();
        return s;
    }
    private String makePrecondition(){
        String s="";
        s+=cells.get(0).getInter();
        return s;
    }
    private String makeOperands(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter()+")";
        else
            s+=cells.get(0).getInter()+", "+cells.get(1).getInter();
        return s;
    }
    private String makeOperand(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter();
        else
            s+=cells.get(0).getInter()+", "+cells.get(1).getInter();
        return s;
    }
    private String makeActions(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter()+",fail;true.";
        else
            s+=cells.get(0).getInter()+", "+cells.get(1).getInter();
        return s;
    }
    private String makeAction(){
        String s="";
        if(cells.size()==1)
            s+=cells.get(0).getInter();
        else
            s+=cells.get(0).getInter()+"\n"+cells.get(1).getInter();
        return s;
    }
    private String makePrinting(){
        String s="";
        for(int i=0;i<cells.size();i++){
            s+=cells.get(i).getInter();
        }
        return s;
    }
    private String makeAssignment(){
        String s="";
        for(int i=0;i<cells.size();i++){
            s+=cells.get(i).getInter();
        }
        return s;
    }
}
