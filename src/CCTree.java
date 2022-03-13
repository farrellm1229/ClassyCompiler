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

    //public static ArrayList<Tree> tree = new ArrayList<Tree>();

    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void create(List<CCToken> tokenStream) {

        tree = new ArrayList<CCTree>();

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();

        for (int i=0; i<size; i++) {

            String element = tokens.get(i).getValueOfToken();
            tree.add(getTree(1, "<Program>", 0));

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
                //case "string":

                    tree.add(getTree(5, "<Statement>", 4));
                    tree.add(getTree(6, "<VarDecl>", 5));
                    tree.add(getTree(7, "<Type>", 6));
                    tree.add(getTree(8, "[ " +tokens.get(i).getValueOfToken() + " ]", 7));
                    tree.add(getTree(9, "<ID>", 6));
                    tree.add(getTree(10, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 9));
                    
                    break;

                case "int":
                if((tokens.get(i+1).getValueOfToken().equals("a"))){ //&& (!tokens.get(i+2).getValueOfToken().equals("int"))){
                    int eleven = 11;
                    int four = 4;
                    int twelve = 12;
                    int thirteen = 13;
                    int fourteen = 14;
                    int fifteen = 15;
                    int sixteen = 16;
                    

                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));
                    /*four++;
                    eleven++;
                    twelve++;
                    thirteen++;
                    fourteen++;
                    fifteen++;
                    sixteen++;*/

                    
                }
                if((tokens.get(i+1).getValueOfToken().equals("z"))){ //&& (!tokens.get(i+2).getValueOfToken().equals("int"))){
                    int eleven = 17;
                    int four = 4;
                    int twelve = 18;
                    int thirteen = 19;
                    int fourteen = 20;
                    int fifteen = 21;
                    int sixteen = 22;
                    

                    tree.add(getTree(eleven, "<Statement>", four));
                    tree.add(getTree(twelve, "<VarDecl>", eleven));
                    tree.add(getTree(thirteen, "<Type>", twelve));
                    tree.add(getTree(fourteen, "[ " +tokens.get(i).getValueOfToken() + " ]", thirteen));
                    tree.add(getTree(fifteen, "<ID>", twelve));
                    tree.add(getTree(sixteen, "[ " +tokens.get(i+1).getValueOfToken() + " ]", fifteen));
                    /*four++;
                    eleven++;
                    twelve++;
                    thirteen++;
                    fourteen++;
                    fifteen++;
                    sixteen++;*/

                    
                }
                    break;
                case "string":
                    tree.add(getTree(17, "<Statement>", 4));
                    tree.add(getTree(18, "<VarDecl>", 17));
                    tree.add(getTree(19, "<Type>", 18));
                    tree.add(getTree(20, "[ " +tokens.get(i).getValueOfToken() + " ]", 19));
                    tree.add(getTree(21, "<ID>", 18));
                    tree.add(getTree(22, "[ " +tokens.get(i+1).getValueOfToken() + " ]", 21));
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

                if(tokens.get(i+1).getTypeOfToken().equals("ASSIGNMENT")){
                    //tree.add(getTree(25, "<StatementList>", 4));
                    tree.add(getTree(26, "<Statement>", 4));

                    tree.add(getTree(27, "<AssignmentStatement>", 26));
                    tree.add(getTree(28, "<ID>", 27));
                    tree.add(getTree(29, "[ " +tokens.get(i).getValueOfToken() + " ]", 28));
                    tree.add(getTree(30, "[ " + tokens.get(i+1).getValueOfToken() + " ]", 27));
                    
                    tree.add(getTree(31, "<Expr>", 27));
                    tree.add(getTree(32, "[ " +tokens.get(i+2).getValueOfToken() + " ]",31));
                }
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
            
        
        

        }
        
        
        printTree(tree.get(0), 0); //print tree yay!
    }
}