package com.currencyapp.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class JsonRateModel {
    @NotBlank
    private String table;
    @NotBlank
    private String no;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate tradingDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate effectiveDate;
    private List<Rate> rates;

    public JsonRateModel(String table, String no, LocalDate tradingDate,
                         LocalDate effectiveDate, List<Rate> rates) {
        this.table = table;
        this.no = no;
        this.tradingDate = tradingDate;
        this.effectiveDate = effectiveDate;
        this.rates = rates;
    }

    public JsonRateModel() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JsonRateModel{");
        sb.append("table='").append(table).append('\'');
        sb.append(", no='").append(no).append('\'');
        sb.append(", tradingDate=").append(tradingDate);
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", rates=").append(rates);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonRateModel)) return false;
        JsonRateModel that = (JsonRateModel) o;
        return Objects.equals(table, that.table) &&
                Objects.equals(no, that.no) &&
                Objects.equals(tradingDate, that.tradingDate) &&
                Objects.equals(effectiveDate, that.effectiveDate) &&
                Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, no, tradingDate, effectiveDate, rates);
    }
}
