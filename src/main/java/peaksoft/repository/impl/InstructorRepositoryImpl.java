package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Long id, Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        entityManager.remove(entityManager.find(Instructor.class, id));
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        entityManager.merge(instructor1);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return entityManager.createQuery("select i from Instructor i where i.course.id = :id", Instructor.class).setParameter("id", id).getResultList();
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) throws IOException {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getInstructors() != null) {
            for (Instructor instructor1 : course.getInstructors()) {
                if (instructor1.getId() == instructorId) {
                    throw  new IOException("This instructor already exists!");
                }
            }
        }
        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.merge(instructor);
        entityManager.merge(course);
    }
}
