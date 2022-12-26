package ru.netology.manager;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.TicketByTimeComparator;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

public class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    TicketByTimeComparator comparator = new TicketByTimeComparator();

    Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 160);
    Ticket ticket2 = new Ticket(2, 230, "SPB", "MSK", 160);
    Ticket ticket3 = new Ticket(3, 360, "MSK", "SMR", 100);
    Ticket ticket4 = new Ticket(4, 330, "MSK", "SPB", 150);
    Ticket ticket5 = new Ticket(5, 500, "MSK", "SPB", 180);
    Ticket ticket6 = new Ticket(6, 390, "LA", "NY", 180);
    Ticket ticket7 = new Ticket(7, 220, "MSK", "SPB", 100);
    Ticket ticket8 = new Ticket(8, 420, "MSK", "SPB", 160);


    @Test
    public void shouldSearchWhenFewTicketsFound() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {ticket1, ticket7, ticket4, ticket8, ticket5};
        Ticket[] actual = manager.findAll("MSK", "SPB");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchWhenOneTicketFound() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {ticket6};
        Ticket[] actual = manager.findAll("LA", "NY");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchWhenTicketNotSuit() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("SMR", "MSK");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTheFastest() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {ticket7, ticket4, ticket1, ticket8, ticket5};
        Ticket[] actual = manager.findAll("MSK", "SPB",comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}
