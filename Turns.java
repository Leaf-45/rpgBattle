import java.security.SecureRandom;
import java.util.*;

public class Turns extends Battle
{
    Weather s = seasonChance();

    // all the methods relating to the character's turns
    // Sets the season for charlie's spell
    public Weather seasonChance()
    {
        SecureRandom rng = new SecureRandom();
        int season = rng.nextInt(101);
        if (season <= 25)
        {
            return Weather.Spring;
        }
        else if (season <= 50)
        {
            return Weather.Summer;
        }
        else if (season <= 75)
        {
            return Weather.Fall;
        }
        else return Weather.Winter;
    }

    public void stackCheck()
    {
        Scanner sc = new Scanner(System.in);
        if (dishes.isEmpty())
        {
            System.out.println("David is all out of dishes to throw!");
            characterTurn(David);
            return;
        }
        System.out.printf("Stacked will do %d damage" ,dishes.peek());
        System.out.printf("%n1: Yes%n2: No%n");
        try
        {
            int input = sc.nextInt();
            if (input == 1)
            {
                goblin.setHp(goblin.getHp() - stacked());
            }
            if (input == 2)
            {
                characterTurn(David);
            }
            else System.out.println("Please input a valid number");
            stackCheck();
        } catch (InputMismatchException i)
        {
            System.out.println("Please enter a valid input");
            stackCheck();
        }

    }

    // player turn
    Stack<Character> defenseCheck = new Stack<Character>();
    public void characterTurn(Character c)
    {
        Scanner sc = new Scanner(System.in);
        try {
            int input = sc.nextInt();
            switch (input)
            {
                case 1: goblin.setHp(goblin.getHp() - basicAttack(c.getAtk(), goblin.getDef(), c.getName(), goblin.getName()));
                    break;
                case 2: defend(c, c.getName());
                    defenseCheck.add(c);
                    break;
                case 3: status(c);
                    break;
                case 4:
                    if(c == Aleph)
                    {
                        goblin.setHp(goblin.getHp() - flaria(Aleph.getAtk(),goblin.getDef(), Aleph.getMp()));
                        break;
                    }
                    else if (c == Brett)
                    {
                        healia(Brett.getMp());
                        break;
                    }
                    else if (c == Charlie)
                    {
                        weathersFury(s);
                        break;
                    }
                    else if (c == David)
                    {
                        stackCheck();
                        break;
                    }
                default: System.out.printf("%nPlease enter a valid input%n");
                    characterTurn(c);
            }
        } catch (InputMismatchException i)
        {
            System.out.println("Invalid input");
            characterTurn(c);
        }

    }


    // monster turn methods
    public Character targetCharacter()
    {
        SecureRandom rng = new SecureRandom();
        int target = rng.nextInt(101);
        if (target < 25 && Aleph.getHp() > 0)
        {
            return Aleph;
        }
        else if (target < 50 && Brett.getHp() > 0)
        {
            return Brett;
        }
        else if (target < 75 && Charlie.getHp() > 0)
        {
            return Charlie;
        }
        else if (David.getHp() > 0)
        {
            return David;
        }
        return null;
    }

    public void monsterTurn(Character c)
    {
        System.out.printf("%n------------------------------------------------------------------------------------");
        System.out.printf("%nIt's " + goblin.getName() + " Turn!");
        if (c == null)
        {
            targetCharacter();
        }
        SecureRandom rng = new SecureRandom();
        int attack = rng.nextInt(101);
        if (attack < 60 && !(c == null))
        {
            c.setHp(c.getHp() - basicAttack(goblin.getAtk(),c.getDef(), goblin.getName(), c.getName()));
        }
        else if (attack < 80 && !(c == null))
        {
            c.setHp(c.getHp() - charge(goblin.getAtk(),c.getDef(), c.getName()));
        }
        else wastedTurn();
    }


    // turn order
    List<Character> playableCharacters = new ArrayList<>(Arrays.asList(Aleph, Brett, Charlie, David));
    int[] speed = {Aleph.getSpe(), Brett.getSpe(), Charlie.getSpe(), David.getSpe()};
    Stack<Character> turnOrder = new Stack<>();

    public void healthCheck(List<Character> c)
    {
        for (Character i : c)
        {
            if (i.getHp() < 0)
            {
                System.out.printf("%n--------------------------------------------------" +
                        "----------------------------------");
                System.out.printf("%n%s is currently down!", i.getName());
            }
        }
    }

