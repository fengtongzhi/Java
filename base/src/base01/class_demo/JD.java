package base01.class_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JD implements Switch {

    private String name;
    private boolean status;

    @Override
    public void press() {
        status=!status;
    }
}
