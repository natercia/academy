package com.ctw.workstation.booking.entity;

import com.ctw.workstation.BuildableObject;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingIdGenerator")
    @SequenceGenerator(name = "bookingIdGenerator", sequenceName = "SEQ_BOOKING_ID")
    private Long id;
    @Column(name = "rack_id")
    private Long rackId;
    @Column(name = "requester_id")
    private Long requesterId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "book_from", nullable = false)
    private LocalDateTime bookFrom;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "book_to")
    private LocalDateTime bookTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", updatable = false, insertable = false, nullable = false)
    private Rack requested;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", updatable = false, insertable = false, nullable = false)
    public TeamMember requester;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private LocalDateTime modified_at;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    private Booking(Booking.BookingBuilder builder) {
        this.id = builder.id;
        this.rackId = builder.rackId;
        this.requesterId= builder.requesterId;
        this.bookFrom = builder.bookFrom;
        this.bookTo = builder.bookTo;

    }

    public Booking() {

    }

    public static class BookingBuilder {
        private Long id;
        private Long rackId;
        private Long requesterId;
        private LocalDateTime bookFrom;
        private LocalDateTime bookTo;

        public BookingBuilder() {
        }

        public BookingBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public Booking.BookingBuilder setRackId(Long rackId) {
            this.rackId = rackId;
            return this;
        }

        public Booking.BookingBuilder setRequesterId(Long requesterId) {
            this.requesterId = requesterId;
            return this;
        }

        public Booking.BookingBuilder setBookFrom(LocalDateTime bookFrom) {
            this.bookFrom = bookFrom;
            return this;
        }

        public Booking.BookingBuilder setBookTo(LocalDateTime bookTo) {
            this.bookTo = bookTo;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Long getRackId() {
        return rackId;
    }
    public Long getRequesterId() {
        return requesterId;
    }
    public LocalDateTime getBookFrom() {
        return bookFrom;
    }
    public LocalDateTime getBookTo() {
        return bookTo;
    }
    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }
    public void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }
    public void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setModifiedAt(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public BookingReturnDTO toDTO(){
        return new BookingReturnDTO(this.getId(), this.getRackId(), this.getRequesterId(), this.getBookFrom(), this.getBookTo(),this.getCreated_at(), this.getModified_at());
    }
}
