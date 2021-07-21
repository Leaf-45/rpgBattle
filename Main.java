public class Main
{
    public static void main(String[]args)
    {
        Turns turns = new Turns();
        System.out.println("Your party encountered a wild boss goblin!");
        //this while loop goes on if the player has any more useable characters or if the goblin is defeated
        while (!turns.isPlayerWon() && !turns.isGoblinWon())
        {
            turns.Fight();
        }
        if (turns.isPlayerWon())
        {
            System.out.println("You defeated the goblin and received nothing at all!");
        }
        if (turns.isGoblinWon())
        {
            System.out.printf("%nEveryone died%nThe end");
        }
    }
}
