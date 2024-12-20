[1mdiff --git a/src/main/java/com/gym/gym/controller/ClubController.java b/src/main/java/com/gym/gym/controller/ClubController.java[m
[1mindex 56b43e7..8cd31aa 100644[m
[1m--- a/src/main/java/com/gym/gym/controller/ClubController.java[m
[1m+++ b/src/main/java/com/gym/gym/controller/ClubController.java[m
[36m@@ -15,7 +15,7 @@[m [mimport org.springframework.web.bind.annotation.PathVariable;[m
 import com.gym.gym.model.Club;[m
 import com.gym.gym.service.ClubService;[m
 import com.gym.gym.service.CoursService;[m
[31m-[m
[32m+[m[32mimport com.gym.gym.service.SalleService;[m
 [m
 import org.springframework.web.bind.annotation.PostMapping;[m
 [m
[1mdiff --git a/src/main/java/com/gym/gym/repository/CoachRepository.java b/src/main/java/com/gym/gym/repository/CoachRepository.java[m
[1mindex 91b0162..9535f1a 100644[m
[1m--- a/src/main/java/com/gym/gym/repository/CoachRepository.java[m
[1m+++ b/src/main/java/com/gym/gym/repository/CoachRepository.java[m
[36m@@ -15,7 +15,7 @@[m [mimport java.util.Optional;[m
 [m
 public interface CoachRepository extends JpaRepository<Coach, Long> {[m
 [m
[31m-    Coach findByUserId(Long userId);[m
[32m+[m[32m    Optional<Coach> findByUser_Id(Long userId);[m
 [m
     // Find coaches by price range[m
     List<Coach> findByPrixCoursBetween(float minPrice, float maxPrice);[m
[36m@@ -36,5 +36,5 @@[m [mpublic interface CoachRepository extends JpaRepository<Coach, Long> {[m
     @Query("UPDATE Coach c SET c.prixCours = :prixCours WHERE c.id = :id")[m
     int updateCoachPrice(@Param("id") Long id, @Param("prixCours") float prixCours);[m
 [m
[31m-    Optional<Coach> findByUsersId(Long userId);[m
[32m+[m[32m    // Optional<Coach> findByUsersId(Long userId);[m
 }[m
[1mdiff --git a/src/main/java/com/gym/gym/service/CoachService.java b/src/main/java/com/gym/gym/service/CoachService.java[m
[1mindex b3d846f..4dc446d 100644[m
[1m--- a/src/main/java/com/gym/gym/service/CoachService.java[m
[1m+++ b/src/main/java/com/gym/gym/service/CoachService.java[m
[36m@@ -24,7 +24,8 @@[m [mpublic class CoachService {[m
     }[m
 [m
     public Coach findById(Long userId) {[m
[31m-        return coachRepository.findByUserId(userId);[m
[32m+[m[32m        return coachRepository.findByUser_Id(userId)[m
[32m+[m[32m                .orElseThrow(() -> new RuntimeException("Coach not found for user ID: " + userId));[m
     }[m
 [m
     // by price[m
[36m@@ -51,6 +52,7 @@[m [mpublic class CoachService {[m
     }[m
 [m
     public boolean isUserAlreadyACoach(Long userId) {[m
[31m-        return coachRepository.findByUsersId(userId).isPresent();[m
[32m+[m[32m        // return false;[m
[32m+[m[32m        return coachRepository.findByUser_Id(userId).isPresent();[m
     }[m
 }[m
[1mdiff --git a/src/main/resources/templates/add_course.html b/src/main/resources/templates/add_course.html[m
[1mindex 90a3602..77e5ea1 100644[m
[1m--- a/src/main/resources/templates/add_course.html[m
[1m+++ b/src/main/resources/templates/add_course.html[m
[36m@@ -9,6 +9,7 @@[m
     <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">[m
 </head>[m
 [m
[32m+[m
 <body class="min-h-screen bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">[m
     <!-- Select club  -->[m
     <div th:if="${step == 1}" class="min-h-screen bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">[m
[36m@@ -40,16 +41,16 @@[m
                     <div class="grid grid-cols-1 md:grid-cols-2 gap-6">[m
                         <div th:each="club: ${clubs}"[m
                             class="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow duration-200 text-left border border-gray-100 hover:border-purple-200">[m
[31m-                            <h3 class="text-xl font-semibold text-gray-900" th:text="${club.getNom()}" />[m
[32m+[m[32m                            <h3 class="text-xl font-semibold text-gray-900" th:text="${club.getNom()}" >[m
                             <div class="mt-2 flex items-center text-gray-600">[m
                                 <svg class="w-4 h-4 mr-2" data-lucide="map-pin"></svg>[m
[31m-                                <span th:text="${club.getAdresse()}" />[m
[32m+[m[32m                                <span th:text="${club.getAdresse()}" >[m
                             </div>[m
                             <!-- <div class="mt-1 flex items-center text-gray-600">[m
                                 <svg class="w-4 h-4 mr-2" data-lucide="users"></svg>[m
                                 <span>150 members</span>[m
                             </div> -->[m
[31m-                            <a th:href="@{'/cours/create?step=2&clubId=' + ${club.getId()}}"[m
[32m+[m[32m                            <a th:href="@'/cours/create?step=2&clubId=' + $club.getId()"[m
                                 class="mt-4 flex items-center text-purple-600 font-medium">[m
                                 Create course here[m
                                 <svg class="ml-2 w-4 h-4" data-lucide="arrow-right"></svg>[m
[1mdiff --git a/src/main/resources/templates/add_user.html b/src/main/resources/templates/add_user.html[m
[1mindex 24715b6..f10019e 100644[m
[1m--- a/src/main/resources/templates/add_user.html[m
[1m+++ b/src/main/resources/templates/add_user.html[m
[36m@@ -71,7 +71,7 @@[m
                     </h2>[m
                     <div class="grid grid-cols-1 md:grid-cols-3 gap-4">[m
                         <label class="relative block cursor-pointer" th:each="model : ${modelAbonnement}">[m
[31m-                            <input type="radio" class="peer sr-only" name="planId" th:value="${model.id}"[m
[32m+[m[32m                            <input type="radio" class="peer sr-only" name="planId" th:value="${model.id}" required[m
                                 th:checked="${model.id == user?.abonnement?.getModelAbonnement()?.getId()}" />[m
                             <div[m
                                 class="rounded-lg border-2 border-gray-200 p-4 hover:border-blue-500 peer-checked:border-blue-600 peer-checked:bg-blue-50">[m
[1mdiff --git a/src/main/resources/templates/list.html b/src/main/resources/templates/list.html[m
[1mindex 5568ffa..9d2ea86 100644[m
[1m--- a/src/main/resources/templates/list.html[m
[1m+++ b/src/main/resources/templates/list.html[m
[36m@@ -1,4 +1,3 @@[m
[31m-</html>[m
 <!DOCTYPE html>[m
 <html lang="en" xmlns:th="http://www.thymeleaf.org">[m
 [m
