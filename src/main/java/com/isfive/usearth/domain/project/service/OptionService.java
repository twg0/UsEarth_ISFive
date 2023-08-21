package com.isfive.usearth.domain.project.service;

import com.isfive.usearth.domain.project.entity.*;
import com.isfive.usearth.domain.project.repository.OptionRepository;
import com.isfive.usearth.domain.project.repository.OptionValueRepository;
import com.isfive.usearth.domain.project.repository.RewardSkuRepository;
import com.isfive.usearth.domain.project.repository.SkuValueRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final RewardSkuRepository rewardSkuRepository;
    private final SkuValueRespository skuValueRespository;
    private final OptionRepository optionRepository;
    private final OptionValueRepository optionValueRepository;

    public void createOptionValue(String value) {
        OptionValue optionValue = OptionValue.builder()
                .value(value)
                .build();
        optionValueRepository.save(optionValue);
    }

    public void createOption(String name, Reward reward, List<OptionValue> valueList) {
        Option option = Option.builder()
                .name(name)
                .reward(reward)
                .optionValues(valueList)
                .build();
        optionRepository.save(option);
    }

    public void createSkuValue(Option option, OptionValue optionValue) {
        SkuValue skuValue = SkuValue.builder()
                .option(option)
                .optionValue(optionValue)
                .build();
        skuValueRespository.save(skuValue);
    }

    public void createRewardSku(Integer stock, Reward reward, List<SkuValue> skuValueList) {
        RewardSku rewardSku = RewardSku.builder()
                .stock(stock)
                .reward(reward)
                .skuValues(skuValueList)
                .build();
        rewardSkuRepository.save(rewardSku);
    }

}
