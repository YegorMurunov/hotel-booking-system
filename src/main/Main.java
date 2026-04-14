public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрація системи розрахунку бронювання ===\n");

        // Сценарій 1: Звичайне бронювання
        // Кімната Standard (100.0/доба), 5 днів, звичайний сезон, звичайний гість.
        // Очікуваний розрахунок: 100 * 5 = 500.0
        Booking normalBooking = new Booking(RoomType.STANDARD, 5, false, false);
        System.out.println("1. Звичайне бронювання (Standard, 5 днів):");
        System.out.println("До сплати: $" + normalBooking.calculatePrice() + "\n");

        // Сценарій 2: Високий сезон + VIP клієнт
        // Кімната Suite (500.0/доба), 3 дні, високий сезон (+20%), VIP-гість (-10%).
        // Базова ціна: 500 * 3 = 1500.
        // Високий сезон: 1500 * 1.2 = 1800.
        // Знижка VIP: Знижка не застосовується, тому що високий сезон
        Booking vipBooking = new Booking(RoomType.SUITE, 3, true, true);
        System.out.println("2. Бронювання VIP у високий сезон (Suite, 3 дні):");
        System.out.println("До сплати: $" + vipBooking.calculatePrice() + "\n");

        // Сценарій 3: Довготривале проживання
        // Кімната Deluxe (250.0/доба), 15 днів, звичайний сезон, звичайний гість.
        // Базова ціна: 250 * 15 = 3750.
        // Знижка за тривалість > 14 днів: 3750 - 5% = 3562.5
        Booking longStayBooking = new Booking(RoomType.DELUXE, 15, false, false);
        System.out.println("3. Довготривале проживання > 14 днів (Deluxe, 15 днів):");
        System.out.println("До сплати: $" + longStayBooking.calculatePrice() + "\n");

        // Сценарій 4: Перевірка валідації (Граничні значення)
        // Спроба забронювати на від'ємну кількість днів (-2)
        System.out.println("4. Перевірка захисту від некоректних даних:");
        try {
            Booking invalidBooking = new Booking(RoomType.PRESIDENT, -2, false, false);
            invalidBooking.calculatePrice();
        } catch (IllegalArgumentException e) {
            System.out.println("Помилку успішно перехоплено: " + e.getMessage());
        }
    }
}