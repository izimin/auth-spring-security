package org.psu.auth.model.extension;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.Setter;

        import java.util.Map;

@AllArgsConstructor(staticName="of")
@Getter
@Setter
public class Base<T> {
    private String status;
    private Map<String, Result<T>> results;
    private AbstractProblem problem;
}
