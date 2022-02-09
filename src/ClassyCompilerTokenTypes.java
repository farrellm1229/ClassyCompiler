public class ClassyCompilerTokenTypes { //Fun name? Maybe, maybe not...but maybe!
	
	public enum TokenType {
	  
		LEFT_BRACKET("T_OPEN_BRACE"),
		RIGHT_BRACKET("T_CLOSE_BRACE"),
		EOP("T_EOP");
	
	
		private TokenType(String nameOfToken) { //this allows me to have the corresponding string values
												//for the token names
		}
	}
}
	