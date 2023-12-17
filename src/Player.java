import java.util.*;
public class Player {
    private Scanner scan = new Scanner(System.in);
    private int x;
    private int y; 
    private int z;
    private int direction;
    private String playerName;
    private int numplayers;
    private int hp;

    
    
    public Player(){
        x = 0;
        y = 0;
        z = 0;
        direction = 1;
        playerName = "P" + numplayers;
    }
    public Player(String name, int x, int y, int z){
        this.z = z;
        this.x = x;
        this.y = y;
        playerName = name;
        hp = 20;
        direction = 1;

    }
    public Player(int x, int y, int z, String name, int direction, int hp){
        this.x = x;
        this.y = y;
        this.z = z;
        playerName = name;
        this.direction = direction;
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }
    public String getName(){
        return playerName;
    }
    public int getDirection(){
        return direction;
    }
    public int getNumPlayers(){
        return numplayers;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }

    public String toString(){
        String temp = "Name: " + playerName + "\n" + "Coordinates: " + x + " " + y + " " + z + "\n" + "Health: " + hp + "\n" + "Direction: " + direction;
        return temp;

    }

    public void setHp(int desiredHp){
        hp = desiredHp;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public void move(int direction, int units){
        if(direction == 1){
            x = x + units;
        }else if(direction == 2){
            x = x - units;
        }else if(direction == 3){
            y = y + units;
        }else if(direction == 4){
            y = y - units;
        }else if(direction == 5){
            z = z + units;
        }else if(direction == 6){
            z = z - units;
        }
    }
    public void teleport(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void teleport(Player playerName){
        x = playerName.getX();
        y = playerName.getY();
        z = playerName.getZ();
    }
    public void attack(Player playerName, int damage){
        playerName.hp = playerName.hp - damage;
        if (hp + (damage / 2) > 100) {
            this.setHp(100);
        }else{
            this.setHp(hp + (damage/2));
        }
    }
    public double getDistance(int x, int y, int z){
        return (Math.pow(x - this.x, 2.0) + Math.pow(y - this.y, 2.0) + Math.pow(z - this.z, 2.0));
    }
    public double getDistance(Player playerName){
        return (Math.pow(playerName.getX() - this.x, 2.0) + Math.pow(playerName.getY() - this.y, 2.0) + Math.pow(playerName.getZ() - this.z, 2.0));
    }

    
}
