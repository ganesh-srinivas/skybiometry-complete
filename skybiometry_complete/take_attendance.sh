#!/bin/bash
for i in $(ls faces/take_attendance_pictures/*.jpg); do
 java -cp '.:unirest.jar' take_attendance_faces_recognize $i csd301_test;
done;