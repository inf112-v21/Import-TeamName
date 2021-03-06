package inf112.skeleton.app.map;

/**
 *  Returns name of given tile id.
 */
public enum Tiles {

    FLOOR(5),
    EMPTY(48),

    //Game elements
    PIT(6),
    REPAIR_SITE_SINGLE(15),
    REPAIR_SITE_DOUBLE(7),
    GEAR_ROTATE_LEFT(53),
    GEAR_ROTATE_RIGHT(54),
    FLAG1(55),
    FLAG2(63),
    FLAG3(71),
    FLAG4(79),

    //Docking bays (Starting points)
    DOCKING_BAY1(121),
    DOCKING_BAY2(122),
    DOCKING_BAY3(123),
    DOCKING_BAY4(124),
    DOCKING_BAY5(129),
    DOCKING_BAY6(130),
    DOCKING_BAY7(131),
    DOCKING_BAY8(132),

    //Pushers (Position) (Pushing Direction)
    PUSHER_NORTH_SOUTH_2(1),
    PUSHER_EAST_WEST_2(2),
    PUSHER_SOUTH_NORTH(3),
    PUSHER_WEST_EAST_2(4),
    PUSHER_NORTH_SOUTH(9),
    PUSHER_EAST_WEST(10),
    PUSHER_SOUTH_NORTH_2(11),
    PUSHER_WEST_EAST(12),

    //Walls
    WALL_RIGHT(23),
    WALL_DOWN(29),
    WALL_LEFT(30),
    WALL_TOP(31),
    WALL_LEFT_DOWN_CORNER(32),
    WALL_BOTTOM_RIGHT_CORNER(8),
    WALL_LEFT_TOP_CORNER(24),
    WALL_TOP_RIGHT_CORNER(16),

    //Double conveyor
    DOUBLE_CONVEYOR_DOWN(21),
    DOUBLE_CONVEYOR_LEFT(22),
    DOUBLE_CONVEYOR_UP(13),
    DOUBLE_CONVEYOR_RIGHT(14),
    DOUBLE_CONVEYOR_DOWN_RIGHT(25),
    DOUBLE_CONVEYOR_RIGHT_UP(26),
    DOUBLE_CONVEYOR_LEFT_UP(27),
    DOUBLE_CONVEYOR_DOWN_LEFT(28),
    DOUBLE_CONVEYOR_LEFT_DOWN(17),
    DOUBLE_CONVEYOR_UP_LEFT(18),
    DOUBLE_CONVEYOR_UP_RIGHT(19),
    DOUBLE_CONVEYOR_RIGHT_DOWN(20),

    //Double merged conveyors
    DOUBLE_CONVEYOR_TO_UP_FROM_LEFT_AND_DOWN(73),
    DOUBLE_CONVEYOR_TO_RIGHT_FROM_LEFT_AND_UP(74),
    DOUBLE_CONVEYOR_TO_DOWN_FROM_RIGHT_AND_UP(75),
    DOUBLE_CONVEYOR_TO_LEFT_FROM_RIGHT_AND_DOWN(76),
    DOUBLE_CONVEYOR_TO_UP_FROM_RIGHT_AND_DOWN(77),
    DOUBLE_CONVEYOR_TO_RIGHT_FROM_LEFT_AND_DOWN(78),
    DOUBLE_CONVEYOR_TO_RIGHT_FROM_UP_AND_DOWN(81),
    DOUBLE_CONVEYOR_TO_DOWN_FROM_RIGHT_AND_LEFT(82),
    DOUBLE_CONVEYOR_TO_LEFT_FROM_UP_AND_DOWN(83),
    DOUBLE_CONVEYOR_TO_UP_FROM_LEFT_AND_RIGHT(84),
    DOUBLE_CONVEYOR_TO_LEFT_FROM_RIGHT_AND_UP(85),
    DOUBLE_CONVEYOR_TO_DOWN_FROM_LEFT_AND_UP(86),

