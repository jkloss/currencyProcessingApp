package com.currencyapp.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class Rate {
    @NotBlank
    private String currencyCode;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    public Rate(String currencyCode, LocalDate date) {
        this.currencyCode = currencyCode;
        this.date = date;
    }

    public Rate() {
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Rate{");
        sb.append("currencyCode='").append(currencyCode).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;
        Rate rate = (Rate) o;
        return Objects.equals(currencyCode, rate.currencyCode) &&
                Objects.equals(date, rate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, date);
    }
}
