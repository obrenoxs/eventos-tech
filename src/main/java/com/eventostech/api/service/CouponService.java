package com.eventostech.api.service;

import com.eventostech.api.domain.coupon.Coupon;
import com.eventostech.api.domain.coupon.CouponRequestDTO;
import com.eventostech.api.domain.event.Event;
import com.eventostech.api.repositories.CouponRepository;
import com.eventostech.api.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    public CouponService(CouponRepository couponRepository, EventRepository eventRepository) {
        this.couponRepository = couponRepository;
        this.eventRepository = eventRepository;
    }

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(new Date(couponData.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }
}
