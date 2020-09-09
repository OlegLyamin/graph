package com.graph.config;

import com.graph.model.dto.request.EdgeRequestDTO;
import com.graph.model.dto.response.EdgeResponseDTO;
import com.graph.model.dto.response.GraphResponseDTO;
import com.graph.model.entity.Edge;
import com.graph.model.entity.Graph;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }


}
