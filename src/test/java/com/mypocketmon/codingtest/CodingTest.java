package com.mypocketmon.codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors.*;
import java.util.stream.Stream;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

public class CodingTest {
	
	@Test
	void Json_연결_테스트() throws JSONException {
		        
        String domainUrl = "http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew";
        String responseMsg = "";
        HashMap<String, String> keyValue = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
        	put("serviceKey","wCHX60C%2BIfCd2Y25%2BJqvIez%2Fa98KbIKTq8AU%2Bt1mvJBnU%2FTV218wsEWfxRvLaqSz9krdQ6Aq9TM3dibJJVFLzQ%3D%3D");
        	put("returnType","JSON");
        	put("numOfRows","10");
        	put("pageNo","1");
        	put("cond[country_nm::EQ]","가나");
        	put("cond[country_iso_alp2::EQ]","GH");
			}
		};
        responseMsg = httpConnect_GET(domainUrl, "UTF-8", keyValue);
        
        System.out.println(responseMsg);
        
		/*
		JsonParser parser = new JsonParser
		JSONObject jsonObj = (JSONObject) obj;

		String code = (String) jsonObj.get("code");
		String name = (String) jsonObj.get("name");
		jsonStr에 json이 String 문자로 담겨 있고 이걸 JSONObject에 담기 위해

		JSONParser parser = new JSONParser();
		Object obj = parser.parse( jsonStr );

		이렇게 파싱을 해야합니다.

		JSONObject jsonObj = (JSONObject) obj;
		
		
		JSONObject object = new JSONObject(str);
		JSONArray jsonArray = new JSONArray();
		for(jsonArray : object.length()) {
			jsonArray.put(object);
		}
		
		
		// https://dpdpwl.tistory.com/60
        
		System.out.println(jsonArray.length());
		//jsonArray = (JSONArray) jsonParser.parseList(str);
		
		JSONObject object;
		try {
			object = (JSONObject) jsonArray.getJSONObject(i);
			String data = object.getString("data");
			System.out.println(data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	@Test
	void 이진법_테스트() {
		// 주어진 1과 0으로 이루어진 문자열에서 1과 0의 개수가 같고, 1과 0의 묶음으로 구성된 문자열의 개수를 구하시오
		String s = "0011000110001110101011100"; // 6개 0011 01 10 1100 01 0011
		String temp = "";
		int temp0to1 = 0;
		int temp1to0 = 0;
		ArrayList<String> resultList = new ArrayList<String>();
		
		for(int i = 0 ; i < s.length() ; i++) {
			for(int j = 0 ; j < s.length()-i ; j++) {
				temp = s.substring(i, i+j+1);
				if(temp.length()%2==0) {
					for(int k = 0; k < temp.length() ; k++) {
						if("0".equals(temp.substring(0,1))) {
							temp0to1 = temp.indexOf("1");
							if((temp.length()/2 == temp0to1) && temp.substring(temp0to1, temp.length()).indexOf("0")<0) {
								resultList.add(temp);
								break;
							}						
						}else if("1".equals(temp.substring(0,1))) {
							temp1to0 = temp.indexOf("0");
							if((temp.length()/2 == temp1to0) && temp.substring(temp1to0, temp.length()).indexOf("1")<0) {
								resultList.add(temp);
								break;
							}		
						}
					}
				}
			}
		}
		System.out.println("이진법_테스트 result : " + resultList.size());
		/*
		for(String rst : resultList) {
			System.out.println(rst);
		}
		*/
	}
	
	@Test
	void 이진법_테스트_람다_스트림_사용() {
		// 주어진 1과 0으로 이루어진 문자열에서 1과 0의 개수가 같고, 1과 0의 묶음으로 구성된 문자열의 개수를 구하시오
		String exStr = "0011000110001110101011100"; // 6개 0011 01 10 1100 01 0011
		ArrayList<String> strList = new ArrayList<String>();
		
		for(int i = 0 ; i < exStr.length() ; i++) {
			for(int j = 0 ; j < exStr.length()-i ; j++) {
				strList.add(exStr.substring(i, i+j+1));
			}
		}
		
		Stream<String> stream1 = strList.stream();
		long stream1_cnt = 0;
		stream1_cnt = stream1
							.filter(s -> s.length()%2==0)
							.filter(s -> s.equals(str_countEqual(s)))
							.count();
		
		System.out.println("이진법_테스트_람다_스트림_사용] result : " + stream1_cnt);
	}
							
	private String str_countEqual(String str) {
		String result = "";
		String firstStr = str.substring(0,1);
		String oppStr = firstStr.equals("0") ? "1" : "0";
		int strIndex = str.indexOf(oppStr);
		
		if((str.length()/2 == strIndex) && str.substring(strIndex, str.length()).indexOf(firstStr)<0) {
			result = str;
		}
		return result;
	}
	
	private void condition_print() {
		/*
		String[][] clothes = new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		Stream<String[][]> stream = Arrays.stream(clothes)
											.collect(groupingBy(clothes -> clothes[1], mapping(clothes -> clothes[1])) // key값 지정
											.filter(clothes -> clothes.contains("a"))
											.map(clothes -> clothes.toUpperCase()));
		return;
		*/
	}	
	
	private String httpConnect_GET(String domainUrl, String encoding, HashMap<String, String> keyValue) {
			
		StringBuilder urlBuilder = new StringBuilder(domainUrl); /*URL*/
		String postStr = "";
		int i = 0;
        try {
        	for(Entry<String, String> entry : keyValue.entrySet()) {
        		if(i==0) {
        			postStr = "?";
        		}else {
        			postStr = "&";
        		}
        		urlBuilder.append(postStr
        								+ URLEncoder.encode(entry.getKey(), encoding) 
        								+ "=" + URLEncoder.encode(entry.getValue(), encoding));
        		i++;
        	}		        
        } catch (UnsupportedEncodingException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } 
        URL url;
        String responseStr = "";
		try {
			url = new URL(urlBuilder.toString());
			try {
				HttpURLConnection conn;
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/json");
				System.out.println("Response code: " + conn.getResponseCode());
				BufferedReader rd;
				if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				}
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				conn.disconnect();
				
				responseStr = sb.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseStr;
	}
	
	@Test
	public int solution(String begin, String target, String[] words) {
		/**
	문제 설명
	두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

	1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
	2. words에 있는 단어로만 변환할 수 있습니다.
	예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

	두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.

	제한사항
	각 단어는 알파벳 소문자로만 이루어져 있습니다.
	각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
	words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
	begin과 target은 같지 않습니다.
	변환할 수 없는 경우에는 0를 return 합니다.
	
	입출력 예
	// begin	target	words	return
	// "hit"	"cog"	["hot", "dot", "dog", "lot", "log", "cog"]	4
	// "hit"	"cog"	["hot", "dot", "dog", "lot", "log"]			0
		 * 
		 */
        int answer = 0;
        
        boolean isNotNull = false;        
        for(String str : words){
            if(str.equals(target)) isNotNull = true;
        }
        if(isNotNull){
            int count = 0;
            String temp = begin;
            for(String str : words){
                for(int i=0;i<begin.length();i++){
                   if(begin.charAt(i)==str.charAt(i)){
                       ++count;
                       if(count==1){
                           temp = str;
                           answer++;
                           count=0;
                       }
                   }
                   if(target.equals(temp)){
                      break;
                   }
               }
            }
        }
        return answer;
    }

	@Test
	void solution() {
		/**
		 * 
		 * 문제 설명
		n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
		
		-1+1+1+1+1 = 3
		+1-1+1+1+1 = 3
		+1+1-1+1+1 = 3
		+1+1+1-1+1 = 3
		+1+1+1+1-1 = 3
		사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
		
		제한사항
		주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
		각 숫자는 1 이상 50 이하인 자연수입니다.
		타겟 넘버는 1 이상 1000 이하인 자연수입니다.
		입출력 예
		numbers	target	return
		[1, 1, 1, 1, 1]	3	5
		[4, 1, 2, 1]	4	2
		 *
		 *store.values().stream()
							.filter(member -> member.getName().equals(name))
							.findAny();
		 *
		 **/
		
		int[] numbers = {1, 1, 1, 1, 1};
		int target = 3;
		
        int answer = 0;
        int sum = 0;
        for(int i : numbers) {
        	sum += caseFor(caseFor(sum,i), i);
        }
        if(sum == target)	answer ++;
        
        //System.out.println(" count : " + answer);
        
	}
	
	public int opposit(int x) {
		int result = 0;
		
		if(x==0)	result = 1;
		else if(x==1)	result = -1;
		
		return result;
	}
	public int caseFor(int sum, int i) {
		int sumi=0;
		for(int x=0;x<2;x++) {
    		sum += i*opposit(x);
    	}
		return sumi;
	}
	
	@Test
	void solution_기능개발() {
		/**
		 * 문제 설명
			프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
			
			또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
			
			먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
			
			제한 사항
			작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
			작업 진도는 100 미만의 자연수입니다.
			작업 속도는 100 이하의 자연수입니다.
			배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
			입출력 예
			progresses	speeds	return
			[93, 30, 55]	[1, 30, 5]	[2, 1]
			[95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
			입출력 예 설명
			입출력 예 #1
			첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
			두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
			세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.
			
			따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.
			
			입출력 예 #2
			모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.
			
			따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.
		 * **/
		//int[] progresses = {93, 30, 55};
		//int[] speeds = {1, 30, 5};
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		int temp = 0;
		int base = 0;
		int depCount = 0;
		ArrayList<Integer> answerList = new ArrayList<Integer>();
		
		for(int i = 0; i<progresses.length; i++) {
			
			temp = (100-progresses[i])/speeds[i];			
			if((100-progresses[i])%speeds[i] != 0)	temp += 1;
			
			// 1 : 7, 3, 9
			// >> 2, 1
			// 2: 5, 10, 1, 1, 20, 1
			// 1, 3, 2
			
			System.out.println(i + ") base : " + base + " / temp : " + temp + " / depCount : " + depCount);
			if(base <= temp) {
				base = temp;
				answerList.add(1);
            } else {
            	depCount = answerList.get(answerList.size() - 1) + 1;
            	answerList.set(answerList.size() - 1, depCount);
            }
			/*
			if(base <= temp) {
				base = temp;
				answerList.add(depCount);
				depCount++;
            }else {
            	depCount += answerList.get(answerList.size()) + 1;
            }
			 */
		}
				
		for(int a : answerList) {
			System.out.println("- " + a);
		}
				
				
				/*
				depCount++;
				// list에 담고 depCount 초기화
				answerList.add(depCount);
				depCount = 1;
				if(true) {
				}else if(progresses.length-1 == i) {
					answerList.add(1);
				}else {
					depCount++;
				}
			}
			
			
			else if(progresses.length-1 == i){
				answerList.add(depCount);
			}else {
				depCount++;
			}
        }	
        */	
        
        
    }	
}
