/* I originally had this named as a class with enum inside,
	but it doesn't allow me to import the enum, removing the class name
	fixed the issue.
*/
	public enum ClassyCompilerTokenTypes {
	  
		LEFT_BRACKET("T_OPEN_BRACE"),
		RIGHT_BRACKET("T_CLOSE_BRACE"),
		EOP("T_EOP");

		public final String name;

		private ClassyCompilerTokenTypes(String name) { //this allows me to have the corresponding string values
														//for the token names
			this.name = name;
			
		}

		public String toString() {

			return name; //returns name in parentheses aka the token name
			
		}
	}