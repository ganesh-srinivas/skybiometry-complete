import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.*;
import java.io.*;
import org.json.*;

public class add_students_faces_detect_save{
	static HttpResponse<JsonNode> tags_save(String api_key, String api_secret, String x_mashape_key, String tids, String uids_with_namespace) throws UnirestException, JSONException{
		// These code snippets use an open-source library. http://unirest.io/java
		HttpResponse<JsonNode> response = Unirest.post("https://face.p.mashape.com/tags/save?api_key="+api_key+"&api_secret="+api_secret)
		.header("X-Mashape-Key", x_mashape_key)
		.header("Content-Type", "application/x-www-form-urlencoded")
		.header("Accept", "application/json")
		.field("tids", tids)
		.field("uid", uids_with_namespace)
		.asJson();

		return response;
	}


	static int get_n_tids(HttpResponse<JsonNode> response){
		int n_tids=1;
		try{
		n_tids = response.getBody().getObject().getJSONArray("photos").getJSONObject(0).getJSONArray("tags").length();		
		} catch(Exception e){
			e.printStackTrace();
		}
		return n_tids;
	}

	static String get_tid(HttpResponse<JsonNode> response){
		String tid = new String();
		try{
			tid= response.getBody().getObject().getJSONArray("photos").getJSONObject(0).getJSONArray("tags").getJSONObject(0).getString("tid");
		}catch(Exception e){
			e.printStackTrace();
		}
		return tid;
	}


	public static void main(String[] args) throws UnirestException{		
		String filename = new String();
		String uid_with_namespace = new String();
		filename = args[0];
		uid_with_namespace = args[1];
		//System.out.println(args[1]);

		String api_key = "10a74ff8e05245b48ef5d3fb1ca99326";
		String api_secret = "0374470e39c348c2a312fca9660b1623";
		String x_mashape_key = "BqdRVOLyQ0mshhPZZdTcyvrfPg9Cp1NtNZ5jsnPNWdTPSF4FUl";

		System.out.println("Detecting picture: "+filename+" ...");

		// These code snippets use an open-source library. http://unirest.io/java
		HttpResponse<JsonNode> response = Unirest.post("https://face.p.mashape.com/faces/detect?api_key="+api_key+"&api_secret="+api_secret)
		.header("X-Mashape-Key", x_mashape_key)
		.field("attributes", "none")
		.field("detector", "Normal")
		.field("files", new File(filename))
		.asJson();

		//System.out.println(response.getBody()+"\n\n");

		int n_tags = get_n_tids(response);

		if(n_tags==0){
			System.out.println("Error: No face found!");
			// go to activity with button "Click here to take picture again" which takes them to the picture taking activity
		}
		else if (n_tags>1){
			System.out.println("Error: More than one face found!");
			// go to activity with error message "Error: More than one face found!" and a button "Click here to take picture again" which takes them to the picture taking activity
		}
		else{
			String tid = get_tid(response);

			System.out.println("Saving picture as "+uid_with_namespace+" ...");
			// tags/save
			HttpResponse<JsonNode> tags_save_response = tags_save(api_key, api_secret, x_mashape_key, tid, uid_with_namespace);

			//System.out.println(tags_save_response.getBody());
			System.out.print(tags_save_response.getBody().getObject().getString("status"));
			
			System.out.print(": "+ uid_with_namespace + " has been added as student.\n\n");
		}
	}
}