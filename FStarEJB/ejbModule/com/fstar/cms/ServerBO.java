package com.fstar.cms;

import java.util.HashMap;
import java.util.Map;

import com.fstar.sys.DB;

public class ServerBO {
	public Map<String, Object> server(Map<String, Object> map) throws Exception
	{
		Map<String, Object> returnmap = new HashMap<String, Object>();
		String action = "";
		if (map.containsKey("ACTION")){
			action = (String) map.get("ACTION");
		}
		System.out.println("inputMAP="+map);
		
		if (action.equals("add")){
			String server_id = String.valueOf(map.get("server_id"));
			int server_type = Integer.parseInt((String)map.get("server_type"));
			String server_name = String.valueOf(map.get("server_name"));
			String ip = String.valueOf(map.get("ip"));
			String url_prefix = String.valueOf(map.get("url_prefix"));
			DB.update("INSERT INTO fs_server (server_id, server_type, server_name, ip, url_prefix) "
					+ "VALUES ('"+server_id+"', "+server_type+", '"+server_name+"', '"+ip+"', '"+url_prefix+"')");
		}else if (action.equals("delete")){
			String server_id = String.valueOf(map.get("server_id"));
			DB.update("DELETE FROM fs_server where server_id = '"+server_id+"'");
		}else if (action.equals("modify")){
			String server_id = String.valueOf(map.get("server_id"));
			int server_type = Integer.parseInt((String)map.get("server_type"));
			String server_name = String.valueOf(map.get("server_name"));
			String ip = String.valueOf(map.get("ip"));
			String url_prefix = String.valueOf(map.get("url_prefix"));
			DB.update("UPDATE fs_server SET server_type = "+server_type+",server_name = '"+server_name
					+"',ip = '"+ip+"',url_prefix = '"+url_prefix+"' where server_id = '"+server_id+"'");
		}
		returnmap.put("Rows", DB.query("select * from fs_server"));
		return returnmap;
	}
}
