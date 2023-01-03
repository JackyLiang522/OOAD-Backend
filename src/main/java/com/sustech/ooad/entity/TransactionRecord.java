package com.sustech.ooad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "transaction_record")
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int remain;

    private int change;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "client_id",
            nullable = false,
            referencedColumnName = "id")
    private Client client;


    private Date date;

    private String courseName;

    public TransactionRecord() {

    }

    public TransactionRecord(int remain, int change, Client client, Date date, String courseName) {
        this.remain = remain;
        this.change = change;
        this.client = client;
        this.date = date;
        this.courseName = courseName;
    }
}
