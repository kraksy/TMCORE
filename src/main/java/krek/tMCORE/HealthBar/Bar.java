package krek.tMCORE.HealthBar;

public class Bar {

    String solid;
    String empty;
    String leftCorner;
    String rightCorner;
    String HIcon; // health icon
    String MIcon; // mana icon
    String AIcon; // armor icon

    int HV; // health value
    int MV; // mana value
    String health;
    String mana;
    int AV; // armor value

    public Bar(String solid, String empty, String leftCorner, String rightCorner, String HIcon, String MIcon, String AIcon, int HV, int MV, String health, String mana, int AV) {
        this.solid = solid;
        this.empty = empty;
        this.leftCorner = leftCorner;
        this.rightCorner = rightCorner;
        this.HIcon = HIcon;
        this.MIcon = MIcon;
        this.AIcon = AIcon;
        this.HV = HV;
        this.MV = MV;
        this.health = health;
        this.mana = mana;
        this.AV = AV;
    }

    public String getSolid() {
        return solid;
    }

    public void setSolid(String solid) {
        this.solid = solid;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getLeftCorner() {
        return leftCorner;
    }

    public void setLeftCorner(String leftCorner) {
        this.leftCorner = leftCorner;
    }

    public String getRightCorner() {
        return rightCorner;
    }

    public void setRightCorner(String rightCorner) {
        this.rightCorner = rightCorner;
    }

    public String getHIcon() {
        return HIcon;
    }

    public void setHIcon(String HIcon) {
        this.HIcon = HIcon;
    }

    public String getMIcon() {
        return MIcon;
    }

    public void setMIcon(String MIcon) {
        this.MIcon = MIcon;
    }

    public String getAIcon() {
        return AIcon;
    }

    public void setAIcon(String AIcon) {
        this.AIcon = AIcon;
    }

    public int getHV() {
        return HV;
    }

    public void setHV(int HV) {
        this.HV = HV;
    }

    public int getMV() {
        return MV;
    }

    public void setMV(int MV) {
        this.MV = MV;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public int getAV() {
        return AV;
    }

    public void setAV(int AV) {
        this.AV = AV;
    }
}
