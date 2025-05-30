import arc.*;

public class CPTrio{
	public static void main(String[] args){
		Console con = new Console();
		int intMoney;
		String strName;
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
		//Sort the card deck here bubble sort
		
		
		con.println("What is your name?");
		strName = con.readLine();
		
		if (strName.equals("statitan")){
			intMoney = 10000;
		}else{
			intMoney = 1000;
		}
		
		while(strPlayAgain.equalsIgnoreCase("y")){
			intCards = loadDeck();
			intCards = sort(intCards);
			con.println("You have $" + intMoney);
			con.println("How much do you bet?");
			intBet = con.readInt();
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
					con.println("found pair");
				}else if (intCardCount[intCount] == 3){
					blnThreeKind = true;
					con.println("found 3k");
					intMult = 3;
				}else if (intCardCount[intCount] == 4){
					blnFourKind = true;
					con.println("found 4k");
					intMult = 25;
				}
			}
			
			if (intPairs == 1){
				if(blnThreeKind == true){
					blnFullHouse = true;
					con.println("is full");
					intMult = 9;
				}else if(intCardCount[0] == 2 || intCardCount[10] == 2 || intCardCount[11] == 2 || intCardCount[12] == 2){
					blnJacksUp = true;
					con.println("is jacks up");
					intMult = 1;
				}else{
					intMult = -1;
					con.println("pair not high enough");
				}
			}
			
			if (intPairs == 0){
				intMult = -1;
				con.println("no pairs");
			}
			
			if(intPairs == 2){
				intMult = 2;
				con.println("2p");
			}
			
			if((intCardValue[0] == intCardValue[1]-1 && intCardValue[1] == intCardValue[2]-1 && intCardValue[2] == intCardValue[3]-1 && intCardValue[3] == intCardValue[4]-1)){
				blnStraight = true;
				con.println("str");
				intMult = 4;
			}
			
			if(intSuitValue[0] == intSuitValue[1] && intSuitValue[0] == intSuitValue[2] && intSuitValue[0] == intSuitValue[3] && intSuitValue[0] == intSuitValue[4]){
				blnFlush = true;
				con.println("flush");
				intMult = 6;
			}
			
			if((intCardValue[0] == 1 && intCardValue[1] == 10 && intCardValue[2] == 11 && intCardValue[3] == 12 && intCardValue[4] == 13)){
				blnRoyalStr = true;
				con.println("royal str");
				intMult = 4;
			}
			
			if(blnStraight == true && blnFlush == true){
				blnStrFlush = true;
				con.println("strflush");
				intMult = 50;
			}
			
			if(blnFlush == true && blnRoyalStr == true && intSuitValue[0] == 4){
				blnRoyalFlush = true;
				con.println("royal flush");
				intMult = 800;
			}
			
			
			
			intWin = intBet * intMult;
			if (intMult != -1){
				con.println("You won $" + intWin);
			}else{
				con.println("You lost $" + intBet);
			}
			intMoney += intWin;
			
			if(intMoney != 0){
				con.println("do you want to play again? y/n");
				strPlayAgain = con.readLine();
			}else{
				strPlayAgain = "n";
			}
		}
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

        int intTemp [] = new int [3];
        int intCount;
        int intCount2;
        int intCount3;

        for (intCount2 = 0; intCount2 < 52-1; intCount2++) {
            for (intCount = 0; intCount < 52-1; intCount++) {
                if (intDeck [intCount][2] > intDeck [intCount+1][2]) {
                    for (intCount3 = 0; intCount3 < 3; intCount3++) {
                        intTemp [intCount3] = intDeck [intCount][intCount3];
                        intDeck [intCount][intCount3] = intDeck [intCount+1][intCount3];
                        intDeck [intCount+1][intCount3] = intTemp [intCount3];
                    }
                }
            }
        }
        return intDeck;
    }
    
    public static int[][] sortHand(int intDeck[][]) {

        int intTemp [] = new int [2];
        int intCount;
        int intCount2;
        int intCount3;
        
        System.out.println("bubble sorting");

        for (intCount2 = 0; intCount2 < 5-1; intCount2++) {
            for (intCount = 0; intCount < 5-1; intCount++) {
                if (intDeck [intCount][0] > intDeck [intCount+1][0]) {
                    for (intCount3 = 0; intCount3 < 2; intCount3++) {
                        intTemp [intCount3] = intDeck [intCount][intCount3];
                        intDeck [intCount][intCount3] = intDeck [intCount+1][intCount3];
                        intDeck [intCount+1][intCount3] = intTemp [intCount3];
                    }
                }
            }
        }
        return intDeck;
    }
}


