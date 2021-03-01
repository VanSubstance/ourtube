package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.my.spring.domain.words.NounDto;

import com.google.gson.reflect.TypeToken; 
import java.lang.reflect.Type;


public class WordTest {

	public static void main(String[] args) {
		System.out.println("테스트");
		List<String> synonyms = new ArrayList<String>();
		List<NounDto> dataset = getData("video");
		System.out.println(dataset.size());
	}

	/**
	 * @param type
	 */
	public static List<NounDto> getData(String type) {
		List<NounDto> result = new ArrayList<NounDto>();
		Gson gson = new Gson();
		try {
			URL url = new URL("http://222.232.15.205:8082/data/words/" + type);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			
			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Stream을 처리해줘야 하는 귀찮음이 있음.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				Type typeReturn = new TypeToken<HashMap<String, List<NounDto>>>(){}.getType();
				HashMap<String, List<NounDto>> dataset = gson.fromJson(sb.toString(), typeReturn);
				System.out.println("체크포인트 1: 데이터 타입: " + dataset.getClass());
				for (List<NounDto> nouns : dataset.values()) {
					result.addAll(nouns);
				}
				System.out.println("완료");
			} else {
				System.out.println(con.getResponseMessage());
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return result;
	}

}
