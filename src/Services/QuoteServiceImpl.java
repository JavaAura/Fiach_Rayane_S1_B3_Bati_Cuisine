package Services;

import Models.Quote;
import Repository.Interfaces.QuoteRepository;
import Repository.QuoteRepositoryImpl;
import Services.Interfaces.QuoteService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;

    public QuoteServiceImpl() {
        this.quoteRepository = new QuoteRepositoryImpl();
    }

    @Override
    public void addQuote(int projectId, String emissionDate, String validityDate, double estimatedAmount) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date emission = formatter.parse(emissionDate);
            Date validity = formatter.parse(validityDate);

            Quote quote = new Quote(estimatedAmount, emission, validity, projectId);
            quoteRepository.addQuote(quote);
        } catch (ParseException e) {
            System.out.println("Erreur lors du formatage des dates.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuoteStatus(int projectId, boolean status) {
        quoteRepository.updateQuoteStatus(projectId, status);
    }


}
