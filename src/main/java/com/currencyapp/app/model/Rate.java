package com.currencyapp.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Rate {
    @NotBlank
    private String currency;
    @NotBlank
    private String code;
    @NotNull
    private Double bid;
    @NotNull
    private Double ask;

    public Rate(String currency, String code, Double bid, Double ask) {
        this.currency = currency;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
    }

    public Rate() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        sb.append("currency='").append(currency).append('\'');
        sb.append(", code='").append(code).append('\'');
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
        return Objects.equals(currency, rate.currency) &&
                Objects.equals(code, rate.code) &&
                Objects.equals(bid, rate.bid) &&
                Objects.equals(ask, rate.ask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, code, bid, ask);
    }
}
