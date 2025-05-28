import arc.*;

public class CPTrio{
	public static void main(String[] args){
		Console con = new Console();
		int intMoney;
		String strName;
		int intCards[][];
		intCards = new int[52][3];
		
		int intHand[][];
		intHand = new int[5][2];
		int intSuit;
		int intCard;
		int intRow;
		int intSwap;
		int intSwapped;
		int intSwappedCards;
		int intCount;
		
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
		
		//Sort the card deck here bubble sort
		
		for(intRow = 0; intRow < 5; intRow++){
			intHand[intRow][0] = intCards[intRow][0];
			intHand[intRow][1] = intCards[intRow][1];
		}
		
		con.println("How many cards would you like to swap");
		intSwap = con.readInt();
		con.println("Which cards would you like to swap (Example, 134 would swap cards 1, 3 and 4)");
		intSwappedCards = con.readInt();
		for(intCount = 0; intCount < intSwap; intRow++){
			intSwapped = intSwappedCards/((10*(intSwap))/10);
			intRow++;
			intHand[intSwapped-1][0] = intCards[intRow][0];
			intHand[intSwapped-1][1] = intCards[intRow][1];
		}
		
		intCount = 0;
		for(intCount = 0; intCount < 5; intCount++){
			con.print(intHand[intCount][0] + " ");
			con.println(intHand[intCount][1]);
		}
	}
}

