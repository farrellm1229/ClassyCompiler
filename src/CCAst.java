import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CCAst {
    private int nodeID;
    private String nodeValue;
    private int parentId;     
    public CCAst() {
    }

    public CCAst(int nodeID, String nodeValue, int parentId) {
        this.nodeID = nodeID;
        this.nodeValue = nodeValue;
        this.parentId = parentId;
    }

    public int getID() {
        return nodeID;
    }

    public void setID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


    private static List<CCAst> tree; //defining array for tree
                                    //from tokens from token stream


    public static CCAst getTree(int nodeID, String nodeValue, int parentID) {
        CCAst cst = new CCAst();
        cst.setID(nodeID);
        cst.setNodeValue(nodeValue);
        cst.setParentId(parentID);

        return cst;
    }

    public static List<CCAst> selectChildren(int parentId) {
        List<CCAst> result = new ArrayList<CCAst>();
        for (CCAst t : tree) {
            if (t.getParentId() == parentId) {
                result.add(t);
            }
        }
        return result;
    }

    public static void printTree(CCAst cst, int level) {
        List<CCAst> children = selectChildren(cst.getID());
        String indent = ""; //creating incrementing "-" for cst
        for (int i = 0; i < level; i++) {
            indent = indent + "-";
        }
        System.out.println(indent + cst.getNodeValue()); //print node with according number of "-"
        if(children.size() > 0){
            level++;
        }
        for (CCAst obj : children) {
            printTree(obj, level);
        }
        
    }

        int one = 1;
        int two = 2;
        int three = 3;
        int five=5;

        //for int tree
        int eleven = 11;
        int four = 4; //original statementlist node
        int twelve = 12;
        int thirteen = 13;
        int fourteen = 14;
        int fifteen = 15;
        int sixteen = 16;

        //for StringTree
        int seventeen = 17;
        int eighteen = 18;
        int nineteen = 19;
        int twenty = 20;
        int twentyone = 21;
        int twentytwo = 22;

        
        int twentysix = 26;
        int twentyseven = 27;
        int twentyeight = 28;
        int twentynine = 29;
        int thirty = 30;
        int thirtyone = 31;
        int thirtytwo = 32;
        int thirtythree = 33;
        int thirtyfour = 34;
        int thirtyfive = 35;


        int thirtysix = 36;
        int thirtyseven = 37;
        int thirtyeight = 38;
        int thirtynine = 39;
        int forty = 40;
        int fortyone = 41;
        int fortytwo = 42;

    private static ArrayList<String> symTabName = new ArrayList<String>();
    private static ArrayList<Integer> symTabScopeLine = new ArrayList<Integer>();
    public static LinkedHashMap<String,String> symbolTable = new LinkedHashMap<String,String>();
    // static Hashtable<Integer,Integer> symTabScopeLine = new Hashtable<Integer,Integer>();  
    

    




    //public static ArrayList<Tree> tree = new ArrayList<Tree>();
    public void intTree(String letter, int i){
        
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            System.out.println(scope + "inint");

            
                    //tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<Variable Declaration>", two));
                    //tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(thirteen, "[ " +tokens.get(i).getValueOfToken() + " ]", twelve));
                    //symTabType.add(tokens.get(i).getValueOfToken());
                    symbolTable.put(tokens.get(i+1).getValueOfToken(), tokens.get(i).getValueOfToken());
                    symTabScopeLine.add(scope);

                    //tree.add(getTree(fourteen, "<ID>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", twelve));    
                    //symTabName.add(tokens.get(i+1).getValueOfToken());

                }
                    eleven=eleven+10;
                    twelve=twelve+10;
                    thirteen=thirteen+10;
                    fourteen=fourteen+10;
                    fifteen=fifteen+10;
                    sixteen=sixteen+10;
    }
    public void stringTree(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            System.out.println(scope);
            scope=scope+0;
            
            //tree.add(getTree(eleven, "<Statement>", four));
            tree.add(getTree(twelve, "<Variable Declaration>", two));
            //tree.add(getTree(thirteen, "<Type>", twelve));
            tree.add(getTree(thirteen, "[ " +tokens.get(i).getValueOfToken() + " ]", twelve));
            
            //symTabType.add(tokens.get(i).getValueOfToken());
            //tree.add(getTree(fourteen, "<ID>", twelve));
            tree.add(getTree(fourteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", twelve));    
            //symTabName.add(tokens.get(i+1).getValueOfToken());
            symbolTable.put(tokens.get(i+1).getValueOfToken(), tokens.get(i).getValueOfToken());
            symTabScopeLine.add(scope);


}
                    eleven=eleven+100;
                    twelve=twelve+100;
                    thirteen=thirteen+100;
                    fourteen=fourteen+100;
                    fifteen=fifteen+100;
                    sixteen=sixteen+100;
    }
    public void booleanTree(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            
                   
                    //tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<Variable Declaration>", two));
                    //tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(thirteen, "[ " +tokens.get(i).getValueOfToken() + " ]", twelve));
                    //symTabType.add(tokens.get(i).getValueOfToken());
                    //tree.add(getTree(fourteen, "<ID>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", twelve));    
                    //symTabName.add(tokens.get(i+1).getValueOfToken());
                    symbolTable.put(tokens.get(i+1).getValueOfToken(), tokens.get(i).getValueOfToken());
                    symTabScopeLine.add(scope);


}

                    eleven=eleven+29;
                    twelve=twelve+29;
                    thirteen=thirteen+29;
                    fourteen=fourteen+29;
                    fifteen=fifteen+29;
                    sixteen=sixteen+29;
    }
    int scope;
    public void blockStartTree2(String letter, int i){

        
        if(i==0) {
            scope = 0;

            tree.add(getTree(two, "<BLOCK>", one));
        //two++;
        //one++;
        }
        else{
            scope++;
            two++;
            one++;
            tree.add(getTree(two, "<BLOCK>", one));


        }
    }
    public void blockEndTree(String letter, int i){
        scope--;
        
    }

    public void assignmentTree(String letter, int i){
        if(tokens.get(i-1).getValueOfToken().equals(letter)){
            
            //tree.add(getTree(25, "<StatementList>", 4));
            //tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<Assignment Statement>", two));
            //tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyseven));
            //tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            //tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",twentyseven));
        }
        if(tokens.get(i+1).getValueOfToken().equals(letter)){
            //tree.add(getTree(25, "<StatementList>", 4));
            
            tree.add(getTree(twentyseven, "<Assignment Statement>", two));
            //tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyseven));
            //tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            //tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",twentyseven));
        }
        twentysix=twentysix+6;
        twentyseven=twentyseven+6;
        twentyeight=twentyeight+6;
        twentynine=twentynine+6;
        thirty=thirty+6;
        thirtyone=thirtyone+6;
        thirtytwo=thirtytwo+6;


    }
    

    public void priTree(String letter, int i){
        if(tokens.get(i+2).getValueOfToken().equals(letter)){
            

                    //tree.add(getTree(33, "<Statement>", 4));
                    tree.add(getTree(thirtyfour, "<Print Statement>", 2));

                   // tree.add(getTree(35, "[ " +tokens.get(i).getValueOfToken() + " ]", 34)); //[print]
                    //tree.add(getTree(36, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 34));//[(]
                   // tree.add(getTree(37, "<Expr>", 36));//
                    tree.add(getTree(thirtyeight, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyfour));// [ x ]
                    if(tokens.get(i+3).getTypeOfToken().equals("PLUS")){ //case for int expr within print statement
                        tree.add(getTree(thirtynine, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour));// [ + ]
                        tree.add(getTree(thirtynine+62, "[ " +tokens.get(i+4).getValueOfToken() + " ]", thirtyfour));// [ x ]
                        //tree.add(getTree(3900, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 34));// [ ) ]

                    }
                    else{
                        //tree.add(getTree(39, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 34)); // [ ) ] for case print(x)
                    }
        }
        thirtyfour+=13;
        thirtyeight+=13;
        thirtynine+=13;

        /*else if(tokens.get(i+3).getTypeOfToken().equals(letter)){

                    tree.add(getTree(33, "<Statement>", 4));
                    tree.add(getTree(34, "<PrintStatement>", 33));

                    tree.add(getTree(35, "[ " +tokens.get(i).getValueOfToken() + " ]", 34));
                    tree.add(getTree(36, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 34));
                    tree.add(getTree(37, "<Expr>", 36));
                    tree.add(getTree(39, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 37));
                    tree.add(getTree(40, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 37));fvardec
                    tree.add(getTree(41, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 37));

                    tree.add(getTree(42, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 34));
        }*/
    }
    public void whileTree(String letter, int i){
        
        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (((i==1)) || (i==7))){ //7 is case for parseTest1.txt
            
            //tree.add(getTree(40, "<Statement>", 4));
            tree.add(getTree(41, "<While Statement>", 2));
            tree.add(getTree(42, "[ " +tokens.get(i).getValueOfToken() + " ]", 41));
            tree.add(getTree(43, "<BooleanExpr>", 41));
            tree.add(getTree(44, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 43));
            tree.add(getTree(45, "<Expr>", 43));
            tree.add(getTree(46, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 45));
            tree.add(getTree(47, "<BoolOp>", 43));
            tree.add(getTree(48, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 47));
            tree.add(getTree(49, "<Expr>", 43));
            tree.add(getTree(50, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 49));
            tree.add(getTree(51, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 43));
            tree.add(getTree(52, "<Block>", 41));
            tree.add(getTree(53, "[ " +tokens.get(i+6).getValueOfToken() + " ]", 52));
        }
        //i is 8 in the double while test case
        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (i!=1) && (i < 14)){ //implying double while statement test case
                    
            tree.add(getTree(400, "<Statement>", 4));
            tree.add(getTree(410, "<WhileStatement>", 400));
            tree.add(getTree(420, "[ " +tokens.get(i).getValueOfToken() + " ]", 410));
            tree.add(getTree(430, "<BooleanExpr>", 410));
            tree.add(getTree(440, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 430));
            tree.add(getTree(450, "<Expr>", 430));
            tree.add(getTree(460, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 450));
            tree.add(getTree(470, "<BoolOp>", 430));
            tree.add(getTree(480, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 470));
            tree.add(getTree(490, "<Expr>", 430));
            tree.add(getTree(500, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 490));
            tree.add(getTree(510, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 430));
            tree.add(getTree(520, "<Block>", 410));
            tree.add(getTree(530, "[ " +tokens.get(i+6).getValueOfToken() + " ]", 520));
        }

        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (i!=1) && (i > 14)){ //implying triple while statement test case
                        //{while(a==true){while(b==true){print(c)}}}$
            
            //System.out.print(i);
            tree.add(getTree(4000, "<Statement>", 4));
            tree.add(getTree(4100, "<WhileStatement>", 4000));
            tree.add(getTree(4200, "[ " +tokens.get(i).getValueOfToken() + " ]", 4100));
            tree.add(getTree(4300, "<BooleanExpr>", 4100));
            tree.add(getTree(4400, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 4300));
            tree.add(getTree(4500, "<Expr>", 4300));
            tree.add(getTree(4600, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 4500));
            tree.add(getTree(4700, "<BoolOp>", 4300));
            tree.add(getTree(4800, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 4700));
            tree.add(getTree(4900, "<Expr>", 4300));
            tree.add(getTree(5000, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 4900));
            tree.add(getTree(5100, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 4300));
            tree.add(getTree(5200, "<Block>", 4100));
            tree.add(getTree(5300, "[ " +tokens.get(i+6).getValueOfToken() + " ]", 5200));
            }
        
    }
    public void ifTree(String letter, int i){

        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (((i==1)) || (i==7))){ //7 is case for parseTest1.txt
            
            tree.add(getTree(60, "<Statement>", 4));
            tree.add(getTree(61, "<WhileStatement>", 60));
            tree.add(getTree(62, "[ " +tokens.get(i).getValueOfToken() + " ]", 61));
            tree.add(getTree(63, "<BooleanExpr>", 61));
            tree.add(getTree(64, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 63));
            tree.add(getTree(65, "<Expr>", 63));
            tree.add(getTree(66, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 65));
            tree.add(getTree(67, "<BoolOp>", 63));
            tree.add(getTree(68, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 67));
            tree.add(getTree(69, "<Expr>", 63));
            tree.add(getTree(70, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 69));
            tree.add(getTree(71, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 63));
            tree.add(getTree(72, "<Block>", 61));
            tree.add(getTree(73, "[ " +tokens.get(i+6).getValueOfToken() + " ]", 72));
        }

        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (i!=1) && (i < 14)){ //implying double if statement test case
            
            tree.add(getTree(600, "<Statement>", 4));
            tree.add(getTree(610, "<WhileStatement>", 600));
            tree.add(getTree(620, "[ " +tokens.get(i).getValueOfToken() + " ]", 610));
            tree.add(getTree(630, "<BooleanExpr>", 610));
            tree.add(getTree(640, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 630));
            tree.add(getTree(650, "<Expr>", 630));
            tree.add(getTree(660, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 650));
            tree.add(getTree(670, "<BoolOp>", 630));
            tree.add(getTree(680, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 670));
            tree.add(getTree(690, "<Expr>", 630));
            tree.add(getTree(700, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 690));
            tree.add(getTree(710, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 630));
            tree.add(getTree(720, "<Block>", 610));
            tree.add(getTree(730, "[ " +tokens.get(i+6).getValueOfToken() + " ]", 720));
        }
        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (i!=1) && (i > 14)){ //implying triple if statement test case
            
            tree.add(getTree(6000, "<Statement>", 4));
            tree.add(getTree(6100, "<WhileStatement>", 6000));
            tree.add(getTree(6200, "[ " +tokens.get(i).getValueOfToken() + " ]", 6100));
            tree.add(getTree(6300, "<BooleanExpr>", 6100));
            tree.add(getTree(6400, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 6300));
            tree.add(getTree(6500, "<Expr>", 6300));
            tree.add(getTree(6600, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 6500));
            tree.add(getTree(6700, "<BoolOp>", 6300));
            tree.add(getTree(6800, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 6700));
            tree.add(getTree(6900, "<Expr>", 6300));
            tree.add(getTree(7000, "[ " +tokens.get(i+4).getValueOfToken() + " ]", 6900));
            tree.add(getTree(7100, "[ " +tokens.get(i+5).getValueOfToken() + " ]", 6300));
            tree.add(getTree(7200, "<Block>", 6100));
            tree.add(getTree(7300, "[ " +tokens.get(i+6).getValueOfToken() + " ]", 7200));
        }
    }



    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void create(List<CCToken> tokenStream) {
            
        tree = new ArrayList<CCAst>();

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            

            String element = tokens.get(i).getValueOfToken();
            //tree.add(getTree(1, "<Program>", 0));

            switch (element) {
                
                
        
                //case ")":
                //blockStartTree("{", i);
                //break;
                case "{":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    blockStartTree2("{", i);
                    
                    
                    //tree.add(getTree(2, "<Block>", 1));
                    //tree.add(getTree(3, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
                    //tree.add(getTree(4, "<StatementList>", 2));

                    break;
                    

                case "}" :
                    blockEndTree("}",i);    
                //tree.add(getTree(300, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));


                    break;

                //case "int":
                case "boolean": //having them all together caused issues :(
                    booleanTree("a", i); booleanTree("b", i); booleanTree("c", i); booleanTree("d", i);
                    booleanTree("e", i); booleanTree("f", i); booleanTree("g", i); booleanTree("h", i);
                    booleanTree("i", i); booleanTree("j", i); booleanTree("k", i); booleanTree("l", i);
                    booleanTree("m", i); booleanTree("n", i); booleanTree("o", i); booleanTree("p", i);
                    booleanTree("q", i); booleanTree("r", i); booleanTree("s", i); booleanTree("t", i);
                    booleanTree("u", i); booleanTree("v", i); booleanTree("w", i); booleanTree("x", i);
                    booleanTree("y", i); booleanTree("z", i);

                    break;

                case "int":
                    intTree("a", i);
                    intTree("b", i);
                    intTree("c", i);
                    intTree("d", i);
                    intTree("e", i);
                    intTree("f", i);
                    intTree("g", i);
                    intTree("h", i);
                    intTree("i", i);
                    intTree("j", i);
                    intTree("k", i);
                    intTree("l", i);
                    intTree("m", i);
                    intTree("n", i);
                    intTree("o", i);
                    intTree("p", i);
                    intTree("q", i);
                    intTree("r", i);
                    intTree("s", i);
                    intTree("t", i);
                    intTree("u", i);
                    intTree("v", i);
                    intTree("w", i);
                    intTree("x", i);
                    intTree("y", i);
                    intTree("z", i);
                    break;
                case "string":
                    stringTree("a", i);
                    stringTree("b", i);
                    stringTree("c", i);
                    stringTree("d", i);
                    stringTree("e", i);
                    stringTree("f", i);
                    stringTree("g", i);
                    stringTree("h", i);
                    stringTree("i", i);
                    stringTree("j", i);
                    stringTree("k", i);
                    stringTree("l", i);
                    stringTree("m", i);
                    stringTree("n", i);
                    stringTree("o", i);
                    stringTree("p", i);
                    stringTree("q", i);
                    stringTree("r", i);
                    stringTree("s", i);
                    stringTree("t", i);
                    stringTree("u", i);
                    stringTree("v", i);
                    stringTree("w", i);
                    stringTree("x", i);
                    stringTree("y", i);
                    stringTree("z", i);

                    break;
                
                //checking for ID in assignment statement
                
              
                case "=":
                    assignmentTree("a", i); assignmentTree("b", i); assignmentTree("c", i); assignmentTree("d", i);
                    assignmentTree("e", i); assignmentTree("f", i); assignmentTree("g", i); assignmentTree("h", i);
                    assignmentTree("i", i); assignmentTree("j", i); assignmentTree("k", i); assignmentTree("l", i);
                    assignmentTree("m", i); assignmentTree("n", i); assignmentTree("o", i); assignmentTree("p", i);
                    assignmentTree("q", i); assignmentTree("r", i); assignmentTree("s", i); assignmentTree("t", i);
                    assignmentTree("u", i); assignmentTree("v", i); assignmentTree("w", i); assignmentTree("x", i);
                    assignmentTree("y", i); assignmentTree("z", i); assignmentTree("STRING", i);

                    break;
                
                
                                

                case "print":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    priTree("0", i);priTree("1", i);priTree("2", i);priTree("3", i);priTree("4", i);                     
                    priTree("5", i);priTree("6", i);priTree("7", i);                     
                    priTree("8", i);priTree("9", i);
                    //letter cases
                    priTree("a", i);priTree("g", i);priTree("m", i);priTree("s", i);                    
                    priTree("b", i);priTree("h", i);priTree("n", i);priTree("t", i);     
                    priTree("c", i);priTree("i", i);priTree("o", i);priTree("u", i);
                    priTree("d", i);priTree("j", i);priTree("p", i);priTree("v", i);     
                    priTree("e", i);priTree("k", i);priTree("q", i);priTree("w", i);
                    priTree("f", i);priTree("l", i);priTree("r", i);priTree("x", i);
                    priTree("y", i);priTree("z", i);
                    priTree("PLUS", i);
                    break;
                case "while":
                    whileTree("a", i);whileTree("g", i);whileTree("m", i);whileTree("s", i);                    
                    whileTree("b", i);whileTree("h", i);whileTree("n", i);whileTree("t", i);     
                    whileTree("c", i);whileTree("i", i);whileTree("o", i);whileTree("u", i);
                    whileTree("d", i);whileTree("j", i);whileTree("p", i);whileTree("v", i);     
                    whileTree("e", i);whileTree("k", i);whileTree("q", i);whileTree("w", i);
                    whileTree("f", i);whileTree("l", i);whileTree("r", i);whileTree("x", i);
                    whileTree("y", i);whileTree("z", i);
                break;
                case "if":
                    ifTree("a", i);ifTree("g", i);ifTree("m", i);ifTree("s", i);                    
                    ifTree("b", i);ifTree("h", i);ifTree("n", i);ifTree("t", i);     
                    ifTree("c", i);ifTree("i", i);ifTree("o", i);ifTree("u", i);
                    ifTree("d", i);ifTree("j", i);ifTree("p", i);ifTree("v", i);     
                    ifTree("e", i);ifTree("k", i);ifTree("q", i);ifTree("w", i);
                    ifTree("f", i);ifTree("l", i);ifTree("r", i);ifTree("x", i);
                    ifTree("y", i);ifTree("z", i);
                break;

                //case "$":
                //blockEndTree("}", i);
                //break;

                case "$":
                //System.out.println("$$");


                    if (i == (tokens.size()-1)){ //making sure the EOP $ is at the end
                                                 //of the token stream
                        //System.out.println("found EOP");
                        tree.add(getTree(3000, "[ " +tokens.get(i).getValueOfToken() + " ]", 1));

                    }
                    break;

            
                    
                    
            }
            //boolean test = (tokens.get(i+1).getTypeOfToken().equals("ASSIGNMENT"));
           
            //j++;
        
        
        }
        
        
        
        printTree(tree.get(0), 0); //print tree yay!
    }

    public void symbolTable(){
        System.out.println("Name    Type    Scope");
        System.out.println("---------------------");
        int test=0;
        System.out.println(symbolTable.toString());


        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
        
            System.out.println(key+"       "+value +"       " + symTabScopeLine.get(test));
            //System.out.println(test);

            test++;
              

           }  
           
        //System.out.println(symbolTable.toString());
        

            
    }
}