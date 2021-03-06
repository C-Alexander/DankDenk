import com.dank.contexts.CategoryContext;
import com.dank.contexts.JPACategoryContext;
import com.dank.contexts.JPAMemeContext;
import com.dank.contexts.MemeContext;
import com.dank.repositories.CategoryRepository;
import com.google.inject.AbstractModule;
import com.dank.repositories.MemeRepository;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(MemeRepository.class);
        bind(MemeContext.class).to(JPAMemeContext.class);
        bind(CategoryRepository.class);
        bind(CategoryContext.class).to(JPACategoryContext.class);
    }

}
