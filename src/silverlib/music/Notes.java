package silverlib.music;

/**
 * A list of musical constants
 *
 * @since 1.10.0
 * @author Jake Trimble
 */
public enum Notes {
    /**
     * The volume Pianissimo
     */
    PP(127/7+8,"PP"),
    /**
     * The volume Piano
     */
    P(127* 2/7,"P"),
    /**
     * The volume Mezopiano
     */
    MP(127* 3/7,"MP"),
    /**
     * The volume Mezoforte
     */
    MF(127* 4/7,"MF"),
    /**
     * The volume Forte
     */
    F(127* 5/7,"F"),
    /**
     * The volume Fortissimo
     */
    FF(127* 6/7,"FF"),
    /**
     * The volume Fortissitissimo
     */
    FFF(127,"FFF"),

    /**
     * The tempo Largo
     */
    LARGO(40,"Largo"),
    /**
     * The tempo Adagio
     */
    ADAGIO(66,"Adagio"),
    /**
     * The tempo Andante
     */
    ANDANTE(76,"Andante"),
    /**
     * The tempo Moderato
     */
    MODERATO(120,"Moderato"),
    /**
     * The tempo Allegro
     */
    ALLEGRO(150,"Allegro"),
    /**
     * The tempo Presto
     */
    PRESTO(180,"Presto"),

    C1(24,"C1"),
    C1$(25,"C1#"),
    D1(26,"D1"),
    D1$(27,"D1#"),
    E1(28,"E1"),
    F1(29,"F1"),
    F1$(30,"F1#"),
    G1(31,"G1"),
    G1$(32,"G1#"),
    A1(33,"A1"),
    A1$(34,"A1#"),
    B1(35,"B1"),

    C2(36,"C2"),
    C2$(37,"C2#"),
    D2(38,"D2"),
    D2$(39,"D2#"),
    E2(40,"E2"),
    F2(41,"F2"),
    F2$(42,"F2#"),
    G2(43,"G2"),
    G2$(44,"G2#"),
    A2(45,"A2"),
    A2$(46,"A2#"),
    B2(47,"B2"),

    C3(48,"C3"),
    C3$(49,"C3#"),
    D3(50,"D3"),
    D3$(51,"D3#"),
    E3(52,"E3"),
    F3(53,"F3"),
    F3$(54,"F3#"),
    G3(55,"G3"),
    G3$(56,"G3#"),
    A3(57,"A3"),
    A3$(58,"A3#"),
    B3(59,"B3"),

    C4(60,"C4"),
    C4$(61,"C4#"),
    D4(62,"D4"),
    D4$(63,"D4#"),
    E4(64,"E4"),
    F4(65,"F4"),
    F4$(66,"F4#"),
    G4(67,"G4"),
    G4$(68,"G4#"),
    A4(69,"A4"),
    A4$(70,"A4#"),
    B4(71,"B4"),

    C5(72,"C5"),
    C5$(73,"C5#"),
    D5(74,"D5"),
    D5$(75,"D5#"),
    E5(76,"E5"),
    F5(77,"F5"),
    F5$(78,"F5#"),
    G5(79,"G5"),
    G5$(80,"G5#"),
    A5(81,"A5"),
    A5$(82,"A5#"),
    B5(83,"B5"),

    C6(84,"C6"),
    C6$(85,"C6#"),
    D6(86,"D6"),
    D6$(87,"D6#"),
    E6(88,"E6"),
    F6(89,"F6"),
    F6$(90,"F6#"),
    G6(91,"G6"),
    G6$(92,"G6#"),
    A6(93,"A6"),
    A6$(94,"A6#"),
    B6(95,"B6"),

    C7(96,"C7"),
    C7$(97,"C7#"),
    D7(98,"D7"),
    D7$(99,"D7#"),
    E7(100,"E7"),
    F7(101,"F7"),
    F7$(102,"F7#"),
    G7(103,"G7"),
    G7$(104,"G7#"),
    A7(105,"A7"),
    A7$(106,"A7#"),
    B7(107,"B7"),

    REST(0,"_");

    private final int keyNum;
    private final String key;
    private final String chari;

    Notes(int kn, String k){
        keyNum = kn;
        key = k;
        chari = "" + key.charAt(0);
    }

    /**
     * @return The integer value associated with the note/volume/tempo
     */
    public int getKeyNum() {
        return keyNum;
    }

    /**
     * @return The String value associated with the note/volume/tempo
     */
    public String getKey() {
        return key;
    }

    /**
     * @return The first character in the String value associated with the note/volume/tempo
     */
    public String getNote(){
        return chari;
    }


    /**
     * Matches String values with Enum values
     * @param k The string to match
     * @return The matching enumeration value if k is a valid key, <code>Notes.REST</code> otherwise
     *
     * @since 1.10.0
     */
    public static final Notes match(String k){
        for (Notes n : Notes.values()){
            if (n.key.equals(k)){
                return n;
            }
        }

        return REST;
    }
}