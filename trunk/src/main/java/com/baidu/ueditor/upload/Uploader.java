package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;
	private String root = null;
	public Uploader(HttpServletRequest request, Map<String, Object> conf,String root) {
		this.request = request;
		this.conf = conf;
		this.root = root;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf,this.root);
		} else {
			state = BinaryUploader.save(this.request, this.conf,this.root);
		}

		return state;
	}
}
