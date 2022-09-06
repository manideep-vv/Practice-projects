package com.in28minutes.limitsserviceh2configClient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Setter
@Getter
@ConfigurationProperties("limits-service")
@Component
public class ConfigProps {
    String minimum;
    String maximum;

}
