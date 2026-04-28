import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тестування логіки Booking на основі методу функціональних діаграм (причинно-наслідкових зв'язків).
 */
public class BookingCauseEffectTest {

    private final Room standardRoom = RoomType.STANDARD; // Припускаємо базову ціну 100.0 за добу

    /**
     * Тест зв'язку: Причина 1 -> Наслідок 20 (Тотожність)
     * Якщо Високий сезон (1), то застосовується націнка 20% (20).
     */
    @Test
    public void testEffect20_HighSeasonIdentity() {
        // Умови: isHighSeason = true, VIP = true, days = 15 (VIP і дні мають ігноруватися)
        Booking booking = new Booking(standardRoom, 15, true, true);

        // Базова ціна: 1500. Націнка 20%: 1500 * 1.2 = 1800.0
        assertEquals(1800.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест зв'язку: Заперечення 1 AND Причина 2 -> Наслідок 21 (Кон'юнкція)
     * Якщо НЕ Високий сезон ТА є VIP, застосовується знижка 10%.
     */
    @Test
    public void testEffect21_VipDiscountConjunction() {
        // Умови: isHighSeason = false, VIP = true, days = 10 
        Booking booking = new Booking(standardRoom, 10, false, true);

        // Базова ціна: 1000. Знижка 10%: 1000 * 0.9 = 900.0
        assertEquals(900.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест зв'язку: Заперечення 1 AND Причина 3 -> Наслідок 22 (Кон'юнкція)
     * Якщо НЕ Високий сезон ТА днів > 14, застосовується знижка 5%.
     */
    @Test
    public void testEffect22_LongStayDiscountConjunction() {
        // Умови: isHighSeason = false, VIP = false, days = 15
        Booking booking = new Booking(standardRoom, 15, false, false);

        // Базова ціна: 1500. Знижка 5%: 1500 * 0.95 = 1425.0
        assertEquals(1425.0, booking.calculatePrice(), 0.01);
    }

    /**
     * Тест зв'язку: Заперечення 1 AND Заперечення 2 AND Заперечення 3 -> Наслідок 23
     * Якщо жодна умова знижки/націнки не виконується, повертається базова ціна.
     */
    @Test
    public void testEffect23_BasePriceAllNegations() {
        // Умови: isHighSeason = false, VIP = false, days = 10
        Booking booking = new Booking(standardRoom, 10, false, false);

        // Базова ціна: 1000 * 1.0 = 1000.0
        assertEquals(1000.0, booking.calculatePrice(), 0.01);
    }
}