    //Normal conveyor
    NORMAL_CONVEYOR_LEFT_DOWN(33),
    NORMAL_CONVEYOR_UP_LEFT(34),
    NORMAL_CONVEYOR_UP_RIGHT(35),
    NORMAL_CONVEYOR_RIGHT_DOWN(36),
    NORMAL_CONVEYOR_DOWN_RIGHT(41),
    NORMAL_CONVEYOR_RIGHT_UP(42),
    NORMAL_CONVEYOR_LEFT_UP(43),
    NORMAL_CONVEYOR_DOWN_LEFT(44),
    NORMAL_CONVEYOR_UP(49),
    NORMAL_CONVEYOR_DOWN(50),
    NORMAL_CONVEYOR_LEFT(51),
    NORMAL_CONVEYOR_RIGHT(52),

    //Normal merged conveyors
    NORMAL_CONVEYOR_TO_UP_FROM_LEFT_AND_DOWN(57),
    NORMAL_CONVEYOR_TO_RIGHT_FROM_UP_AND_LEFT(58),
    NORMAL_CONVEYOR_TO_DOWN_FROM_RIGHT_AND_UP(59),
    NORMAL_CONVEYOR_TO_LEFT_FROM_DOWN_AND_RIGHT(60),
    NORMAL_CONVEYOR_TO_RIGHT_FROM_UP_AND_DOWN(61),
    NORMAL_CONVEYOR_TO_DOWN_FROM_RIGHT_AND_LEFT(62),
    NORMAL_CONVEYOR_TO_UP_FROM_RIGHT_AND_DOWN(65),
    NORMAL_CONVEYOR_TO_RIGHT_FROM_LEFT_AND_DOWN(66),
    NORMAL_CONVEYOR_TO_DOWN_FROM_LEFT_AND_UP(67),
    NORMAL_CONVEYOR_TO_LEFT_FROM_RIGHT_AND_UP(68),
    NORMAL_CONVEYOR_TO_UP_FROM_RIGHT_AND_LEFT(69),
    NORMAL_CONVEYOR_TO_LEFT_FROM_UP_AND_DOWN(70),

    //Laser (Position) (Shooting Direction)
    LASER_DOWN_UP(37),
    LASER_LEFT_RIGHT(38),
    LASER_BEAM_HORIZONTAL(39),
    LASER_BEAM_CROSS(40),

    LASER_TOP_DOWN(45),
    LASER_RIGHT_LEFT(46),
    LASER_BEAM_VERTICAL(47),
    LASER_BEAM_CRISSCROSS(101),
    LASER_BEAM_PARALLEL_VERTICAL(102),
    LASER_BEAM_PARALLEL_HORIZONTAL(103),

    LASER_DOUBLE_UP(87),
    LASER_DOUBLE_LEFT(93),
    LASER_DOUBLE_DOWN(94),
    LASER_DOUBLE_RIGHT(95),

    //Temporary names
    TEMP1(89),
    TEMP2(90),
    TEMP3(91),
    TEMP4(92),
    TEMP5(97),
    TEMP6(98),
    TEMP7(99),
    TEMP8(100),

    //Pit
    PIT_TOP_LEFT_CORNER(105),
    PIT_TOP(106),
    PIT_TOP_RIGHT(107),
    PIT_RIGHT(108),
    TEMP13(109),
    TEMP14(110),
    PIT_DOWN_LEFT(112),
    PIT_DOWN(113),
    PIT_DOWN_RIGHT(114),
    PIT_LEFT(115),
    TEMP19(116),
    TEMP20(117);

    //56 is blank
    //64 is blank
    //72 is blank
    //80 is blank
    //96 is blank
    //88 is blank
    //104 is blank
    //111 is blank
    //118 is blank
    //125-128 is blank
    //133-136 is irrelevant blank

    private final int tileId;

    //Constructor
    Tiles (int id) {
        this.tileId = id;
    }

    /**
     * returns tileID of tile
     */
    public int getTileID() {
        return tileId;
    }

}
