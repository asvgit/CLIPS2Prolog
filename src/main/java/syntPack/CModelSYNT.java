package syntPack;

import mainPack.CModelVED;
import mainPack.Consts;

import java.util.ArrayList;

/**
 * Created by ArlSava on 22.10.2015.
 */
public class CModelSYNT {
    private CModelVED myModel;
    private String myResult;
    public CModelSYNT(CModelVED mm){
        myModel=mm;
    }
    public String addSpases(String line){
        String s="";
        for(int i=0;i<line.length();i++){
            if((line.charAt(i)=='(')||(line.charAt(i)==')'))
                s+=" "+line.charAt(i)+" ";
            else
                s+=line.charAt(i);
        }
        return delComments(s);
    }
    private String delComments(String line){
        String s="";
        boolean f=true;
        for(int i=0;i<line.length();i++){
            if(line.charAt(i)==';')
                f=false;
            if(f){
                if(line.charAt(i)!='\t')
                    s+=line.charAt(i);
            }
            if(line.charAt(i)=='\n')
                f=true;
        }
        return getLines(s);
    }
    private String getLines(String line){
        String s="";
        ArrayList<String> lines = new ArrayList<String>();
        for(int i=0;i<line.length();i++)
            if((line.charAt(i)!=' ')&&(line.charAt(i)!='\n'))
                s+=line.charAt(i);
            else
                if((s.length()!=0)){
                    lines.add(s);
                    s="";
                }
        if((s.length()!=0))
            lines.add(s);
        s="";
        for(int i=0;i<lines.size();i++)
            s+=lines.get(i)+" ";
        return s;
    }
    private ArrayList<String> getLineToLines(String line){
        String s="";
        ArrayList<String> lines = new ArrayList<String>();
        line=addSpases(line);
        for(int i=0;i<line.length();i++)
            if((line.charAt(i)!=' ')&&(line.charAt(i)!='\n'))
                s+=line.charAt(i);
            else
            if((s.length()!=0)){
                lines.add(s);
                s="";
            }
        if((s.length()!=0))
            lines.add(s);
        return lines;
    }
    private String findEqual(String word, String[][] base){
        for(int i=0;i<base.length;i++)
            if(word.equals(base[i][1]))
                return base[i][0];
        return "("+word+")-?";
    }
    private String findId(String word){
        String s="";
        if(word.charAt(1)=='?')
            s=Consts.IDCALLING;
        else
            s=word;
        return s;
    }
    private String findText(String word){
        String s="";
        if(word.charAt(1)=='\"')
            s=Consts.TEXTCALLING;
        else
            s=word;
        return s;
    }
    private boolean makeLoockBack(String word){
        if(word.equals(Consts.FACTS))
            return true;
        if(word.equals(Consts.FACT))
            return true;
        if(word.equals(Consts.RULE))
            return true;
        if(word.equals(Consts.FACTCALLING))
            return true;
        return false;
    }
    private String seeString(String word){
        if(word.equals(Consts.FACTS))
            return Consts.FACTSCALLING;
        if(word.equals(Consts.FACT))
            return Consts.FACTCALLING;
        if(word.equals(Consts.RULE))
            return Consts.RULECALLING;
        //if(word.equals(Consts.FACTCALLING))
            return Consts.STRINGCALLING;
        //return word;
    }
    public String[][] getChange(String line,String[][] base){
        String s="";
        String b="";
        String lookback="1";
        ArrayList<String> lines = getLineToLines(line);
        ArrayList<String> name = new ArrayList<String>();
        String[][] res= new String[lines.size()][2];
        for(int i=0;i<lines.size();i++) {
            b=findEqual(lines.get(i), base);
            b=findId(b);
            b=findText(b);
            if(b.charAt(b.length()-1)!='?')
                lookback="";
            else{
                b=seeString(lookback);
            }
            s+=b+": "+lines.get(i)+"\n";
            name.add(b);
            if(makeLoockBack(b))
                lookback=b;
            else
                lookback="";
        }
        for(int i=0;i<lines.size();i++){
            res[i][0]=name.get(i);
            if(name.get(i).equals(Consts.IDCALLING))
                res[i][1]=lines.get(i).substring(1);
            else
                if(name.get(i).equals(Consts.TEXTCALLING))
                    res[i][1]=lines.get(i).substring(1, lines.get(i).length()-1);
                else
                    res[i][1]=lines.get(i);

        }
        myResult=s;
        return res;
    }

    public String getChange(){
        return myResult;
    }

}
