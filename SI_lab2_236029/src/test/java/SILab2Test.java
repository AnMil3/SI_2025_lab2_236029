import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    SILab2 siLab2 = new SILab2();

    @Test
    void checkCartEveryStatement() {
        // Тест 1: allItems = null, cardNumber = '' - 1, 2, 22
        RuntimeException exception;
        exception = assertThrows(RuntimeException.class, () -> siLab2.checkCart(null, ""));
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));
        // Тест 2: allItems = [{name='', quantity=x, price=x, discount=x}], cardNumber = '' - 1, 3, 4.1, 4.2, 5, 6, 7, 22
        List<Item> allItems = new LinkedList<>();
        allItems.add(new Item("", 0, 0, 0));
        exception = assertThrows(RuntimeException.class, () -> siLab2.checkCart(allItems, ""));
        assertTrue(exception.getMessage().contains("Invalid item!"));
        // Тест 3: allItems = [{name='Apple', quantity=1, price=1, discount=0.1}], cardNumber = '' - 1, 3, 4.1, (4.2, 5, 6, 8, 9, 10, 11, 4.3), 13, 20, 22
        List<Item> allItems2 = new LinkedList<>();
        allItems2.add(new Item("Apple", 1, 1, 0.1));
        exception = assertThrows(RuntimeException.class, () -> siLab2.checkCart(allItems2, ""));
        assertTrue(exception.getMessage().contains("Invalid card number!"));
        // Тест 4: allItems = [{name='Apple', quantity=1, price=1, discount=0}], cardNumber = '123a456789123456' - 1, 3, 4.1, (4.2, 5, 6, 8, 10, 12, 4.3), 13, 14, 15, 16.1, (16.2, 17, 18, 16.3), 19, 22
        List<Item> allItems3 = new LinkedList<>();
        allItems3.add(new Item("Apple", 1, 1, 0));
        exception = assertThrows(RuntimeException.class, () -> siLab2.checkCart(allItems3, "123a456789123456"));
        assertTrue(exception.getMessage().contains("Invalid character in card number!"));
        // Тест 5: allItems = [{name='Apple', quantity=1, price=1, discount=0}], cardNumber = '1234567891234567' - 1, 3, 4.1, (4.2, 5, 6, 8, 10, 12, 4.3), 13, 14, 15, 16.1, (16.2, 17, 18, 16.3), 21, 22
        List<Item> allItems4 = new LinkedList<>();
        allItems4.add(new Item("Apple", 1, 1, 0));
        assertEquals(1, siLab2.checkCart(allItems4, "1234567891234567"));
    }

    @Test
    void checkCartMultipleCondition() {
        // TXX: allItems = [{name='Apple', quantity=0, price=310, discount=0}]
        List<Item> allItems = new LinkedList<>();
        allItems.add(new Item("Apple", 0, 310, 0));
        assertEquals(-30, siLab2.checkCart(allItems, "1234567891234567"));
        // FTX: allItems = [{name='Apple', quantity=0, price=1, discount=0.1}]
        List<Item> allItems2 = new LinkedList<>();
        allItems2.add(new Item("Apple", 0, 1, 0.1));
        assertEquals(-30, siLab2.checkCart(allItems2, "1234567891234567"));
        // FFF: allItems = [{name='Apple', quantity=0, price=0, discount=0}]
        List<Item> allItems3 = new LinkedList<>();
        allItems3.add(new Item("Apple", 0, 0, 0));
        assertEquals(0, siLab2.checkCart(allItems3, "1234567891234567"));
        // FFT: allItems = [{name='Apple', quantity=15, price=0, discount=0}]
        List<Item> allItems4 = new LinkedList<>();
        allItems4.add(new Item("Apple", 15, 0, 0));
        assertEquals(-30, siLab2.checkCart(allItems4, "1234567891234567"));
    }
}
