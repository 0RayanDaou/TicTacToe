package com.rayandaou;

import java.util.Scanner;

import com.rayandaou.TicTacToe.Best;

public class TicTacToeMain {
	//introducing the board that the two players will play on
	public static int [][] board;
	//storage of the markers (X or O)
	public static char playerMarker, AIMarker;
	//introduce our helper class provided by you
	public static TicTacToe TT = new TicTacToe ();
	//used for the AI random move 
	public static String letters[]= {"a","b","c"};	
	public static int row,r = 0,c= 0;
	public static boolean checkA;
	public static boolean check;
	public static char col ;
	public static int rowA;
	public static int colA = 0;
	public static char columnA;
	public static int column = 0;
	public static String name;
	public static String str;
	//the best move will be stored here that we will use in our smart AI move 
	public static Best bestMove = TT.new Best(TT.COMPUTER ,  r , c);
	//this method is for the computer to choose the optimal move
	public static void AIPlayerSmart ( int [][] board , char AIMarker){
		board = TT.getBoard();
		bestMove = TT.chooseMove(TT.COMPUTER);
		colA = bestMove.column;
		rowA = bestMove.row;
		TT.playMove(TT.COMPUTER , rowA , colA);
	}
	//this method is for the computer to choose a random move
	public static void AIPlayerWeak (int [] [] board , char AIMarker){
		
		checkA = false;
		//the do loop analyzes the random input and assure playing of an available move
			do {
	    rowA= (int)(Math.random()*3);
		columnA= (letters[(int)(Math.random()*3)]).charAt(0);
		if (columnA == 'a') {
    	    colA = 0;
    	}
		else if (columnA == 'b') {
    		colA = 1;
    	}else if (columnA == 'c') {
    		colA =2;
    	}
	    checkA = TT.playMove(TT.COMPUTER, rowA, colA);
	    board = TT.getBoard();	
		}while(!checkA);
	}
	
