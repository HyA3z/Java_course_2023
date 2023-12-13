package analysis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/financial")
public class FinancialPlatformController {

    private final TransactionService transactionService;

    public FinancialPlatformController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/Analysis")
    public String Analysis(Model model) {
        try {
            CompletableFuture<List<Transaction>> transactionsFuture = transactionService.readTransactions();
            CompletableFuture<Map<String, Double>> filteredTransactionsFuture = transactionService.filtering(transactionsFuture.join(), 100.0);
            CompletableFuture<Map<String, Double>> groupedTransactionsFuture = transactionService.Grouping(transactionsFuture.join());
            CompletableFuture<Double> averageAmountFuture = transactionService.Aggregation(transactionsFuture.join(), "Restaurant bill");
            CompletableFuture<Map<String, Long>> uniqueUsersCountFuture = transactionService.Unique(transactionsFuture.join());

            // Wait for all CompletableFuture to complete
            CompletableFuture.allOf(transactionsFuture, filteredTransactionsFuture, groupedTransactionsFuture, averageAmountFuture, uniqueUsersCountFuture).join();

            model.addAttribute("allTransactions", transactionsFuture.get());
            model.addAttribute("filteredTransactions", filteredTransactionsFuture.get());
            model.addAttribute("groupedTransactions", groupedTransactionsFuture.get());
            model.addAttribute("averageAmount", averageAmountFuture.get());
            model.addAttribute("uniqueUsersCount", uniqueUsersCountFuture.get());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while processing the transactions.");
        }

        return "results";
    }

}
