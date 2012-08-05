/**
 * Tester for Lab 2 (Egg Trader).
 */
public class BRE_ACTUAL_0014
    public static void main ( String arge[] )
    {

        EggTrader joe = new EggTrader();
        EggTrader larry = new EggTrader();
        System.out.println ( "Two egg traders are in business todaay, Joe and Larry.\n" );
        
        double costPerDozen = 37.50;
        int eggsToSell = 6;
        double costPerPiece = 5.50;

        joe.buyOneDozen (costPerDozen);
        System.out.printf ( "Joe bought one dozen eggs at Php %.2f.\n", costPerDozen );
        joe.sellEggs (eggsToSell, costPerPiece) ;
        System.out.printf ( "Joe sold %d eggs at Php %.2f each.\n", eggsToSell, costPerPiece );

        costPerDozen = costPerDozen - 4;
        eggsToSell = eggsToSell + 2;
        costPerPiece = costPerPiece - 2;
        
        larry.buyOneDozen (costPerDozen);
        System.out.printf ( "Larry bought one dozen eggs at Php %.2f.\n", costPerDozen );
        larry.sellEggs (eggsToSell, costPerPiece) ;
        System.out.printf ( "Larry sold %d eggs at Php %.2f each.\n", eggsToSell, costPerPiece );  

        costPerDozen = costPerDozen + 5;
        eggsToSell = eggsToSell - 3;
        costPerPiece = costPerPiece + 3;
        
        joe.buyOneDozen (costPerDozen);
        System.out.printf ( "Joe bought one dozen eggs at Php %.2f.\n", costPerDozen );
        joe.sellEggs (eggsToSell, costPerPiece) ;
        System.out.printf ( "Joe sold %d eggs at Php %.2f each.\n", eggsToSell, costPerPiece );

        costPerDozen = costPerDozen - 2;
        eggsToSell = eggsToSell + 5;
        costPerPiece = costPerPiece - 2;
        
        larry.buyOneDozen (costPerDozen);
        System.out.printf ( "Larry bought one dozen eggs at Php %.2f.\n", costPerDozen );
        larry.sellEggs (eggsToSell, costPerPiece) ;
        System.out.printf ( "Larry sold %d eggs at Php %.2f each.\n", eggsToSell, costPerPiece ); 

        System.out.println ( );
        System.out.println ( "------ CLOSING TIME ------" );
        System.out.println ( );
        System.out.println ( "JOE" );
        System.out.printf ( "Eggs on Hand: %d\n", joe.getEggCount() );
        System.out.printf ( "Cash on Hand: Php %.2f\n\n", joe.getCashOnHand() );

        System.out.println ( "LARRY" );
        System.out.printf ( "Eggs on Hand: %d\n", larry.getEggCount() );
        System.out.printf ( "Cash on Hand: Php %.2f\n\n", larry.getCashOnHand() );
        System.out.println ( "-------------------------" );
        
     }
}

