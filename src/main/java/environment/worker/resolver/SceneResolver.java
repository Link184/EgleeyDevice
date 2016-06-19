package environment.worker.resolver;

import environment.unit.resolver.AbstractResolver;
import scene.scenes.SceneInterface;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

public class SceneResolver extends AbstractResolver
{
    public Object map(Map.Entry entry) throws Exception
    {
        LinkedHashMap current = (LinkedHashMap) entry.getValue();
        Constructor<?> cons = Class
            .forName((String) current.get("class"))
            .getConstructor();

        Object obj;

        if (!((obj = cons.newInstance()) instanceof SceneInterface)) {
            throw new Exception("Scene class must implement SceneInterface");
        }

        return obj;
    }

    /**
     * prefix to identify resolved options
     *
     * @return String
     */
    public String getPrefix()
    {
        return "@";
    }

    /**
     * @return String
     */
    public String getProperty()
    {
        return "scenes";
    }
}