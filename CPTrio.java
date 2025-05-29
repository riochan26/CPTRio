import arc.*;

public class CPTrio{
	public static void main(String[] args){
		Console con = new Console();
		int intMoney;
		String strName;
		int intCards[][];
		intCards = loadDeck();
		
		int intHand[][];
		intHand = new int[5][2];
		int intRow;
		int intSwap;
		int intSwapped;
		String strSwappedCards;
		int intCount;
		
		strSwappedCards = "";
		
		//Sort the card deck here bubble sort
		intCards = sort(intCards);
		
		for(intRow = 0; intRow < 5; intRow++){
			intHand[intRow][0] = intCards[intRow][0];
			intHand[intRow][1] = intCards[intRow][1];
			con.println(intHand[intRow][0] + " " + intHand[intRow][1]);
		}
		
		con.println("How many cards would you like to swap");
		intSwap = con.readInt();
		
		while(intSwap > 5 && intSwap < 0){
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
		
		if(intHand[0][1] == intHand[1][1] && intHand[0][1] == intHand[2][1] && intHand[0][1] == intHand[3][1] && intHand[0][1] == intHand[4][1]){
			con.println("flush");
			int intSuit;
			intSuit = intHand[0][1];
			if((intHand[0][0] == (intHand[1][0]-1) && intHand[0][0] == (intHand[2][0]-2) && intHand[0][0] == (intHand[3][0]-3) && intHand[0][0] == (intHand[4][0]-4)) || (intHand[0][0] == 1 && intHand[1][0] == 10 && intHand[2][0] == 11 && intHand[3][0] == 12 && intHand[4][0] == 13)){
				con.println("straight flush");
				if (intSuit == 4 && intHand[0][0] == 1 && intHand[4][0] == 13){
					con.println("royal flush");
				}
			}
		}
		else if((intHand[0][0] == (intHand[1][0]-1) && intHand[0][0] == (intHand[2][0]-2) && intHand[0][0] == (intHand[3][0]-3) && intHand[0][0] == (intHand[4][0]-4)) || (intHand[0][0] == 1 && intHand[1][0] == 10 && intHand[2][0] == 11 && intHand[3][0] == 12 && intHand[4][0] == 13)){
			con.println("straight");
		}
		
		for(intCount = 0; intCount < 5; intCount++){
			for(intCount2 = 0+intCount; intCount2< 5; intCount2++){
				if(intHand[intCount][0] == intHand[intCount][1])
				intCard = intHand[intCount][0];
				con.println("pair " + intCard);
				break;
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


