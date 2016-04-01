package lexPack;

import mainPack.CModelVED;
import mainPack.Consts;

import java.util.ArrayList;

/**
 * Created by ArlSava on 03.12.2015.
 */
public class CModelLEX {
    private CModelVED myModel;
    private String[][] myBase;
    private ArrayList<CCell> cells;
    private CCell generalCell;
    //inter
    private ArrayList<String> facts = new ArrayList<String>();
    private ArrayList<String> rules = new ArrayList<String>();

    public CModelLEX(CModelVED mm){
        myModel = mm;
        myBase=myModel.getRules();
    }

    public void setBase(String[][] base){
        cells = makeFirstLine(base);
        makeTree();
        makro();
    }

    public String getInter(){
        String s = "";
        s=generalCell.getInter();
        return putFacts()+putRules()+s;
    }

    public String getResult(){
        return generalCell.toString("");
    }

    public void addFact(String f){
        facts.add(f);
    }

    public void addRule(String r){
        rules.add(r);
    }

    private String putFacts(){
        ArrayList<String> lines = new ArrayList<String>();
        String str = ":-dynamic([";
        String fact = "";
        String s = "";
        int n=0;
        for(int i=0;i<facts.size();i++){
            if(facts.get(i).equals("+1"))
                n++;
            else {
                if(!fact.equals("")) {
                    lines.add(str + fact + "/" + n + "]).\n");
                }
                fact=facts.get(i);
                n=0;
            }
        }
        for(int i=0;i<lines.size()-1;i++){
            if(lines.get(i).equals(lines.get(i+1))) {
                lines.remove(i + 1);
                i--;
            }
        }
        for(int i=0;i<lines.size();i++)
            s+=lines.get(i);
        return s;
    }

    private String putRules(){
        String s="";
        for(int i=0;i<rules.size();i++){
            s+=":-initialization("+rules.get(i)+").\n";
        }
        return s.substring(0,s.length()-2)+".\n";
    }

    private void makro(){
        for(int i=0;i<cells.size()-1;i++)
            if(cells.get(i).getName().equals(cells.get(i+1).getName())){
                CCell newCell = new CCell(cells.get(i).getName());
                newCell.addCell(cells.get(i+1));
                newCell.addCell(cells.get(i));
                cells.remove(i+1);
                cells.remove(i);
                cells.add(i,newCell);
                i--;
            }
        if(cells.size()==2){
            generalCell = new CCell(myBase[0][0].substring(0,myBase[0][0].length()));
            generalCell.addCell(cells.get(1));
            generalCell.addCell(cells.get(0));
        }
    }

    private ArrayList<CCell> makeFirstLine(String[][] base){
        ArrayList<CCell> c = new ArrayList<CCell>();
        for(int i=0;i<base.length;i++){
            c.add(new CCell(base[i][0],base[i][1],this));
        }
        return c;
    }

    private void makeTree(){
        for(int i=0;i<cells.size();i++){
            if(cells.get(i).getName().equals(Consts.BRSTART)){
                CBotton botton = isBotton(i,cells);
                if(botton!=null){
                    if(checkRuleUp(i, botton.getEnd()))
                        makeTree();
                    return;
                }
            }
        }
    }

    private boolean checkRuleUp(int start,int end){
        for(int i=end;i>=start;i--){
            if(checkRuleDown(start,i))
                return true;
        }
        return false;
    }

    private boolean checkRuleDown(int start,int end){
        int st=start;
        int en=end;
        String s=cellsToString(st,en,cells);
        for(int i=0;i<myBase.length;i++)
            if(s.equals(myBase[i][1])){
                //System.out.println("true");
                //System.out.println(myBase[i][0].substring(0,myBase[i][0].length()));
                CCell newCell = new CCell(myBase[i][0].substring(0,myBase[i][0].length()));
                for(int j=end;j>=start;j--) {
                    newCell.addCell(cells.get(j));
                    cells.remove(j);
                }
                cells.add(start,newCell);
                return true;
            }
        if(start==end)
            return false;
        if(checkRuleDown(st+1,end))
            return true;
        return false;
    }

    private String cellsToString(int start,int end, ArrayList<CCell> cells){
        String s="";
        for(int i=start;i<=end;i++)
            if(cells.get(i).isNonTerminal())
                s+=cells.get(i).getName()+" "+0+" ";
            else
                s+=cells.get(i).getName()+" "+1+" ";
        //System.out.println(s.substring(0,s.length()-1));
        return s.substring(0,s.length()-1);
    }
/*
    private String getRule(int ind){
        String s="";
        String
        for(int i=0;i<myBase[ind][1];i++)
            s+=base[i][0]+" "+base[i][1]+" ";
        return s.substring(0,s.length()-1);
    }
*/
    private CBotton isBotton(int ind, ArrayList<CCell> cells){
        for(int i=ind+1;i<cells.size();i++){
            if(cells.get(i).getName().equals(Consts.BRSTART)){
                return null;
            }
            if(cells.get(i).getName().equals(Consts.BREND)){
                return new CBotton(i);
            }
        }
        return null;
    }

    private class CBotton{
        private int indEnd;
        private boolean isBotton;
        public CBotton(int end){
            indEnd=end;
            isBotton=true;
        }
        public CBotton(){
            indEnd=0;
            isBotton=false;
        }
        public int getEnd(){
            return indEnd;
        }
        public boolean isBotton(){
            return isBotton;
        }
    }
}
