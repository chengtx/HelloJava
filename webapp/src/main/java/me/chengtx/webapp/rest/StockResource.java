package me.chengtx.webapp.rest;

import me.chengtx.webapp.model.Stock;
import me.chengtx.webapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * User resource (exposed at "stock" path)
 */
@Component
@Path("/stocks")
public class StockResource {

    @Autowired
    private StockService stockService;

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getFaviroute() {

        List<String> stockList = new ArrayList<String>() {
            {
                add("sh600153");
                add("sh600737");
                add("sh600489");
            }
        };
        List<Stock> result = new ArrayList<>();
        stockList.forEach(s -> {
                    result.add(stockService.getStock(s));
                }
        );
        return result;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Stock getStock(@PathParam("id") String id) {
        Stock stock = stockService.getStock(id);
        return stock;
    }
}
