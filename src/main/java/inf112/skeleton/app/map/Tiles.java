package inf112.skeleton.app.map;

public enum Tiles {

    private int id;
    private boolean collidable;
    private String name;

    private Tiles (int id, boolean collidable, String name) {
        this(id, collidable, name);
    }

    TEMP1(1, true, "temp1"),
    TEMP2(2, true, "temp2"),
    TEMP3(3, true, "temp3"),
    TEMP4(4, true, "temp4"),
    FLOOR(5, true, "Floor"),
    PIT(6, true, "Pit"),
    RSITE(7, true, "Repair Site"),*
    WALL_BCR(8, true, "Bottom Right Corner Wall"),
    TEMP5(9, true, "temp5"),
    TEMP6(10, true, "temp6"),
    TEMP7(11, true, "temp7"),
    TEMP8(12, true, "temp8"),
    DCONVEYOR_UP(13, true, "Double Conveyor Up"),
    DCONVEYOR_RIGHT(14, true, "Double Conveyor Right"),
    RSITE_S(15, true, "Repair Site Single"),
    WALL_TRC(16, true, "Top Right Corner Wall"),
    DCONVEYOR_DL(17, true, "Double Conveyor Down-Right"),




    NCONVERYOR_DOWN(33,)







}
