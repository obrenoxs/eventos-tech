package com.eventostech.api.controller;

import com.eventostech.api.domain.coupon.Coupon;
import com.eventostech.api.domain.coupon.CouponRequestDTO;
import com.eventostech.api.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("event/{eventId}")
    public ResponseEntity<Coupon> addCuponsToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO data) {
        Coupon coupons = couponService.addCouponToEvent(eventId, data);
        return ResponseEntity.ok().body(coupons);
    }
}
