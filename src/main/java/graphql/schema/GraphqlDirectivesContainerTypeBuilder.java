package graphql.schema;

import graphql.Internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static graphql.Assert.assertNotNull;

@SuppressWarnings("unchecked")
@Internal
public abstract class GraphqlDirectivesContainerTypeBuilder<B extends GraphqlDirectivesContainerTypeBuilder<B, BASE>, BASE extends GraphqlTypeBuilder<BASE>> extends GraphqlTypeBuilder<BASE> {

    protected final List<GraphQLAppliedDirective> appliedDirectives = new ArrayList<>();
    protected final List<GraphQLDirective> directives = new ArrayList<>();


    public B replaceAppliedDirectives(List<GraphQLAppliedDirective> directives) {
        assertNotNull(directives, () -> "directive can't be null");
        this.appliedDirectives.clear();
        this.appliedDirectives.addAll(directives);
        return (B) this;
    }

    public B withAppliedDirectives(GraphQLAppliedDirective... directives) {
        assertNotNull(directives, () -> "directives can't be null");
        this.appliedDirectives.clear();
        for (GraphQLAppliedDirective directive : directives) {
            withAppliedDirective(directive);
        }
        return (B) this;
    }

    public B withAppliedDirective(GraphQLAppliedDirective directive) {
        assertNotNull(directive, () -> "directive can't be null");
        this.appliedDirectives.add(directive);
        return (B) this;
    }

    public B withAppliedDirective(GraphQLAppliedDirective.Builder builder) {
        return withAppliedDirectives(builder.build());
    }


    public B replaceDirectives(List<GraphQLDirective> directives) {
        assertNotNull(directives, () -> "directive can't be null");
        this.directives.clear();
        this.directives.addAll(directives);
        return (B) this;
    }

    public B withDirectives(GraphQLDirective... directives) {
        assertNotNull(directives, () -> "directives can't be null");
        this.directives.clear();
        for (GraphQLDirective directive : directives) {
            withDirective(directive);
        }
        return (B) this;
    }

    public B withDirective(GraphQLDirective directive) {
        assertNotNull(directive, () -> "directive can't be null");
        this.directives.add(directive);
        return (B) this;
    }

    public B withDirective(GraphQLDirective.Builder builder) {
        return withDirective(builder.build());
    }


    /**
     * This method that allows you to set the legacy {@link GraphQLDirective} on an element as well
     * as the modern {@link GraphQLAppliedDirective}s.  Eventually this method will go
     * away and only {@link GraphQLAppliedDirective} will be used.
     *
     * @param directives        the legacy {@link GraphQLDirective}s
     * @param appliedDirectives the more correct {@link GraphQLAppliedDirective}s
     *
     * @return this builder
     */
    public B withAppliedDirectives(Collection<GraphQLDirective> directives, Collection<GraphQLAppliedDirective> appliedDirectives) {
        assertNotNull(directives, () -> "directives can't be null");
        assertNotNull(appliedDirectives, () -> "appliedDirectives can't be null");
        clearDirectives();
        for (GraphQLDirective directive : directives) {
            withDirective(directive);
        }
        for (GraphQLAppliedDirective appliedDirective : appliedDirectives) {
            withAppliedDirectives(appliedDirective);
        }
        return (B) this;
    }

    /**
     * This is used to clear all the directives in the builder so far.
     *
     * @return the builder
     */
    public B clearDirectives() {
        directives.clear();
        appliedDirectives.clear();
        return (B) this;
    }

    protected void copyExistingDirectives(GraphQLDirectiveContainer directivesContainer) {
        clearDirectives();
        directives.addAll(directivesContainer.getDirectives());
        appliedDirectives.addAll(directivesContainer.getAppliedDirectives());
    }
}