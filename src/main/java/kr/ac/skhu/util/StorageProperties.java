package kr.ac.skhu.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Manki Kim on 2017-02-19.
 */
@Configuration
@ConfigurationProperties("storage")
@Getter
@Setter
public class StorageProperties {

    @Value("${static.resource.location}")
    private String location;
}
