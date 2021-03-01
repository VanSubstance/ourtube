package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.my.spring.domain.words.NounDto;

import com.google.gson.reflect.TypeToken; 
import java.lang.reflect.Type;


public class WordTest {

	public static void main(String[] args) {
		System.out.println("�׽�Ʈ");
		List<String> synonyms = new ArrayList<String>();
		List<String> uniques = new ArrayList<String>();
		HashMap<String, List<NounDto>> dataset = getData("video");
		List<NounDto> nouns = new ArrayList<NounDto>();
		// Ű���� �ĺ�, ���� ����
		HashMap<String, List<String>> resultProcess1 = new HashMap<String, List<String>>();
		HashMap<String, NounDto> resultsVideo = new HashMap<String, NounDto>();
		for (Entry<String, List<NounDto>> nounsVideo : dataset.entrySet()) {
			nouns.addAll(nounsVideo.getValue());
		}
		System.out.println("��ü ���: " + nouns.size() + " ��.");
		
		/**
		 * 1�� �õ�: ��縦 �����ϴ� ���� �з�.
		 * ����: ������ ���丮 | ������, ���丮, ������ ���丮
		 */

		for (NounDto noun : nouns) {
			Boolean checkAdded = false;
			for (String keyword: resultProcess1.keySet()) {
				if (keyword.contains(noun.getWord())) {
					synonyms = new ArrayList<String>();
					synonyms = resultProcess1.get(keyword);
					synonyms.add(noun.getWord());
					resultProcess1.put(keyword, synonyms);
					checkAdded = true;
				}
			}
			if (!checkAdded) {
				synonyms = new ArrayList<String>();
				synonyms.add(noun.getWord());
				resultProcess1.put(noun.getWord(), synonyms);
			}
		}
		System.out.println("���� 1 ����. ��ǥ ���: " + resultProcess1.size() + " ��.");
		System.out.println(resultProcess1.keySet());
		
		/**
		 * 2�� �õ�: ������ �� ��ǥ ��� ����. -> ���� ���� ������ ��� �Ǵ� ���� 1�� ��ǥ ��翡 �ش� �� ���
		 */
		for(Entry<String, List<String>> syno : resultProcess1.entrySet()) {
			System.out.println("��ǥ���: "  + syno.getKey());
			for (String word : syno.getValue()) {
				System.out.println("\t" + word);
			}
			System.out.println();
		}

		for (Entry<String, List<NounDto>> nounsVideo : dataset.entrySet()) {
			NounDto keyword = null;
			for (NounDto noun : nounsVideo.getValue()) {
				System.out.print(noun.getWord() + "\t|\t");
				if (keyword == null) {
					keyword = noun;
				} else {
					if (keyword.getCount() < noun.getCount() || resultProcess1.keySet().contains(noun.getWord())) {
						keyword = noun;
					}
				}
			}
			System.out.println();
			System.out.println(nounsVideo.getKey() + " | " + keyword.getWord() + "\n");
			resultsVideo.put(nounsVideo.getKey(), keyword);
		}
		System.out.println("���� 2 ����.");
		
	}

	public static HashMap<String, List<NounDto>> getData(String type) {
		HashMap<String, List<NounDto>> dataset = new HashMap<String, List<NounDto>>();
		Gson gson = new Gson();
		try {
			URL url = new URL("http://222.232.15.205:8082/data/words/" + type);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			
			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Stream�� ó������� �ϴ� �������� ����.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				Type typeReturn = new TypeToken<HashMap<String, List<NounDto>>>(){}.getType();
				dataset = gson.fromJson(sb.toString(), typeReturn);
				System.out.println("�Ϸ�");
			} else {
				System.out.println(con.getResponseMessage());
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return dataset;
	}

}
