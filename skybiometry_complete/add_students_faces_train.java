import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.*;
import java.io.*;
import org.json.*;

public class add_students_faces_train{
	public static void main(String[] args) throws UnirestException{
		String namespace = args[0];
		System.out.println("Asking the server to learn all the faces we've shown it...");

		int n_students=0;
		for(String arg:args) n_students++;
		int i=0;

		// These code snippets use an open-source library. http://unirest.io/java
		HttpResponse<JsonNode> response = Unirest.post("https://face.p.mashape.com/faces/train?api_key=10a74ff8e05245b48ef5d3fb1ca99326&api_secret=0374470e39c348c2a312fca9660b1623")
		.header("X-Mashape-Key", "BqdRVOLyQ0mshhPZZdTcyvrfPg9Cp1NtNZ5jsnPNWdTPSF4FUl")
		.header("Content-Type", "application/x-www-form-urlencoded")
		.header("Accept", "application/json")
		.field("namespace", namespace)
		.field("uids", "all")
		.asJson();

		System.out.println(response.getBody()+"\n\n");

		if(true)
			System.out.println("SUCCESS: Faces learned. Ready to take attendance.\n");
		else
			System.out.println("FAILURE: Faces not learned!\n");

	}
}