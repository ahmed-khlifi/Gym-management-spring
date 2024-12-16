package com.gym.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gym.model.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    @Query(value = """
                SELECT
                    *,
                    DAYNAME(c.jour) AS nom_jour
                FROM
                    Cours c
                WHERE
                    c.salle_id IN (SELECT s.id FROM Salle s WHERE s.club_id = :clubId)
                    AND c.jour BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)
                ORDER BY c.jour
            """, nativeQuery = true)
    List<Object[]> findCoursesByClubIdForCurrentWeek(@Param("clubId") Long clubId);
}
