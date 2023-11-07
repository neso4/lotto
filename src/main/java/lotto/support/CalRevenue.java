package lotto.support;

import java.util.HashMap;
import java.util.List;

public class CalRevenue {
    private final List<Rate> result;

    private final HashMap<String, Integer> count = new HashMap<>();

    private double revenue;

    public CalRevenue(List<Rate> result) {
        this.result = result;

    }

    public void cal() {
        Long firstMoney = result.size() * 1000L;
        Long sum = 0L;
        for (Rate now : result) {
            sum += now.getPrize();
            count.put(now.getRank(), count.getOrDefault(now.getRank(), 0) + 1);
        }
        this.revenue = (double) sum / firstMoney;
    }

    public HashMap<String, Integer> getCount() {
        return this.count;
    }

    public double getRevenue() {
        return this.revenue;
    }


}
