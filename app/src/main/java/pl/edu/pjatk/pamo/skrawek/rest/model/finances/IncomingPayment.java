package pl.edu.pjatk.pamo.skrawek.rest.model.finances;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Model class - used when calling REST API
 */
public class IncomingPayment {
    private PaymentType paymentType;
    private String transactionDate;
    private String contractorDetails;
    private String title;
    private BigDecimal transactionAmount;
    private String transactionCurrency;
    private UUID childId;
    private UUID guardianId;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getContractorDetails() {
        return contractorDetails;
    }

    public void setContractorDetails(String contractorDetails) {
        this.contractorDetails = contractorDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public UUID getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(UUID guardianId) {
        this.guardianId = guardianId;
    }


    @Override
    public String toString() {
        return "IncomingPayment{" +
                "paymentType=" + paymentType +
                ", transactionDate=" + transactionDate +
                ", contractorDetails='" + contractorDetails + '\'' +
                ", title='" + title + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionCurrency='" + transactionCurrency + '\'' +
                ", childId=" + childId +
                ", guardianId=" + guardianId +
                '}';
    }
}
