package com.thanmailabs.taskflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
    @Column(name = "street", nullable = false, length = 40)
    private String street;
    @Column(name = "lane_number", nullable = false)
    private Integer laneNumber;
    @Column(name = "city", nullable = false, length = 40)
    private String city;
    @Column(name = "state", nullable = false, length = 40)
    private String state;
    @Column(name = "country", nullable = false, length = 40)
    private String country;
}