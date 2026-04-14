import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Клас для модульного тестування бізнес-логіки розрахунку вартості бронювання.
 * Тестові сценарії побудовані на основі мінімізованої таблиці рішень (Лабораторна робота №3).
 */
public class BookingTest {

    // Для зручності розрахунків візьмемо кімнату з базовою ціною 100.0
    // Базова ціна за 10 днів = 1000.0, за 15 днів = 1500.0
    private final Room standardRoom = RoomType.STANDARD;

    /**
     * Тест-кейс 1: Звичайний сезон, без статусу VIP, коротке проживання (<= 14 днів).
     * Очікуваний результат: застосовується базова ціна без націнок та знижок (коефіцієнт 1.0).
     */
    @Test
    public void testCase1_NoDiscounts_BasePrice() {
        Booking booking = new Booking(standardRoom, 10, false, false);
        assertEquals(1000.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест-кейс 2: Звичайний сезон, без статусу VIP, довготривале проживання (> 14 днів).
     * Очікуваний результат: застосовується лише знижка за тривалість у розмірі 5%.
     */
    @Test
    public void testCase2_LongStayDiscountOnly() {
        Booking booking = new Booking(standardRoom, 15, false, false);
        // Базова: 1500.0 | Знижка 5%: 1500.0 * 0.95 = 1425.0
        assertEquals(1425.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест-кейс 3: Звичайний сезон, VIP-гість, коротке проживання (<= 14 днів).
     * Очікуваний результат: застосовується лише персональна знижка VIP у розмірі 10%.
     */
    @Test
    public void testCase3_VipDiscountOnly() {
        Booking booking = new Booking(standardRoom, 10, false, true);
        // Базова: 1000.0 | Знижка 10%: 1000.0 * 0.90 = 900.0
        assertEquals(900.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест-кейс 4: Звичайний сезон, VIP-гість, довготривале проживання (> 14 днів).
     * Очікуваний результат: знижки комбінуються, загальна знижка становить 15%.
     */
    @Test
    public void testCase4_VipAndLongStayDiscount() {
        Booking booking = new Booking(standardRoom, 15, false, true);
        // Базова: 1500.0 | Знижка 15%: 1500.0 * 0.85 = 1275.0
        assertEquals(1275.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест-кейс 5: Високий сезон. Перевірка правила мінімізації таблиці рішень (умови "X").
     * Очікуваний результат: статус VIP та тривалість проживання ігноруються.
     * Застосовується виключно націнка високого сезону у розмірі 20%.
     */
    @Test
    public void testCase5_HighSeasonOverridesDiscounts() {
        Booking booking = new Booking(standardRoom, 15, true, true);
        // Базова: 1500.0 | Націнка 20%: 1500.0 * 1.2 = 1800.0 | Знижки: незастосовуються
        assertEquals(1800.0, booking.calculatePrice(), 0.01);
    }
}