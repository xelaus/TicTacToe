
public class TicTacToe {
	
	//Declaring the private variables
	
	//double array that the moves of the players will be stored
	private char gameboard[][];
	
	//Users first argument board size
	private int board_size;
	
	//users second argument inline
	private int inline;
	
	//Constructor for the class
	public TicTacToe (int board_size, int inline, int max_levels){
		this.board_size = board_size;
		this.inline = inline;
		
		//Initializing the gameboard array and making every slot empty(empty character)
		gameboard = new char[board_size][board_size];
		for(int i=0; i < board_size;i++){
			 
			 for(int j=0; j<board_size; j++){

				 gameboard[i][j] = ' ';

			 }
		 }
	}
	
	
	//Method that creates a dictionary
	public Dictionary createDictionary(){
		return new Dictionary(4999);
	}
	
	//Method that represents gameboard as a string then checks if the string is in the dictionary(input) 
	public int repeatedConfig(Dictionary configurations){
		
		//declaring variable result
		String result = "";
	
		 //Looping through the gameboard and adding the stored characters to the String.
		 for (int i=0; i<gameboard.length; i++) {
			    for (int j=0; j<gameboard[i].length; j++) {
			    	
			    	result = result + (Character.toString(gameboard[i][j]));
			    }
			}
		
		 //find method returns -1 if the string not found and the score if it finds the string.
		return configurations.find(result);
	 }
	
	//Represent the game-board as a string then insert it into the input dictionary
	public void insertConfig(Dictionary configurations, int score){
		
		//Initializing the String variable result
		String result = "";
		 
		//Looping through the every char item on the gameboard and adding it to the String
		 for (int i=0; i<board_size; i++) {
			    for (int j=0; j<board_size; j++) {
			    	
			    	result = result + gameboard[i][j];
			    }
			}
		 
		 //Creating a DictEntry object to save it to dictionary later
		 DictEntry pair = new DictEntry(result,score);
		 
		 //Inserting the pair to the dictionary, if it already exists, throws an exception
		 try { configurations.insert(pair); } 
		 
		 catch (DictionaryException e) { System.out.println("Already Exists"); }
	}
	
	
	//Take the location and the symbol as an input and store it in the gameboard
	public void storePlay(int row, int col, char symbol){
		gameboard[row][col]=symbol;
	}
	
	
	//Check if the given location is an empty char.
	public boolean squareIsEmpty (int row, int col){
		if(gameboard[row][col] == ' '){
			//System.out.println("inside squareIsEmpty");
			return true;
		}
		
		else{ return false; }
	}
	
	
	//Check the gameboard vertically, horizantally and diagonally to see if Human or Computer win the game or not
	public boolean wins (char symbol){
		
		//initializing the variables
		int ch=0;
		boolean check=false;
		
		//CHECK VERTICAL
		//Loop through every slot in the gameboard vertically
		//nested loops first 0th row and every column then 1st row every column ...
		//increase ch every time the symbol is encountered and make it 0 again when it is not
		//if ch ever reaches the inline number then stop the loops and make check true
		for(int i=0; i < board_size && ch!=inline ;i++){
			 ch=0;
			 for(int j=0; j<board_size && ch!=inline; j++){
				
				 if(gameboard[j][i] == symbol){
					
					 //System.out.println("Inside wins vertical symbol equals");
					 ch++;
				 }
				 else{ ch=0; }
			 }
		 }
		 if(ch==inline) check=true;
		 
		 
		 //CHECK HORIZANTAL
		 //Same as checking vertically but the places of i and j changes
		 //nested loops first 0th column and every row then 1st column every row ...
		 //if ch equals to inline don't check
		 if(ch!=inline){
			 for(int i=0; i < board_size && ch!=inline ;i++){
				 ch=0;
				 for(int j=0; j<board_size && ch!=inline; j++){

					 if(gameboard[i][j] == symbol){ ch++; }

					 else{ ch=0; }
				 }
			 }
			 if(ch==inline) check=true;
		 }
		 
		 
		 //CHECK FORWARD DIAGONAL
		 //Check horizantally and whenever a symbol is encountered check diagonally
		 //if the symbol is encountered as much as the inline and values does not pass the gameboards size stop checking
		 //then if ch value equals to inline make check true.
		 if(ch!=inline){
			 for(int i=0; i < board_size && ch!=inline ;i++){
				 ch=0;
				 for(int j=0; j<board_size && ch!=inline; j++){
					 ch=0;
					 
					 if(gameboard[i][j] == symbol){
						
						 for(int a = i,b = j; a<board_size && b<board_size && ch!=inline && gameboard[a][b] == symbol;a++,b++){
								ch++;
						 }
					 }

					 else{ ch=0; }
				 }
				}
			 
			 if(ch==inline) check=true;
		}
		 
		 //CHECK BACKWARDS DIAGONAL
		 //Same as FORWARD DIAGONAL but instead of i starting from 0, it starts from board-size-1
		 //Because this time we want to check it starting from the last row and go up
		 if(ch!=inline){
			 for(int i=board_size-1; i >= 0 && ch!=inline ;i--){
				 ch=0;
				 for(int j=0; j<board_size && ch!=inline; j++){
					 ch=0;
					 if(gameboard[i][j] == symbol){
	
						 for(int a = i,b = j; a>=0 && b<board_size && ch!=inline && gameboard[a][b] == symbol ;a--,b++){
									ch++;
								}
					 }	
					 else { ch=0; }
				 
				 }
			 }
			 if(ch==inline) check=true;
		 }
	
		 //Finally, returning the check's value.
		 return check;
		 }
	
	
	//Method that returns true if the game is draw
	public boolean isDraw(){
		 
		//initialize the boolean variable check
		 boolean check = false;
		 
		
		//Check if there are any empty spaces in the gameboard 
		 //if there is ever an empty space change the check's value and exit the loop
		 for(int i=0; i < board_size && !check ;i++){
			 
			 for(int j=0; j<board_size && !check; j++){

				 if(gameboard[i][j] == ' '){ check = true; }

				 else{ }
			 }
		 }
		//Return the opposite of checks value, (if there is an empty space it will return false.)
		 return !check;
	}
	
	
	//Method for returning the appropriate number for the gameboard's current situation
	public int evalBoard(){
		
		//if the symbol "O" (computer) wins returns 3.
		if(wins('O')){ return 3; }
		
		//if the symbol "X" (human) wins returns 0.
		else if(wins('X')){ return 0; }
		
		//if the game is a draw, returns 2
		else if(isDraw()){ return 2; }
		
		//else means it is undecided, returns 1
		else{ return 1; }
	}
		  
	
	
}
