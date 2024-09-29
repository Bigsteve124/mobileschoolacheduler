<h1>Android Mobile School Scheduler</h1>

<h2>Description</h2>

An Android mobile application that schedules school funtions. It has the ability to scheudle a term, link a course to a term and an assessment to  a course. It utilizes SQLITE to build a database. The app has the ability to take a note from the course and share it via email or text. It uses basic clip art for the main screen picture of a school cap. The app utilizes buttons to navigate the screens versus swipe method.

<h3>Uses</h3>

My project is a school scheduling system. It can be used to create terms for an individual student and then associate courses to that term. It allows an infinte number of terms, courses and assessments to be added by utilizing recycler views. If a course is associated with a term the term can not be deleted, much like if an assessment is associated with a course, it too can not be deleted. It has the ability to save to a SQLite database, retireve info from the database and share a note from the course details page. 
Upon launch the users selects the enter button which pulls up the term list. By clicking the term it opens the term details page. This page discloses any associated courses, if there are any you can click them and open the course details page, if not you may add them. In the course details page it lists the assessments associated as well as other course details, if no associated assessment it can be added, otherwise click the assessment and it opens the assessment details page. It also includes buttons to navigate between the screens appropriately. It also has built in validation to make sure all areas are filled in with the exception of note as that is optional.

![mainscreenmobile](https://github.com/user-attachments/assets/eb34b639-a830-4cd2-93fc-c73e7b637fe6)
![term details](https://github.com/user-attachments/assets/eec3825d-035b-45aa-a5e2-43218522094c)


<h4>Technologies</h4>

The application was developed in Android Studio with XML to develop the designs, and Android Java to code the application. It uses SQLite to develop the database. 
