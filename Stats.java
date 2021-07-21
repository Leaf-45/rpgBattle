abstract public class Stats
{
    String name;
    int TotalHp;
    int Hp;
    int TotalMp;
    int Mp;
    int atk;
    int def;
    int spe;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getTotalHp()
    {
        return TotalHp;
    }

    public void setTotalHp(int TotalHp)
    {
        this.TotalHp = TotalHp;
    }

    public int getHp()
    {
        return Hp;
    }

    public void setHp(int hp)
    {
        this.Hp = hp;
    }

    public int getTotalMp()
    {
        return TotalMp;
    }

    public void setTotalMp(int totalMp)
    {
        TotalMp = totalMp;
    }

    public int getMp()
    {
        return Mp;
    }

    public void setMp(int mp)
    {
        this.Mp = mp;
    }

    public int getAtk()
    {
        return atk;
    }

    public void setAtk(int atk)
    {
        this.atk = atk;
    }

    public int getDef()
    {
        return def;
    }

    public void setDef(int def)
    {
        this.def = def;
    }

    public int getSpe()
    {
        return spe;
    }

    public void setSpe(int spe)
    {
        this.spe = spe;
    }
}
