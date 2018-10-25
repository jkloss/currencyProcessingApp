package com.currencyapp.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    @NotBlank
    private String no;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate effectiveDate;
    @NotNull
    private Double bid;
    @NotNull
    private Double ask;

    public Rate(String no, LocalDate effectiveDate, Double bid, Double ask) {
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
    }

    public Rate() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Rate{");
        sb.append("no='").append(no).append('\'');
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", bid=").append(bid);
        sb.append(", ask=").append(ask);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;
        Rate rate = (Rate) o;
        return Objects.equals(no, rate.no) &&
                Objects.equals(effectiveDate, rate.effectiveDate) &&
                Objects.equals(bid, rate.bid) &&
                Objects.equals(ask, rate.ask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, effectiveDate, bid, ask);
    }
}
