package com.basis.cloud.common.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * JacksonTemplate
 *
 * @author biezhi
 * @date 2019-07-19
 */
@Slf4j
@Component
public class JacksonTemplate {

	private ObjectMapper objectMapper;

	@PostConstruct
	void init() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		log.info("初始化 JacksonTemplate");
	}

	public <T> T parse(String json, Class<T> type) {
		try {
			if (StrUtil.isBlank(json) || null == type) {
				return null;
			}
			return objectMapper.readValue(json, type);
		} catch (Exception e) {
			log.error("parse json:{} to bean {} fail", json, type, e);
			return null;
		}
	}

	public <T> T parse(String json, TypeReference<T> typeReference) {
		try {
			if (StrUtil.isBlank(json) || null == typeReference) {
				return null;
			}
			return objectMapper.readValue(json, typeReference);
		} catch (Exception e) {
			log.error("parse json:{} to bean {} fail", json, typeReference, e);
			return null;
		}
	}

	public <T> List<T> parseList(String json, Class<T> type) {
		try {
			if (StrUtil.isBlank(json) || null == type) {
				return null;
			}
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(List.class, type);
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			log.error("parse json:{} to list {} fail", json, type, e);
			return null;
		}
	}

	public Map<String, Object> parseMap(String json) {
		try {
			if (StrUtil.isBlank(json)) {
				return null;
			}

			return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			log.error("parse json:{} to map fail", json, e);
			return null;
		}
	}

	public String toJson(Object value) {
		return this.toJson(value, false);
	}

	public String toJson(Object value, boolean pretty) {
		try {
			if (null == value) {
				return null;
			}
			if (pretty) {
				objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(value);
			}
			return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
			log.error("bean {} to json fail", value, e);
			return null;
		}
	}

}
