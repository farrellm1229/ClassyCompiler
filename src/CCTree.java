import java.util.ArrayList;
import java.util.List;

public class CCTree {
    private int nodeID;
    private String nodeValue;
    private int parentId;     
    public CCTree() {
    }

    public CCTree(int nodeID, String nodeValue, int parentId) {
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


    private static List<CCTree> tree; //defining array for tree
                                    //from tokens from token stream


    public static CCTree getTree(int nodeID, String nodeValue, int parentID) {
        CCTree cst = new CCTree();
        cst.setID(nodeID);
        cst.setNodeValue(nodeValue);
        cst.setParentId(parentID);

        return cst;
    }

    public static List<CCTree> selectChildren(int parentId) {
        List<CCTree> result = new ArrayList<CCTree>();
        for (CCTree t : tree) {
            if (t.getParentId() == parentId) {
                result.add(t);
            }
        }
        return result;
    }

    public static void printTree(CCTree cst, int level) {
        List<CCTree> children = selectChildren(cst.getID());
        String indent = ""; //creating incrementing "-" for cst
        for (int i = 0; i < level; i++) {
            indent = indent + "-";
        }
        System.out.println(indent + cst.getNodeValue()); //print node with according number of "-"
        if(children.size() > 0){
            level++;
        }
        for (CCTree obj : children) {
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
    //public static ArrayList<Tree> tree = new ArrayList<Tree>();
    public void intTree(int i){
        
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
            parseMessage("parseStatement();");
            parseMessage("parseVarDec();");
                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));    
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
            parseMessage("parseStatement();");
            parseMessage("parseVarDec();");
                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));    
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
            parseMessage("parseStatement();");
            parseMessage("parseVarDec();");
                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));    
        }
                    eleven=eleven+29;
                    twelve=twelve+29;
                    thirteen=thirteen+29;
                    fourteen=fourteen+29;
                    fifteen=fifteen+29;
                    sixteen=sixteen+29;
    }
    public void blockStartTree2(int i){
        parseMessage("parseBlock();");
        parseMessage("parseStatementList();");
        parseMessage("parseStatement();");


        if(i==0) {
            tree.add(getTree(2, "<Block>", 1));
            tree.add(getTree(3, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
            tree.add(getTree(four, "<StatementList>", 2));
            //tree.add(getTree(5, "<Statement>", 4));

            //System.out.println(two);
        }
        else if((tokens.get(i).getValueOfToken().equals("{")) && (!tokens.get(i-1).getValueOfToken().equals(")"))) {
       
        tree.add(getTree(four+1, "<Block>", four));
        tree.add(getTree(four+200, "[ " +tokens.get(i).getValueOfToken() + " ]", four+1));
        tree.add(getTree(four+300, "<StatementList>", four+1));
        four++;
        }
/*
else if (i==1){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (tokens.get(i+1).getValueOfToken().equals(letter)) && (tokens.get(i+2).getValueOfToken().equals("}"))){
    tree.add(getTree(5, "<Block>", 4));
    tree.add(getTree(6, "[ " +tokens.get(i).getValueOfToken() + " ]", 5));
    tree.add(getTree(7, "<StatementList>", 5));
    tree.add(getTree(8, "<Statement>", 7));

   
}

else if(i==2){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (!tokens.get(i+1).getValueOfToken().equals(letter))){
            tree.add(getTree(9, "<Block>", 8));
            tree.add(getTree(10, "[ " +tokens.get(i).getValueOfToken() + " ]", 9));
            tree.add(getTree(11, "<StatementList>", 9));
            
            

}

else{
    //TODO fix this, add new block node

}

   */          
                     
    }

        /*
        parseMessage("parseBlock();");
        parseMessage("parseStatementList();");
        parseMessage("parseStatement();");


        if(i==0) {
        

            tree.add(getTree(2, "<Block>", 1));
            tree.add(getTree(3, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
            tree.add(getTree(4, "<StatementList>", 2));
            //tree.add(getTree(5, "<Statement>", 4));

            //System.out.println(two);

}
else if (i==1){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (tokens.get(i+1).getValueOfToken().equals(letter)) && (tokens.get(i+2).getValueOfToken().equals("}"))){
    tree.add(getTree(5, "<Block>", 4));
    tree.add(getTree(6, "[ " +tokens.get(i).getValueOfToken() + " ]", 5));
    tree.add(getTree(7, "<StatementList>", 5));
    tree.add(getTree(8, "<Statement>", 7));

   
}

else if(i==2){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (!tokens.get(i+1).getValueOfToken().equals(letter))){
            tree.add(getTree(9, "<Block>", 8));
            tree.add(getTree(10, "[ " +tokens.get(i).getValueOfToken() + " ]", 9));
            tree.add(getTree(11, "<StatementList>", 9));
            
}

else if(i==3){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (!tokens.get(i+1).getValueOfToken().equals(letter))){
    tree.add(getTree(12, "<Block>", 11));
    tree.add(getTree(13, "[ " +tokens.get(i).getValueOfToken() + " ]", 12));
    tree.add(getTree(14, "<StatementList>", 12));
}

else if(i==4){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (!tokens.get(i+1).getValueOfToken().equals(letter))){
    tree.add(getTree(15, "<Block>", 14));
    tree.add(getTree(16, "[ " +tokens.get(i).getValueOfToken() + " ]", 15));
    tree.add(getTree(17, "<StatementList>", 15));
}

else if(i==5){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (!tokens.get(i+1).getValueOfToken().equals(letter))){
    tree.add(getTree(18, "<Block>", 17));
    tree.add(getTree(19, "[ " +tokens.get(i).getValueOfToken() + " ]", 18));
    tree.add(getTree(20, "<StatementList>", 18));
}

else if(i==6){//((tokens.get(i-1).getValueOfToken().equals(letter)) && (!tokens.get(i+1).getValueOfToken().equals(letter))){
    tree.add(getTree(21, "<Block>", 20));
    tree.add(getTree(22, "[ " +tokens.get(i).getValueOfToken() + " ]", 21));
    tree.add(getTree(23, "<StatementList>", 21));
}

else{
    //TODO fix this, add new block node

}

             
                     
    }

   */
    public void blockEndTree(int i){
        //System.out.println(i);
        //enough cases for {{{}}}$
        //I can add more but this is good for now
        if((tokens.get(i).getValueOfToken().equals("}")) && (tokens.get(i+1).getValueOfToken().equals("$"))) {
            tree.add(getTree(777, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
        }
        else{
            tree.add(getTree(777, "[ " +tokens.get(i).getValueOfToken() + " ]", four));
            four--;


        }
    }
      /*  if(i==1) {
            tree.add(getTree(77, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
           
        }
        else if(i==2) {
            tree.add(getTree(770, "[ " +tokens.get(i).getValueOfToken() + " ]", 5));
            tree.add(getTree(771, "<StatementList>", 7));

           
        }
        else if(i==3) {
            tree.add(getTree(790, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
            tree.add(getTree(791, "<StatementList>", 4));

        }
        else if(i==4) {
            tree.add(getTree(792, "[ " +tokens.get(i).getValueOfToken() + " ]", 5));
            tree.add(getTree(793, "<StatementList>", 7));

        }
        else if(i==5) {
            tree.add(getTree(781, "[ " +tokens.get(i).getValueOfToken() + " ]", 9));
        }
        else if(i==34) {
            tree.add(getTree(124, "[ " +tokens.get(i).getValueOfToken() + " ]", 36));
        }
        else if(i==35) {
            tree.add(getTree(125, "[ " +tokens.get(i).getValueOfToken() + " ]", 36));
        }
        else if(i==36) {
            tree.add(getTree(126, "[ " +tokens.get(i).getValueOfToken() + " ]", 36));
        }
        else if(i==37) {
            tree.add(getTree(127, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
        }
        else{
            tree.add(getTree(128, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));

        }
    }

        //System.out.println(i);
        //enough cases for {{{}}}$
        //I can add more but this is good for now
        /*
        if(i==1) {
            tree.add(getTree(77, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
           
        }
        else if(i==2) {
            tree.add(getTree(770, "[ " +tokens.get(i).getValueOfToken() + " ]", 5));
            //tree.add(getTree(771, "<StatementList>", 7));

           
        }
        else if(i==3) {
            tree.add(getTree(790, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
            //tree.add(getTree(791, "<StatementList>", 4));

        }
        else if(i==4) {
            tree.add(getTree(792, "[ " +tokens.get(i).getValueOfToken() + " ]", 12));
           // tree.add(getTree(793, "<StatementList>", 14));

        }
        else if(i==5) {
            tree.add(getTree(781, "[ " +tokens.get(i).getValueOfToken() + " ]", 9));
        }
        else if(i==6) {
            tree.add(getTree(770, "[ " +tokens.get(i).getValueOfToken() + " ]", 5));
           // tree.add(getTree(771, "<StatementList>", 11));

           
        }
        else if(i==7) {
            tree.add(getTree(790, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
           // tree.add(getTree(791, "<StatementList>", 14));

        }
        else if(i==8) {
            tree.add(getTree(792, "[ " +tokens.get(i).getValueOfToken() + " ]", 15));
           // tree.add(getTree(793, "<StatementList>", 17));

        }
        else if(i==9) {
            tree.add(getTree(781, "[ " +tokens.get(i).getValueOfToken() + " ]", 9));
        }
        else if(i==10) {
            tree.add(getTree(124, "[ " +tokens.get(i).getValueOfToken() + " ]", 36));
        }
        else if(i==35) {
            tree.add(getTree(125, "[ " +tokens.get(i).getValueOfToken() + " ]", 36));
        }
        else if(i==36) {
            tree.add(getTree(126, "[ " +tokens.get(i).getValueOfToken() + " ]", 36));
        }
        else if(i==37) {
            tree.add(getTree(127, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
        }
        else{
            tree.add(getTree(128, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));

        }
    }
*/
    public void assignmentTree(int i){
        /*
        if(tokens.get(i-1).getValueOfToken().equals(letter)){
            parseMessage("parseStatement();");
            parseMessage("parseAssignmentStatement();");
            //tree.add(getTree(25, "<StatementList>", 4));
            tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
            tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
            tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
                tree.add(getTree(thirtytwo, "<IntExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
                if(tokens.get(i+2).getTypeOfToken().equals("PLUS")){
                    tree.add(getTree(thirtytwo+2, "[ " +tokens.get(i+2).getValueOfToken() + " ]",thirtytwo));
                    tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                    if(tokens.get(i+3).getTypeOfToken().equals("CHAR")){
                        tree.add(getTree(thirtytwo+4, "<ID>", thirtytwo+3));//
                        tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                    }
                    else{
                    tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                    tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                    }
                }
                else{
                    //no plus found after first expr
                }

            }
            else if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
                tree.add(getTree(thirtytwo, "<StringExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
                tree.add(getTree(thirtytwo, "<BooleanExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
            else{
                tree.add(getTree(thirtytwo, "<ID>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
        }
       /* if(tokens.get(i+1).getValueOfToken().equals(letter)){
            //tree.add(getTree(25, "<StatementList>", 4));
            tree.add(getTree(twentysix, "<Statement>", four));
            tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
            tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
            tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtyone));
        } */
     /*   twentysix=twentysix+61;
        twentyseven=twentyseven+61;
        twentyeight=twentyeight+61;
        twentynine=twentynine+61;
        thirty=thirty+61;
        thirtyone=thirtyone+61;
        thirtytwo=thirtytwo+61;


    }
    
        */
        //if(i>(tokens.size()/2)){

        if((tokens.get(i+2).getTypeOfToken().equals("PLUS")) && (tokens.get(i+4).getTypeOfToken().equals("PLUS"))){
        if(tokens.size()>10){
        
            //if(tokens.get(i-1).getValueOfToken().equals(letter)){
            parseMessage("parseStatement();");
            parseMessage("parseAssignmentStatement();");
            //tree.add(getTree(25, "<StatementList>", 4));
            tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
            tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
            tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
                tree.add(getTree(thirtytwo, "<IntExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
                if(tokens.get(i+2).getTypeOfToken().equals("PLUS")){
                    tree.add(getTree(thirtytwo+2, "[ " +tokens.get(i+2).getValueOfToken() + " ]",thirtytwo));
                    
                    

                    if((tokens.get(i+3).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+4).getTypeOfToken().equals("PLUS"))){
                        tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                        tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                        tree.add(getTree(thirtytwo+5, "[ " +tokens.get(i+3).getValueOfToken() + " ]",thirtytwo+4));
                        tree.add(getTree(thirtytwo+6, "[ " +tokens.get(i+4).getValueOfToken() + " ]",thirtytwo+4));
                    }
                    

                        if((tokens.get(i+5).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+6).getTypeOfToken().equals("PLUS"))){

                                tree.add(getTree(thirtytwo+7, "<Expr>", thirtytwo+6));//
                                tree.add(getTree(thirtytwo+8, "<IntExpr>", thirtytwo+7));//
                                tree.add(getTree(thirtytwo+9, "[ " +tokens.get(i+5).getValueOfToken() + " ]",thirtytwo+8));
                                tree.add(getTree(thirtytwo+10, "[ " +tokens.get(i+6).getValueOfToken() + " ]",thirtytwo+8));
                        }

                            
                            if((tokens.get(i+7).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+8).getTypeOfToken().equals("PLUS"))){
                                    
                                        tree.add(getTree(thirtytwo+11, "<Expr>", thirtytwo+10));//
                                        tree.add(getTree(thirtytwo+12, "<IntExpr>", thirtytwo+11));//
                                        tree.add(getTree(thirtytwo+13, "[ " +tokens.get(i+7).getValueOfToken() + " ]",thirtytwo+12));
                                        tree.add(getTree(thirtytwo+14, "[ " +tokens.get(i+8).getValueOfToken() + " ]",thirtytwo+12));
                            }
                            

                                        if((tokens.get(i+9).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+10).getTypeOfToken().equals("PLUS"))){

                                                tree.add(getTree(thirtytwo+15, "<Expr>", thirtytwo+14));//
                                                tree.add(getTree(thirtytwo+16, "<IntExpr>", thirtytwo+15));//
                                                tree.add(getTree(thirtytwo+17, "[ " +tokens.get(i+9).getValueOfToken() + " ]",thirtytwo+16));
                                                tree.add(getTree(thirtytwo+18, "[ " +tokens.get(i+8).getValueOfToken() + " ]",thirtytwo+17));
                                        }
                                        
                                                //cases for no plus after digit
                                                if((tokens.get(i+3).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+4).getTypeOfToken().equals("PLUS"))){

                                                    tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                                                    //tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                                                    tree.add(getTree(thirtytwo+5, "[ " +tokens.get(i+3).getValueOfToken() + " ]",thirtytwo+3));
                                                    //tree.add(getTree(thirtytwo+6, "[ " +tokens.get(i+4).getValueOfToken() + " ]",thirtytwo+3));
                                                }
                            
                                                    if((tokens.get(i+5).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+6).getTypeOfToken().equals("PLUS"))){


                            
                                                            tree.add(getTree(thirtytwo+7, "<Expr>", thirtytwo+6));//
                                                            //tree.add(getTree(thirtytwo+8, "<IntExpr>", thirtytwo+7));//
                                                            tree.add(getTree(thirtytwo+9, "[ " +tokens.get(i+5).getValueOfToken() + " ]",thirtytwo+7));
                                                            //tree.add(getTree(thirtytwo+10, "[ " +tokens.get(i+6).getValueOfToken() + " ]",thirtytwo+7));
                            
                                                        
                                                    }
                                                            if((tokens.get(i+7).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+8).getTypeOfToken().equals("PLUS"))){

                                                            
                                                                    tree.add(getTree(thirtytwo+11, "<Expr>", thirtytwo+10));//
                                                                    //tree.add(getTree(thirtytwo+12, "<IntExpr>", thirtytwo+11));//
                                                                    tree.add(getTree(thirtytwo+13, "[ " +tokens.get(i+7).getValueOfToken() + " ]",thirtytwo+11));
                                                                    //tree.add(getTree(thirtytwo+14, "[ " +tokens.get(i+8).getValueOfToken() + " ]",thirtytwo+11));
                                                            }
                                                                    if((tokens.get(i+9).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+10).getTypeOfToken().equals("PLUS"))){

                                                                            tree.add(getTree(thirtytwo+15, "<Expr>", thirtytwo+14));//
                                                                            //tree.add(getTree(thirtytwo+16, "<IntExpr>", thirtytwo+15));//
                                                                            tree.add(getTree(thirtytwo+17, "[ " +tokens.get(i+9).getValueOfToken() + " ]",thirtytwo+15));
                                                                            //tree.add(getTree(thirtytwo+18, "[ " +tokens.get(i+8).getValueOfToken() + " ]",thirtytwo+17));
                                                                    }
                    if(tokens.get(i+3).getTypeOfToken().equals("CHAR")){
                        tree.add(getTree(thirtytwo+4, "<ID>", thirtytwo+3));//
                        tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                    }
                    else{
                   // tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                    //tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                    }
                }
            
    

                                    
                                
                            
                    
            
                else{
                    //no plus found after first expr
                }

            }
            else if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
                tree.add(getTree(thirtytwo, "<StringExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
                tree.add(getTree(thirtytwo, "<BooleanExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
            else{
                tree.add(getTree(thirtytwo, "<ID>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
        
       /* if(tokens.get(i+1).getValueOfToken().equals(letter)){
            //tree.add(getTree(25, "<StatementList>", 4));
            tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
            tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
            tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtyone));
        } */

        
        twentysix=twentysix+61;
        twentyseven=twentyseven+61;
        twentyeight=twentyeight+61;
        twentynine=twentynine+61;
        thirty=thirty+61;
        thirtyone=thirtyone+61;
        thirtytwo=thirtytwo+61;


    }
    else{
        if(tokens.get(i-1).getTypeOfToken().equals("CHAR")){
        
        //if(tokens.get(i-1).getValueOfToken().equals(letter)){
            parseMessage("parseStatement();");
            parseMessage("parseAssignmentStatement();");
            //tree.add(getTree(25, "<StatementList>", 4));
            tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
            tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
            tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
            
            tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
                tree.add(getTree(thirtytwo, "<IntExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
                if(tokens.get(i+2).getTypeOfToken().equals("PLUS")){
                    tree.add(getTree(thirtytwo+2, "[ " +tokens.get(i+2).getValueOfToken() + " ]",thirtytwo));
                    
                    

                    if((tokens.get(i+3).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+4).getTypeOfToken().equals("PLUS"))){
                        tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                        tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                        tree.add(getTree(thirtytwo+5, "[ " +tokens.get(i+3).getValueOfToken() + " ]",thirtytwo+4));
                        tree.add(getTree(thirtytwo+6, "[ " +tokens.get(i+4).getValueOfToken() + " ]",thirtytwo+4));
                    }
                    

                        if((tokens.get(i+5).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+6).getTypeOfToken().equals("PLUS"))){

                                tree.add(getTree(thirtytwo+7, "<Expr>", thirtytwo+6));//
                                tree.add(getTree(thirtytwo+8, "<IntExpr>", thirtytwo+7));//
                                tree.add(getTree(thirtytwo+9, "[ " +tokens.get(i+5).getValueOfToken() + " ]",thirtytwo+8));
                                tree.add(getTree(thirtytwo+10, "[ " +tokens.get(i+6).getValueOfToken() + " ]",thirtytwo+8));
                        }

                            
                          

                                                //cases for no plus after digit
                                                if((tokens.get(i+3).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+4).getTypeOfToken().equals("PLUS"))){

                                                    tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                                                    //tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                                                    tree.add(getTree(thirtytwo+5, "[ " +tokens.get(i+3).getValueOfToken() + " ]",thirtytwo+3));
                                                    //tree.add(getTree(thirtytwo+6, "[ " +tokens.get(i+4).getValueOfToken() + " ]",thirtytwo+3));
                                                }
                            
                                                    if((tokens.get(i+5).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+6).getTypeOfToken().equals("PLUS"))){


                            
                                                            tree.add(getTree(thirtytwo+7, "<Expr>", thirtytwo+6));//
                                                            //tree.add(getTree(thirtytwo+8, "<IntExpr>", thirtytwo+7));//
                                                            tree.add(getTree(thirtytwo+9, "[ " +tokens.get(i+5).getValueOfToken() + " ]",thirtytwo+7));
                                                            //tree.add(getTree(thirtytwo+10, "[ " +tokens.get(i+6).getValueOfToken() + " ]",thirtytwo+7));
                            
                                                        
                                                    }
                                                            
                                                                    
                    if(tokens.get(i+3).getTypeOfToken().equals("CHAR")){
                        tree.add(getTree(thirtytwo+4, "<ID>", thirtytwo+3));//
                        tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                    }
                    else{
                   // tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                    //tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                    }
                }
            
    

                                    
                                
                            
                    
            
                else{
                    //no plus found after first expr
                }

            }
            else if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
                tree.add(getTree(thirtytwo, "<StringExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
                tree.add(getTree(thirtytwo, "<BooleanExpr>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
            else{
                tree.add(getTree(thirtytwo, "<ID>", thirtyone));//
                tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            }
        }
    }
}
else if((tokens.get(i+2).getTypeOfToken().equals("PLUS")) && (!tokens.get(i+4).getTypeOfToken().equals("PLUS"))){
   // if(tokens.get(i-1).getValueOfToken().equals(letter)){
        //System.out.println("hehehehehehe");

        parseMessage("parseStatement();");
        parseMessage("parseAssignmentStatement();");
        //tree.add(getTree(25, "<StatementList>", 4));
        tree.add(getTree(twentysix, "<Statement>", four));

        tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
        tree.add(getTree(twentyeight, "<ID>", twentyseven));
        tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
        tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
        
        tree.add(getTree(thirtyone, "<Expr>", twentyseven));
        if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
            tree.add(getTree(thirtytwo, "<IntExpr>", thirtyone));//
            tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            if(tokens.get(i+2).getTypeOfToken().equals("PLUS")){
                tree.add(getTree(thirtytwo+2, "[ " +tokens.get(i+2).getValueOfToken() + " ]",thirtytwo));
                tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                if(tokens.get(i+3).getTypeOfToken().equals("CHAR")){
                    tree.add(getTree(thirtytwo+4, "<ID>", thirtytwo+3));//
                    tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                }
                else{
                tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                }
            }
            else{
                //no plus found after first expr
            }

        }
        else if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
            tree.add(getTree(thirtytwo, "<StringExpr>", thirtyone));//
            tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
        }
        else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
            tree.add(getTree(thirtytwo, "<BooleanExpr>", thirtyone));//
            tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
        }
        else{
            tree.add(getTree(thirtytwo, "<ID>", thirtyone));//
            tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
        }
    //}
   /* if(tokens.get(i+1).getValueOfToken().equals(letter)){
        //tree.add(getTree(25, "<StatementList>", 4));
        tree.add(getTree(twentysix, "<Statement>", four));
        tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
        tree.add(getTree(twentyeight, "<ID>", twentyseven));
        tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
        tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
        
        tree.add(getTree(thirtyone, "<Expr>", twentyseven));
        tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtyone));
    } */
    twentysix=twentysix+61;
    twentyseven=twentyseven+61;
    twentyeight=twentyeight+61;
    twentynine=twentynine+61;
    thirty=thirty+61;
    thirtyone=thirtyone+61;
    thirtytwo=thirtytwo+61;




    

}
else if((tokens.get(i-1).getTypeOfToken().equals("CHAR")) && (!tokens.get(i+2).getTypeOfToken().equals("PLUS"))){
    if(tokens.get(i-1).getTypeOfToken().equals("CHAR")){
    
    //if(tokens.get(i-1).getValueOfToken().equals(letter)){
        //System.out.println("hehehehehehe");
        parseMessage("parseStatement();");
        parseMessage("parseAssignmentStatement();");
        //tree.add(getTree(25, "<StatementList>", 4));
        tree.add(getTree(twentysix, "<Statement>", four));

        tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
        tree.add(getTree(twentyeight, "<ID>", twentyseven));
        tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
        tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
        
        tree.add(getTree(thirtyone, "<Expr>", twentyseven));
        if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
            tree.add(getTree(thirtytwo, "<IntExpr>", thirtyone));//
            tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
            if(tokens.get(i+2).getTypeOfToken().equals("PLUS")){
                tree.add(getTree(thirtytwo+2, "[ " +tokens.get(i+2).getValueOfToken() + " ]",thirtytwo));
                tree.add(getTree(thirtytwo+3, "<Expr>", thirtytwo));//
                if(tokens.get(i+3).getTypeOfToken().equals("CHAR")){
                    tree.add(getTree(thirtytwo+4, "<ID>", thirtytwo+3));//
                    tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                }
                else{
                tree.add(getTree(thirtytwo+4, "<IntExpr>", thirtytwo+3));//
                tree.add(getTree(thirtytwo+5, "[ " + tokens.get(i+3).getValueOfToken() + " ]", thirtytwo+4));//
                }
            }
            else{
                //no plus found after first expr
            }

        }
        if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
            tree.add(getTree(thirtytwo, "<StringExpr>", thirtyone));//
            tree.add(getTree(thirtytwo+1, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo));
        }
        if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
            tree.add(getTree(thirtytwo+2, "<BooleanExpr>", thirtyone));//
            tree.add(getTree(thirtytwo+3, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo+2));
            
        }
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
            tree.add(getTree(thirtytwo+4, "<ID>", thirtyone));//
            tree.add(getTree(thirtytwo+5, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtytwo+4));
        }
        
    }
   /* if(tokens.get(i+1).getValueOfToken().equals(letter)){
        //tree.add(getTree(25, "<StatementList>", 4));
        tree.add(getTree(twentysix, "<Statement>", four));
        tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
        tree.add(getTree(twentyeight, "<ID>", twentyseven));
        tree.add(getTree(twentynine, "[ " +tokens.get(i-1).getValueOfToken() + " ]", twentyeight));
        tree.add(getTree(thirty, "[ " + tokens.get(i).getValueOfToken() + " ]", twentyseven));
        
        tree.add(getTree(thirtyone, "<Expr>", twentyseven));
        tree.add(getTree(thirtytwo, "[ " +tokens.get(i+1).getValueOfToken() + " ]",thirtyone));
    } */
    


    twentysix=twentysix+61;
    twentyseven=twentyseven+61;
    twentyeight=twentyeight+61;
    twentynine=twentynine+61;
    thirty=thirty+61;
    thirtyone=thirtyone+61;
    thirtytwo=thirtytwo+61;

    

}

    
    
}
    

    
    public void priTree(int i){
        
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) || (tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL"))
        || (tokens.get(i+2).getTypeOfToken().equals("STRING"))|| (tokens.get(i+2).getTypeOfToken().equals("DIGIT"))){
            parseMessage("parseStatement();");
            parseMessage("parsePrintStatement();");
            //System.out.print(i);

                    tree.add(getTree(thirtythree, "<Statement>", four));
                    tree.add(getTree(thirtyfour, "<PrintStatement>", thirtythree));

                    tree.add(getTree(thirtyfive, "[ " +tokens.get(i).getValueOfToken() + " ]", thirtyfour)); //[print]
                    tree.add(getTree(thirtysix, "[ " +tokens.get(i+1).getValueOfToken() + " ]", thirtyfour));//[(]
                    tree.add(getTree(thirtyseven, "<Expr>", thirtysix));//
                    if(tokens.get(i+2).getTypeOfToken().equals("CHAR")){
                        tree.add(getTree(thirtyeight, "<ID>", thirtyseven));//
                        tree.add(getTree(thirtynine, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyeight));// [ x ]
                        tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour)); // [ ) ] for case print(x)
                        
                    }
                    if(tokens.get(i+2).getTypeOfToken().equals("BOOL_VAL")){
                        tree.add(getTree(thirtyeight, "<BooleanExpr>", thirtyseven));//
                        tree.add(getTree(thirtynine, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyeight));// [ x ]
                        tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour)); // [ ) ] for case print(x)
                        
                    }
                    if(tokens.get(i+2).getTypeOfToken().equals("STRING")){
                        tree.add(getTree(thirtyeight, "<StringExpr>", thirtyseven));//
                        tree.add(getTree(thirtynine, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyeight));// [ x ]
                        tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour)); // [ ) ] for case print(x)
                    
                    }
                    if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (!tokens.get(i+3).getTypeOfToken().equals("PLUS"))){

                        tree.add(getTree(thirtyeight, "<IntExpr>", thirtyseven));//
                        tree.add(getTree(thirtynine, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyeight));// [ x ]
                        tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour)); // [ ) ] for case print(x)
                    
                    }
                    if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))){
                        tree.add(getTree(thirtyeight, "<IntExpr>", thirtyseven));//
                        tree.add(getTree(thirtynine, "[ " +tokens.get(i+2).getValueOfToken() + " ]", thirtyeight));// [ x ]
                        tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyeight));// [ + ]
                        tree.add(getTree(thirtynine+2, "[ " +tokens.get(i+4).getValueOfToken() + " ]", thirtyeight));// [ x ]
                        tree.add(getTree(thirtynine+3, "[ " +tokens.get(i+5).getValueOfToken() + " ]", thirtyfour));// [ ) ]
                        /*if(tokens.get(i+3).getTypeOfToken().equals("PLUS")){ //case for int expr within print statement
                            tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyeight));// [ + ]
                            tree.add(getTree(thirtynine+2, "[ " +tokens.get(i+4).getValueOfToken() + " ]", thirtyeight));// [ x ]
                            tree.add(getTree(thirtynine+3, "[ " +tokens.get(i+5).getValueOfToken() + " ]", thirtyfour));// [ ) ]
                            System.out.println("ok");
    
                        }
                        else{
                            tree.add(getTree(thirtynine+1, "[ " +tokens.get(i+3).getValueOfToken() + " ]", thirtyfour)); // [ ) ] for case print(x)
                        }*/
                    }
                    
                }  
                    
        
        thirtythree+=13;
        thirtyfour+=13;
        thirtyfive+=13;
        thirtysix+=13;
        thirtyseven+=13;
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
        

        
        

        if((tokens.get(i+2).getTypeOfToken().equals("CHAR"))){ //7 is case for parseTest1.txt
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

            


            
        //    }
            
        
        }
        forty+=100;
        fortyone+=100;
        
    fortytwo+=100;
    fortythree+=100;
    fortyfour+=100;
    fortyfive+=100;
    fortysix+=100;
    blockffive+=100;
        
        
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
        if((tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL"))){ //7 is case for parseTest1.txt
            forty+=100;
            fortyone+=100;
            
        fortytwo+=100;
        fortythree+=100;
        fortyfour+=100;
        fortyfive+=100;
        fortysix+=100;
        blockffive+=100;
            //if(i<5){
            parseMessage("parseStatement();");
            parseMessage("parseWhileStatement();");
            tree.add(getTree(forty, "<Statement>", four));
            tree.add(getTree(fortyone, "<IfStatement>", forty));
            tree.add(getTree(fortytwo, "[ " +tokens.get(i).getValueOfToken() + " ]", fortyone));
            tree.add(getTree(fortythree, "<BooleanExpr>", fortyone));
            tree.add(getTree(fortyfour, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fortythree));
            
            tree.add(getTree(blockffive, "<Block>", fortyone));
            tree.add(getTree(fortysix, "[ " +tokens.get(i+2).getValueOfToken() + " ]", blockffive));
            
        //    }
            
        
        }

        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (((i==1)) || (i==7))){ //7 is case for parseTest1.txt
            parseMessage("parseStatement();");
            parseMessage("parseIfStatement();");
            tree.add(getTree(60, "<Statement>", four));
            tree.add(getTree(61, "<IfStatement>", 60));
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

        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (i!=1) && (i < 14)){ //implying double if statement test case
            parseMessage("parseStatement();");
            parseMessage("parseIfStatement();");
            tree.add(getTree(600, "<Statement>", four));
            tree.add(getTree(610, "<IfStatement>", 600));
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
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (i!=1) && (i > 14)){ //implying triple if statement test case
            parseMessage("parseStatement();");
            parseMessage("parseIfStatement();");
            tree.add(getTree(6000, "<Statement>", four));
            tree.add(getTree(6100, "<IfStatement>", 6000));
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
            
        tree = new ArrayList<CCTree>();

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