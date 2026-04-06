package com.servixo.dto.user;

import java.util.Date;

public class BookingDto {

    private Long id;
    private Long userId;
    private Long serviceId;
    private Date bookingDate;
    private String status;

    public BookingDto() {}

    public BookingDto(Long id, Long userId, Long serviceId, Date bookingDate, String status) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}