package Cars;

import java.util.Random;

public enum Direction {
    Left,
    Right;

    public static Direction GetRandomGender() {
        Random random = new Random();
        return Direction.values().clone()[random.nextInt(values().length)];
    }
}
