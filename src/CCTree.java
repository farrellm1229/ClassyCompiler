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


    //public static ArrayList<Tree> tree = new ArrayList<Tree>();
    public void intTree(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));    
        }
                    eleven=eleven+5;
                    twelve=twelve+5;
                    thirteen=thirteen+5;
                    fourteen=fourteen+5;
                    fifteen=fifteen+5;
                    sixteen=sixteen+5;
    }
    public void stringTree(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));    
        }
                    eleven=eleven+5;
                    twelve=twelve+5;
                    thirteen=thirteen+5;
                    fourteen=fourteen+5;
                    fifteen=fifteen+5;
                    sixteen=sixteen+5;
    }
    public void booleanTree1(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));    
        }
                    eleven=eleven+5;
                    twelve=twelve+5;
                    thirteen=thirteen+5;
                    fourteen=fourteen+5;
                    fifteen=fifteen+5;
                    sixteen=sixteen+5;
    }

    public void assignmentTree(String assignment, int i){
        if(tokens.get(i+1).getTypeOfToken().equals(assignment)){
            //tree.add(getTree(25, "<StatementList>", 4));
            tree.add(getTree(twentysix, "<Statement>", four));

            tree.add(getTree(twentyseven, "<AssignmentStatement>", twentysix));
            tree.add(getTree(twentyeight, "<ID>", twentyseven));
            tree.add(getTree(twentynine, "[ " +tokens.get(i).getValueOfToken() + " ]", twentyeight));
            tree.add(getTree(thirty, "[ " + tokens.get(i+1).getValueOfToken() + " ]", twentyseven));
            
            tree.add(getTree(thirtyone, "<Expr>", twentyseven));
            tree.add(getTree(thirtytwo, "[ " +tokens.get(i+2).getValueOfToken() + " ]",thirtyone));
        }
        twentysix=twentysix+6;
        twentyseven=twentyseven+6;
        twentyeight=twentyeight+6;
        twentynine=twentynine+6;
        thirty=thirty+6;
        thirtyone=thirtyone+6;
        thirtytwo=thirtytwo+6;


    }

    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void create(List<CCToken> tokenStream) {

        tree = new ArrayList<CCTree>();

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        int j =0;

        for (int i=0; i<size; i++) {

            String element = tokens.get(i).getValueOfToken();
            tree.add(getTree(1, "<Program>", 0));
            String[] alpha = {"a", "b", "c", "d", "e", "f"};
                 

            switch (element) {
                
                
        
                case "{":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    
                    
                    tree.add(getTree(2, "<Block>", 1));
                    tree.add(getTree(3, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));
                    tree.add(getTree(4, "<StatementList>", 2));

                    break;
                    

                case "}" :
                    tree.add(getTree(300, "[ " +tokens.get(i).getValueOfToken() + " ]", 2));


                    break;

                //case "int":
                case "boolean": //having them all together caused issues :(
                    booleanTree1("a", i); booleanTree1("b", i); booleanTree1("c", i); booleanTree1("d", i);
                    booleanTree1("e", i); booleanTree1("f", i); booleanTree1("g", i); booleanTree1("h", i);
                    booleanTree1("i", i); booleanTree1("j", i); booleanTree1("k", i); booleanTree1("l", i);
                    booleanTree1("m", i); booleanTree1("n", i); booleanTree1("o", i); booleanTree1("p", i);
                    booleanTree1("q", i); booleanTree1("r", i); booleanTree1("s", i); booleanTree1("t", i);
                    booleanTree1("u", i); booleanTree1("v", i); booleanTree1("w", i); booleanTree1("x", i);
                    booleanTree1("y", i); booleanTree1("z", i);

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
                //if I uncomment this it messes EVERYTHING up, so
                //they will remain commented out until I find an alternative solution
                
                case "a":
                case "b":
                case "c":
                case "d":
                case "e":
                case "f":
                case "g":
                case "h":
                case "i":
                case "j":
                case "k":
                case "l":
                case "m":
                case "n":
                case "o":
                case "p":
                case "q":
                case "r":
                case "s":
                case "t":
                case "u":
                case "v":
                case "w":
                case "x":
                case "y":
                case "z":
                    assignmentTree("ASSIGNMENT", i);
                    break;
                
                                

                case "print":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    
                    tree.add(getTree(33, "<Statement>", 4));
                    tree.add(getTree(34, "<PrintStatement>", 33));

                    tree.add(getTree(35, "[ " +tokens.get(i).getValueOfToken() + " ]", 34));
                    tree.add(getTree(36, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 34));
                    tree.add(getTree(37, "<Expr>", 36));
                    tree.add(getTree(38, "[ " +tokens.get(i+2).getValueOfToken() + " ]", 37));
                    tree.add(getTree(39, "[ " +tokens.get(i+3).getValueOfToken() + " ]", 34));

                    break;
                case "while":
                    //System.out.println("case");
                    //System.out.println(tokens.get(i).getValueOfToken());
                    
                    tree.add(getTree(40, "<Statement>", 4));
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
                    tree.add(getTree(53, tokens.get(i+6).getValueOfToken(), 52));
                    
                    /*tree.add(getTree(54, "<StatementList>", 52));
                    tree.add(getTree(55, "<Statement>", 54));
                    tree.add(getTree(56, "<PrintStatement>", 55));
                    tree.add(getTree(57, "[ " +tokens.get(i+7).getValueOfToken() + " ]", 56));
                    tree.add(getTree(58, "[ " +tokens.get(i+8).getValueOfToken() + " ]", 56));
                    tree.add(getTree(59, "<Expr>", 58));
                    tree.add(getTree(60, "[ " +tokens.get(i+9).getValueOfToken() + " ]", 59));
                    tree.add(getTree(61, "[ " +tokens.get(i+10).getValueOfToken() + " ]", 56));
*/
                    break;

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