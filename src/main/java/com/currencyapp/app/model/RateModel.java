package com.currencyapp.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateModel {
    @NotBlank
    private String table;
    @NotBlank
    private String currency;
    @NotBlank
    private String code;
    private List<Rate> rates;

    public RateModel(String table, String currency, String code, List<Rate> rates) {
        this.table = table;
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }

    public RateModel() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RateModel{");
        sb.append("table='").append(table).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", rates=").append(rates);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RateModel)) return false;
        RateModel rateModel = (RateModel) o;
        return Objects.equals(table, rateModel.table) &&
                Objects.equals(currency, rateModel.currency) &&
                Objects.equals(code, rateModel.code) &&
                Objects.equals(rates, rateModel.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, currency, code, rates);
    }
}
