public interface ActionMenu1
{
    int basicAttack(int atk, int def, String attacker, String beingAttacked);

    void defend(Character c, String name);

    int flaria(int atk, int def, int mp);

    void healia (int mp);

    int lighting(int atk, int def, int mp);

    int heatHaze(int atk, int def, int mp);

    int autumnsBreeze(int atk, int def, int mp);

    int bitterCold(int atk, int def, int mp);

    void weathersFury (Weather s);

    int charge(int atk, int def, String name);

    void wastedTurn();

}