    public void speedCheck(int[] speed, List<Character> c)
    {
        Arrays.sort(speed);
        for(int i : speed)
        {
            for (Character ii : c)
            {
                if (i == ii.getSpe() && ii.getHp() > 0)
                {
                    turnOrder.add(ii);
                }
            }
        }
    }

    boolean goblinWon = false;
    boolean playerWon = false;

    public void setGoblinWon(boolean goblinWon)
    {
        this.goblinWon = goblinWon;
    }

    public boolean isGoblinWon()
    {
        return goblinWon;
    }

    public void setPlayerWon(boolean playerWon)
    {
        this.playerWon = playerWon;
    }

    public boolean isPlayerWon()
    {
        return playerWon;
    }

    public boolean filledDishes = false;

    // Battle segment
    public void Fight()
    {
        if (!filledDishes)
        {
            fillStacked();
            filledDishes = true;
        }
        if (turnOrder.isEmpty())
        {
            monsterTurn(targetCharacter());
            healthCheck(playableCharacters);
            speedCheck(speed, playableCharacters);
            if (turnOrder.isEmpty())
            {
                setGoblinWon(true);
            }
            while (!defenseCheck.isEmpty())
            {
                Character defend = defenseCheck.pop();
                defend.setDef(defend.getDef() - 5);
            }
        }
        if (!turnOrder.isEmpty())
        {
            Character characterStack = turnOrder.pop();
            UI(characterStack);
            characterTurn(characterStack);
            if (goblin.getHp() <= 0)
            {
                setPlayerWon(true);
            }
        }
    }


    // display
    public void status(Character c)
    {
        System.out.printf("%n" + Aleph.getName() + ":    " + Brett.getName() + ":   " + Charlie.getName() + ":  "
                + David.getName() + ":");
        //HP display
        System.out.printf("%n%d/%d HP %d/%d HP %d/%d HP %d/%d HP", Aleph.getTotalHp(), Aleph.getHp(), Brett.getTotalHp(),
                Brett.getHp(), Charlie.getTotalHp(), Charlie.getHp(), David.getTotalHp(), David.getHp());
        //MP display
        System.out.printf("%n%d/%d MP  %d/%d MP %d/%d MP %d/%d MP", Aleph.getTotalMp(), Aleph.getMp(), Brett.getTotalMp(),
                Brett.getMp(), Charlie.getTotalMp(), Charlie.getMp(), David.getTotalMp(), David.getMp());
        // atk display
        System.out.printf("%n%d atk    %d atk   %d atk   %d atk", Aleph.getAtk(), Brett.getAtk(), Charlie.getAtk(),
                David.getAtk());
        // def display
        System.out.printf("%n%d def     %d def   %d def   %d def", Aleph.getDef(), Brett.getDef(), Charlie.getDef(),
                David.getDef());
        // spe display
        System.out.printf("%n%d spe    %d spe    %d spe    %d spe%n", Aleph.getSpe(), Brett.getSpe(), Charlie.getSpe(),
                David.getSpe());

        characterTurn(c);
    }

    public void UI(Character c)
    {
        System.out.printf("%n------------------------------------------------------------------------------------");
        System.out.printf("%nIt's %s turn!%n1: preform a basic attack%n2: defend for the turn%n" +
                "3: check everyone condition", c.getName());
        if (c == Aleph)
        {
            System.out.printf("%n4: Cast flaria a hard hitting spell (Cost 12mp)%n");
        }
        else if (c == Brett)
        {
            System.out.printf("%n4: Cast Healia which heals everyone for 50hp (cost 7mp)%n");
        }
        else if (c == Charlie)
        {
           if (s == Weather.Spring)
           {
               System.out.printf("%n4: Strike down your foe with lighting! But it could never strike twice right?" +
                       "(Cost 9mp)%n");
           }
           else if (s == Weather.Summer)
           {
               System.out.printf("%n4: Heat haze is a devastatingly strong yet costly spell to cast (cost 20 mp)%n");
           }
           else if (s == Weather.Fall)
           {
               System.out.printf("%n4: Autumn's breeze not only hits the enemy but heals everyone for a small amount" +
                       "(Cost 7mp)%n");
           }
           else if (s == Weather.Winter)
           {
               System.out.printf("%n4: Bitter cold hits hard and permanently reduces " +
                       "the enemies defense by 2 points! (Cost all mp)%n");
           }
        }
        else if (c == David)
        {
            System.out.printf("%n4: Throw the top from your signature stack of dishes ignoring enemy defense " +
                    "(you have %d out of 4 dishes)%n", stackCounter);
        }
    }

}
