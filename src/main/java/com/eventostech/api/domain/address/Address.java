package com.eventostech.api.domain.address;

import com.eventostech.api.domain.event.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @OneToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;
}
