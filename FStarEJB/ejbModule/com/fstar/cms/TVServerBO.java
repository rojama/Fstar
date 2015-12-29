package com.fstar.cms;

import java.sql.Blob;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fstar.cms.modle.MediaType;
import com.fstar.cms.modle.VideoTypeInfo;
import com.fstar.sys.DB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TVServerBO {
	public Map<String, Object> getMediaType(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("DATA", DB.selete(MediaBO.TABLE_MEDIA_TYPE));
		return returnmap;
	}

	// 视频分类
	public Map<String, Object> parseTopCate(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("super_id", "");
		List<Map<String, Object>> re = DB.seleteByColumn(MediaBO.TABLE_MEDIA_TYPE, data, "type_id,type_name,component", "order by order_no");
		TVServerImageBO.changeImageToURL(re, MediaBO.TABLE_MEDIA_TYPE);
		returnmap.put("type", re);
		return returnmap;
	}
	
	// 视频子类
	public Map<String, Object> parseSubCate(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("super_id", map.get("super_id"));
		List<Map<String, Object>> re = DB.seleteByColumn(MediaBO.TABLE_MEDIA_TYPE, data, "type_id,type_name", "order by order_no");
		TVServerImageBO.changeImageToURL(re, MediaBO.TABLE_MEDIA_TYPE);
		returnmap.put("subtype", re);
		return returnmap;
	}
	
	// 视频列表
	public Map<String, Object> parseMediaList(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("type_id", map.get("type_id"));
		List<Map<String, Object>> re = DB.seleteByColumn(MediaBO.TABLE_MEDIA, data, "media_id,media_name", "");
		TVServerImageBO.changeImageToURL(re, MediaBO.TABLE_MEDIA);
		returnmap.put("mediaList", re);
		return returnmap;
	}
	
	// 视频信息
	public Map<String, Object> parseMediaInfo(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("media_id", map.get("media_id"));
		List<Map<String, Object>> re = DB.seleteByColumn(MediaBO.TABLE_MEDIA, data);
		TVServerImageBO.changeImageToURL(re, MediaBO.TABLE_MEDIA);
		returnmap.put("mediaInfo", re);
		return returnmap;
	}
	
	// 视频地址
	public Map<String, Object> parseMediaUrl(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("media_id", map.get("media_id"));
		List<Object> data = new ArrayList<Object>();
		data.add(map.get("media_id"));
		data.add(map.get("series_no"));
		List<Map<String, Object>> re = DB.query("select * from fs_media_url a , fs_server b where a.server_id=b.server_id and a.media_id=? and a.series_no=?", data);
//		List<Map<String, Object>> re = DB.seleteByColumn(MediaBO.TABLE_MEDIA_URL, data);
		returnmap.put("mediaUrl", re);
		return returnmap;
	}

	// 推荐视频
	public Map<String, Object> parseRecommend(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("recommend", "Y");
		List<Map<String, Object>> re = DB.seleteByColumn(MediaBO.TABLE_MEDIA, data);
		TVServerImageBO.changeImageToURL(re, MediaBO.TABLE_MEDIA);
		returnmap.put("video", re);
		return returnmap;
	}
	
	//板块标题
	public Map<String, Object> getTitle(Map<String, Object> map)
			throws Exception {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("block_id", map.get("block_id"));
		List<Map<String, Object>> re = DB.seleteByColumn(TitleBO.TABLE_TITLE, data);
		returnmap.put("title", re);
		return returnmap;
	}
	
}
