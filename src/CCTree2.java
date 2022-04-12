import java.util.ArrayList;
import java.util.List;

public class CCTree2 {
    private int nodeID;
    private String nodeValue;
    private int parentId;     
    public CCTree2() {
    }

    public CCTree2(int nodeID, String nodeValue, int parentId) {
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


    private static List<CCTree2> tree; //defining array for tree
                                    //from tokens from token stream


    public static CCTree2 getTree(int nodeID, String nodeValue, int parentID) {
        CCTree2 cst = new CCTree2();
        cst.setID(nodeID);
        cst.setNodeValue(nodeValue);
        cst.setParentId(parentID);

        return cst;
    }

    public static List<CCTree2> selectChildren(int parentId) {
        List<CCTree2> result = new ArrayList<CCTree2>();
        for (CCTree2 t : tree) {
            if (t.getParentId() == parentId) {
                result.add(t);
            }
        }
        return result;
    }

    public static void printTree(CCTree2 cst, int level) {
        List<CCTree2> children = selectChildren(cst.getID());
        String indent = ""; //creating incrementing "-" for cst
        for (int i = 0; i < level; i++) {
            indent = indent + "-";
        }
        System.out.println(indent + cst.getNodeValue()); //print node with according number of "-"
        if(children.size() > 0){
            level++;
        }
        for (CCTree2 obj : children) {
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
        int fortythree = 43;
        int fortyfour = 44;
        int fortyfive = 45;
        int fortysix = 46;
        int blockffive= 45;



    public static void parseMessage(String message) {
            System.out.println("INFO  Parser - " + message);
            System.out.println("-----------------------------------------------------------");         
    }
   
    public void blockStartTree2(int i){
        parseMessage("parseBlock();");
        parseMessage("parseStatementList();");
        parseMessage("parseStatement();");

        if(i==0) {
            tree.add(getTree(2, "<Block>", 1));
            tree.add(getTree(3, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
            tree.add(getTree(four, "<StatementList>", 2));
        }
        /*else if((tokens.get(i).getValueOfToken().equals("{")) && (!tokens.get(i-1).getValueOfToken().equals(")"))) {
        tree.add(getTree(five+1, "<Block>", four));
        tree.add(getTree(five+2, "[ " +tokens.get(i).getValueOfToken() + " ]", five+1));
        tree.add(getTree(five+3, "<StatementList>", five+1));
        four++;
        five++;
        }*/
        else if((tokens.get(i).getValueOfToken().equals("{")) && (!tokens.get(i-1).getValueOfToken().equals(")")))  {

            four+=2;
            

            tree.add(getTree(four-2, "<Block>",four-3));
            tree.add(getTree(four-1, "[ " +tokens.get(i).getValueOfToken() + " ]", four-2));
            tree.add(getTree(four, "<StatementList>", four-2));
            
          //  tree.add(getTree(four+1, "<Block>", four));
            //tree.add(getTree(four+2, "[ " +tokens.get(i).getValueOfToken() + " ]", four+1));
            //tree.add(getTree(four+3, "<StatementList>", four+1));
            
           // five++;
            }
            
    }
    public void blockEndTree(int i){
        if((tokens.get(i).getValueOfToken().equals("}")) && (tokens.get(i+1).getValueOfToken().equals("$"))) {
            tree.add(getTree(777, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
        }
        else{
            tree.add(getTree(four+1234, "[ " +tokens.get(i).getValueOfToken() + " ]", four));
            four-=2;

            //five+=197;

        }
    }
    public void assignmentTree(int i){

        parseMessage("parseStatement();");
        parseMessage("parseAssignmentStatement();");
        //if(!tokens.get(i+2).getTypeOfToken().equals("PLUS")){
        

        

        //tree.add(getTree(25, "<StatementList>", 4));
        
        tree.add(getTree(five+4, "<Statement>", four));

        tree.add(getTree(five+5, "<AssignmentStatement>", five+4));
        tree.add(getTree(five+6, "<ID>", five+5));
        tree.add(getTree(five+7, "[ " +tokens.get(i-1).getValueOfToken() + " ]", five+6));
        tree.add(getTree(five+8, "[ " + tokens.get(i).getValueOfToken() + " ]", five+5));
        
        tree.add(getTree(five+9, "<Expr>", five+5));
        
        if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
            tree.add(getTree(five+10, "<IntExpr>", five+9));//
            tree.add(getTree(five+11, "[ " +tokens.get(i+1).getValueOfToken() + " ]",five+10));
            if((tokens.size()-i)>=8){



            if(tokens.get(i+2).getTypeOfToken().equals("PLUS")){
                tree.add(getTree(thirtytwo+2, "[ " +tokens.get(i+2).getValueOfToken() + " ]",five+10));
                if(tokens.get(i+3).getTypeOfToken().equals("CHAR")){
                    tree.add(getTree(thirtytwo+3, "<ID>", five+9));//
                    tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+3));//
                }
                
                if((tokens.size()-i)>=10){


                if((tokens.get(i+3).getTypeOfToken().equals("DIGIT")) || (tokens.get(i+4).getTypeOfToken().equals("PLUS"))){
                    tree.add(getTree(thirtytwo+3, "<Expr>", five+10));//
                    tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                    tree.add(getTree(thirtytwo+5, "[ " +tokens.get(i+3).getValueOfToken() + " ]",thirtytwo+4));
                    if((tokens.get(i+4).getTypeOfToken().equals("PLUS"))){

                    tree.add(getTree(thirtytwo+6, "[ " +tokens.get(i+4).getValueOfToken() + " ]",thirtytwo+4));
                    if(tokens.get(i+5).getTypeOfToken().equals("CHAR")){
                        tree.add(getTree(thirtytwo+7, "<ID>", thirtytwo+3));//
                        tree.add(getTree(thirtytwo+8, "[ " + tokens.get(i+5).getValueOfToken() + " ]", thirtytwo+7));//
                        
                    }
                    }
                }
            }
            else{}
            if((tokens.size()-i)>=12){


                    if((tokens.get(i+5).getTypeOfToken().equals("DIGIT")) || (tokens.get(i+6).getTypeOfToken().equals("PLUS"))){

                            tree.add(getTree(thirtytwo+7, "<Expr>", thirtytwo+6));//
                            tree.add(getTree(thirtytwo+8, "<IntExpr>", thirtytwo+7));//
                            tree.add(getTree(thirtytwo+9, "[ " +tokens.get(i+5).getValueOfToken() + " ]",thirtytwo+8));
                            if((tokens.get(i+6).getTypeOfToken().equals("PLUS"))){

                            tree.add(getTree(thirtytwo+10, "[ " +tokens.get(i+6).getValueOfToken() + " ]",thirtytwo+8));
                            if(tokens.get(i+7).getTypeOfToken().equals("CHAR")){
                                tree.add(getTree(thirtytwo+11, "<ID>", thirtytwo+7));//
                                tree.add(getTree(thirtytwo+12, "[ " + tokens.get(i+7).getValueOfToken() + " ]", thirtytwo+11));//
                            }
                            }
                    }
                }
                else{}                
                if((tokens.size()-i)>=14){


                        
                        if((tokens.get(i+7).getTypeOfToken().equals("DIGIT")) || (tokens.get(i+8).getTypeOfToken().equals("PLUS"))){
                                
                                    tree.add(getTree(thirtytwo+11, "<Expr>", thirtytwo+10));//
                                    tree.add(getTree(thirtytwo+12, "<IntExpr>", thirtytwo+11));//
                                    tree.add(getTree(thirtytwo+13, "[ " +tokens.get(i+7).getValueOfToken() + " ]",thirtytwo+12));
                                    if((tokens.get(i+8).getTypeOfToken().equals("PLUS"))){

                                    tree.add(getTree(thirtytwo+14, "[ " +tokens.get(i+8).getValueOfToken() + " ]",thirtytwo+12));
                                    if(tokens.get(i+9).getTypeOfToken().equals("CHAR")){
                                        tree.add(getTree(thirtytwo+15, "<ID>", thirtytwo+11));//
                                        tree.add(getTree(thirtytwo+16, "[ " + tokens.get(i+9).getValueOfToken() + " ]", thirtytwo+15));//
                                    }    
                                }

                        }
                    }
                    else{}
                    if((tokens.size()-i)>=16){



                                    if((tokens.get(i+9).getTypeOfToken().equals("DIGIT")) || (tokens.get(i+10).getTypeOfToken().equals("PLUS"))){

                                            tree.add(getTree(thirtytwo+15, "<Expr>", thirtytwo+14));//
                                            tree.add(getTree(thirtytwo+16, "<IntExpr>", thirtytwo+15));//
                                            tree.add(getTree(thirtytwo+17, "[ " +tokens.get(i+9).getValueOfToken() + " ]",thirtytwo+16));
                                    if((tokens.get(i+10).getTypeOfToken().equals("PLUS"))){

                                            tree.add(getTree(thirtytwo+18, "[ " +tokens.get(i+8).getValueOfToken() + " ]",thirtytwo+17));
                                            if(tokens.get(i+11).getTypeOfToken().equals("CHAR")){
                                                tree.add(getTree(thirtytwo+4, "<ID>", thirtytwo+3));//
                                                tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+11).getValueOfToken() + " ]", thirtytwo+4));//
                                            }
                                    }
                                    }
                                }
                                else{}
              
            }
        


                                
                            
                        
                
        
            else{
                //no plus found after first expr
            }
        }
        }                

    else if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
        tree.add(getTree(five+12, "<StringExpr>", five+9));//
        tree.add(getTree(five+13, "[ " +tokens.get(i+1).getValueOfToken() + " ]",five+12));
    }
    else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
        tree.add(getTree(five+14, "<BooleanExpr>", five+9));//
        tree.add(getTree(five+15, "[ " +tokens.get(i+1).getValueOfToken() + " ]",five+14));
    }
    else if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
        tree.add(getTree(five+16, "<ID>", five+9));//
        tree.add(getTree(five+17, "[ " +tokens.get(i+1).getValueOfToken() + " ]",five+16));
    }
    
    else if(tokens.get(i+1).getValueOfToken().equals("(")){
        
    //i++;
            
            for(int j=i; j<tokens.size()-1; j++){
                if(tokens.get(j).getValueOfToken().equals("(")){
                    tree.add(getTree(five+14, "<BooleanExpr>", five+9));//
                    tree.add(getTree(five+15, "[ " +tokens.get(j).getValueOfToken() + " ]",five+14));
                 //   System.out.println("1 " +five);
                    //five++;

                }
                
                
                else if((tokens.get(j).getTypeOfToken().equals("CHAR")) || (tokens.get(j).getTypeOfToken().equals("DIGIT"))
                || (tokens.get(j).getTypeOfToken().equals("BOOL_VAL")) || (tokens.get(j).getTypeOfToken().equals("STRING")) ){
                    
                    tree.add(getTree(five+16, "<Expr>", five+14));//
                    tree.add(getTree(five+17, "[ " +tokens.get(j).getValueOfToken() + " ]",five+16));
                 //   System.out.println("2 " +five);
                    five+=23;


                    }
               // tree.add(getTree(five+15, "[ " +tokens.get(i+1).getValueOfToken() + " ]",five+14));
               else if(tokens.get(j).getTypeOfToken().equals("BOOL_OP")){
                    
                    tree.add(getTree(five+180, "<BooleanOp>", five-7));//
                    tree.add(getTree(five+190, "[ " +tokens.get(j).getValueOfToken() + " ]",five+180));
                    //System.out.println("3 " +five);
                five+=23;


                }
                else if(tokens.get(j).getValueOfToken().equals(")")){
                    
                    tree.add(getTree(five+21, "[ " +tokens.get(j).getValueOfToken() + " ]",five-102));
                   // System.out.println("5 " +five);
                    five+=23;

                }
               
               // tree.add(getTree(five+18, "[ " +"<Expr>" + " ]",five+9));
                //tree.add(getTree(five+19, "[ " +tokens.get(i+3).getValueOfToken() + " ]",five+18));
                
            
        if(tokens.get(j).getValueOfToken().equals("$")){
            break;
        }
      
            }

        

       //five+=130;
    }
    
