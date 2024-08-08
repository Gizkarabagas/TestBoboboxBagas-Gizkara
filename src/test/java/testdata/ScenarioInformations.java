package testdata;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

public class ScenarioInformations {
    @Setter @Getter
    private static String scenarioName;
    @Setter @Getter
    private static Collection<String> scenarioTags;
}
