import java.security.SecureRandom;
import java.util.Stack;

public class Battle implements ActionMenu1
{
    //playable characters
    Character Aleph = new Character("Aleph",100,24,22,7,10);
    Character Brett = new Character("Brett", 70, 32, 20, 10, 8);
    Character Charlie = new Character("Charlie", 50,50,20,15,5);
    Character David = new Character("David",80,0,30,20,4);
    //enemy
    Monster goblin = new Monster("Goblin",150,40,15,3);


    //Actions that can be performed during battle
    @Override
    public int basicAttack(int atk, int def, String attacker, String beingAttacked)
    {
        int damage = atk - def;
        System.out.printf("%n" + attacker + " performed a basic attack!");
        System.out.printf("%nit did %d damage to %s!", damage, beingAttacked);
        return damage;
    }


    @Override
    public void defend(Character c, String name)
    {
        c.setDef(c.getDef() + 10);
        System.out.printf("%n%s defended!", name);
    }

    @Override
    public int flaria(int atk, int def, int mp)
    {
        int damage = (int) ((atk * 1.5) - def);
        if (mp < 12)
        {
            System.out.println("You tried to cast flaria but you didn't have enough mp!");
            return 0;
        }
        else
        {
            Aleph.setMp(Aleph.getMp() - 12);
            System.out.printf("%nFlaria did %d damage!", damage);
            return damage;
        }
    }

    public void healing(Character c)
    {
        // initialize how much is being healed
        int heal = 0;
        if (c == Brett)
        {
            heal = 50;
        }
        else if (c == Charlie)
        {
            heal = 25;
        }
        // throwing an error if heal was not initialized properly
        if (heal == 0)
        {
            throw new IllegalArgumentException("Healing method is not working properly");
        }
        // heal Aleph
        if (Aleph.getHp() + heal > Aleph.getTotalHp())
        {
            Aleph.setHp(Aleph.TotalHp);
        }
        else Aleph.setHp(Aleph.getHp() + heal);
        // heal Brett
        if (Brett.getHp() + heal > Brett.getTotalHp())
        {
            Brett.setHp(Brett.TotalHp);
        }
        else Brett.setHp(Brett.getHp() + heal);
        // heal Charlie
        if (Charlie.getHp() + heal > Charlie.getTotalHp())
        {
            Charlie.setHp(Charlie.TotalHp);
        }
        else Charlie.setHp(Charlie.getHp() + heal);
        // heal David
        if (David.getHp() + heal > David.getTotalHp())
        {
            David.setHp(David.TotalHp);
        }
        else David.setHp(David.getHp() + heal);
    }

    @Override
    public void healia(int mp)
    {
        if (mp < 6)
        {
            System.out.println("You tried to cast Healia but didn't have enough mp!");
        }
        else
        {
            System.out.printf("%nBrett healed everyone!");
            Brett.setMp(Brett.getMp() - 6);
            healing(Brett);
        }
    }

    @Override
    public int lighting(int atk, int def, int mp)
    {
        int damage = (int) ((atk * 1.25) - def);
        if (mp < 9)
        {
            System.out.println("You tried to cast Lighting but didn't have enough mp!");
            return 0;
        }
        else
        {
            Charlie.setMp(Charlie.getMp() - 9);
            System.out.printf("Lighting did %d damage!", damage);
            return damage;
        }
    }

    @Override
    public int heatHaze(int atk, int def, int mp)
    {
        int damage = ((atk * 2) - def);
        if (mp < 20)
        {
            System.out.println("You tried to cast Heat Haze but didn't have enough mp!");
            return 0;
        }
        else
        {
            Charlie.setMp(Charlie.getMp() - 20);
            System.out.printf("Heat Haze dealt %d damage!", damage);
            return damage;
        }
    }

    @Override
    public int autumnsBreeze(int atk, int def, int mp)
    {
        int damage = (int) ((atk * 1.25) - def);
        if (mp < 7)
        {
            System.out.println("You tried to cast Autumn's Breeze but didn't have enough mp!");
            return 0;
        }
        else
        {
            Charlie.setMp(Charlie.getMp() - 7);
            healing(Charlie);
            System.out.printf("%nAutumn's breeze dealt %d damage!", damage);
            System.out.printf("%nThe breeze healed the party for 25 HP!");
            return damage;
        }
    }

    @Override
    public int bitterCold(int atk, int def, int mp)
    {
        if (Charlie.getMp() == 0)
        {
            System.out.println("You tried to cast Bitter cold but didn't have enough mp!");
            return 0;
        }
        else
        {
            Charlie.setMp(0);
            goblin.setDef(goblin.getDef() - 2);
            System.out.printf("%nThe goblin's constant shivering permanently lowered it's defense by 2 points!");
            return (int) ((atk * 1.8) - def);
        }
    }

    @Override
    public void weathersFury(Weather s)
    {
        if (s == Weather.Spring)
        {
            SecureRandom rng = new SecureRandom();
            int struckTwice = rng.nextInt(101);
            goblin.setHp(goblin.getHp() - lighting(Charlie.getAtk(), goblin.getDef(), Charlie.getMp()));
            if (struckTwice < 49)
            {
                System.out.printf("%nLighting struck twice!?%n");
                goblin.setHp(goblin.getHp() - lighting(Charlie.getAtk(), goblin.getDef(), Charlie.getMp()));
            }
        }
        if  (s == Weather.Summer)
        {
            goblin.setHp(goblin.getHp() - heatHaze(Charlie.getAtk(), goblin.getDef(), Charlie.getMp()));
        }

        if(s == Weather.Fall)
        {
            goblin.setHp(goblin.getHp() - autumnsBreeze(Charlie.getAtk(), goblin.getDef(), Charlie.getMp()));
        }

        if(s == Weather.Winter)
        {
            goblin.setHp(goblin.getHp() - bitterCold(Charlie.getAtk(), goblin.getDef(), Charlie.getMp()));
        }
    }

    @Override
    public int charge(int atk, int def, String name)
    {
        int damage = (int) ((atk * 1.5) - def);
        System.out.printf("%nThe goblin charged at %s for %d damage!", name, damage);
        return damage;
    }

    @Override
    public void wastedTurn()
    {
        System.out.printf("%nThe goblin stared out into space!");
    }

    Stack<Integer> dishes = new Stack<Integer>();
    int stackCounter = 0;

    public void fillStacked()
    {
        SecureRandom rng = new SecureRandom();
        for (int i = 0; i <= 3; i++)
        {
            int fill = rng.nextInt(31);
            dishes.push(fill + 1);
            stackCounter++;
        }
    }

    public int stacked()
    {
        int damage = dishes.pop();
        stackCounter--;
        System.out.printf("%nThe dish did %d damage!", damage);
        return damage;
    }
}
