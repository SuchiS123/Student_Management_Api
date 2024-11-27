package com.student.students.ErrorDetails;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private String message;
    private Date date;
    private String request;

    public ErrorDetails(String message, Date date, String request) {
        this.message = message;
        this.date = date;
        this.request = request;
    }
}
