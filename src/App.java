import java.util.*;


import javax.sound.sampled.SourceDataLine;
public class App {
    Scanner scan = new Scanner(System.in);
    Player p1 = new Player();
    Player p2 = new Player(InputHelper.getNonZeroLenString(scan, "Player 2 \n Enter your username:"), InputHelper.getInt(scan, "Enter your x-coordinates"), InputHelper.getInt(scan, "Enter your y-coordinates"), InputHelper.getInt(scan, "Enter your z-coordinates"));
    Player p3 = new Player(InputHelper.getInt(scan, " Player 3 \n Enter your x-coordinates"),  InputHelper.getInt(scan, "Enter your y-coordinates"),  InputHelper.getInt(scan, "Enter your z-coordinates"), InputHelper.getNonZeroLenString(scan, "Enter your username:"), InputHelper.getRangedInt(scan, "Enter the direction your facing: ", 1, 6), InputHelper.getRangedInt(scan, "Enter your hp: ", 1, 100));
    int currentPlayer;
    public static void main(String[] args) throws Exception {
        App m = new App();
        System.out.println("");
        System.out.println(m.p1.toString());
        System.out.println(m.p2.toString());
        System.out.println(m.p3.toString() + "\n");
        do{
            m.gameFlow();
        }while(InputHelper.getYNConfirm(m.scan, "Continue? Y/N"));
        
       


    }


    public void gameFlow(){
        Player[] pList = {p1, p2, p3};
        for (int i = 1; i <= 3; i++) {
            final int pIndex = i;
            HashMap<String, Runnable> gameMap = new HashMap<>();
            gameMap.put("A", () -> {
                pList[pIndex-1].attack(obectHandler(), InputHelper.getRangedInt(scan, "Enter damage amount:", 0, pList[currentPlayer].getHp()));
            });

            gameMap.put("T", () -> {
                pList[pIndex-1].teleport(obectHandler());
            });

            gameMap.put("Tl", () -> {
                pList[pIndex-1].teleport(InputHelper.getInt(scan, "Enter x-coordinate"), InputHelper.getInt(scan, "Enter y-coordinate"), InputHelper.getInt(scan, "Enter z-coordinate"));
            });
            
            gameMap.put("M", () -> {
                pList[pIndex-1].move(InputHelper.getRangedInt(scan, "Enter direction to move too", 1, 6), InputHelper.getPositiveNonZeroInt(scan, "Enter amount of units to move"));
            });

            gameMap.put("D", () -> {
                double temp = pList[pIndex-1].getDistance(InputHelper.getInt(scan, "Enter x-coordinate"), InputHelper.getInt(scan, "Enter y-coordinate"), InputHelper.getInt(scan, "Enter z-coordinate"));
                System.out.println(temp);
            });
            
            gameMap.put("Dp", () -> {
                double temp = pList[pIndex-1].getDistance(obectHandler());
                System.out.println(temp);
            });

            gameMap.put("S", () -> {
                String temp = pList[pIndex-1].toString();
                System.out.println(temp);
            });
            
            System.out.println("\nPlayer " + i + " enter your move: \n A - Attack \nT - Teleport to player\n Tl - teleport to location\n M - Move \n D - Get distance from specified coordinates \n Dp - get distance from specified player \n S - display stats");
            //hashmap done
            boolean sentinel = true;
            do{
                String temp = scan.nextLine();
                if(gameMap.containsKey(temp)){
                    gameMap.get(temp).run();
                    sentinel = false;
                }else{
                    System.out.println("Invalid command - Make sure the input is case-sensitive");
                }
            }while(sentinel);
        }
    }

    private Player obectHandler(){
        HashMap<String, Player> playerMap = new HashMap<>();
        playerMap.put(p1.getName(), p1);
        playerMap.put(p2.getName(), p2);
        playerMap.put(p3.getName(), p3);
        do{
        System.out.println("Enter player name you wish to preform this action on: ");
        String temp = scan.nextLine();
        if (!playerMap.containsKey(temp)) {
            System.out.println("Invalid Player Name Try Again:");
        }else{
            determineInt(playerMap.get(temp));
            return playerMap.get(temp);
        }
        }while(true);
        
    }

    private void determineInt(Player player){
        if (player == p1) {
            currentPlayer = 0;
        }else if(player == p2){
            currentPlayer = 1;
        }else if(player == p3){
            currentPlayer = 2;
        }
    }
}
