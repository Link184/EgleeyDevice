package environment.unit.resolver;

import environment.unit.container.ContainerInterface;
import environment.unit.tree_builder.TreeBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ResolverInterface
{
    /**
     * prefix to identify resolved options
     *
     * @return String
     */
    String getPrefix();

    /**
     * postfix to identify resolved options
     *
     * @return String
     */
    String getPostfix();

    /**
     * @return ResolverInterface
     */
    ResolverInterface setContainer(ContainerInterface container);

    /**
     * build config tree;
     *
     * @param treeBuilder TreeBuilder
     * @return TreeBuilder
     */
    TreeBuilder buildConfigTree(TreeBuilder treeBuilder) throws Exception;

    /**
     * @param entry LinkedHashMap
     */
    Object resolve(Map.Entry entry) throws Exception;

    /**
     * callback after resolve apply
     */
    void done(LinkedHashMap instances);

    /**
     * @throws Exception
     */
    void resolve() throws Exception;
}
