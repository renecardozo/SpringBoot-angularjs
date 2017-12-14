package nekocoder.bookingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {
    private BookingRepository bookingRepository;

    @Autowired
    public BookingController( BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<HotelBooking> getAll() {
        return this.bookingRepository.findAll();
    }

    @RequestMapping( value = "/affordable/{price}", method = RequestMethod.GET)
    public List<HotelBooking> getAffordable(@PathVariable double price) {
        return this.bookingRepository.findByPricePerNightLessThan(price);
    }

    @RequestMapping( value = "/create" , method = RequestMethod.POST)
    public List<HotelBooking> create( @RequestBody HotelBooking hotelBooking ) {
        this.bookingRepository.save(hotelBooking);
        return this.bookingRepository.findAll();
    }

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.POST)
    public List<HotelBooking> remove( @PathVariable long id) {
        this.bookingRepository.delete(id);
        return this.bookingRepository.findAll();
    }
}
