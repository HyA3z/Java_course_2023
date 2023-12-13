package analysis;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    // Absolute path
    private static final String CSV_FILE_PATH = "/Users/ziyangchen/IdeaProjects/untitled4/src/main/resources/transactions.csv";

    @Async
    public CompletableFuture<List<Transaction>> readTransactions() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            return CompletableFuture.completedFuture(
                    br.lines()
                            .skip(1)
                            .map(this::mapToTransaction)
                            .collect(Collectors.toList())
            );
        }
    }

    private Transaction mapToTransaction(String line) {
        String[] values = line.split(",");
        return new Transaction(
                Long.parseLong(values[0]),
                values[1],
                Double.parseDouble(values[2]),
                values[3],
                Long.parseLong(values[4])
        );
    }

    @Async
    public CompletableFuture<Map<String, Double>> filtering(List<Transaction> transactions, double threshold) {
        return CompletableFuture.completedFuture(
                transactions.stream()
                        .filter(transaction -> transaction.getAmount() <= threshold)
                        .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)))
        );
    }

    @Async
    public CompletableFuture<Map<String, Double>> Grouping(List<Transaction> transactions) {
        return CompletableFuture.completedFuture(
                transactions.stream()
                        .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)))
        );
    }

    @Async
    public CompletableFuture<Double> Aggregation(List<Transaction> transactions, String type) {
        return CompletableFuture.completedFuture(
                transactions.stream()
                        .filter(transaction -> transaction.getType().equals(type))
                        .mapToDouble(Transaction::getAmount)
                        .average()
                        .orElse(0.0)
        );
    }

    @Async
    public CompletableFuture<Map<String, Long>> Unique(List<Transaction> transactions) {
        return CompletableFuture.completedFuture(
                transactions.stream()
                        .collect(Collectors.groupingBy(Transaction::getType, Collectors.mapping(Transaction::getUserId, Collectors.counting())))
        );
    }
}
