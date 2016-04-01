package mainPack;

/**
 * Created by ArlSava on 21.10.2015.
 */
public class Consts {
    //for gui
    public static final int WIDHT = 640;
    public static final int HEIGHT = 480;
    public static final String TITLEMAINFORM = "Программная реализация курсовой работы";
    public static final String TMFILE = "Файл";
    public static final String TMIOPEN = "Открыть";
    public static final String TMISAVE = "Сохранить";
    public static final String TMEXECUTION = "Пуск";
    public static final String TMIRUN = "Старт";
    //for gui(tab)
    public static final String TTINPUT = "Входные данные";
    public static final String TTSYNT = "Входное выражение";
    public static final String TTLEX = "Построенное дерево";
    public static final String TTINTER = "Результат интерпретации";
    //for db
    public static final String URL = "jdbc:mysql://localhost:3306/dbwork";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    //QUERYS
    public static final String GET_RESERVED_WORDS = "select * from reserved_word;";
    //public static final String CLEAR_TABLE = "delete from scanner_result;";
    public static final String CLEAR_TABLE = "drop table scanner_result;";
    public static final String FILL_TABLE = "insert into scanner_result(name,value) values(?,?);";
    public static final String CREATE_TABLE = "CREATE TABLE scanner_result (\n" +
            "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  name varchar(45) NOT NULL,\n" +
            "  value varchar(45) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";
    public static final String GET_MY_RULES = "select * from my_rules;";
    //for vvod
    public static final String TDOPENFILE="Открыть Файл";
    public static final String FTYPE="clp";
    public static final String FDESCR="CLIPS файлы";
    //for synt
    public static final String FACTS="facts";
    public static final String RULE="rule";
    public static final String FACT="start_bracket";

    public static final String FACTSCALLING="facts'_name";
    public static final String RULECALLING="rule's_name";
    public static final String FACTCALLING="fact's_name";
    public static final String IDCALLING="id";
    public static final String TEXTCALLING="text";
    public static final String STRINGCALLING="string";
    //for lex
    public static final String BRSTART="start_bracket";
    public static final String BREND="end_bracket";

    //for inter
    public static final String CONSTSCALLING = "CONSTS";
    public static final String THEFACTCALLING = "THEFACT";
    public static final String FACTSCOLLING = "FACTS";
    public static final String DEFFACTSCALLING = "DEFFACTS";
    public static final String DEFRULESCALLING = "DEFRULES";
    public static final String PRECONDITIONSCALLING = "PRECONDITIONS";
    public static final String PRECONDITIONCALLING = "PRECONDITION";
    public static final String OPERANDSCALLING = "OPERANDS";
    public static final String OPERANDCALLING = "OPERAND";
    public static final String ACTIONSCALLING = "ACTIONS";
    public static final String ACTIONCALLING = "ACTION";
    public static final String PRINTCALLING = "PRINT";
    public static final String ASSIGNMENTCALLING = "ASSIGNMENT";
    public static final String PROGCALLING = "PROG";
    public static final String PRINTNAMECALLING = "print";
    public static final String LNCALLING = "ln";
    /*public static final String COLLING = "CONSTS";
    public static final String COLLING = "CONSTS";
    public static final String COLLING = "CONSTS";*/
}
