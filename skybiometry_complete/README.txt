Proper documentation at https://skybiometry.com/Documentation
Easy-to-understand code snippets at https://market.mashape.com/skybiometry-1/skybiometry-face-detection-and-recognition

API workflow: Here's a brief overview:
1.	face enrollment/training
1.1	faces/detect		-returns temporary tag id for an image
1.2 tags/save			-saves the passed tag id as specified uid
1.3 faces/training		-trains "all"/specified uids for recognition

2.	face recognition
2.1 faces/recognize		-returns matching uid for a new image

--
EXAMPLE USAGE:
Since I'm using the unirest library, you must mention unirest.jar (which contains unirest library and its dependencies) when compiling and running.

1. FACE ENROLLMENT FOR TWO IMAGES:
javac -cp '.:unirest.jar' add_students_faces_detect_save.java
java -cp '.:unirest.jar' add_students_faces_detect_save.java faces/train/1.jpg tanay@csd301_test
java -cp '.:unirest.jar' add_students_faces_detect_save.java faces/train/2.jpg chris@csd301_test
/*
java -cp '.:unirest.jar' add_students_faces_detect_save.java path/to/image.jpg what_uid_this_face_should_have@data_namespace
*/

javac -cp '.:unirest.jar' add_students_faces_train.java
javac -cp '.:unirest.jar' add_students_faces_train csd301_test
/*
java -cp '.:unirest.jar' add_students_faces_detect_save.java data_namespace
*/

2. FACE RECOGNITION FOR AN IMAGE:
javac -cp '.:unirest.jar' take_attendance_recognize.java
java -cp '.:unirest.jar' take_attendance_recognize faces/test/1.jpg csd301_test
/*
java -cp '.:unirest.jar' take_attendance_recognize path/to/image.jpg data_namespace
*/
