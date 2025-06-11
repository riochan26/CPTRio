//-------------------------------------------------------------------------
// Name:         RioCPT
// Purpose:      Create a Jacks or Better Video Poker Game
// Author:       Chan R.
// Created:      May 28, 2025
//-------------------------------------------------------------------------
import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTrio{
	public static void main(String[] args){
		Console con = new Console("Video Poker", 1280, 720);
		mainMenu(con);
	}

	public static void mainMenu(Console con){
		Font txtFont = con.loadFont("assets/Raleway-SemiBold.ttf", 30);
		while (true){
			//Create the Main Menu
			con.clear();
			BufferedImage imgMain = con.loadImage("assets/StartMenu.png");
			con.drawImage(imgMain, 0, 0);
			con.repaint();
			
			char charTitleInput;
			charTitleInput = con.getChar();
			char charReturn = 'a';
			con.setDrawFont(txtFont);
			
			//Key Inputs to Access Menus
			if(charTitleInput == 'q' || charTitleInput == 'Q'){
				boolean blnQuit = true;
				int intyQuit = -720;
				con.setDrawColor(new Color(0, 0, 0, 128));
				BufferedImage imgQuit = con.loadImage("assets/Quit.png");
				System.out.println("pressed q");
				while(intyQuit != 0){
					//Screen Animation
					con.drawImage(imgMain, 0, 0);
					con.fillRect(0, intyQuit, 1280, 720);
					con.drawImage(imgQuit, 345, intyQuit+205);
					con.repaint();
					con.sleep(5);
					intyQuit += 10;
					System.out.println(intyQuit);
				}
				//Confirm Quit
				char charConfirm;
				while(blnQuit == true){
					charConfirm = con.getChar();
					if(charConfirm == 'y' || charConfirm == 'Y'){
						con.closeConsole();
					}else if(charConfirm == 'n' || charConfirm == 'N'){
						while(intyQuit != -720){
							con.drawImage(imgMain, 0, 0);
							con.fillRect(0, intyQuit, 1280, 720);
							con.drawImage(imgQuit, 345, intyQuit+205);
							con.repaint();
							con.sleep(5);
							intyQuit -= 10;
							System.out.println(intyQuit);
						}
						blnQuit = false;
						break;
					}
				}
			}
			
			if(charTitleInput == 's' || charTitleInput == 'S'){
				//Secret Joke
				BufferedImage imgSecret = con.loadImage("assets/secretMenu.png");
				Font jokeFont = con.loadFont("assets/Raleway-SemiBold.ttf", 26);
				con.setDrawColor(new Color(0, 0, 0, 128));
				con.setDrawFont(jokeFont);
				con.fillRect(0, 0, 1280, 720);
				con.drawImage(imgSecret, 345, 205);
				con.setDrawColor(Color.WHITE);
				con.drawString("Did you hear about the blonde who", 423, 283);
				con.drawString("brought a bag of fries to the poker game?", 385, 308);
				con.repaint();
				con.sleep(1000);
				con.drawString(".", 631, 350);
				con.repaint();
				con.sleep(1000);
				con.drawString("..", 631, 350);
				con.repaint();
				con.sleep(1000);
				con.drawString("...", 631, 350);
				con.repaint();
				con.sleep(1000);
				con.drawString("Somebody told her to bring her own chips!", 379, 392);
				con.repaint();
				con.sleep(2000);
				con.drawString("*crickets*", 584, 443);
				con.repaint();
				con.sleep(5000);
			}
			
			if(charTitleInput == 'p' || charTitleInput == 'P'){
				System.out.println("play game");
				//Ask User for Name
				con.clear();
				con.setDrawFont(txtFont);
				String strName = "";
				boolean blnNameEntered = false;
				
				int intyName = -720;
				BufferedImage imgName = con.loadImage("assets/EnterName.png");
				while(intyName != 0){
					con.drawImage(imgMain, 0, 0);
					con.setDrawColor(new Color(0, 0, 0, 128));
					con.fillRect(0, intyName, 1280, 720);
					con.drawImage(imgName, 345, intyName+205);
					con.repaint();
					con.sleep(5);
					intyName += 10;
					System.out.println(intyName);
				}
				while(blnNameEntered == false){
					System.out.println("resetting screen state");
					
					con.drawImage(imgMain, 0, 0);
					con.setDrawColor(new Color(0, 0, 0, 128));
					con.fillRect(0, intyName, 1280, 720);
					con.drawImage(imgName, 345, intyName+205);
					
					con.setDrawColor(Color.WHITE);
					con.drawString(strName, 430, 360);
					System.out.println("reset");
					
					con.repaint();
					//Drawing Name to Screen
					char charTyped = con.getChar();
					if(charTyped == 8 && strName.length() > 0){
						strName = strName.substring(0, strName.length()-1);
					}
					if((charTyped < 32 || charTyped > 127)){
						strName = strName;
					}else{
						strName = strName + charTyped;
					}
					if(charTyped == 27){
						while(intyName != -720){
							con.setDrawColor(new Color(0, 0, 0, 128));
							con.drawImage(imgMain, 0, 0);
							con.fillRect(0, intyName, 1280, 720);
							con.drawImage(imgName, 345, intyName+205);
							con.repaint();
							con.sleep(5);
							intyName -= 10;
							System.out.println(intyName);
						}
						mainMenu(con);
					}
					if(charTyped == '\n'){
						if(!strName.equals("")){
							blnNameEntered = true;
						}else{
							//prevents nameless players
							con.setDrawColor(Color.RED);
							con.drawString("Invalid Name!", 430, 360);
							con.repaint();
							con.setDrawColor(Color.WHITE);
							con.sleep(1200); 
							con.repaint();
							strName = ""; 
							continue;
						}
					}
					System.out.println(charTyped);
					System.out.println(strName);
					con.sleep(5);
				}
				//Secret Name
				int intMoney;
				if (strName.equals("statitan")){
					intMoney = 10000;
				}else{
					intMoney = 1000;
				}
				//Send to game state, append money and name to leaderboard
				intMoney = GameScreen(con, intMoney);
				TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
				leaderboard.println(strName);
				leaderboard.println(intMoney);
				leaderboard.close();
			}
			
			if(charTitleInput == 'l' || charTitleInput == 'L'){
				System.out.println("printing leaderboard");
				//Leaderboard Printing
				con.clear();
				BufferedImage imgLB = con.loadImage("assets/Leaderboard.png");
				con.drawImage(imgLB, 0, 0);
				con.repaint();
				TextInputFile lbScore = new TextInputFile("leaderboard.txt");
				int intCount = 0;
				String strTempName;
				String strTempMoney;
				//Counting length of file
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
				con.setDrawColor(Color.WHITE);
				//Bubble Sort Leaderboard and Print
				strLb = sortLeaderboard(strLb, intCount);
				for(intCount = 0; intCount < 10; intCount++){
					con.drawString(Integer.toString(intCount+1), intxRank, inty);
					con.drawString(strLb[intCount][0], intxName, inty);
					con.drawString("$" + strLb[intCount][1], intxMoney, inty);
					inty += 50;
					con.repaint();
				}
				System.out.println("printed leaderboard");
				
				//Wait for user input to return to main menu
				while(true){
					charReturn = con.getChar();
					if(charReturn == 'l' || charReturn == 'L'){
						break;
					}
				}
			}
			
			if(charTitleInput == 'h' || charTitleInput == 'H'){
				System.out.println("help");
				//Opens help menu
				con.clear();
				BufferedImage imgHelp1 = con.loadImage("assets/Help[0].png");
				BufferedImage imgHelp2 = con.loadImage("assets/Help[1].png");
				
				con.drawImage(imgHelp1, 0, 0);
				con.repaint();
				//Wait for input to go to next screen
				while(true){
					charReturn = con.getChar();
					if(charReturn == 'h' || charReturn == 'H'){
						break;
					}
				}
				//Wait for input to go to next screen
				con.drawImage(imgHelp2, 0, 0);
				con.repaint();
				while(true){
					charReturn = con.getChar();
					if(charReturn == 'h' || charReturn == 'H'){
						break;
					}
				}
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
		String strPlayAgain = "p";
		boolean blnBet;
		int intxBet = 0;
		con.clear();
		BufferedImage imgGame = con.loadImage("assets/GameMenu.png");
		BufferedImage imgBet = con.loadImage("assets/Bet.png");
		String strBetDraw = "";
		int textWidth = con.getTextFontMetrics().stringWidth(strBetDraw);
		
		while(strPlayAgain.equalsIgnoreCase("p")){
			blnBet = false;
			//create and sort deck
			intCards = loadDeck();
			intCards = sort(intCards);
			intBet = 0;
			String strBet = "";
			
			Font titleFont = con.loadFont("assets/Raleway-SemiBold.ttf", 50);
			Font txtFont = con.loadFont("assets/Raleway-SemiBold.ttf", 30);
			BufferedImage imgBack = con.loadImage("assets/cardback.png");
			
			while(blnBet == false){
				con.setDrawFont(txtFont);
				strBetDraw = "Bet: $" + strBet;
				textWidth = con.getTextFontMetrics().stringWidth(strBetDraw);
				intxBet = 1280 - textWidth - 100;
				
				//draw bet screen
				con.drawImage(imgGame, 0, 0);
				con.drawString("Money: $" + intMoney, 53, 35);
				con.drawString(strBetDraw, intxBet, 35);
				con.setDrawColor(new Color(0, 0, 0, 128));
				con.fillRect(0, 0, 1280, 720);
				con.drawImage(imgBet, 345, 205);
				con.setDrawColor(Color.WHITE);
				con.drawString(strBet, 430, 360);
				con.repaint();
				
				//request user for bet and draw to screen
				char charTyped = con.getChar();
					if(charTyped == 8 && strBet.length() > 0){
						strBet = strBet.substring(0, strBet.length()-1);
					}
					if((charTyped < 48 || charTyped > 57)){
						strBet = strBet;
					}else{
						strBet = strBet + charTyped;
					}
					if(charTyped == '\n'){
						if(!strBet.equals("") && Integer.parseInt(strBet) > 0 && Integer.parseInt(strBet) <= intMoney){
							blnBet = true;
						}else{
							con.drawImage(imgGame, 0, 0);
							con.drawString("Money: $" + intMoney, 53, 35);
							con.drawString(strBetDraw, intxBet, 35);
							con.setDrawColor(new Color(0, 0, 0, 128));
							con.fillRect(0, 0, 1280, 720);
							con.drawImage(imgBet, 345, 205);
							con.setDrawColor(Color.RED);
							con.drawString("Invalid Bet!", 430, 360);
							con.repaint();
							con.setDrawColor(Color.WHITE);
							con.sleep(1200); 
							con.repaint();
							strBet = ""; 
							continue;
						}
					}
					System.out.println(charTyped);
					System.out.println(strBet);
					System.out.println(intBet);
					con.sleep(5);
			}
			intBet = Integer.parseInt(strBet);
			intMoney -= intBet;
			intMult = 0;
			intWin = 0;
			con.clear();
			con.drawImage(imgGame, 0, 0);
			con.repaint();
			
			//get 5 cards onto screen
			for(intRow = 0; intRow < 5; intRow++){
				intHand[intRow][0] = intCards[intRow][0];
				intHand[intRow][1] = intCards[intRow][1];
				animCard(intHand, intRow, con, intMoney, strBetDraw, intxBet);
				System.out.println(intHand[intRow][0] + " " + intHand[intRow][1]);
			}
			boolean blnSwapPhase = true;
			boolean blnCard1Swap = false;
			boolean blnCard2Swap = false;
			boolean blnCard3Swap = false;
			boolean blnCard4Swap = false;
			boolean blnCard5Swap = false;
			con.setDrawFont(txtFont);
			con.drawString("Click on the cards you wish to swap", 387, 156);
			con.drawString("You keep the face-up cards", 454, 184);
			con.drawString("Press [Enter] to confirm selection", 406, 212);
			
			//Starts swapping phase
			while(blnSwapPhase == true){
				int intxMouse = con.currentMouseX();
				int intyMouse = con.currentMouseY();
				int intClicked = con.currentMouseButton();
				//System.out.println(intxMouse);
				//System.out.println(intyMouse);
				//System.out.println(intClicked);
				for(intCount = 0; intCount < 5; intCount++){
					System.out.println(intCount);
					BufferedImage imgFront = con.loadImage("assets/" + Integer.toString(intHand[intCount][0]) + Integer.toString(intHand[intCount][1]) + ".png");
					con.drawImage(imgFront, 165 + 202*intCount, 365);
					
				}
				//hitbox system, toggleable
				if(intyMouse >= 365 && intyMouse <= 555 && intClicked == 1){
					if(intxMouse >= 165 && intxMouse <= 307){
						con.sleep(200);
						if(blnCard1Swap == false){
							blnCard1Swap = true;
						}else{
							blnCard1Swap = false;
						}
					}else if(intxMouse >= 367 && intxMouse <= 509){
						con.sleep(200);
							if(blnCard2Swap == false){
								blnCard2Swap = true;
							}else{
								blnCard2Swap = false;
							}
					}else if(intxMouse >= 569 && intxMouse <= 711){
						con.sleep(200);
							if(blnCard3Swap == false){
								blnCard3Swap = true;
							}else{
								blnCard3Swap = false;
							}
					}else if(intxMouse >= 771 && intxMouse <= 913){
						con.sleep(200);
							if(blnCard4Swap == false){
								blnCard4Swap = true;
							}else{
								blnCard4Swap = false;
							}
					}else if(intxMouse >= 973 && intxMouse <= 1115){
						con.sleep(200);
							if(blnCard5Swap == false){
								blnCard5Swap = true;
							}else{
								blnCard5Swap = false;
							}
					}
				}
				//illusion of "flipping" the cards when clicked
				if(blnCard1Swap == true){
					con.drawImage(imgBack, 165, 365);
				}
				if(blnCard2Swap == true){
					con.drawImage(imgBack, 367, 365);
				}
				if(blnCard3Swap == true){
					con.drawImage(imgBack, 569, 365);
				}
				if(blnCard4Swap == true){
					con.drawImage(imgBack, 771, 365);
				}
				if(blnCard5Swap == true){
					con.drawImage(imgBack, 973, 365);
				}
				//when enter is press, confirm choices
				char charTyped = con.currentChar();
				if(charTyped == '\n'){
					blnSwapPhase = false;
				}
				con.repaint();
			}
			
			//redraw the entire screen to remove instructions
			con.drawImage(imgGame, 0, 0);
			con.drawString("Money: $" + intMoney, 53, 35);
			con.drawString(strBetDraw, intxBet, 35);
			
			for(intCount = 0; intCount < 5; intCount++){
					System.out.println(intCount);
					BufferedImage imgFront = con.loadImage("assets/" + Integer.toString(intHand[intCount][0]) + Integer.toString(intHand[intCount][1]) + ".png");
					con.drawImage(imgFront, 165 + 202*intCount, 365);
					
				}
			
			if(blnCard1Swap == true){
				con.drawImage(imgBack, 165, 365);
			}
			if(blnCard2Swap == true){
				con.drawImage(imgBack, 367, 365);
			}
			if(blnCard3Swap == true){
				con.drawImage(imgBack, 569, 365);
			}
			if(blnCard4Swap == true){
				con.drawImage(imgBack, 771, 365);
			}
			if(blnCard5Swap == true){
				con.drawImage(imgBack, 973, 365);
			}
			//if cards are being swapped, add swapping text and add tension
			if(blnCard1Swap == true || blnCard2Swap == true || blnCard3Swap == true || blnCard4Swap == true || blnCard5Swap == true){
				con.setDrawFont(titleFont);
				con.drawString("Swapping...", 503, 199);
				con.repaint();
				con.sleep(5000);
			}
			//swaps the face down cards
			if(blnCard1Swap == true){
				intRow++;
				System.out.println("swapping 1st");
				intHand[0][0] = intCards[intRow][0];
				intHand[0][1] = intCards[intRow][1];
			}
			if(blnCard2Swap == true){
				intRow++;
				System.out.println("swapping 2nd");
				intHand[1][0] = intCards[intRow][0];
				intHand[1][1] = intCards[intRow][1];
			}
			if(blnCard3Swap == true){
				intRow++;
				System.out.println("swapping 3rd");
				intHand[2][0] = intCards[intRow][0];
				intHand[2][1] = intCards[intRow][1];
			}
			if(blnCard4Swap == true){
				intRow++;
				System.out.println("swapping 4th");
				intHand[3][0] = intCards[intRow][0];
				intHand[3][1] = intCards[intRow][1];
			}
			if(blnCard5Swap == true){
				intRow++;
				System.out.println("swapping 5th");
				intHand[4][0] = intCards[intRow][0];
				intHand[4][1] = intCards[intRow][1];
			}
			
			//redraw screen to remove instructions
			con.clear();
			con.setDrawFont(txtFont);
			con.drawImage(imgGame, 0, 0);
			con.drawString("Money: $" + intMoney, 53, 35);
			con.drawString(strBetDraw, intxBet, 35);
			
			for(intCount = 0; intCount < 5; intCount++){
				System.out.println("redrawing");
				System.out.println(intHand[intCount][0] + intHand[intCount][1]);
				BufferedImage imgFront = con.loadImage("assets/" + Integer.toString(intHand[intCount][0]) + Integer.toString(intHand[intCount][1]) + ".png");
				con.drawImage(imgFront, 165 + 202*intCount, 365);
				con.repaint();
			}
			
			con.sleep(2000);
			intHand = sortHand(intHand);
			//determine the value of the hand
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
			String strWin = "";
			String strPayout = "You Win: $";
			
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
			//determines payout
			if(blnRoyalFlush == true){
				intMult = 800;
				strWin = "Royal Flush!";
			}else if(blnStrFlush == true){
				intMult = 50;
				strWin = "Straight Flush!";
			}else if(blnFourKind == true){
				intMult = 25;
				strWin = "Four of a Kind!";
			}else if(blnFullHouse == true){
				intMult = 9;
				strWin = "Full House!";
			}else if(blnFlush == true){
				intMult = 6;
				strWin = "Flush!";
			}else if(blnStraight == true){
				intMult = 4;
				strWin = "Straight!";
			}else if(blnThreeKind == true){
				intMult = 3;
				strWin = "Three of a Kind!";
			}else if(intPairs == 2){
				intMult = 2;
				strWin = "Two Pairs!";
			}else if(blnJacksUp == true){
				intMult = 1;
				strWin = "Jacks and Up!";
			}else{
				intMult = 0;
				strWin = "You Lost...";
			}
			
			intWin = intBet * intMult;
			strPayout = strPayout + intWin;
			//draw win/lose screen
			BufferedImage imgWin = con.loadImage("assets/win.png");
			BufferedImage imgBankrupt = con.loadImage("assets/noMoney.png");
			BufferedImage imgReturn = con.loadImage("assets/Return.png");
			con.setDrawFont(titleFont);
			int winWidth = con.getDrawFontMetrics().stringWidth(strWin);
			int intxWinDraw = 640 - winWidth/2;
			
			con.setDrawFont(txtFont);
			int payoutWidth = con.getDrawFontMetrics().stringWidth(strPayout);
			int intxPayoutDraw = (1280 - payoutWidth)/2;
			
			con.setDrawColor(new Color(0, 0, 0, 128));
			con.fillRect(0, 0, 1280, 720);
			
			intMoney += intWin;
			
			if(intMoney != 0){
				con.drawImage(imgWin, 345, 205);
				con.setDrawColor(Color.WHITE);
				con.setDrawFont(titleFont);
				con.drawString(strWin, intxWinDraw, 257);
				con.setDrawFont(txtFont);
				con.drawString(strPayout, intxPayoutDraw, 359);
				con.repaint();
				//play again system, restarts loop if play again, return to menu if not
				while(true){
					char charTyped = con.getChar();
					if(charTyped == 'p' || charTyped == 'P'){
						break;
					}else if(charTyped == 'm' || charTyped == 'M'){
						strPlayAgain = "m";
						con.drawImage(imgReturn, 345, 205);
						break;
					}
				}
			}else{
				//if out of money, immediately send user to main menu
				con.drawImage(imgBankrupt, 345, 205);
				con.setDrawColor(Color.WHITE);
				con.setDrawFont(titleFont);
				con.drawString(strWin, intxWinDraw, 257);
				con.repaint();
				strPlayAgain = "m";
				con.sleep(5000);
			}
		}
		return intMoney;
	}

	public static int[][] loadDeck(){
		//Create the deck, return it to game
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
		//Sort the deck, return it to game
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
		//Sort the hand, return it to game
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
		//Sort the leaderboard, return to leaderboard
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
	
	public static void animCard(int intHand[][], int intCount, Console con, int intMoney, String strBetDraw, int intxBet){
		//Card animation
		System.out.println("animating");
		BufferedImage imgBack = con.loadImage("assets/cardback.png");
		BufferedImage imgGame = con.loadImage("assets/GameMenu.png");
		int intxCard = 165 + 202*intCount;
		
		int intyCard = 915;
		while(intyCard != 355){
			con.drawImage(imgGame, 0, 0);
			con.drawString("Money: $" + intMoney, 53, 35);
			con.drawString(strBetDraw, intxBet, 35);
			for(int intCount2 = 0; intCount2 < intCount; intCount2++){
				//keep the cards that are previously drawn on the screen
				System.out.println(intCount2);
				BufferedImage imgFront = con.loadImage("assets/" + Integer.toString(intHand[intCount2][0]) + Integer.toString(intHand[intCount2][1]) + ".png");
				con.drawImage(imgFront, 165 + 202*intCount2, 365);
			}
			con.drawImage(imgBack, intxCard, intyCard);
			intyCard -= 10;
			System.out.println(intyCard);
			con.repaint();
			con.sleep(5);
		}
		intyCard += 10;
	}
}
