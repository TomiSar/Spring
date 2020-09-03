package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
class BookingCommandLineRunner implements CommandLineRunner {

    @Autowired BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
        StringBuilder sbBookings = new StringBuilder();
        sbBookings.append(("\n*---------------Bookings---------------*")).append("\n");

        for (Booking booking: this.bookingRepository.findAll()) {
            sbBookings.append(booking.toString()).append("\n");
        }

        System.out.println(sbBookings);
    }
}

//RestController that send bookings in the Browser (http://localhost:8080/bookings)
@RestController
class BookingRestController {

    @Autowired BookingRepository bookingRepository;

    @RequestMapping("/bookings")
    Collection<Booking> bookings () {
        return this.bookingRepository.findAll();
    }
}

//JPARepository that uses Booking class Entities type Booking, id Long
interface BookingRepository extends JpaRepository<Booking, Long> {

    Collection<Booking> findByBookingName(String bookingName);
}

//JPA Entity and Class to add Booking
@Entity
class Booking {

    @Id //Each JPA entity must have a primary key which uniquely identifies it. The @Id annotation defines the primary key.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookingName;

    public Booking(String bookingName) {
        super();
        this.bookingName = bookingName;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    @Override
    public String toString() {
        return "Booking [ id =" + id + ", bookingName = " + bookingName + " ]";
    }
}
