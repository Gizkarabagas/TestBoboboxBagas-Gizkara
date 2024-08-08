package config.global;

import lombok.Getter;
import lombok.Setter;

public class FlowControl {
    @Getter @Setter
    private static boolean continueFlow,strictFlow;
}
