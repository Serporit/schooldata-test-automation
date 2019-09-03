package utils;

import bo.ListCreationParameters;
import bo.ListCreationParametersBuilder;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class YamlLoader {
    public static ListCreationParameters loadFromFile(String path) {
        Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            return yaml.load(new FileInputStream("src/test/resources/listCreationConfigs/1.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
