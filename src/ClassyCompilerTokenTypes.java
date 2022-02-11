/* I originally had this named as a class with enum inside,
	but it doesn't allow me to import the enum, removing the class name
	fixed the issue.
*/
	public enum ClassyCompilerTokenTypes {
	  
		LEFT_BRACKET("T_OPEN_BRACE"),
		RIGHT_BRACKET("T_CLOSE_BRACE"),
		IF("T_IF"),
		LEFT_PARENTHESIS("T_OPEN_PARENTHESIS"),
		RIGHT_PARENTHESIS("T_CLOSE_PARENTHESIS"),
		PRINT("T_PRINT"),
		WHILE("T_WHILE"),
		BOOL_VAL("T_BOOL_VAL"),
		ASSIGNMENT("T_ASSIGN_OP"),
		CHAR("T_CHAR"),
		DIGIT("T_DIGIT"),
		BOOL_OP("T_BOOL_OP"),
		TYPE("T_TYPE"),
		PLUS("T_INT_OP"),
		EOP("T_EOP");

		public final String name;

		private ClassyCompilerTokenTypes(String name) { //this allows me to have the corresponding string values
														//for the token names
			this.name = name;
			
		}

	}