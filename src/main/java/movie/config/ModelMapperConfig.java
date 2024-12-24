package movie.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(String.class, Long.class).setConverter(
                (MappingContext<String, Long> context) -> {
                    String source = context.getSource();
                    if (source == null || source.isEmpty()) {
                        return null;
                    }
                    try {
                        return Long.parseLong(source);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid format for Long: " + source, e);
                    }
                }
        );

        return modelMapper;
    }
}
