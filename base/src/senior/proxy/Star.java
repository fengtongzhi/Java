package senior.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Star implements Performance {
    private String name;

    @Override
    public void sing(String name) {
        System.out.println(this.name+" 正在唱歌："+name);
    }

    @Override
    public String dance() {
        System.out.println(this.name+" 正在跳舞");
        return "谢谢！谢谢！";
    }
}
