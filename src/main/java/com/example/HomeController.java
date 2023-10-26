package com.example;

import com.example.binding.RequestBindableBean;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.RequestBean;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;

@Controller("/api")
@Validated
public class HomeController {

    @Get("/echo{?bean*}")
    public HttpResponse<Object> echo(@Valid @RequestBean RequestBindableBean bean) {
        int min = bean.getMin();
        int max = bean.getMax();

        String acctNumber = bean.getAccountNumber();
        String json = String.format("{\"min\": \"%d\",\"max:\":\"%d\",\"accountNumber\":\"%s\"}", min, max, acctNumber);
        return HttpResponse.ok(json);
    }
}
