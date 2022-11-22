package peaksoft.repository;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(/*Long id,*/ Course course);

    void deleteCourse(Long id);

    void updateCourse(Course course);

    Course getCourseById(Long id);

    List<Course> getAllCourses(/*Long id*/);
}