public class Monster extends Stats
{
    Monster(String name, int TotalHp, int atk, int def, int spe)
    {
        setName(name);
        setTotalHp(TotalHp);
        setHp(TotalHp);
        setAtk(atk);
        setDef(def);
        setSpe(spe);
    }

}
