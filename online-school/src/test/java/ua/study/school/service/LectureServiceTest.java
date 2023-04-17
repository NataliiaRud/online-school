package ua.study.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.study.school.repository.LectureRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {
    @Mock
    private LectureRepository lectureRepository;

    @Test
    public void testPrintLectureIds() {
        LectureService lectureService = new LectureService(lectureRepository);
        lectureService.printLectureIds();
        verify(lectureRepository, times(1)).findAll();
    }
}
