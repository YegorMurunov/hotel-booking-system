/**
 * Перерахування доступних категорій номерів та їхньої базової вартості за добу.
 */
public enum RoomType implements Room {
    STANDARD(100.0),
    DELUXE(250.0),
    SUITE(500.0),
    PRESIDENT(1200.0);

    private final double basePrice;

    RoomType(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }
}