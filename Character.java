public class Character extends Stats
{

    Character(String name, int TotalHp, int TotalMp, int atk, int def, int spe)
    {
        setName(name);
        setTotalHp(TotalHp);
        setHp(TotalHp);
        setTotalMp(TotalMp);
        setMp(TotalMp);
        setAtk(atk);
        setDef(def);
        setSpe(spe);
    }

}