//}
five+=111;
        thirtytwo+=111;

    }

    public void intTree(int i){
        
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
            parseMessage("parseStatement();");
            parseMessage("parseVarDec();");
                    tree.add(getTree(five+18, "<Statement>", four));
                    tree.add(getTree(five+19, "<VarDecl>", five+18));
                    tree.add(getTree(five+20, "<Type>", five+19));
                    tree.add(getTree(five+21, "[ " +tokens.get(i).getValueOfToken() + " ]", five+20));
                    tree.add(getTree(five+22, "<ID>", five+19));
                    tree.add(getTree(five+23, "[ " +tokens.get(i+1).getValueOfToken() + " ]", five+22));    
        }
                   five+=317;
    }
    public void stringTree(int i){
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
            parseMessage("parseStatement();");
            parseMessage("parseVarDec();");
                    tree.add(getTree(five+24, "<Statement>", four));
                    tree.add(getTree(five+25, "<VarDecl>", five+24));
                    tree.add(getTree(five+26, "<Type>", five+25));
                    tree.add(getTree(five+27, "[ " +tokens.get(i).getValueOfToken() + " ]", five+26));
                    tree.add(getTree(five+28, "<ID>", five+25));
                    tree.add(getTree(five+29, "[ " +tokens.get(i+1).getValueOfToken() + " ]", five+28));    
        }
                   five+=317;
    }
    public void booleanTree(int i){
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
            parseMessage("parseStatement();");
            parseMessage("parseVarDec();");
                    tree.add(getTree(five+30, "<Statement>", four));
                    tree.add(getTree(five+31, "<VarDecl>", five+30));
                    tree.add(getTree(five+32, "<Type>", five+31));
                    tree.add(getTree(five+33, "[ " +tokens.get(i).getValueOfToken() + " ]", five+32));
                    tree.add(getTree(five+34, "<ID>", five+31));
                    tree.add(getTree(five+35, "[ " +tokens.get(i+1).getValueOfToken() + " ]", five+34));    
        }
                   five+=317;
    }
    public void priTree(int i){
        
        if(((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT")) 
        || (tokens.get(i+2).getTypeOfToken().equals("STRING")) || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))) && (!tokens.get(i+3).getTypeOfToken().equals("PLUS"))){
            

                    //tree.add(getTree(33, "<Statement>", 4));
                    tree.add(getTree(thirtyfour, "<PrintStatement>", four));
                    tree.add(getTree(thirtyeight+thirtyfour, "[ " +tokens.get(i).getValueOfToken() + " ]", thirtyfour));// [ x ]
                    tree.add(getTree(thirtyeight+33, "[ " +tokens.get(i+1).getValueOfToken() + " ]", thirtyfour));// [ x ]

                    tree.add(getTree(thirtyfour+thirtyfour, "<Expr>", thirtyfour));

                    tree.add(getTree(thirtyeight+38, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyfour+thirtyfour));// [ x ]
                    tree.add(getTree(thirtyeight+39, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour));// [ x ]

                   
        }
        else if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))){
            tree.add(getTree(thirtyfour, "<PrintStatement>", four));
            tree.add(getTree(thirtyeight+thirtyfour, "[ " +tokens.get(i).getValueOfToken() + " ]", thirtyfour));// [ x ]
            tree.add(getTree(thirtyeight+33, "[ " +tokens.get(i+1).getValueOfToken() + " ]", thirtyfour));// [ x ]

            tree.add(getTree(thirtyfour+21, "<Expr>", thirtyfour));
            tree.add(getTree(thirtyfour+29, "<IntExpr>", thirtyfour+21));

            tree.add(getTree(thirtyeight, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyfour+29));// [ x ]
            
            tree.add(getTree(thirtyfour+31, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour+29));// [ + ]
            tree.add(getTree(thirtyfour+39, "[ " +tokens.get(i+4).getValueOfToken() + " ]", thirtyfour+29));// [ + ]

            tree.add(getTree(thirtyfour+41, "[ " +tokens.get(i+5).getValueOfToken() + " ]", thirtyfour));// [ x ]

        }
        thirtyfour+=413;
        thirtyeight+=413;
        thirtynine+=413;
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
    public void whileTree(int i){
        
        
        if((tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")) && (!tokens.get(i+2).getTypeOfToken().equals("CHAR"))){ //7 is case for parseTest1.txt
            //if(i<5){
       // System.out.println("h19091081");
                
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            tree.add(getTree(forty, "<Statement>", four));
            tree.add(getTree(fortyone, "<WhileStatement>", forty));
            tree.add(getTree(fortytwo, "[ " +tokens.get(i).getValueOfToken() + " ]", fortyone));
            tree.add(getTree(fortythree, "<BooleanExpr>", fortyone));
            tree.add(getTree(fortyfour, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fortythree));
            
            tree.add(getTree(blockffive, "<Block>", fortyone));
            tree.add(getTree(fortysix, "[ " +tokens.get(i+2).getValueOfToken() + " ]", blockffive));
            
        //    }
        
            
        
        }
        

        
        

        else if((tokens.get(i+2).getTypeOfToken().equals("CHAR"))){ //7 is case for parseTest1.txt
            //if(i<5){
               
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            tree.add(getTree(forty, "<Statement>", four));
            tree.add(getTree(fortyone, "<WhileStatement>", forty));
            tree.add(getTree(fortytwo, "[ " +tokens.get(i).getValueOfToken() + " ]", fortyone));
            tree.add(getTree(fortythree, "<BooleanExpr>", fortyone));
            tree.add(getTree(fortyfour, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fortythree));
            
            tree.add(getTree(blockffive, "<Expr>", fortythree));
            tree.add(getTree(fortysix, "[ " +tokens.get(i+2).getValueOfToken() + " ]", blockffive));
            tree.add(getTree(fortysix+1, "<BoolOp>", fortythree));
            tree.add(getTree(fortysix+2, "[ " +tokens.get(i+3).getValueOfToken() + " ]", fortysix+1));

            tree.add(getTree(fortysix+4, "<Expr>", fortythree));
            tree.add(getTree(fortysix+5, "[ " +tokens.get(i+4).getValueOfToken() + " ]", fortysix+4));
            tree.add(getTree(fortysix+6, "[ " +tokens.get(i+5).getValueOfToken() + " ]", fortythree));

            tree.add(getTree(fortysix+7, "<Block>", fortysix+6));
            tree.add(getTree(fortysix+8, "[ " +tokens.get(i+6).getValueOfToken() + " ]", fortysix+7));
            


            
        //    }
            
        
        }
        forty+=56;
        fortyone+=56;
        
    fortytwo+=56;
    fortythree+=56;
    fortyfour+=56;
    fortyfive+=56;
    fortysix+=56;
    blockffive+=56;
        
        
        /*
        
        
        if((tokens.get(i+2).getValueOfToken().equals(letter)) && (((i==1)) || (i==7))){ //7 is case for parseTest1.txt
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            //System.out.print(i);
            tree.add(getTree(40, "<Statement>", four));
            tree.add(getTree(41, "<WhileStatement>", 40));
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
                                                                                    //{while(a==true){while(b==true){print(c)}}}$
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            tree.add(getTree(400, "<Statement>", four));
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
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            //System.out.print(i);
            tree.add(getTree(4000, "<Statement>", four));
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
            
        */
    }
    public void ifTree(int i){
        forty+=655;
        fortyone+=655;
        
    fortytwo+=655;
    fortythree+=655;
    fortyfour+=655;
    fortyfive+=655;
    fortysix+=655;
    blockffive+=655;
        
        if((tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")) && (!tokens.get(i+2).getTypeOfToken().equals("CHAR"))){ //7 is case for parseTest1.txt
            //if(i<5){
       // System.out.println("h19091081");
                
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            tree.add(getTree(forty, "<Statement>", four));
            tree.add(getTree(fortyone, "<IfStatement>", forty));
            tree.add(getTree(fortytwo, "[ " +tokens.get(i).getValueOfToken() + " ]", fortyone));
            tree.add(getTree(fortythree, "<BooleanExpr>", fortyone));
            tree.add(getTree(fortyfour, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fortythree));
            
            tree.add(getTree(blockffive, "<Block>", fortyone));
            tree.add(getTree(fortysix, "[ " +tokens.get(i+2).getValueOfToken() + " ]", blockffive));
            //tree.add(getTree(blockffive, "<Block>", fortyone));
            //tree.add(getTree(fortysix, "[ " +tokens.get(i+2).getValueOfToken() + " ]", blockffive));
            
        //    }
        
            
        
        }
        

        
        

        else if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("DIGIT"))  || (tokens.get(i+2).getTypeOfToken().equals("STRING"))
        || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL")) ){ //7 is case for parseTest1.txt
            //if(i<5){
               
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            tree.add(getTree(forty, "<Statement>", four));
            tree.add(getTree(fortyone, "<IfStatement>", forty));
            tree.add(getTree(fortytwo, "[ " +tokens.get(i).getValueOfToken() + " ]", fortyone));
            tree.add(getTree(fortythree, "<BooleanExpr>", fortyone));
            tree.add(getTree(fortyfour, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fortythree));
            
            tree.add(getTree(blockffive, "<Expr>", fortythree));
            tree.add(getTree(fortysix, "[ " +tokens.get(i+2).getValueOfToken() + " ]", blockffive));
            tree.add(getTree(fortysix+1, "<BoolOp>", fortythree));
            tree.add(getTree(fortysix+2, "[ " +tokens.get(i+3).getValueOfToken() + " ]", fortysix+1));

            tree.add(getTree(fortysix+4, "<Expr>", fortythree));
            tree.add(getTree(fortysix+5, "[ " +tokens.get(i+4).getValueOfToken() + " ]", fortysix+4));
            tree.add(getTree(fortysix+6, "[ " +tokens.get(i+5).getValueOfToken() + " ]", fortythree));
            
            tree.add(getTree(fortysix+7, "<Block>", fortysix+6));
            tree.add(getTree(fortysix+8, "[ " +tokens.get(i+6).getValueOfToken() + " ]", fortysix+7));
            


            
        //    }
            
        
        }
        
        
        
    }



    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void create(List<CCToken> tokenStream) {
            
        tree = new ArrayList<CCTree2>();

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            

            String element = tokens.get(i).getValueOfToken();
            tree.add(getTree(1, "<Program>", 0));

            switch (element) {
                
                
        
                //case ")":
                //blockStartTree(i);
                //break;
                case "{":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    blockStartTree2(i);
                    
                    
                    //tree.add(getTree(2, "<Block>", 1));
                    //tree.add(getTree(3, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
                    //tree.add(getTree(4, "<StatementList>", 2));

                    break;
                    

                case "}" :
                    blockEndTree(i);    
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
                //blockEndTree(i);
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
}