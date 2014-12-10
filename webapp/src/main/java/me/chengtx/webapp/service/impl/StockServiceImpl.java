package me.chengtx.webapp.service.impl;

import me.chengtx.webapp.model.Stock;
import me.chengtx.webapp.service.StockService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 12/10/2014
 */
@Service
public class StockServiceImpl implements StockService {

    public static final Charset CS = Charset.forName("GB2312");

    @Override
    public Stock getStock(String id) {
        String url = "http://hq.sinajs.cn/list=" + id;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), CS));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                String item = line.substring(line.indexOf("\"", 0) + 1, line.length() - 2);
                Stock s = Stock.parseStock(item);
                s.setId(id);
                return s;
            }
        } catch (IOException e) {
        }
        return new Stock();
    }
}
