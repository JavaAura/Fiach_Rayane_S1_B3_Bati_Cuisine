package Models;

import java.util.Date;

public class Quote {
    private double estimatedAmount;
    private Date emissionDate;
    private Date validityDate;
    private boolean accepted;
    private int projectId;

    public Quote(double estimatedAmount, Date emissionDate, Date validityDate, boolean accepted, int projectId) {
        this.estimatedAmount = estimatedAmount;
        this.emissionDate = emissionDate;
        this.validityDate = validityDate;
        this.accepted = accepted;
        this.projectId = projectId;
    }

    public Quote(double estimatedAmount, Date emissionDate, Date validityDate, int projectId) {
        this.estimatedAmount = estimatedAmount;
        this.emissionDate = emissionDate;
        this.validityDate = validityDate;
        this.projectId = projectId;
    }
    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public Date getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "estimatedAmount=" + estimatedAmount +
                ", emissionDate=" + emissionDate +
                ", validityDate=" + validityDate +
                ", accepted=" + accepted +
                ", projectId=" + projectId +
                '}';
    }
}
