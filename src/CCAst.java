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
    public void intTree(int i){
        
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
        
            //System.out.println(scope + "inint");

            
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
    public void stringTree(int i){
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
        
            
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
    public void booleanTree(int i){
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
            
                   
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

    public void assignmentTree(int i){
        if((tokens.get(i-1).getTypeOfToken().equals("CHAR")) && (tokens.get(i+3).getValueOfToken().equals("=="))){

            tree.add(getTree(twelve, "<Assignment Statement>", two));
            tree.add(getTree(thirteen, "<Equality>", twelve));
            tree.add(getTree(fourteen, "[ " + tokens.get(i+2).getValueOfToken() + " ]" , thirteen));
            tree.add(getTree(fourteen, "[ " + tokens.get(i+4).getValueOfToken() + " ]", thirteen));
            //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
            //symTabScopeLine.add(scope);


}
        else if((tokens.get(i-1).getTypeOfToken().equals("CHAR")) && (tokens.get(i+3).getValueOfToken().equals("!="))){

            tree.add(getTree(twelve+7, "<Assignment Statement>", two));
            tree.add(getTree(thirteen+7, "<Inequality>", twelve+7));
            tree.add(getTree(fourteen+7, "[ " + tokens.get(i+2).getValueOfToken() + " ]" , thirteen+7));
            tree.add(getTree(fourteen+7, "[ " + tokens.get(i+4).getValueOfToken() + " ]", thirteen+7));
            //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
            //symTabScopeLine.add(scope);


}
        else if((tokens.get(i-1).getTypeOfToken().equals("CHAR")) && ((!tokens.get(i+3).getValueOfToken().equals("!=")) ||(!tokens.get(i+3).getValueOfToken().equals("==")))){

            
            //tree.add(getTree(25, "<StatementList>", 4));
            //tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<Assignment Statement>", two));
            //tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyseven));
            //tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            //tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",twentyseven));
            

}
twelve=twelve+219;
            thirteen=thirteen+219;
            fourteen=fourteen+219;
twentysix=twentysix+219;
        twentyseven=twentyseven+219;
        twentyeight=twentyeight+219;
        twentynine=twentynine+219;
        thirty=thirty+219;
        thirtyone=thirtyone+219;
        thirtytwo=thirtytwo+219;

        /*if((tokens.get(i+1).getValueOfToken().equals)) && (!tokens.get(i+1).getValueOfToken().equals))){
            //tree.add(getTree(25, "<StatementList>", 4));
            
            tree.add(getTree(twentyseven, "<Assignment Statement>", two));
            //tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyseven));
            //tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            //tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",twentyseven));
        }*/



    }
    

    public void priTree(int i){
        if(((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT")) 
        || (tokens.get(i+2).getTypeOfToken().equals("STRING")) || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))) && (!tokens.get(i+3).getTypeOfToken().equals("PLUS"))){
            

                    //tree.add(getTree(33, "<Statement>", 4));
                    tree.add(getTree(thirtyfour, "<Print Statement>", two));
                    tree.add(getTree(thirtyeight, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyfour));// [ x ]
                   
        }
        else if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))){
            tree.add(getTree(thirtyfour, "<Print Statement>", two));
            tree.add(getTree(thirtyfour+21, "<Addition>", thirtyfour));
            
            tree.add(getTree(thirtyfour+31, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyfour+21));// [ + ]
            tree.add(getTree(thirtyfour+41, "[ " +tokens.get(i+4).getValueOfToken() + " ]", thirtyfour+21));// [ x ]

        }
           
        thirtyfour+=13;
        thirtyeight+=13;
        thirtynine+=13;

        /*else if(tokens.get(i+3).getTypeOfToken().equals)){

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
    public void whileTree(int i){
        
        
        if(((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT"))  || (tokens.get(i+2).getTypeOfToken().equals("STRING"))
        || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))) && (tokens.get(i+3).getValueOfToken().equals("=="))){

            tree.add(getTree(twelve, "<While Statement>", two));
            tree.add(getTree(thirteen, "<Equality>", twelve));
            tree.add(getTree(fourteen, "[ " + tokens.get(i+2).getValueOfToken() + " ]" , thirteen));
            tree.add(getTree(fourteen, "[ " + tokens.get(i+4).getValueOfToken() + " ]", thirteen));
            //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
            //symTabScopeLine.add(scope);


}
        else if(((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT"))  || (tokens.get(i+2).getTypeOfToken().equals("STRING"))
        || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))) && (tokens.get(i+3).getValueOfToken().equals("!="))){
            tree.add(getTree(twelve+7, "<While Statement>", two));
            tree.add(getTree(thirteen+7, "<Inequality>", twelve+7));
            tree.add(getTree(fourteen+7, "[ " + tokens.get(i+2).getValueOfToken() + " ]" , thirteen+7));
            tree.add(getTree(fourteen+7, "[ " + tokens.get(i+4).getValueOfToken() + " ]", thirteen+7));
            //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
            //symTabScopeLine.add(scope);


}
else if((tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")) && (!tokens.get(i+2).getTypeOfToken().equals("CHAR"))){

    tree.add(getTree(twelve+77, "<While Statement>", two));
    tree.add(getTree(fourteen+77, "[ " + tokens.get(i+1).getValueOfToken() + " ]" , twelve+77));
    //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
    //symTabScopeLine.add(scope);


}


            //eleven=eleven+113;
            twelve=twelve+413;
            thirteen=thirteen+413;
            fourteen=fourteen+413;
           // fifteen=fifteen+113;
            //sixteen=sixteen+113;
       
    }
    public void ifTree(int i){
        if(((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT"))  || (tokens.get(i+2).getTypeOfToken().equals("STRING"))
        || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))) && (tokens.get(i+3).getValueOfToken().equals("=="))){

            tree.add(getTree(twelve, "<If Statement>", two));
            tree.add(getTree(thirteen, "<Equality>", twelve));
            tree.add(getTree(fourteen, "[ " + tokens.get(i+2).getValueOfToken() + " ]" , thirteen));
            tree.add(getTree(fourteen, "[ " + tokens.get(i+4).getValueOfToken() + " ]", thirteen));
            //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
            //symTabScopeLine.add(scope);


}
        else if(((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT"))  || (tokens.get(i+2).getTypeOfToken().equals("STRING"))
        || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))) && (tokens.get(i+3).getValueOfToken().equals("!="))){

            tree.add(getTree(twelve+7, "<If Statement>", two));
            tree.add(getTree(thirteen+7, "<Inequality>", twelve+7));
            tree.add(getTree(fourteen+7, "[ " + tokens.get(i+2).getValueOfToken() + " ]" , thirteen+7));
            tree.add(getTree(fourteen+7, "[ " + tokens.get(i+4).getValueOfToken() + " ]", thirteen+7));
            //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
            //symTabScopeLine.add(scope);


}
else if((tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")) && (!tokens.get(i+2).getTypeOfToken().equals("CHAR"))){

    tree.add(getTree(twelve+77, "<If Statement>", two));
    tree.add(getTree(fourteen+77, "[ " + tokens.get(i+1).getValueOfToken() + " ]" , twelve+77));
    //symbolTable.put(tokens.get(i+2).getValueOfToken(), tokens.get(i+4).getValueOfToken());
    //symTabScopeLine.add(scope);


}


            //eleven=eleven+113;
            twelve=twelve+113;
            thirteen=thirteen+113;
            fourteen=fourteen+113;
           // fifteen=fifteen+113;
            //sixteen=sixteen+113;
       
/*
        if((tokens.get(i+2).getValueOfToken().equals)) && (((i==1)) || (i==7))){ //7 is case for parseTest1.txt
            
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

        if((tokens.get(i+2).getValueOfToken().equals)) && (i!=1) && (i < 14)){ //implying double if statement test case
            
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
        if((tokens.get(i+2).getValueOfToken().equals)) && (i!=1) && (i > 14)){ //implying triple if statement test case
            
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
        }*/
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
                    booleanTree(i);

                    break;

                case "int":
                    intTree(i);
                    break;
                case "string":
                    stringTree(i);

                    break;
                
                //checking for ID in assignment statement
                
              
                case "=":
                    assignmentTree(i);

                    break;
                
                
                                

                case "print":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    priTree(i);
                    break;
                case "while":
                    whileTree(i);
                break;
                case "if":
                    ifTree(i);
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
        //System.out.println(symbolTable.toString());


        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.printf("%-8s%-10s%-8s\n" , key,  value, symTabScopeLine.get(test));
           // System.out.println("key+"       "+value +"       " + symTabScopeLine.get(test));
            //System.out.println(test);
            //printf("'%-5d'"
            test++;
              

           }  
           
        //System.out.println(symbolTable.toString());
        

            
    }
}