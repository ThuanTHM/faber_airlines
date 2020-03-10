package com.example.restservice.service;

import com.example.restservice.entity.Seat;
import com.example.restservice.exception.RecordNotFoundException;
import com.example.restservice.jpa.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service("seatService")
public class SeatServiceImpl implements SeatService {

    static Logger logger = Logger.getLogger(SeatServiceImpl.class.getName());

    @Autowired
    private SeatRepository seatRepository;

    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void delete(Long id) throws RecordNotFoundException {
        Optional<Seat> seat = seatRepository.findById(id);

        if (seat.isPresent()) {
            seatRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }
    }

    @Override
    public Seat findById(Long id) throws RecordNotFoundException {
        Optional<Seat> seat = seatRepository.findById(id);

        if (seat.isPresent()) {
            return seat.get();
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }
    }

    public Collection<Seat> findAll() {
        return seatRepository.findAll();
    }

    public Page<Seat> findAll(PageRequest p) {
        return seatRepository.findAll(p);
    }

    @Override
    public Seat createOrUpdate(Seat seat) {
        if (seat.getId() == null) {
            seat = seatRepository.save(seat);
            return seat;
        } else {
            Optional<Seat> curFilght = seatRepository.findById(seat.getId());//current data
            if (curFilght.isPresent()) {
                Seat newEntity = curFilght.get();
                newEntity.setSeatRank(seat.getSeatRank());
                newEntity.setTotalNum(seat.getTotalNum());
                newEntity.setAdultPrice(seat.getAdultPrice());
                newEntity.setChildPrice(seat.getChildPrice());
                newEntity.setInfantPrice(seat.getInfantPrice());
                newEntity = seatRepository.save(newEntity);
                return newEntity;
            } else {
                seat = seatRepository.save(seat);
                return seat;
            }
        }
    }

}
