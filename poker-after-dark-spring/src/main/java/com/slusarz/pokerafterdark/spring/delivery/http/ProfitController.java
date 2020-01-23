package com.slusarz.pokerafterdark.spring.delivery.http;

import com.slusarz.pokerafterdark.application.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.profit.ProfitQueryResult;
import com.slusarz.pokerafterdark.specification.api.GetProfitRequest;
import com.slusarz.pokerafterdark.specification.api.GetProfitResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.GetProfitsMapper;
import com.slusarz.pokerafterdark.spring.delivery.request.QueryRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProfitController.PROFIT_PATH)
public class ProfitController {

    final static String PROFIT_PATH = "profit";

    @Autowired
    private ServiceExecutor serviceExecutor;

    @Autowired
    private GetProfitsMapper getPlayersMapper;

    @PostMapping
    public GetProfitResponse getProfit(@RequestBody GetProfitRequest request) {
        return new QueryRequestHandler<GetProfitResponse, GetProfitRequest, ProfitQueryResult>
                (serviceExecutor, getPlayersMapper).handle(request);
    }
}
