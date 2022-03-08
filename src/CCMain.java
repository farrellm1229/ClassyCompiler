import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CCMain { //good name? maybe, maybe not...but maybe?

    public static void displayMessage(CCTokenTypes token, String value, int lineNum) {
        System.out.println("DEBUG Lexer - " + token.name + " [ " + value + " ] found at line: " + lineNum);
        
    }

    public static void errorMessage(String value, int lineNum) {
        System.out.println("ERROR Lexer - Unrecognized Token [ " + value + " ] found at line: " + (lineNum-1));
    }
	public static void warningMessage(int lineNum) {
        System.out.println("    WARNING - Unterminated Comment! found at line: " + lineNum);

    }

    public static void matchTokens(String args) throws IOException {
        //Read Sample Program File
        //File sampleProgram = new File(args);
		BufferedReader lineScanner = new BufferedReader(new FileReader(args));

        //Declaring variables 
        //some of which are only used once or twice but they get the job done
		Matcher lexemeValue;
       
        int programNumber=1;
        int lineNumber=1;
        Boolean ignoreComment = false;
       // Boolean insideOfString = false; --- This was/is a work in progress. As of now
       //I am just matching the contents inside a string as a single token using RegEx

       
		Scanner readFileContents=new Scanner(args);
		String filePath=readFileContents.next();
	
		
		//reading the file containing sample programs to test
        //functionality of the Classy Compiler lexical analyzer
		File file = new File(filePath);
		
		FileInputStream tokenStream = null;
		String sampleProgramContents=null;
    
        tokenStream = new FileInputStream(file); //extract contents of file and put into input stream
        byte[] saveTokens = new byte[(int)file.length()];
        new DataInputStream(tokenStream).readFully(saveTokens);
        tokenStream.close(); //close input stream of tokens
        sampleProgramContents = new String(saveTokens);
        
        
		ArrayList<CCToken> listOfTokens=new ArrayList<CCToken>(); //CC - Classy Compiler Token
        ArrayList<CCTokenStream> listOfLexemes=new ArrayList<CCTokenStream>(); // token stream for lexemes

		
		String currentLine = sampleProgramContents; //I refer to this variable to move the buffer/scanner
        //to the next line when reading multiline comments, as well as when there is nothing left to read

        /*--------------------------------------------------------------------------*/
        /*  ---  ALL REGEX WERE CREATED AND TESTED USING https://regex101.com/  --- */
        /*--------------------------------------------------------------------------*/

        //REGEX pattern matching for KEYWORDS/SYMBOLS as well as adding them to the array list        
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(if)"), CCTokenTypes.IF));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(while)"), CCTokenTypes.WHILE));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(print)"), CCTokenTypes.PRINT));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(string|int|boolean)"), CCTokenTypes.TYPE));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^((-)?[0-9.])"), CCTokenTypes.DIGIT));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(\\\"([^\\\"\\n]*)\\\")"), CCTokenTypes.STRING));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(true|false)"), CCTokenTypes.BOOL_VAL));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^[a-z.]"), CCTokenTypes.CHAR));	
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(\\{)"), CCTokenTypes.LEFT_BRACKET));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(\\})"), CCTokenTypes.RIGHT_BRACKET));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(\\()"), CCTokenTypes.LEFT_PARENTHESIS));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(\\))"), CCTokenTypes.RIGHT_PARENTHESIS));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(==)"), CCTokenTypes.BOOL_OP));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(?<![=!])=(?!=)"), CCTokenTypes.ASSIGNMENT));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(!=)"), CCTokenTypes.BOOL_OP));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^(\\+)"), CCTokenTypes.PLUS));
		listOfLexemes.add(new CCTokenStream(Pattern.compile("^([$])"), CCTokenTypes.EOP));
        
        //REGEX pattern matching for finding start and end comment symbols
        Pattern endOfComment = Pattern.compile("\\*/");
        Pattern startOfComment = Pattern.compile("^/\\*");
   
		//unrecognizedSymbol stores any symbols that are not
        //considered tokens by the lexer/our grammar
		String unrecognizedSymbol = "";
		
		while ((currentLine = lineScanner.readLine()) != null) { //while current line in text file is not blank
                
            while(currentLine.length() > 0) {

                if(currentLine.startsWith(" ")) {
                    currentLine=currentLine.trim();//removing blank spaces from currentLine
                }

                //begin ignoring comment process
                if (ignoreComment == true) {
                    //set lexemeValue to symbol for end of comment (*/)
                    lexemeValue = endOfComment.matcher(currentLine); //look for end of comment symbol since we already found the start
                    if (lexemeValue.find()) {
                        ignoreComment = false; //stop ignoring the comment because we found the end
                        
                        currentLine = currentLine.substring(lexemeValue.end()); //set scanner to the end of the comment
                        continue;
                    }
                    else {
                        warningMessage(lineNumber);
                        //issue warning message since ignore comment is true
                        //but no end comment symbol was found (unterminated comment)
                        break;
                    }
                }
                                
                lexemeValue = startOfComment.matcher(currentLine);
                if (lexemeValue.find()) { // if we found /*
                    ignoreComment = true; //start ignoring the comment
                    
                    currentLine = currentLine.substring(lexemeValue.end()); // set scanner to end of "/*"
                    continue;
                }

                if (ignoreComment == true) { //meaning we already found start comment symbol
                
                    lexemeValue = endOfComment.matcher(currentLine);
                    while (lexemeValue.find() == false) { //look for end of comment if we did not find it already
                        lexemeValue = endOfComment.matcher(currentLine);
                
                        if (lexemeValue.find() == false) { //if we did not find end of comment
                            currentLine = lineScanner.readLine(); //go to next line
                            break;
                        }
                        
                        currentLine = currentLine.substring(lexemeValue.end());
                        lexemeValue = endOfComment.matcher(currentLine);
                    }

                    continue;

                }
                    
                //this matcher holds the longest possible
                //matches after being compared to the regex expressions
                Matcher longestPossibleMatch = null;
                int lpmLength = 0; //lpm (LPM) short for longestPossibleMatch, stores the length
                CCTokenStream lpmData=null;//Data/value of LPM
                
                //look for matches of the RegExp's in the List of Lexemes
                for(CCTokenStream valueOfLexemes : listOfLexemes) {
                    Matcher lookForMatch = valueOfLexemes.getRegExPattern().matcher(currentLine);
                    
                    
                    if(lookForMatch.find()) {

                        String token = lookForMatch.group().trim();
                        int tokenLength = token.length();
                        if (tokenLength > lpmLength) {
                            //if the current LPM length is less than the token length, update LPM accordingly
                            lpmLength = tokenLength;
                            longestPossibleMatch = lookForMatch;
                            lpmData = valueOfLexemes;
                            
                        }
                       //) System.out.println(token);
                        
                        //System.out.println(lpmData.getType());
                        //if (lpmData.getType().toString() == "EOP") {
                          //  System.out.println("idk");

                            
                        //}

                    }
				}

			if(longestPossibleMatch != null) {
                
				String value = longestPossibleMatch.group().trim();
                                                        //name of token type    //value of token                    //line number
				System.out.println("DEBUG Lexer - " + lpmData.getType().name +" [ "+ value + " ] found at line " + lineNumber);
                //look for EOP symbol
                if (lpmData.getType().toString() == "EOP") {

//				if (lpmData.getType().name == "T_EOP") {
					//int programNumber = 1;
                    
					System.out.println("-----------------------------------------------------------");
                    System.out.println("INFO  Lexer - Classy Compiler has finished lexical analysis of Program #" + programNumber);
					System.out.println("-----------------------------------------------------------");
					programNumber = programNumber + 1;
                    
                    //System.out.println("done1234");
                    
                            
                    // System.out.println(lpmData.getType().name);
                        
                      //  for(CCToken name:listOfTokens) {
                            
                        //    System.out.println(name.getValueOfToken());
                          //  System.out.println(lpmData.getType().toString());

        
                            //System.out.println(name.getValueOfToken());
                            //if (item.getValueOfToken()=="{"){
                            //  System.out.println("testing$$$$$");
        
                            //}
                    //}
                    //listOfTokens.clear();
 
                    
      
       

                    
                    
                    
                    //listOfTokens = (ArrayList<CCToken>) listOfTokens.subList(listOfTokens.size()-2, listOfTokens.size()-1);
                    
				}
				
				//get rid of LPM from current line in sample program
				currentLine = longestPossibleMatch.replaceFirst("");
	
				//put LPM into the other arraylist
				CCToken lpmToken = new CCToken(lpmData.getType().name(),value);
                listOfTokens.add(lpmToken);
                	
				//lineNumber++;
				if (lpmData.getType().name == "T_EOP") {
 //               if (listOfTokens.toString()=="EOP") {     
                //if (lpmData.getType().toString() == "EOP") {


                                    //Begin Parsing Process
                    CCParser2 parser = new CCParser2();
                    System.out.println("here");
                    
                    //for(CCToken name:listOfTokens) {
                      //  System.out.println(name.getTypeOfToken());
                
                    //}
                    boolean parseStatus = parser.parseOutcome(listOfTokens);
                    if (parseStatus == true) {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("INFO  Parser - Classy Compiler Parser Outcome: SUCCESS\n");
                        System.out.println("-----------------------------------------------------------");
                        //for(CCToken name:listOfTokens) {
                          //  System.out.println(name.getTypeOfToken());
                
                    //}
                
                        
                    }
                    else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("INFO  Parser - Classy Compiler Parser Outcome: FAILED\n");
                        System.out.println("-----------------------------------------------------------");
                        
                
                    } 
                    System.out.println(listOfTokens.get(listOfTokens.size()-1).getValueOfToken());
                        //if (listOfTokens.get(listOfTokens.size()-1).getValueOfToken().equals("$")){
                            System.out.println("working on it");
                            //listOfTokens.clear();
                            //System.out.println(listOfTokens.get(0).getValueOfToken());
                        //} 
                                    
                    listOfTokens.clear();

            
                    }
                
                
			}

			else {
                 //if an unrecognized symbol is found
                 //set it to the current line
				unrecognizedSymbol = currentLine;
				break;
            }
			//lineNumber++;
            continue;
		}
		lineNumber = lineNumber + 1; //increment line number when scanner reaches a new line

	    }
		
		if(unrecognizedSymbol.length() > 0) {//check if we have any unrecognized tokens
            //if we do, that means we have at least one error
			int numberOfErrors = 1;
			errorMessage(unrecognizedSymbol, lineNumber - 1);
            System.out.println("-----------------------------------------------------------");
			System.out.print("INFO  Lexer - Classy Compiler has failed lexical analysis due to " + numberOfErrors + " error(s).\n");
            System.out.println("-----------------------------------------------------------");
			System.out.print("INFO  Parser - Classy Compiler has skipped Parse (and CST) due to " + numberOfErrors + " lexer error(s).\n");
            System.out.println("-----------------------------------------------------------");

            numberOfErrors = numberOfErrors + 1;
            
		}
                            
                            
        lineScanner.close();
        readFileContents.close();
	}
    
    
	
    public static void main(String[] args) throws IOException{
        System.out.println("-----------------------------------------------------------");
        System.out.println("INFO  Lexer - Welcome To The Classy Compiler!");
        System.out.println("-----------------------------------------------------------");
        
        matchTokens(args[0]);
        //call match tokens, args[0] represents the sample program file
        //when running java ClassyCompilerMain sampleProgram.txt
        

    }
    
}