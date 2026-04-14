/**
 * Клас, що представляє сутність бронювання.
 * Містить усю бізнес-логіку для розрахунку вартості проживання.
 */

public class Booking {
    private final Room room; // Об'єкт номера, який бронюється
    private final int days; // Тривалість проживання в днях, не може бути менше 0 та більше 365
    private final boolean isHighSeason; // Ознака "високого сезону"
    private final boolean isVipGuest; // Ознака VIP-статусу клієнта


    // Конструктор з валідацією вхідних даних
    public Booking(Room room, int days, boolean isHighSeason, boolean isVipGuest) {
        if (days <= 0 || days > 365) {
            throw new IllegalArgumentException("Days must be between 1 and 365");
        }

        this.room = room;
        this.days = days;
        this.isHighSeason = isHighSeason;
        this.isVipGuest = isVipGuest;
    }

    /**
     * Головний метод розрахунку фінальної вартості бронювання.
     */
    public double calculatePrice() {
        // Базова вартість: ціна за добу * кількість днів
        double price = room.getBasePrice() * days;

        // Застосування націнок та знижок
        price = applySeasonMultiplier(price);

        if (!isHighSeason) {
            price = applyDiscounts(price);
        }

        return price;
    }

    /**
     * Застосовує націнку, якщо бронювання припадає на високий сезон.
     */
    public double applySeasonMultiplier(double currentPrice) {
        // Якщо гарячий сезон, то застосовується націнка на базову вартість
        if (isHighSeason) {
            return currentPrice * 1.2;
        }
        return currentPrice;
    }

    /**
     * Розраховує та застосовує всі можливі знижки до поточної вартості.
     */
    public double applyDiscounts(double currentPrice) {
        double discountMultiplier = 1.0;

        // Якщо vip клієнт, то діє знижка в 10%
        if (isVipGuest) {
            discountMultiplier -= 0.1;
        }
        // Якщо кількість днів проживання більше 14, то діє знижка в 5 відсотків
        if (days > 14) {
            discountMultiplier -= 0.05;
        }
        return currentPrice * discountMultiplier;
    }
}