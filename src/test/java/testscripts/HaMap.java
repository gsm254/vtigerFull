package testscripts;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HaMap {

	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("hi", "hello");
		map.put("123", "hell");
		
		for(Entry<String, String> e:map.entrySet()) {
			System.out.println(e.getKey()+" "+e.getValue());
		}
	}

}
