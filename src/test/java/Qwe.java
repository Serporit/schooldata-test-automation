import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Qwe {
    public static void main(String[] args) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream("src/test/resources/listCreationConfigs/1.yml");
        Map<String, Object> obj = yaml.load(inputStream);
//        ListCreationParameters2 parameters = yaml.load(inputStream);
//        System.out.println(parameters);
        System.out.println(obj);
    }
}
