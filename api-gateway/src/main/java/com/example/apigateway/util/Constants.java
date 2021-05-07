package com.example.apigateway.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

	// 业务类型
	public static List<String> CONFIG_TYPE = Arrays.asList("download", "live", "live_pull", "live_push", "static",
			"vod");
	// 业务类型map
	@SuppressWarnings("serial")
	public final static Map<String, String> CONFIG_TYPE_MAP = new HashMap<String, String>() {
		{
			put("下载（大文件）", "download");
			put("直播", "live");
			put("直播-拉流", "live_pull");
			put("直播-推流", "live_push");
			put("静态（页面、小文件）", "static");
			put("点播（音视频）", "vod");
		}
	};
	@SuppressWarnings("serial")
	public final static Map<String, String> CONFIG_TYPE_MAP_TWO = new HashMap<String, String>() {
		{
			put("download", "下载（大文件）");
			put("live", "直播");
			put("live_pull", "直播-拉流");
			put("live_push", "直播-推流");
			put("static", "静态（页面、小文件）");
			put("vod", "点播（音视频）");
		}
	};
	// 业务范围
	public static List<String> CONFIG_SCOPE = Arrays.asList("abroad", "all", "cn");

	@SuppressWarnings("serial")
	public final static Map<String, String> CONFIG_SCOPE_MAP = new HashMap<String, String>() {
		{
			put("海外", "abroad");
			put("全球", "all");
			put("国内", "cn");
		}
	};

	@SuppressWarnings("serial")
	public final static Map<String, String> CONFIG_SCOPE_MAP_TWO = new HashMap<String, String>() {
		{
			put("abroad", "海外");
			put("all", "全球");
			put("cn", "国内");
		}
	};
	
	private static final String URL = "/api/v2/stat/";
	//外部接口的url
	@SuppressWarnings("serial")
	public final static Map<String, String> EXTERNAL_URL_MAP = new HashMap<String, String>() {
		{
			put(URL+"traffic", "GET");
			put(URL+"region/traffic", "GET");
			put(URL+"refresh", "GET");
			put(URL+"refresh/search", "GET");
			put(URL+"domain/list", "GET");
		}
	};
	
}
