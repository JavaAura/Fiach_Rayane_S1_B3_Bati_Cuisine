package Repository.Interfaces;

import Models.Quote;

public interface QuoteRepository {
    void addQuote(Quote quote);
    void updateQuoteStatus(int projectId, boolean status);

}
