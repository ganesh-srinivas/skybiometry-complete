#!/bin/bash
for i in $(ls faces/add_students_pictures/*.jpg); do
 java -cp '.:unirest.jar' add_students_faces_detect_save $i `basename $i .jpg`@csd301_test;
done;

java -cp '.:unirest.jar' add_students_faces_train csd301_test;
