public class Pokemon {
    String name,type,type2;
    int hp,atk,defense,spd;

    public Pokemon(String name, String type, String type2, int hp, int atk, int defense, int spd){
        this.name = name;
        this.type = type;
        this.type2 = type2;
        this.hp = hp;
        this.atk = atk;
        this.defense = defense;
        this.spd = spd; 
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getType2(){
        return type2;
    }

    public int getHp(){
        return hp;
    }

    public int getAtk(){
        return atk;
    }

    public int getDef(){
        return defense;
    }

    public int getSpd(){
        return spd;
    }

    public String toString(){
        return name + ", " + type + " " + type2 + "| hp: " + hp + " | atk: " + atk + " | def: " + defense + " | spd: " + spd;
    }
}
