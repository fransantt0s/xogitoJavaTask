
package com.example.demo.dao;

import com.example.demo.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class ProjectDaoTest {

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void testFindById() {
        Project project = projectDao.findById(1L).orElse(null);
        assertNotNull(project);
    }

    @Test
    public void testSave() {
        Project Project = new Project();
        Project savedProject = projectDao.save(Project);
        assertNotNull(savedProject);

    }

    @Test
    public void deleteProject() {
        Project Project = new Project();
        Project savedProject = projectDao.save(Project);
        projectDao.delete(savedProject);
        assert(!projectDao.findAll().contains(Project));
    }

    @Test
    public void testFindByName() {
        Project project= projectDao.findProjectByName("Visa").orElse(null);
        assertNotNull(project);
    }

}
