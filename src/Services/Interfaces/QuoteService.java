package Services.Interfaces;



public interface QuoteService {
    void addQuote(int projectId, String emissionDate, String validityDate, double estimatedAmount);
}
