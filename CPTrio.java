import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTrio{
	public static void main(String[] args){
		Console con = new Console("Video Poker", 1280, 720);
		
		while (true){
			con.clear();
			BufferedImage imgMain = con.loadImage("assets/StartMenu.png");
			con.drawImage(imgMain, 0, 0);
			con.repaint();
			
			char charTitleInput;
			charTitleInput = con.getChar();
			char charReturn = 'a';
			
			if(charTitleInput == 'q' || charTitleInput == 'Q'){
				char charConfirm = con.getChar();
				boolean blnQuit = true;
				
				while(blnQuit == true){
					if(charConfirm == 'y' || charConfirm == 'Y'){
						con.closeConsole();
					}else if(charConfirm == 'n' || charConfirm == 'N'){
						blnQuit = false;
						break;
					}
				}
				
			}
			
			if(charTitleInput == 's' || charTitleInput == 'S'){
				con.clear();
				con.println("Did you hear about the blonde who brought a bag of fries to the poker game?");
				con.sleep(1000);
				con.println("...");
				con.sleep(1000);
				con.println("...");
				con.sleep(1000);
				con.println("...");
				con.sleep(1000);
				con.println("Somebody told her to bring her own chips");
				con.sleep(2000);
				con.println("*crickets*");
				con.sleep(5000);
			}
			
			if(charTitleInput == 'p' || charTitleInput == 'P'){
				con.clear();
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 1280, 720);
				
				String strName;
				con.println("What is your name?");
				strName = con.readLine();
				while(strName.length() == 0 || strName.substring(0, 1) == " "){
					con.println("Invalid");
					con.println("What is your name?");
					strName = con.readLine();
				}
				
				int intMoney;
				if (strName.equals("statitan")){
					intMoney = 10000;
				}else{
					intMoney = 1000;
				}
				
				intMoney = GameScreen(con, intMoney);
				TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
				leaderboard.println(strName);
				leaderboard.println(intMoney);
			}
			
			if(charTitleInput == 'l' || charTitleInput == 'L'){
				System.out.println("printing leaderboard");
				con.clear();
				BufferedImage imgLB = con.loadImage("assets/Leaderboard.png");
				con.drawImage(imgLB, 0, 0);
				con.repaint();
				TextInputFile lbScore = new TextInputFile("leaderboard.txt");
				int intCount = 0;
				String strTempName;
				String strTempMoney;
				while(lbScore.eof() == false){
					strTempName = lbScore.readLine();
					strTempMoney = lbScore.readLine();
					intCount++;
				}
				lbScore.close();
				String strLb[][];
				strLb = new String[intCount][2];
				intCount = 0;
				lbScore = new TextInputFile("leaderboard.txt");
				while(lbScore.eof() == false){
					strLb[intCount][0] = lbScore.readLine();
					strLb[intCount][1] = lbScore.readLine();
					intCount++;
				}
				int inty = 125;
				int intxRank = 275;
				int intxName = 385;
				int intxMoney = 700;
				Font txtFont = con.loadFont("assets/Raleway-SemiBold.ttf", 30);
				con.setDrawFont(txtFont);
				con.setDrawColor(Color.WHITE);
				strLb = sortLeaderboard(strLb, intCount);
				for(intCount = 0; intCount < 10; intCount++){
					con.drawString(Integer.toString(intCount+1), intxRank, inty);
					con.drawString(strLb[intCount][0], intxName, inty);
					con.drawString("$" + strLb[intCount][1], intxMoney, inty);
					inty += 50;
					con.repaint();
				}
				System.out.println("printed leaderboard");
				
				while(true){
					charReturn = con.getChar();
					if(charReturn == 'l' || charReturn == 'L'){
						break;
					}
				}
			}
			
			if(charTitleInput == 'h' || charTitleInput == 'H'){
				System.out.println("help");
				con.println("help");
				con.println("press any key to return");
				con.getChar();
			}
		}
	}
		
	public static int GameScreen(Console con, int intMoney){
		int intCards[][];
		int intHand[][];
		intHand = new int[5][2];
		int intRow;
		int intSwap;
		int intSwapped;
		String strSwappedCards;
		int intCount;
		int intBet;
		int intMult;
		int intWin;
		strSwappedCards = "";
		String strPlayAgain = "y";
		
		while(strPlayAgain.equalsIgnoreCase("y")){
			intCards = loadDeck();
			intCards = sort(intCards);
			intBet = 0;
			con.println("You have $" + intMoney);
			while(intBet <= 0 || intBet > intMoney){
				con.println("How much do you bet?");
				intBet = con.readInt();
			}
			intMoney -= intBet;
			intMult = 0;
			intWin = 0;
			
			for(intRow = 0; intRow < 5; intRow++){
				intHand[intRow][0] = intCards[intRow][0];
				intHand[intRow][1] = intCards[intRow][1];
				con.println(intHand[intRow][0] + " " + intHand[intRow][1]);
			}
			
			con.println("How many cards would you like to swap");
			intSwap = con.readInt();
			while(intSwap > 5 || intSwap < 0){
				con.println("Invalid");
				con.println("How many cards would you like to swap");
				intSwap = con.readInt();
			}
			
			if (intSwap != 0){
				con.println("Which cards would you like to swap (Example, 134 would swap cards 1, 3 and 4)");
				strSwappedCards = con.readLine();
				
				while(strSwappedCards.length() != intSwap){
					con.println("Invalid");
					con.println("Which cards would you like to swap (Example, 134 would swap cards 1, 3 and 4)");
					strSwappedCards = con.readLine();
				}
			}
			for(intCount = 0; intCount < intSwap; intCount++){
				intSwapped = Integer.parseInt(strSwappedCards.substring(intCount, intCount+1));
				intRow++;
				intHand[intSwapped-1][0] = intCards[intRow][0];
				intHand[intSwapped-1][1] = intCards[intRow][1];
			}
			
			for(intCount = 0; intCount < 5; intCount++){
				con.print(intHand[intCount][0] + " ");
				con.println(intHand[intCount][1]);
				
			}
			intHand = sortHand(intHand);
			
			int intCardValue[];
			intCardValue = new int[5];
			int intSuitValue[];
			intSuitValue = new int[5];
			int intCardCount[];
			intCardCount = new int[13];
			
			int intPairs = 0;
			boolean blnThreeKind = false;
			boolean blnFourKind = false;
			boolean blnJacksUp = false;
			boolean blnFullHouse = false;
			boolean blnStraight = false;
			boolean blnFlush = false;
			boolean blnRoyalStr = false;
			boolean blnStrFlush = false;
			boolean blnRoyalFlush = false;
			
			for(intCount = 0; intCount < 5; intCount++){
				intCardValue[intCount] = intHand[intCount][0];
				intSuitValue[intCount] = intHand[intCount][1];
				intCardCount[intCardValue[intCount]-1]++;
			}
			
			for(intCount = 0; intCount < 13; intCount++){
				if (intCardCount[intCount] == 2){
					intPairs++;
				}else if (intCardCount[intCount] == 3){
					blnThreeKind = true;
				}else if (intCardCount[intCount] == 4){
					blnFourKind = true;
				}
			}
			
			if (intPairs == 1){
				if(blnThreeKind == true){
					blnFullHouse = true;
				}else if(intCardCount[0] == 2 || intCardCount[10] == 2 || intCardCount[11] == 2 || intCardCount[12] == 2){
					blnJacksUp = true;
				}
			}
			
			if((intCardValue[0] == intCardValue[1]-1 && intCardValue[1] == intCardValue[2]-1 && intCardValue[2] == intCardValue[3]-1 && intCardValue[3] == intCardValue[4]-1)){
				blnStraight = true;
			}
			
			if(intSuitValue[0] == intSuitValue[1] && intSuitValue[0] == intSuitValue[2] && intSuitValue[0] == intSuitValue[3] && intSuitValue[0] == intSuitValue[4]){
				blnFlush = true;
			}
			
			if((intCardValue[0] == 1 && intCardValue[1] == 10 && intCardValue[2] == 11 && intCardValue[3] == 12 && intCardValue[4] == 13)){
				blnRoyalStr = true;
			}
			
			if(blnStraight == true && blnFlush == true){
				blnStrFlush = true;
			}
			
			if(blnFlush == true && blnRoyalStr == true && intSuitValue[0] == 4){
				blnRoyalFlush = true;
			}
			
			if(blnRoyalFlush == true){
				intMult = 800;
			}else if(blnStrFlush == true){
				intMult = 50;
			}else if(blnFourKind == true){
				intMult = 25;
			}else if(blnFullHouse == true){
				intMult = 9;
			}else if(blnFlush == true){
				intMult = 6;
			}else if(blnStraight == true){
				intMult = 4;
			}else if(blnThreeKind == true){
				intMult = 3;
			}else if(intPairs == 2){
				intMult = 2;
			}else if(blnJacksUp == true){
				intMult = 1;
			}else{
				intMult = 0;
			}
			
			intWin = intBet * intMult;
			if (intMult != 0){
				con.println("You won $" + intWin);
			}else{
				con.println("You lost $" + intBet);
			}
			intMoney += intWin;
			
			if(intMoney != 0){
				con.println("you now have $" + intMoney);
				con.println("do you want to play again? y/n");
				strPlayAgain = con.readLine();
			}else{
				strPlayAgain = "n";
				con.println("Press any button to return to menu");
				con.getChar();
			}
		}
		return intMoney;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int[][] loadDeck(){
		int intCards[][];
		int intCard;
		int intSuit;
		int intRow;
		intCards = new int[52][3];
		
		intCard = 1;
		intSuit = 1;
		
		for(intRow = 0; intRow < 52; intRow++){
			intCards[intRow][0] = intCard;
			intCards[intRow][1] = intSuit;
			intCards[intRow][2] = (int)(Math.random()*100);
			intCard++;
			if(intCard == 14){
				intCard = 1;
				intSuit++;
			}
		}
		
		return intCards;
	}
	
	public static int[][] sort(int intDeck[][]) {
		
		int intTemp[] = new int[3];
		int intCount;
		int intCount2;
		int intCount3;
		
		for (intCount2 = 0; intCount2 < 52 - 1; intCount2++) {
			for (intCount = 0; intCount < 52 - 1; intCount++) {
				if (intDeck[intCount][2] > intDeck[intCount+1][2]) {
					for (intCount3 = 0; intCount3 < 3; intCount3++) {
						intTemp[intCount3] = intDeck[intCount][intCount3];
						intDeck[intCount][intCount3] = intDeck[intCount+1][intCount3];
						intDeck[intCount+1][intCount3] = intTemp[intCount3];
					}
				}
			}
		}
		return intDeck;
	}
	
	public static int[][] sortHand(int intDeck[][]) {
		
		int intTemp[] = new int[2];
		int intCount;
		int intCount2;
		int intCount3;
		
		for (intCount2 = 0; intCount2 < 5 - 1; intCount2++) {
			for (intCount = 0; intCount < 5 - 1; intCount++) {
				if (intDeck[intCount][0] > intDeck[intCount+1][0]) {
					for (intCount3 = 0; intCount3 < 2; intCount3++) {
						intTemp[intCount3] = intDeck[intCount][intCount3];
						intDeck[intCount][intCount3] = intDeck[intCount+1][intCount3];
						intDeck[intCount+1][intCount3] = intTemp[intCount3];
					}
				}
			}
		}
		return intDeck;
	}
	
	public static String[][] sortLeaderboard(String strLb[][], int intRows) {
		
		String strTemp [] = new String[2];
		int intCount;
		int intCount2;
		int intCount3;
		
		for (intCount2 = 0; intCount2 < intRows-1; intCount2++) {
			for (intCount = 0; intCount < intRows-1; intCount++) {
				if (Integer.parseInt(strLb[intCount][1]) < Integer.parseInt(strLb[intCount+1][1])) {
					for (intCount3 = 0; intCount3 < 2; intCount3++) {
						strTemp[intCount3] = strLb[intCount][intCount3];
						strLb[intCount][intCount3] = strLb[intCount+1][intCount3];
						strLb[intCount+1][intCount3] = strTemp[intCount3];
					}
				}
			}
		}
		return strLb;
	}
}


