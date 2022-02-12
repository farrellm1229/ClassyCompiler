import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassyCompilerMain { //good name? maybe, maybe not...but maybe?

    public static void displayMessage(ClassyCompilerTokenTypes token, String value, int lineNum){
        System.out.println("DEBUG Lexer -  " + token.name + " [ " + value + " ] found at line: " + lineNum);

    }

    public static void errorMessage(ClassyCompilerTokenTypes token, String value, int lineNum){
        System.out.println("ERROR Lexer - " + token.name + " Unrecognized Token [ " + value + " ] found at line: " + lineNum);

    }

    public static void matchTokens(String currentLineFile) throws IOException{
        //Read Sample Program File
        File sampleProgram = new File(currentLineFile);
		BufferedReader lineScanner = new BufferedReader(new FileReader(sampleProgram));

        //Variable Declaration
		Matcher lexemeValue;
        String currentLine;
        int programNumber=1;
        int lineNumber=1;
        Boolean ignoreComment = false;


        //REGEX pattern matching for creating tokens and matching lexemes
        //symbols
        Pattern openBracketToken = Pattern.compile("\\{", Pattern.MULTILINE);
        Pattern closeBracketToken = Pattern.compile("\\}", Pattern.MULTILINE);
        Pattern endOfProgram = Pattern.compile("[$]$");
        Pattern openParenthesisToken = Pattern.compile("\\(");
        Pattern closeParenthesisToken = Pattern.compile("\\)");
        Pattern assignmentToken = Pattern.compile("(?<!=)={1}(?!=)");
        Pattern isEqualToken = Pattern.compile("(?<!=)={2}(?!=)");
        Pattern notEqualToken = Pattern.compile("(!=){1}");
        Pattern additionToken = Pattern.compile("(?<!=)[+{1}](?!=)");
        Pattern insideStringToken = Pattern.compile("\"([^\"]*)\"");
        
        Pattern error = Pattern.compile("\\b(?!(string|int|boolean|for|while|true|false|print|[a-z.]$)\\b)\\w+\\b");
        
		


        Pattern boolOpToken = Pattern.compile("(?<![!==])!?=(?![!=])");

        
        Pattern endOfComment = Pattern.compile("\\*/");
        Pattern startOfComment = Pattern.compile("^/\\*");

        
        //keywords
        Pattern boolValToken = Pattern.compile("(?i:false|true)");
		Pattern whileToken = Pattern.compile("(?i:while)");
		Pattern ifToken = Pattern.compile("(?i:if)");
        Pattern typeToken = Pattern.compile("\\b(?i:string|int|boolean)\\b");
        Pattern digitToken = Pattern.compile("[0-9.]");
        Pattern charToken = Pattern.compile("^[a-z.]$");
        Pattern printToken = Pattern.compile("(?i:print)");


        
		while ((currentLine = lineScanner.readLine()) != null) { //while current line in text file is not blank

            if (currentLine.length() == 0) { //if current line is empty move to next
				continue;
            }
        
            while(currentLine.length() > 0 ){
                currentLine = currentLine.trim(); //get rid of whitespace from current line

                if (currentLine.length() == 0){ // | currentLine.endsWith("$")){
                    break;
                }
               
				if (ignoreComment == true) { 
					lexemeValue = endOfComment.matcher(currentLine); //look for end of comment symbol since we already found the start
					if (lexemeValue.find()) {
						
						ignoreComment = false; //stop ignoring the comment because we found the end
						
						currentLine = currentLine.substring(lexemeValue.end()); //set scanner to the end of the comment
						continue;
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


               /* if (currentLine.endsWith("$")){
                    System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.EOP + " [ " + currentLine.substring(0, currentLine.length() - 0) + " ] found at line: " + lineNumber + "\n");
                    System.out.println("------------- Classy Compiler has finished Lexical Analysis of Program: #" + programNumber + " -------------" + "\n");
                    
                    programNumber++;

                    break;

                } */ //this ^^^^ is a work in progress. Issue is could be whitespace after $

/*start of searching for symbols*/
            //Find symbol matches in text file

           /* lexemeValue = commentToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                System.out.println("comments");
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = commentToken.matcher(currentLine);
					continue;
                } */
            lexemeValue = insideStringToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.STRING, "' '", lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = insideStringToken.matcher(currentLine);
					continue;
                }
            //find openBracket
            lexemeValue = openBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.LEFT_BRACKET, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openBracketToken.matcher(currentLine);
					continue;
                }
            //find closeBracket
            lexemeValue = closeBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                displayMessage(ClassyCompilerTokenTypes.RIGHT_BRACKET, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = closeBracketToken.matcher(currentLine);
                    continue;
                }
                //find open Parenthesis
            lexemeValue = openParenthesisToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_PARENTHESIS + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openParenthesisToken.matcher(currentLine);
					continue;
                }
            //find close Parenthesis
            lexemeValue = closeParenthesisToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.RIGHT_PARENTHESIS + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue =closeParenthesisToken.matcher(currentLine);
                    continue;
                }
                //find true or false keywords
            lexemeValue = boolValToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.BOOL_VAL, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = boolValToken.matcher(currentLine);
					continue;
                }
               
                //find type keywords: string, int, boolean
            lexemeValue = typeToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.TYPE, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = typeToken.matcher(currentLine);
					continue;
                }
                //find "while" keyword
            lexemeValue = whileToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.WHILE, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = whileToken.matcher(currentLine);
					continue;
                }
            lexemeValue = printToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.PRINT, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = printToken.matcher(currentLine);
                    continue;
                }
               

            //find if Keyword
            lexemeValue = ifToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.IF + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = ifToken.matcher(currentLine);
                    continue;
                }
//find single equals sign (=)
            lexemeValue = assignmentToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.ASSIGNMENT, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openBracketToken.matcher(currentLine);
					continue;
                } 
                //find not equals sign (!=)
            lexemeValue = notEqualToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.BOOL_OP, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = notEqualToken.matcher(currentLine);
					continue;
                }
            lexemeValue = isEqualToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.BOOL_OP, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = isEqualToken.matcher(currentLine);
					continue;
                } 
            /*lexemeValue = boolOpToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.BOOL_OP, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = boolOpToken.matcher(currentLine);
                    continue;
                } */
            lexemeValue = additionToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.PLUS, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = additionToken.matcher(currentLine);
					continue;
                }
//find single lowercase letters            
            lexemeValue = charToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.CHAR, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = charToken.matcher(currentLine);
					continue;
                }
//find single digits          
            lexemeValue = digitToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.DIGIT, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = digitToken.matcher(currentLine);
					continue;
                }

            lexemeValue = error.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                errorMessage(ClassyCompilerTokenTypes.ERROR, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = error.matcher(currentLine);
                    continue;
                }
                
                //Looking for End Of Program symbol ($)   
            lexemeValue = endOfProgram.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.EOP + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber + "\n");
                System.out.println("INFO  Lexer -  Classy Compiler has finished Lexical Analysis of Program: #" + programNumber + "\n");
                
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = endOfProgram.matcher(currentLine);
                programNumber++;

                    continue;
                }
            

                continue;
            }
            
            lineNumber = lineNumber + 1; //increment line number when scanner reaches a new line

        }
            
            
            }
        
    public static void main(String[] args) throws IOException{
        System.out.println("\nINFO  Lexer - Welcome To The Classy Compiler!\n");
        
        matchTokens(args[0]);
        //call match tokens, args[0] represents the sample program file
        //when running java ClassyCompilerMain sampleProgram.txt



    }
    
}
