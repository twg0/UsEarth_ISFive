package com.isfive.usearth.domain.project.service;

import com.isfive.usearth.domain.project.entity.Option;
import com.isfive.usearth.domain.project.entity.OptionValue;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.repository.OptionRepository;
import com.isfive.usearth.domain.project.repository.OptionValueRepository;
import com.isfive.usearth.domain.project.repository.RewardSkuRepository;
import com.isfive.usearth.domain.project.repository.SkuValueRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OptionService {

    private final RewardSkuRepository rewardSkuRepository;
    private final SkuValueRespository skuValueRespository;
    private final OptionRepository optionRepository;
    private final OptionValueRepository optionValueRepository;

    public List<Option> convertOption(Map<String, String> options, Reward reward) {
        List<Option> optionList = new ArrayList<>();

        for (Map.Entry<String, String> pair : options.entrySet()) {
            // 옵션 이름 저장
            Option option = createOption(pair.getKey());
            optionRepository.save(option);

            // 옵션 값 리스트 저장
            List<OptionValue> optionValueList = createOptionValue(convertOptionValueStrToList(pair.getValue()));
            for (OptionValue optionValue : optionValueList)
                option.addOptionValue(optionValue);
            optionList.add(option);
            optionRepository.save(option);
        }
        optionRepository.saveAll(optionList);

        return optionList;
    }

    public List<String> convertOptionValueStrToList(String optionStr) {
        optionStr = optionStr.replace("\"", "");
        List<String> optionList = Arrays.stream(optionStr.split(","))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());
        return optionList;
    }

    @Transactional
    public List<OptionValue> createOptionValue(List<String> optionValues) {
        List<OptionValue> optionValueList = new ArrayList<>();
        for (String value : optionValues) {
            OptionValue optionValue = OptionValue.builder()
                    .value(value)
                    .build();
            optionValueList.add(optionValue);
        }
        optionValueRepository.saveAll(optionValueList);
        return optionValueList;
    }

    @Transactional
    public Option createOption(String name) {
        Option option = Option.builder()
                .name(name)
                .build();
        optionRepository.save(option);
        return option;
    }

}