	public static void main (String []args) {
		
		Scanner scan = new Scanner(System.in);
		//Start by entering player's name
		System.out.println("Enter your worrior's name: ");
		name = scan.nextLine();
		//choosing the player's marker
		
		//When the game ends the player is supposed to enter yes for another game, if it is a yes this do allows him to play again 
		//Outer is used to break from a specific loop 
		outer : do {
		System.out.println("Choose your Marker(X or O) choose X to start first: ");
		playerMarker = scan.nextLine().charAt(0);
		//if the player types any other than X or O he should repeat until he enter only X/x or O/o
		while(playerMarker != 'X' && playerMarker != 'x' && playerMarker != 'o' && playerMarker != 'O') {
			System.out.println("Invalid input, enter (X or O) only: ");
			playerMarker = scan.nextLine().charAt(0);
		}
		//if the player choose X/x the player starts if the player choose O/o the AI starts
		if(playerMarker == 'X') {
			AIMarker = 'O';
			}
			else AIMarker = 'X';
		System.out.println(name + "'s Marker: " + playerMarker + " ");
		System.out.println("Computer's Marker: " + AIMarker + " ");
		//stating some notes and rules that the player need to follow for a good game
		System.out.println("Play your move in the following syntax-(a,b,c) for columns (3,2,1) for rows- for example a2 (X or O):");
		System.out.println("If you want to exit during the game type: quit");
		System.out.println("If you want to create a new game type: new");
		
		TT.clearBoard();
		str = "";
		//As long as no one wins or the board is not full the game completes using this second do loop
		        outer2 : do {	
			//this if statement plays the game where the player starts 
			if  (playerMarker == 'X' || playerMarker == 'x') {	 
			check = false;			
			do { 
			//A statement that completes on appearing so the player remembers to not enter unallowed moves
			System.out.println("Don't enter the same input twice, and don't enter your opponents already taken move!");
			str = scan.nextLine();
			//this while loop specifies the input that the user should enter
			//any other input will result in repition of entering a move
		while(!str.equalsIgnoreCase("new") && !str.equalsIgnoreCase("quit") 
				&& !str.equalsIgnoreCase("dump")  
				&& !str.equalsIgnoreCase("a1 " + playerMarker)   
				&& !str.equalsIgnoreCase("a2 " + playerMarker)   
				&& !str.equalsIgnoreCase("a3 " + playerMarker)   
				&& !str.equalsIgnoreCase("b1 " + playerMarker)   
				&& !str.equalsIgnoreCase("b2 " + playerMarker)  
				&& !str.equalsIgnoreCase("b3 " + playerMarker) 
				&& !str.equalsIgnoreCase("c1 " + playerMarker)  
				&& !str.equalsIgnoreCase("c2 " + playerMarker)  
				&& !str.equalsIgnoreCase("c3 " + playerMarker)  ) {
			System.out.println("Enter valid input!");
			str = scan.nextLine();
		}
		//if the player enters quit the game will quit completely
		if (str.equalsIgnoreCase("quit")) 
			break outer;
		//if the player enters dump it prints the current state of board
		else if(str.equalsIgnoreCase("dump")) {
				board = TT.getBoard();
			    PrintBoard(board , playerMarker , AIMarker );
			    
			}
		//if the player enters new the game will create a new game
		else if (str.equalsIgnoreCase("new")) {
			TT.clearBoard();
			System.out.println("New Game: ");
		}
		else {
			//if the input is correct the game continues
			//the code analyzes the string entered 
		col = str.charAt(0);
		row = (Integer.parseInt(((str.charAt(1))+ ""))) ;
            if (col == 'a') {
	    	    column = 0;
	    	}
			else if (col == 'b') {
	    		column = 1;
	    	}else if (col == 'c') {
	    		column =2;
	    	} if ((row-1) == 0) {
	    		row = 2; 
	    	}else if ((row-1) == 2) {
	    		row =0;
	    	}else if ((row-1) == 1) {
	    		row =1;
	    	}
	    	//the code plays the move as long as the move is allowed and available
			check = TT.playMove(TT.HUMAN, row, column);
			board = TT.getBoard();
			//the if statement assures as long as the game didn't end the code will complete
			if ((TT.isAWin(TT.HUMAN)) || (TT.isAWin(TT.COMPUTER)) || (TT.boardIsFull()) ) {
				PrintBoard(board, playerMarker , AIMarker);
				break outer2;
			}
		}
		}while(!check);
			//PrintBoard( board , playerMarker , AIMarker);
			//AIPlayerSmart(board , AIMarker);
			//PrintBoard(board , playerMarker , AIMarker );
			
             PrintBoard(board , playerMarker , AIMarker);
			AIPlayerWeak( board , playerMarker);				
			PrintBoard(board , playerMarker , AIMarker);
			}
			// this else statement runs the game if the computer starts
			else {
				//this if statement is to assure that when the user asks to print board using "dump" the computer doesn't play a move 
				if(!str.equalsIgnoreCase("dump")){
				board = TT.getBoard();
				AIPlayerSmart(board , AIMarker);
				//AIPlayerWeak(board , AIMarker);
				PrintBoard(board , playerMarker , AIMarker);
				board = TT.getBoard();
				//the if statement assures as long as the game didn't end the code will complete
				if ((TT.isAWin(TT.HUMAN)) || (TT.isAWin(TT.COMPUTER)) || (TT.boardIsFull()) ) {
					break outer2;
				}
				}
				do {
					System.out.println("Don't enter the same input twice, and don't enter your opponents already taken move!");
					str = scan.nextLine();
					//this while loop specifies the input that the user should enter
					//any other input will result in repition of entering a move
				while(!str.equalsIgnoreCase("new") && !str.equalsIgnoreCase("quit") 
						&& !str.equalsIgnoreCase("dump")  
						&& !str.equalsIgnoreCase("a1 " + playerMarker)   
						&& !str.equalsIgnoreCase("a2 " + playerMarker)   
						&& !str.equalsIgnoreCase("a3 " + playerMarker)   
						&& !str.equalsIgnoreCase("b1 " + playerMarker)   
						&& !str.equalsIgnoreCase("b2 " + playerMarker)  
						&& !str.equalsIgnoreCase("b3 " + playerMarker) 
						&& !str.equalsIgnoreCase("c1 " + playerMarker)  
						&& !str.equalsIgnoreCase("c2 " + playerMarker)  
						&& !str.equalsIgnoreCase("c3 " + playerMarker)  ) {
					System.out.println("Enter valid input!");
					str = scan.nextLine();
				}
				//if the player enters quit the game will quit completely
				if (str.equalsIgnoreCase("quit")) 
					break outer;
				//We use an outer to exit from all the loops that are needed for the game
				//if the player enters dump it prints the current state of board
				else if(str.equalsIgnoreCase("dump")) {
						board = TT.getBoard();
					    PrintBoard(board , playerMarker , AIMarker );
					}
				//if the player enters new the game will create a new game
				else if (str.equalsIgnoreCase("new")) {
					TT.clearBoard();
					System.out.println("New Game: ");
					if(AIMarker == 'X' || AIMarker == 'x') {
						//AIPlayerWeak( board , playerMarker);	
						AIPlayerSmart(board , AIMarker);
						PrintBoard(board , playerMarker , AIMarker );
						check = false;
					}
				}
				else {
					str = str  + " ";
				col = str.charAt(0);
				row = (Integer.parseInt(((str.charAt(1))+ ""))) ;
				//if the input is correct the game continues
				//the code analyzes the string entered 
					if (col == 'a') {
			    	    column = 0;
			    	}
					else if (col == 'b') {
			    		column = 1;
			    	}else if (col == 'c') {
			    		column =2;
			    	} if ((row-1) == 0) {
			    		row = 2; 
			    	}else if ((row-1) == 2) {
			    		row =0;
			    	}else if ((row-1) == 1) {
			    		row =1;
			    	}
					check = TT.playMove(TT.HUMAN, row, column);
					board = TT.getBoard();
					PrintBoard(board , playerMarker , AIMarker );
				}
				}while(!check);
			}
		}while ((!TT.isAWin(TT.HUMAN)) && (!TT.isAWin(TT.COMPUTER)) && (!TT.boardIsFull()) );
	    //the if statements below ensure the right winner stating
		if(TT.isAWin(TT.HUMAN)) {
			System.out.println("Human wins!");
		}
		else if(TT.isAWin(TT.COMPUTER)) {
			System.out.println("Computer wins!");
		}
		else if(TT.boardIsFull()) {
			System.out.println("It is a draw");
		}
		System.out.println("Enter yes to play again, or any other string to stop");
		str = scan.nextLine();
		}while(str.equalsIgnoreCase("Yes"));
		scan.close();
	}
	
	public static void PrintBoard(int [][] board , char playerMarker , char AIMarker ) {
		 //this method goes over the the board and prints it's current state
	    	for (int i =0; i<3; i++) {
	    		System.out.println();
	    		for (int j =0;j<3;j++) {
	    			
	    			if (board [i][j] == 0 ) System.out.print(playerMarker + " ");
	    			else if (board[i][j] == 1) System.out.print(AIMarker + " ");
	    			else if (board [i][j] == 2) System.out.print("- ");
	    		}
	    	}
	    	System.out.println();
	    }
	

	
	
}
