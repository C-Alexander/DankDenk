package dao;

import com.google.inject.Guice;
import com.dank.contexts.EmployeeDao;
import com.dank.entities.Category;
import com.dank.entities.Meme;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.db.jpa.JPAApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by harmeet on 29/12/16.
 */
public class MemeDaoImplTest {

    @Inject
    private static Application fakeApplication;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private JPAApi jpaApi;

    @Before
    public void setup() {
        fakeApplication = Helpers.fakeApplication();

        GuiceApplicationBuilder builder =
                new GuiceApplicationLoader().builder(new ApplicationLoader.Context(Environment.simple()));
        Guice.createInjector(builder.applicationModule()).injectMembers(this);

        Helpers.start(fakeApplication);
    }

    @After
    public void destroy() {
        Helpers.stop(fakeApplication);
    }

    @Test
    public void testSaveAndFindById() throws InterruptedException {
        Category category = new Category();
        category.setName("Tester");

        Meme meme = new Meme();
        meme.setName("Kick");
        meme.setAge(15);
        meme.setSex("Male");
        meme.setCategory(category);

        Meme resultMeme = jpaApi.withTransaction(() -> {
            employeeDao.save(meme);
            return employeeDao.findById(100);
        });

        Category expectedDept = new Category();
        expectedDept.setId(20);
        expectedDept.setName("Training");

        Meme expectedMeme = new Meme();
        expectedMeme.setId(100);
        expectedMeme.setName("Jimmy");
        expectedMeme.setSex("MALE");
        expectedMeme.setAge(27);
        expectedMeme.setCategory(expectedDept);

        assertThat(resultMeme, is(equalTo(expectedMeme)));
        assertThat(resultMeme, is(not(equalTo(meme))));
    }

    @Test
    public void testFindAll() {
        List<Meme> allMemes = jpaApi.withTransaction(entityManager -> employeeDao.findAll());

        Category expectedDept = new Category();
        expectedDept.setId(20);
        expectedDept.setName("Training");

        Meme expectedMeme = new Meme();
        expectedMeme.setId(100);
        expectedMeme.setName("Jimmy");
        expectedMeme.setSex("MALE");
        expectedMeme.setAge(27);
        expectedMeme.setCategory(expectedDept);

        assertThat(allMemes.size(), is(equalTo(2)));
        assertThat(allMemes, hasItems(expectedMeme));
    }

    @Test
    public void testUpdate() {
        int result = employeeDao.updateById(101);

        Category expectedDept = new Category();
        expectedDept.setId(20);
        expectedDept.setName("Training");

        Meme expectedMeme = new Meme();
        expectedMeme.setId(101);
        expectedMeme.setName("Kick");
        expectedMeme.setSex("MALE");
        expectedMeme.setAge(25);
        expectedMeme.setCategory(expectedDept);

        Meme resultMeme = jpaApi.withTransaction(() ->
                employeeDao.findById(101)
        );

        assertThat(result, is(equalTo(1)));
        assertThat(resultMeme, is(equalTo(expectedMeme)));
    }
}
